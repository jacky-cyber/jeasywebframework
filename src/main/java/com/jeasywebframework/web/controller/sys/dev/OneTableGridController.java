package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.domain.dev.GenColumn;
import com.jeasywebframework.domain.dev.GenTable;
import com.jeasywebframework.service.dev.GeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Controller
@RequestMapping("/sys/dev/gen/1table_grid/")
public class OneTableGridController {



    private static final Logger logger = LoggerFactory.getLogger(OneTableGridController.class);



    @Autowired
    private GeneratorService generatorService;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list(Model model) {

        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        List<GenTable> tableList = generatorService.findAllTable();
        model.addAttribute("tableList", tableList);
        return "sys/dev/gen/1table_grid/list";
    }


    @RequestMapping(value = "domain.html", method = RequestMethod.GET)
    public String domain(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);


        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/java_domain";
    }

    @RequestMapping(value = "dao.html", method = RequestMethod.GET)
    public String dao(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);


        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/java_dao";
    }

    @RequestMapping(value = "controller.html", method = RequestMethod.GET)
    public String controller(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);


        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/java_controller";
    }


    @RequestMapping(value = "list.vm.html", method = RequestMethod.GET)
    public String list_vm(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/vm_list";
    }

    @RequestMapping(value = "add.vm.html", method = RequestMethod.GET)
    public String add_vm(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);


        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/vm_add";
    }


    @RequestMapping(value = "edit.vm.html", method = RequestMethod.GET)
    public String edit_vm(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/vm_edit";
    }


    @RequestMapping(value = "list.js.html", method = RequestMethod.GET)
    public String list_js(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/js_list";
    }

    @RequestMapping(value = "add.js.html", method = RequestMethod.GET)
    public String add_js(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/js_add";
    }


    @RequestMapping(value = "edit.js.html", method = RequestMethod.GET)
    public String edit_js(Model model, String name) {
        Date now = new Date(System.currentTimeMillis());
        model.addAttribute("now", now);

        GenTable table = generatorService.findByName(name);
        List<GenColumn> columnList = generatorService.findAllColumn(name);
        model.addAttribute("table", table);
        model.addAttribute("columnList", columnList);

        return "sys/dev/gen/1table_grid/js_edit";
    }

}
