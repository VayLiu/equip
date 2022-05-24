package com.xxx.equip.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.equip.mapper.LoginMapper;
import com.xxx.equip.pojo.Login;
import com.xxx.equip.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {
}
