package com.jeasywebframework.web.controller.sys.dept;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.SysDeptDepartment;
import com.jeasywebframework.service.dept.DepartmentService;
import com.jeasywebframework.utils.AjaxUtil;
import com.jeasywebframework.utils.json.WebJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Controller
@RequestMapping("/sys/dept/department/")
public class DepartmentController {


    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);


    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dept/department/list";
    }

    @RequestMapping(value = "list-tree.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray listTree() {
        List<SysDeptDepartment> departmentList = departmentService.findAll(new Sort(Sort.Direction.ASC, "path"));

        JSONArray jsonArray = new JSONArray();
        for (SysDeptDepartment department : departmentList) {
            JSONObject jo = JSONObject.fromObject(department, WebJsonConfig.getInstance());

            jo.put("pId", department.getParentId());
            jsonArray.add(jo);
        }
        return jsonArray;
    }


    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listGrid() {
        JSONObject jsonObject = new JSONObject();

        List<SysDeptDepartment> departmentList = departmentService.findAll(new Sort(Sort.Direction.ASC, "path"));

        JSONArray jsonArray = new JSONArray();
        for (SysDeptDepartment department : departmentList) {
            JSONObject jo = JSONObject.fromObject(department);

            if (department.getChildrenNum().intValue() > 0) {
                jo.put("isLeaf", false);
            } else {
                jo.put("isLeaf", true);
            }

            jo.remove("id");
            jo.put("id", String.valueOf(department.getId()));

            jo.put("expanded", false);
            jo.put("loaded", true);
            String pid = department.getParentId() > 0 ? String.valueOf(department.getParentId()) : "";
            jo.put("parent", pid);


            JSONObject cell = new JSONObject();
            cell.put("cell", jo);
            cell.put("id", String.valueOf(department.getId()));

            jsonArray.add(cell);

        }
        jsonObject.put("rows", jsonArray);
        jsonObject.put("total", 1);
        jsonObject.put("page", 1);
        jsonObject.put("records", departmentList.size());


        return jsonObject;
    }


    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add(Model model, @RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        model.addAttribute("parentId", parentId);

        if (parentId != 0L) {
            SysDeptDepartment parent = departmentService.findOne(parentId);
            model.addAttribute("pIdName", parent.getName());
        } else {
            model.addAttribute("pIdName", "顶级机构名称");
        }

        List<SysDeptDepartment> departmentList = departmentService.findAll();
        model.addAttribute("deptList", departmentList);
        return "sys/dept/department/add";
    }

    @RequestMapping(value = "save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SysDeptDepartment sysDeptDepartment, HostHolder hostHolder) {
        Long parentId = sysDeptDepartment.getParentId();

        String path = "";
        if (parentId == 0L) {
            sysDeptDepartment.setLevel(1);
        } else {
            SysDeptDepartment parent = departmentService.findOne(parentId);
            sysDeptDepartment.setLevel(parent.getLevel() + 1);
            path = parent.getPath();

            Long count = departmentService.countByParentId(parentId);
            parent.setChildrenNum(count + 1);
            departmentService.saveAndFlush(parent);
        }

        Date date = new Date(System.currentTimeMillis());
        sysDeptDepartment.setCreateTime(date);
        sysDeptDepartment.setUpdateTime(date);
        sysDeptDepartment.setCreateUserId(hostHolder.getHostId());
        sysDeptDepartment.setUpdateUserId(hostHolder.getHostId());

        sysDeptDepartment.setChildrenNum(0L);


        departmentService.save(sysDeptDepartment);


        sysDeptDepartment.setPath(path + "/" + sysDeptDepartment.getId());
        departmentService.saveAndFlush(sysDeptDepartment);

        return AjaxUtil.success();
    }

    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Model model, Long id) {
        SysDeptDepartment department = departmentService.findOne(id);
        model.addAttribute("dept", department);

        model.addAttribute("parentId", department.getParentId());
        if (department.getParentId() > 0L) {
            SysDeptDepartment parent = departmentService.findOne(department.getParentId());
            model.addAttribute("pIdName", parent.getName());
        } else {
            model.addAttribute("pIdName", "顶级机构名称");
        }

        List<SysDeptDepartment> departmentList = departmentService.findAll();
        model.addAttribute("deptList", departmentList);
        return "sys/dept/department/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(SysDeptDepartment sysDeptDepartment, HostHolder hostHolder) {
        SysDeptDepartment old = departmentService.findOne(sysDeptDepartment.getId());

        // 不能修改的属性
        sysDeptDepartment.setCreateTime(old.getCreateTime());
        sysDeptDepartment.setPath(old.getPath());
        sysDeptDepartment.setLevel(old.getLevel());
        sysDeptDepartment.setParentId(old.getParentId());
        sysDeptDepartment.setChildrenNum(old.getChildrenNum());


        Date now = new Date(System.currentTimeMillis());
        sysDeptDepartment.setUpdateUserId(hostHolder.getHostId());
        sysDeptDepartment.setUpdateTime(new Date(System.currentTimeMillis()));

        departmentService.saveAndFlush(sysDeptDepartment);

        return AjaxUtil.success();
    }


}
