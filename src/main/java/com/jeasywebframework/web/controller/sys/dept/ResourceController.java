package com.jeasywebframework.web.controller.sys.dept;

import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.domain.dept.SysDeptResource;
import com.jeasywebframework.service.dept.ResourceService;
import com.jeasywebframework.utils.AjaxUtil;
import com.jeasywebframework.utils.json.WebJsonConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * Created by jeasywebframework@gmail.com on 13-12-19.
 */
@Controller
@RequestMapping("/sys/dept/resource/")
public class ResourceController {


    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ApplicationContext applicationContext;


    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dept/resource/list";
    }

    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listGrid() {
        JSONObject jsonObject = new JSONObject();

        List<SysDeptResource> departmentList = resourceService.findAll(new Sort(Sort.Direction.ASC, "path"));

        JSONArray jsonArray = new JSONArray();
        for (SysDeptResource department : departmentList) {
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


    @RequestMapping(value = "list-tree.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray loadTree() {
        JSONArray jsonArray = new JSONArray();
        List<SysDeptResource> moduleList = resourceService.findAll(new Sort(Sort.Direction.DESC, SysDeptResource.PATH));

        for (SysDeptResource module : moduleList) {
            JSONObject jo = JSONObject.fromObject(module, WebJsonConfig.getInstance());
            jo.put("pId", module.getParentId());
            jsonArray.add(jo);
        }

        return jsonArray;
    }

    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add(Model model, @RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        model.addAttribute("parentId", parentId);

        if (parentId != 0L) {
            SysDeptResource parent = resourceService.findOne(parentId);
            model.addAttribute("pIdName", parent.getName());
        } else {
            model.addAttribute("pIdName", "顶级模块名称");
        }


        return "sys/dept/resource/add";
    }


    @RequestMapping(value = "save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(SysDeptResource sysDeptModule, HostHolder hostHolder) {

        Long parentId = sysDeptModule.getParentId();

        String path = "";
        if (parentId == 0L) {
            sysDeptModule.setLevel(1);
        } else {
            SysDeptResource parent = resourceService.findOne(parentId);
            sysDeptModule.setLevel(parent.getLevel() + 1);
            path = parent.getPath();

            Long count = resourceService.countByParentId(parentId);
            parent.setChildrenNum(count + 1);
            resourceService.saveAndFlush(parent);
        }
        Date date = new Date(System.currentTimeMillis());
        sysDeptModule.setCreateTime(date);
        sysDeptModule.setUpdateTime(date);
        sysDeptModule.setCreateUserId(hostHolder.getHostId());
        sysDeptModule.setUpdateUserId(hostHolder.getHostId());

        sysDeptModule.setChildrenNum(0L);

        resourceService.save(sysDeptModule);


        sysDeptModule.setPath(path + "/" + sysDeptModule.getId());
        resourceService.saveAndFlush(sysDeptModule);

        return AjaxUtil.success();
    }


    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Model model, Long id) {
        SysDeptResource department = resourceService.findOne(id);
        model.addAttribute("dept", department);

        model.addAttribute("parentId", department.getParentId());
        if (department.getParentId() > 0L) {
            SysDeptResource parent = resourceService.findOne(department.getParentId());
            model.addAttribute("pIdName", parent.getName());
        } else {
            model.addAttribute("pIdName", "顶级模块名称");
        }

        return "sys/dept/resource/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(SysDeptResource sysDeptModule, HostHolder hostHolder) {

        SysDeptResource old = resourceService.findOne(sysDeptModule.getId());

        sysDeptModule.setUpdateTime(new Date(System.currentTimeMillis()));
        sysDeptModule.setUpdateUserId(hostHolder.getHostId());


        //不能修改的项目
        sysDeptModule.setCreateTime(old.getCreateTime());
        sysDeptModule.setCreateUserId(old.getCreateUserId());

        sysDeptModule.setPath(old.getPath());
        sysDeptModule.setLevel(old.getLevel());
        sysDeptModule.setParentId(old.getParentId());
        sysDeptModule.setChildrenNum(old.getChildrenNum());

        resourceService.saveAndFlush(sysDeptModule);
        return AjaxUtil.success();
    }


    @RequestMapping(value = "batch-add.html", method = RequestMethod.GET)
    public String batchAdd(
            String className, //
            @RequestParam(value = "moduleId", defaultValue = "0") Long moduleId,//
            Model model) {
        if (moduleId > 0) {
            SysDeptResource module = resourceService.findOne(moduleId);
            model.addAttribute("pIdName", module.getName());
        } else {
            model.addAttribute("pIdName", "请选择模块");
        }
        model.addAttribute("moduleId", moduleId);

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();

        Iterator<RequestMappingInfo> iterator = mappingInfoHandlerMethodMap.keySet().iterator();
        List<String> controllerList = new ArrayList<String>();


        List<SysDeptResource> resourceList = new ArrayList<SysDeptResource>();

        Map<String, String> treeMap = new HashMap<String, String>();
        while (iterator.hasNext()) {
            RequestMappingInfo requestMappingInfo = iterator.next();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodMap.get(requestMappingInfo);
            treeMap.put(handlerMethod.getBeanType().getName(), handlerMethod.getBeanType().getName());

            if (StringUtils.equals(handlerMethod.getBeanType().getName(), className)) {
                SysDeptResource sysDeptResource = new SysDeptResource();
                RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);

                RequestMapping requestMapping1 = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
                String path = "";
                if (requestMapping1 != null && requestMapping1.value() != null && requestMapping1.value().length > 0) {
                    for (String s : requestMapping1.value()) {
                        path = s;
                    }
                }

                sysDeptResource.setUrl(path + StringUtils.join(requestMapping.value(), ","));
                RequestMethod[] requestMethods = requestMapping.method();
                String[] methods = new String[requestMethods.length];
                int i = 0;
                for (RequestMethod requestMethod : requestMethods) {
                    methods[i++] = requestMethod.name();
                }

                sysDeptResource.setMethod(StringUtils.join(methods, "/"));
                sysDeptResource.setJavaMethod(handlerMethod.getBeanType().getName() + "#" + handlerMethod.getMethod().getName());


                resourceList.add(sysDeptResource);
            }
        }

        model.addAttribute("resourceList", resourceList);

        Iterator<String> iterator1 = treeMap.keySet().iterator();
        while (iterator1.hasNext()) {
            String className1 = iterator1.next();
            controllerList.add(className1);
        }

        model.addAttribute("controllerList", controllerList);


        return "sys/dept/resource/batch-add";
    }


    @RequestMapping(value = "batch-save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject batchSave(String resourceList, Long moduleId, HostHolder hostHolder) {

        JSONArray jsonArray = JSONArray.fromObject(resourceList);

        List<SysDeptResource> resources = new ArrayList<SysDeptResource>();

        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);
            SysDeptResource sysDeptResource = (SysDeptResource) jsonObject.toBean(jsonObject, SysDeptResource.class);


            Date now = new Date(System.currentTimeMillis());
            sysDeptResource.setCreateTime(now);
            sysDeptResource.setUpdateTime(now);
            sysDeptResource.setCreateUserId(hostHolder.getHostId());
            sysDeptResource.setUpdateUserId(hostHolder.getHostId());


            resources.add(sysDeptResource);
        }
        resourceService.batchSave(resources);

        return AjaxUtil.success();
    }


}
