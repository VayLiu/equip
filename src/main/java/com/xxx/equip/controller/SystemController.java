package com.xxx.equip.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.equip.pojo.*;
import com.xxx.equip.service.AdminService;
import com.xxx.equip.service.LoginService;
import com.xxx.equip.service.UserService;
import com.xxx.equip.util.JwtHelper;
import com.xxx.equip.util.Result;
import com.xxx.equip.util.ResultCodeEnum;
import com.xxx.equip.util.VerifyCodeImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Api(tags = "公共控制")
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @ApiOperation("文件上传统一入口")
    @PostMapping("/uploadImage")
    public Result uploadImage(
            @ApiParam("图片文件") @RequestPart("multipartFile") MultipartFile multipartFile
    ) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String originalFilename = multipartFile.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String newFileName = uuid + originalFilename.substring(i);
        // 1.保存文件
        try {
            multipartFile.transferTo(new File("../../../../../../../target/classes/public/upload/".concat(newFileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2.响应图片路径
        String path = "/upload/".concat(newFileName);
        return Result.success(path);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/getInfo")
    public Result getInfoByToken(@RequestHeader String token) {
        // 1.验证Token是否过期
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        }
        // 2.从Token中解析出用户ID和用户类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("userType", userType);
        switch (userType) {
            case 0: {
                Admin admin = adminService.getById(userId);
                map.put("user", admin);
                break;
            }
            case 1:
            case 2:
            case 3: {
                User user = userService.getById(userId);
                map.put("user", user);
                break;
            }
        }
        return Result.success(map);
    }

    @ApiOperation("登录验证")
    @PostMapping("/login")
    public Result login(
            @ApiParam("JSON对象") @RequestBody LoginForm loginForm,
            HttpServletRequest request
    ) {
        // 1.校验验证码
        HttpSession session = request.getSession();
        String sessionVerifyCode = (String) session.getAttribute("verifyCode");
        String loginVerifyCode = loginForm.getVerifyCode();
        // 判断session内的验证码是否失效
        if ("".equals(sessionVerifyCode) || null == sessionVerifyCode) {
            return Result.fail().message("验证码失效，请刷新后重试");
        }
        // 验证码不相同（不区分大小写）
        if (!sessionVerifyCode.equalsIgnoreCase(loginVerifyCode)) {
            return Result.fail().message("验证码有误，请重新输入");
        }
        // 从session域中移除验证码
        session.removeAttribute("verifyCode");

        // 2.分用户类型进行校验
        Map<String, Object> map = new LinkedHashMap<>();
        switch (loginForm.getRole()) {
            case 0: {    // 系统管理员
                try {
                    Admin admin = adminService.login(loginForm);
                    if (null != admin) {
                        // 用户ID和类型转为一个密文，以token的形式进行反馈
                        map.put("token", JwtHelper.createToken(admin.getId().longValue(), loginForm.getRole()));
                    } else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    loginService.save(new Login(new Date()));
                    return Result.success(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            }
            case 1:
            case 2:
            case 3: {
                try {
                    User user = userService.login(loginForm);
                    if (null != user) {
                        // 用户ID和类型转为一个密文，以token的形式进行反馈
                        map.put("token", JwtHelper.createToken(user.getId().longValue(), loginForm.getRole()));
                    } else {
                        throw new RuntimeException("用户名或密码有误");
                    }
                    loginService.save(new Login(new Date()));
                    return Result.success(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            }
        }
        return Result.fail().message("查无此用户");
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) {
        // 获取图片
        BufferedImage verifyCodeImage = VerifyCodeImage.getVerifyCodeImage();
        // 获取图片对应的验证码
        String verifyCode = new String(VerifyCodeImage.getVerifyCode());
        // 将验证码文本放入到session域，为验证作准备
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", verifyCode);
        // 将验证码图片响应给浏览器
        try {
            ImageIO.write(verifyCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("统计登录数")
    @GetMapping("/getLoginCount")
    public Result getLoginCount() {
        long oneDay = 24 * 60 * 60 * 1000;
        Date date = new Date();
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 7; i > 0; i--) {
            QueryWrapper<Login> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("created_at", new Date(date.getTime() - oneDay * i), new Date(date.getTime() - oneDay * (i - 1)));
            HashMap<String, Object> map = new HashMap<>();
            map.put("date", new Date(date.getTime() - oneDay * (i-1)));
            map.put("num", loginService.count(queryWrapper));
            list.add(map);
        }
        return Result.success(list);
    }

}
