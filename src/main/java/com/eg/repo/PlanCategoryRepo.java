package com.eg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eg.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{
	

}
