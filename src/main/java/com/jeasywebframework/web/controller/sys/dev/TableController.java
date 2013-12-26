package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.domain.dev.GenColumn;
import com.jeasywebframework.domain.dev.GenTable;
import com.jeasywebframework.service.dev.GeneratorService;
import com.jeasywebframework.utils.AjaxUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-20.
 */
@Controller
@RequestMapping("/sys/dev/gen/table/")
public class TableController {


    private static final Logger logger = LoggerFactory.getLogger(TableController.class);



    @Autowired
    private GeneratorService generatorService;


    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list(Model model) {

        List<GenTable> tableList = generatorService.findAllTable();
        model.addAttribute("tableList", tableList);

        return "sys/dev/gen/table/list";
    }

    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(String name, Model model) {
        GenTable table = generatorService.findByName(name);

        List<GenColumn> columnList = generatorService.findAllColumn(name);


        model.addAttribute("columnList", columnList);
        model.addAttribute("table", table);

        return "sys/dev/gen/table/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(GenTable table, String columnList) {

        JSONArray jsonArray = JSONArray.fromObject(columnList);

        List<GenColumn> columns = new ArrayList<GenColumn>();

        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject obj = jsonArray.getJSONObject(j);

            GenColumn genColumn = (GenColumn) JSONObject.toBean(obj, GenColumn.class);
            columns.add(genColumn);
        }

        generatorService.saveAll(table, columns);

        return AjaxUtil.success();
    }


}
