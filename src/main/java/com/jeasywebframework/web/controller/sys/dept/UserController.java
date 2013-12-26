package com.jeasywebframework.web.controller.sys.dept;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.Department;
import com.jeasywebframework.domain.dept.User;
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
        Page<User> pageRst = userService.findByKeywordAndDeptId(pageable, keyword, deptId);
        return AjaxUtil.jqGridJson(pageRst);
    }


    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add(@RequestParam(value = "deptId", defaultValue = "0") Long deptId, Model model) {
        if (deptId > -1L) {
            Department department = departmentService.findOne(deptId);

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
    public JSONObject save(User user, String password2, HostHolder hostHolder) {


        if (StringUtils.isEmpty(user.getUsername())) {
            return AjaxUtil.failure("账号不能为空值，请重新输入数据！");
        }


        if (!org.apache.commons.lang.StringUtils.equals(password2, user.getPassword())) {
            return AjaxUtil.failure("密码和确认密码不一致，请重新输入数据！");
        }

        if (password2.length() < 6) {
            return AjaxUtil.failure("密码长度不能少于6位，请重新输入数据！");
        }


        Date date = new Date(System.currentTimeMillis());
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setCreateUserId(hostHolder.getHostId());
        user.setUpdateUserId(hostHolder.getHostId());


        String salt = RandomStringUtils.random(8);
        String pwd1 = MD5Util.getMD5Str(user.getPassword()) + salt;
        String password = MD5Util.getMD5Str(pwd1);

        user.setSalt(salt);
        user.setPassword(password);

        userService.save(user);
        return AjaxUtil.success(null);
    }


    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        User user = userService.findOne(id);

        Department department = departmentService.findOne(user.getDepartmentId());
        if (department != null) {
            model.addAttribute("departmentId", department.getId());
            model.addAttribute("pIdName", department.getName());
        } else {
            model.addAttribute("deparetmentId", -1L);
            model.addAttribute("pIdName", "请选择对应的机构");
        }


        model.addAttribute("user", user);
        return "sys/dept/user/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(User user, HostHolder hostHolder) {
        User old = userService.findOne(user.getId());

        Date date = new Date(System.currentTimeMillis());
        user.setUpdateTime(date);
        user.setUpdateUserId(hostHolder.getHostId());


        //不能修改的属性
        user.setCreateTime(old.getCreateTime());
        user.setCreateUserId(old.getCreateUserId());

        user.setSalt(old.getSalt());
        user.setPassword(old.getPassword());


        userService.saveAndFlush(user);
        return AjaxUtil.success(null);
    }


}
