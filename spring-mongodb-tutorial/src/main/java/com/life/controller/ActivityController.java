package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.life.domain.Activity;
import com.life.domain.Role;
import com.life.domain.User;
import com.life.dto.UserListDto;
import com.life.service.ActivityService;
import com.life.service.UserService;

@Controller
@RequestMapping("/activity")
public class ActivityController
{

    @Autowired
    private ActivityService service;

    @RequestMapping
    public String getUsersPage()
    {
        return "users";
    }

    @RequestMapping(value = "/activities")
    public @ResponseBody
    List<Activity> getActvities()
    {
        return service.readAll();
    }

    @RequestMapping(value = "/get")
    public @ResponseBody
    Activity get(@RequestBody Activity activity)
    {
        return service.read(activity);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    Activity create(@RequestBody Activity activity)
    {
        return service.create(activity);
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
