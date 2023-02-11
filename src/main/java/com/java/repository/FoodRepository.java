package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Food;
import com.java.entity.Purchase;

public interface FoodRepository extends JpaRepository<Food, Long> {
	

	

}

