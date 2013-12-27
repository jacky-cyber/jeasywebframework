package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.domain.dev.ColumnInfo;
import com.jeasywebframework.domain.dev.TableInfo;
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

        List<TableInfo> tableList = generatorService.findAllTable();
        model.addAttribute("tableList", tableList);

        return "sys/dev/gen/table/list";
    }

    @RequestMapping(value = "edit.html", method = RequestMethod.GET)
    public String edit(String name, Model model) {
        TableInfo table = generatorService.findByName(name);

        List<ColumnInfo> columnList = generatorService.findAllColumn(name);


        model.addAttribute("columnList", columnList);
        model.addAttribute("table", table);

        return "sys/dev/gen/table/edit";
    }


    @RequestMapping(value = "update.ajax", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(TableInfo table, String columnList) {

        JSONArray jsonArray = JSONArray.fromObject(columnList);

        List<ColumnInfo> columns = new ArrayList<ColumnInfo>();

        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject obj = jsonArray.getJSONObject(j);

            ColumnInfo columnInfo = (ColumnInfo) JSONObject.toBean(obj, ColumnInfo.class);
            columns.add(columnInfo);
        }

        generatorService.saveAll(table, columns);

        return AjaxUtil.success();
    }


}
