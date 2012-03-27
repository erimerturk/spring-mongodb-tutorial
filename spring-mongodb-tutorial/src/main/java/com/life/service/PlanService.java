package com.life.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.life.domain.Plan;
import com.life.repository.ActivityRepository;
import com.life.repository.PlanRepository;

@Service
public class PlanService{

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	public Plan create(Plan plan) {
		return planRepository.save(plan);
	}
	
	public Plan read(Plan plan) {
		return planRepository.findById(plan.getId());
	}
	
	public List<Plan> readAll() {
		return planRepository.findAll();
	}
	
	public Plan update(Plan plan) {
	    Plan existingPlan = planRepository.findById(plan.getId());
	    Preconditions.checkNotNull(existingPlan, "Plan couldnt found");
	    existingPlan.setName(plan.getName());
	    existingPlan.setActivities(plan.getActivities());
	    activityRepository.save(plan.getActivities());
		return planRepository.save(existingPlan);
	}
	
	public Boolean delete(Plan plan) {
	    Plan existingPlan = planRepository.findById(plan.getId());
		
		if (existingPlan == null) {
			return false;
		}
		planRepository.delete(plan);
		return true;
	}
}
