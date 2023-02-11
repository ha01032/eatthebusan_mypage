package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Purchase;
import com.java.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	
	@Query(value = "select count(*) FROM review WHERE member_code = :memberCode",
			 nativeQuery = true)
	int countreview(String memberCode);
	

}

