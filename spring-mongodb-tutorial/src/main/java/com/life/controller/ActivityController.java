package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.life.domain.Activity;
import com.life.domain.Plan;
import com.life.service.ActivityService;
import com.life.service.PlanService;

@Controller
@RequestMapping("/activity")
public class ActivityController
{

    @Autowired
    private ActivityService service;
    
    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/activities", headers="Accept=application/xml, application/json")
    public @ResponseBody List<Activity> getActvities()
    {
        return service.readAll();
    }

    @RequestMapping(value = "/get")
    public @ResponseBody Activity get(@RequestBody Activity activity)
    {
        return service.read(activity);
    }

    @RequestMapping(value = "/create/{planID}", method = RequestMethod.POST, headers="Accept=application/xml, application/json")
    public @ResponseBody List<Activity> create(@RequestBody Activity activity, @PathVariable(value="planID") String planID)
    {
        Plan p = new Plan();
        p.setId(planID);
        Plan exis = planService.read(p);
        exis.addActivity(activity);
        return planService.update(exis).getActivities();
    }

    public @ResponseBody
    Activity update(@RequestBody Activity activity)
    {
        return service.update(activity);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Boolean delete(@RequestParam String activityName)
    {

        Activity existingActivity = new Activity();
        existingActivity.setName(activityName);

        return service.delete(existingActivity);
    }
}
