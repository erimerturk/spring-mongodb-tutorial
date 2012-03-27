package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.life.domain.Plan;
import com.life.domain.User;
import com.life.service.PlanService;
import com.life.service.UserService;

@Controller
@RequestMapping("/plan")
public class PlanController
{

    @Autowired
    private UserService userService;
    
    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/plans", headers="Accept=application/xml, application/json")
    public @ResponseBody List<Plan> getPlans()
    {
        return planService.readAll();
    }

    @RequestMapping(value = "/create/{userID}", method = RequestMethod.POST, headers="Accept=application/xml, application/json")
    public @ResponseBody List<Plan> create(@RequestBody Plan plan, @PathVariable(value="userID") String userID)
    {
        User user = new User();
        user.setUsername(userID);
        User exis = userService.read(user);
        plan.setUserName(exis.getUsername());
        exis.addPlan(plan);
        return userService.update(exis).getPlans();
    }

}
