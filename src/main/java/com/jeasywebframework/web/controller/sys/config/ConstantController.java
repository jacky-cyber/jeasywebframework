package com.jeasywebframework.web.controller.sys.config;

import com.jeasywebframework.domain.config.Constant;
import com.jeasywebframework.domain.dept.HostHolder;
import com.jeasywebframework.service.config.ConstantService;
import com.jeasywebframework.utils.AjaxUtil;
import net.sf.json.JSONObject;
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
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
@Controller
@RequestMapping("/sys/config/constant/")
public class ConstantController {


    @Autowired
    private ConstantService constantService;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/config/constant/list";
    }

    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    public JSONObject listGrid(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "20") int rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Constant> pageRst = constantService.findAll(pageable);
        return AjaxUtil.jqGridJson(pageRst);
    }

    @RequestMapping(value = "add.html", method = RequestMethod.GET)
    public String add() {
        return "sys/config/constant/add";
    }

    @RequestMapping(value = "save.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject save(Constant constant, HostHolder hostHolder) {
        Date now = new Date(System.currentTimeMillis());
        constant.setUpdateTime(now);
        constant.setUpdateUserId(hostHolder.getHostId());

        constant.setCreateUserId(hostHolder.getHostId());
        constant.setCreateTime(now);

        constantService.save(constant);

        return AjaxUtil.success();
    }


    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(Long id, Model model) {
        Constant constant = constantService.findOne(id);
        model.addAttribute("constant", constant);

        return "sys/config/constant/edit";
    }

    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(Constant constant, HostHolder hostHolder) {
        Date now = new Date(System.currentTimeMillis());
        constant.setUpdateTime(now);
        constant.setUpdateUserId(hostHolder.getHostId());

        Constant old = constantService.findOne(constant.getId());
        constant.setCreateUserId(old.getUpdateUserId());
        constant.setCreateTime(old.getCreateTime());

        constantService.saveAndFlush(constant);

        return AjaxUtil.success();
    }


}
