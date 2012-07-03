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
import com.life.domain.Plan;
import com.life.domain.User;
import com.life.service.PlanService;
import com.life.service.UserService;
import com.life.service.auth.impl.AuthServiceImpl;

@Controller
@RequestMapping("/plan")
public class PlanController
{

    @Autowired
    private PlanService planService;

    @Autowired
    private AuthServiceImpl authService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/plans/{autId}", headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Plan> getPlans(@PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        return user.getPlans();
    }

    @RequestMapping(value = "/create/{autId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<Plan> create(@RequestBody Plan plan, @PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        Plan newPlan = planService.create(user, plan);
        return userService.findUserById(newPlan.getUserId()).getPlans();
    }

}
