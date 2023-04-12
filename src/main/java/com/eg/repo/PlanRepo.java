package com.eg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
