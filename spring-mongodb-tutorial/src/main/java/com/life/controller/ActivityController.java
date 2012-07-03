package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.life.domain.Activity;
import com.life.domain.Plan;
import com.life.domain.User;
import com.life.service.ActivityService;
import com.life.service.PlanService;
import com.life.service.auth.impl.AuthServiceImpl;

@Controller
@RequestMapping("/activity")
public class ActivityController
{

    @Autowired
    private PlanService planService;

    @Autowired
    private AuthServiceImpl authService;
    
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activities/{autId}/{planId}", headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Activity> getActivities(@PathVariable(value = "autId") String autId, @PathVariable(value = "planId") String planId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        Preconditions.checkNotNull(user, "Invaid auth id. User couldnt find");
        Plan plan = planService.getPlan(planId);
        return plan.getActivities();
    }

    @RequestMapping(value = "/create/{autId}/{planId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Activity> create(@RequestBody Activity activity, @PathVariable(value = "autId") String autId, @PathVariable(value = "planId") String planId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        Preconditions.checkNotNull(user, "Invaid auth id. User couldnt find");
        Plan plan = planService.getPlan(planId);
        activityService.createActivity(plan, activity);
        return plan.getActivities();
    }}
