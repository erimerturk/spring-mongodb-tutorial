package com.life.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.life.domain.Activity;
import com.life.domain.Plan;
import com.life.repository.ActivityRepository;
import com.life.repository.FeedRepository;
import com.life.repository.PreparationRepository;
import com.life.repository.RiskRepository;

@Service
public class ActivityService
{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PreparationRepository preparationRepository;

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private FeedRepository feedRepository;

    public Activity create(Activity activity)
    {
        preparationRepository.save(activity.getPreparations());
        riskRepository.save(activity.getRisks());
        feedRepository.save(activity.getFeeds());
        return activityRepository.save(activity);
    }

    public Activity read(Activity activity)
    {
        return activity;
    }

    public List<Activity> readAll()
    {
        return activityRepository.findAll();
    }

    public Activity update(Activity activity)
    {
        Activity existingActivity = activityRepository.findByName(activity.getName());

        Preconditions.checkNotNull(existingActivity, "Activity couldnt found");
        existingActivity.setDuration(activity.getDuration());
        existingActivity.setName(activity.getName());
        return activityRepository.save(existingActivity);
    }

    public Boolean delete(Activity activity)
    {
        Activity existingActivity = activityRepository.findByName(activity.getName());

        if (existingActivity == null)
        {
            return false;
        }
        activityRepository.delete(existingActivity);
        return true;
    }

    public void createActivity(Plan plan, Activity activity)
    {
        //plan.saveOrUpdate(plan.addActivity(activity));
    }
}
