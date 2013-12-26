package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.dao.dev.SysDevInsideDao;
import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.utils.AjaxUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
@Controller
@RequestMapping("/sys/dev/inside/")
public class InsideController {

    @Autowired
    private SysDevInsideDao sysDevInsideDao;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dev/inside/list";
    }

    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listGrid(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "20") int rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Tracker> pageRst = sysDevInsideDao.findByParentId(0L, pageable);

        return AjaxUtil.jqGridJson(pageRst);
    }

    @RequestMapping(value = "detail.html", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        Tracker sysDevInside = sysDevInsideDao.findOne(id);
        model.addAttribute("inside", sysDevInside);

        return "sys/dev/inside/detail";
    }

    @RequestMapping(value = "detail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listDetail(Long id) {
        JSONObject jsonObject = new JSONObject();

        Tracker inside = sysDevInsideDao.findOne(id);

        List<Tracker> insideList = sysDevInsideDao.findByPathLike(inside.getPath() + "%",
                new Sort(Sort.Direction.ASC, "path"));

        JSONArray jsonArray = new JSONArray();
        for (Tracker department : insideList) {
            JSONObject jo = JSONObject.fromObject(department);

            jo.remove("startTime");
            jo.put("startTime", DateFormatUtils.format(department.getStartTime(), "yyyy-MM-dd hh:mm:ss:SSS"));
            jo.remove("endTime");
            jo.put("endTime", DateFormatUtils.format(department.getEndTime(), "yyyy-MM-dd hh:mm:ss:SSS"));

            jo.put("cost", department.getEndTime() - department.getStartTime());

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
        jsonObject.put("records", insideList.size());
        return jsonObject;
    }

}
