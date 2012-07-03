package com.life.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.life.domain.Plan;
import com.life.domain.User;
import com.life.repository.ActivityRepository;
import com.life.repository.PlanRepository;

@Service
public class PlanService
{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserService userService;

    public Plan create(User user, Plan plan)
    {

        plan.setUserId(user.getId());
        user.addPlan(plan);
        userService.create(user);

        return planRepository.findByUniqId(plan.getUniqId());
    }

    public Plan read(Plan plan)
    {
        return planRepository.findByUniqId(plan.getUniqId());
    }

    public List<Plan> readAll()
    {
        return planRepository.findAll();
    }

    public Plan update(Plan plan)
    {
        Plan existingPlan = planRepository.findById(plan.getId());
        Preconditions.checkNotNull(existingPlan, "Plan couldnt found");
        existingPlan.setName(plan.getName());
        existingPlan.setActivities(plan.getActivities());
        activityRepository.save(plan.getActivities());
        return planRepository.save(existingPlan);
    }

    public Boolean delete(Plan plan)
    {
        Plan existingPlan = planRepository.findById(plan.getId());

        if (existingPlan == null)
        {
            return false;
        }
        planRepository.delete(plan);
        return true;
    }

    public Plan getPlan(String planId)
    {
        return planRepository.findById(planId);
    }
}
