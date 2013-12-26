package com.jeasywebframework.web.controller.sys.dept;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.Role;
import com.jeasywebframework.domain.dept.RoleResource;
import com.jeasywebframework.service.dept.RoleService;
import com.jeasywebframework.utils.AjaxUtil;
import net.sf.json.JSONObject;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-23.
 */
@Controller
@RequestMapping("/sys/dept/role/")
public class RoleController {


    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dept/role/list";
    }


    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listData(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "50") int rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Role> pageRst = roleService.findAll(pageable);
        return AjaxUtil.jqGridJson(pageRst);
    }


    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add() {
        return "sys/dept/role/add";
    }

    @RequestMapping(value = "save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(Role role, String resourceIds, HostHolder hostHolder) {
        // TODO
        Date now = new Date(System.currentTimeMillis());
        role.setCreateTime(now);
        role.setUpdateTime(now);

        role.setCreateUserId(hostHolder.getHostId());
        role.setUpdateUserId(hostHolder.getHostId());


        roleService.save(role, resourceIds);
        return AjaxUtil.success();
    }


    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Model model, Long id) {
        Role role = roleService.findOne(id);
        model.addAttribute("sysDeptRole", role);

        List<RoleResource> roleResourceList = roleService.findAllRoleResourceByRoleId(id);
        List<String> resouceIds = new ArrayList<String>();
        for (RoleResource roleResource : roleResourceList) {
            resouceIds.add(roleResource.getId() + "");
        }

        model.addAttribute("resourceIds", StringUtils.join(resouceIds, ","));

        return "sys/dept/role/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(Role role, String resourceIds, HostHolder hostHolder) {
        // TODO
        Date now = new Date(System.currentTimeMillis());
        role.setUpdateTime(now);
        role.setUpdateUserId(hostHolder.getHostId());


        // TODO 不能修改的数据项目
        Role old = roleService.findOne(role.getId());
        role.setCreateTime(old.getCreateTime());
        role.setCreateUserId(old.getCreateUserId());


        roleService.update(role, resourceIds);
        return AjaxUtil.success();
    }


}
