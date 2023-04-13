package com.eg.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eg.constants.AppConstants;
import com.eg.entity.Plan;
import com.eg.props.AppProperties;
import com.eg.service.PlanService;

@RestController
public class PlanRestController {
	// @Autowired
	private PlanService planService;

	/*
	 * @Autowired private AppProperties appProps;
	 */
	// constructor injection to prevent write Map<String, String> messages =
	// appProps.getMessages();
	private Map<String, String> messages;

	public PlanRestController(PlanService planService, AppProperties appProps) {
		this.planService = planService;
		this.messages = appProps.getMessages();

	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String responseMsg =AppConstants.EMPTY_STR;
		boolean isSaved = planService.savePlan(plan);
		if (isSaved) {

			responseMsg = messages.get(AppConstants.PLAN_SAVE_SUCC);

		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);

	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);

	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isUpdated = planService.updatePlan(plan);
		String msg =AppConstants.EMPTY_STR;

		if (isUpdated) {
			msg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isDeleted = planService.deletePlanById(planId);
		String msg = AppConstants.EMPTY_STR;

		if (isDeleted) {
			msg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		String msg = AppConstants.EMPTY_STR;

		if (isStatusChanged) {
			msg = messages.get(AppConstants.PLAN_CHANGE_STATUS_SUCC);
		} else {
			msg = messages.get(AppConstants.PLAN_CHANGE_STATUS_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

}
