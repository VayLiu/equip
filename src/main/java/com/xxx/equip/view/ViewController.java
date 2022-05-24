package com.xxx.equip.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Date: 2022/4/20 22:31
 */
@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/info")
    public String infoPage() {
        return "info";
    }

    @GetMapping("/updatePassword")
    public String updatePasswordPage() {
        return "updatePassword";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin";
    }

    @GetMapping("/user/student")
    public String studentPage() {
        return "user/student";
    }

    @GetMapping("/user/teacher")
    public String teacherPage() {
        return "user/teacher";
    }

    @GetMapping("/user/manager")
    public String managerPage() {
        return "user/manager";
    }

    @GetMapping("/lab")
    public String labPage() {
        return "lab/lab";
    }

    @GetMapping("/lab/approve")
    public String labApprovePage() {
        return "lab/approve";
    }

    @GetMapping("/lab/list")
    public String labListPage() {
        return "lab/list";
    }

    @GetMapping("/lab/info")
    public String labInfoPage() {
        return "lab/info";
    }

    @GetMapping("/student/list")
    public String studentListPage() {
        return "student/list";
    }

    @GetMapping("/student/apply")
    public String studentApplyPage() {
        return "student/apply";
    }

    @GetMapping("/notice")
    public String noticePage() {
        return "notice/notice";
    }

    @GetMapping("/notice/list")
    public String noticeListPage() {
        return "notice/list";
    }

    @GetMapping("/notice/info")
    public String noticeInfoPage() {
        return "notice/info";
    }

    @GetMapping("/notice/publish")
    public String noticePublishPage() {
        return "notice/publish";
    }

    @GetMapping("/equip")
    public String equipPage() {
        return "equip/equip";
    }

    @GetMapping("/equip/list")
    public String equipListPage() {
        return "equip/list";
    }

    @GetMapping("/equip/sub")
    public String equipSubPage() {
        return "equip/sub";
    }

    @GetMapping("/equip/sublist")
    public String equipSubListPage() {
        return "equip/sublist";
    }

    @GetMapping("/equip/repair")
    public String equipRepairPage() {
        return "equip/repair";
    }

    @GetMapping("/equip/scrap")
    public String equipScrapPage() {
        return "equip/scrap";
    }

    @GetMapping("/equip/sub/approve")
    public String equipApproveSubPage() {
        return "equip/sub/approve";
    }

    @GetMapping("/equip/repair/approve")
    public String equipApproveRepairPage() {
        return "equip/repair/approve";
    }

    @GetMapping("/equip/repair/repair")
    public String equipRepairRepairPage() {
        return "equip/repair/repair";
    }

    @GetMapping("/equip/scrap/approve")
    public String equipApproveApprovePage() {
        return "equip/scrap/approve";
    }

    @GetMapping("/equip/infoRecord/sub")
    public String equipInfoRecordSubPage() {
        return "equip/infoRecord/sub";
    }

    @GetMapping("/equip/infoRecord/repair")
    public String equipInfoRecordRepairPage() {
        return "equip/infoRecord/repair";
    }

    @GetMapping("/equip/infoRecord/scrap")
    public String equipInfoRecordScrapPage() {
        return "equip/infoRecord/scrap";
    }


}
