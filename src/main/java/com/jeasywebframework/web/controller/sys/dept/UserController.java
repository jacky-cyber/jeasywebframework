package com.jeasywebframework.web.controller.sys.dept;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.SysDeptDepartment;
import com.jeasywebframework.domain.dept.SysDeptUser;
import com.jeasywebframework.service.dept.DepartmentService;
import com.jeasywebframework.service.dept.UserService;
import com.jeasywebframework.utils.AjaxUtil;
import com.jeasywebframework.utils.MD5Util;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.StringTokenizer;


/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Controller
@RequestMapping("/sys/dept/user/")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;


    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dept/user/list";
    }

    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listPage(
            @RequestParam(value = "deptId", defaultValue = "-1") Long deptId,
            String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "rows", defaultValue = "20") int rows
    ) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<SysDeptUser> pageRst = userService.findByKeywordAndDeptId(pageable, keyword, deptId);
        return AjaxUtil.jqGridJson(pageRst);
    }


    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add(@RequestParam(value = "deptId", defaultValue = "0") Long deptId, Model model) {
        if (deptId > -1L) {
            SysDeptDepartment department = departmentService.findOne(deptId);

            model.addAttribute("departmentId", deptId);
            model.addAttribute("pIdName", department.getName());
        } else {
            model.addAttribute("departmentId", "0");
            model.addAttribute("pIdName", "请选择所属机构");
        }

        return "sys/dept/user/add";
    }


    @RequestMapping(value = "save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SysDeptUser sysDeptUser, String password2, HostHolder hostHolder) {


        if (StringUtils.isEmpty(sysDeptUser.getUsername())) {
            return AjaxUtil.failure("账号不能为空值，请重新输入数据！");
        }


        if (!org.apache.commons.lang.StringUtils.equals(password2, sysDeptUser.getPassword())) {
            return AjaxUtil.failure("密码和确认密码不一致，请重新输入数据！");
        }

        if (password2.length() < 6) {
            return AjaxUtil.failure("密码长度不能少于6位，请重新输入数据！");
        }


        Date date = new Date(System.currentTimeMillis());
        sysDeptUser.setCreateTime(date);
        sysDeptUser.setUpdateTime(date);
        sysDeptUser.setCreateUserId(hostHolder.getHostId());
        sysDeptUser.setUpdateUserId(hostHolder.getHostId());


        String salt = RandomStringUtils.random(8);
        String pwd1 = MD5Util.getMD5Str(sysDeptUser.getPassword()) + salt;
        String password = MD5Util.getMD5Str(pwd1);

        sysDeptUser.setSalt(salt);
        sysDeptUser.setPassword(password);

        userService.save(sysDeptUser);
        return AjaxUtil.success(null);
    }


    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        SysDeptUser sysDeptUser = userService.findOne(id);

        SysDeptDepartment department = departmentService.findOne(sysDeptUser.getDepartmentId());
        if (department != null) {
            model.addAttribute("departmentId", department.getId());
            model.addAttribute("pIdName", department.getName());
        } else {
            model.addAttribute("deparetmentId", -1L);
            model.addAttribute("pIdName", "请选择对应的机构");
        }


        model.addAttribute("user", sysDeptUser);
        return "sys/dept/user/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(SysDeptUser sysDeptUser, HostHolder hostHolder) {
        SysDeptUser old = userService.findOne(sysDeptUser.getId());

        Date date = new Date(System.currentTimeMillis());
        sysDeptUser.setUpdateTime(date);
        sysDeptUser.setUpdateUserId(hostHolder.getHostId());


        //不能修改的属性
        sysDeptUser.setCreateTime(old.getCreateTime());
        sysDeptUser.setCreateUserId(old.getCreateUserId());

        sysDeptUser.setSalt(old.getSalt());
        sysDeptUser.setPassword(old.getPassword());


        userService.saveAndFlush(sysDeptUser);
        return AjaxUtil.success(null);
    }


}
