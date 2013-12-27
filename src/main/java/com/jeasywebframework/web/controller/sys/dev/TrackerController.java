package com.jeasywebframework.web.controller.sys.dev;

import com.jeasywebframework.dao.dev.TrackerDao;
import com.jeasywebframework.domain.dev.Tracker;
import com.jeasywebframework.utils.AjaxUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/sys/dev/tracker/")
public class TrackerController {

    @Autowired
    private TrackerDao trackerDao;

    @RequestMapping(value = "list.html", method = RequestMethod.GET)
    public String list() {
        return "sys/dev/tracker/list";
    }

    @RequestMapping(value = "list.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listGrid(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "rows", defaultValue = "20") int rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Long count = trackerDao.countByParentId(0L);
        List<Tracker> list = trackerDao.findByParentId(0L, pageable.getOffset(), pageable.getPageSize());

        Page<Tracker> pageRst = new PageImpl<Tracker>(list, pageable, count);

        return AjaxUtil.jqGridJson(pageRst);
    }

    @RequestMapping(value = "detail.html", method = RequestMethod.GET)
    public String detail(Long id, Model model) {
        Tracker sysDevInside = trackerDao.findOne(id);
        model.addAttribute("inside", sysDevInside);

        return "sys/dev/tracker/detail";
    }

    @RequestMapping(value = "detail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject listDetail(Long id) {
        JSONObject jsonObject = new JSONObject();

        Tracker parentTracker = trackerDao.findOne(id);

        List<Tracker> insideList = trackerDao.findByPathLike(parentTracker.getPath() + "%");

        long parentCost = parentTracker.getEndTime() - parentTracker.getStartTime();

        JSONArray jsonArray = new JSONArray();
        for (Tracker department : insideList) {
            JSONObject jo = JSONObject.fromObject(department);


            Long cost = department.getEndTime() - department.getStartTime();
            jo.put("cost", cost);

            Long s1 = department.getStartTime();
            Long a1 = s1 - parentTracker.getStartTime();


            double w1 = Double.valueOf(a1) / Double.valueOf(parentCost);
            double w2 = Double.valueOf(cost) / Double.valueOf(parentCost);



            jo.put("w1", String.valueOf(w1 * 100));
            jo.put("w2", String.valueOf(w2 * 100));

            jo.remove("startTime");
            jo.put("startTime", DateFormatUtils.format(department.getStartTime(), "yyyy-MM-dd hh:mm:ss:SSS"));
            jo.remove("endTime");
            jo.put("endTime", DateFormatUtils.format(department.getEndTime(), "yyyy-MM-dd hh:mm:ss:SSS"));


            if (department.getChildrenNum().intValue() > 0) {
                jo.put("isLeaf", false);
            } else {
                jo.put("isLeaf", true);
            }

            jo.remove("id");
            jo.put("id", String.valueOf(department.getId()));

            jo.put("expanded", true);
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
