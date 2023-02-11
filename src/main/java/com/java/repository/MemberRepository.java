package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.dto.FoodDto;
import com.java.dto.FoodReviewDto;
import com.java.dto.MemberFavoriteDto;
import com.java.dto.MemberReviewDto;
import com.java.entity.Food;
import com.java.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	int countByMemberMailAndMemberPwd(String memberMail, String memberPwd);
	
	Member findByMemberMail(String memberMail);
	
	@Query(value = "select * FROM member WHERE member_code = :memberCode",
			 nativeQuery = true)	
	Member findmember(String memberCode);
	
	@Query(value = "select count(*) FROM member WHERE member_code = :memberCode",
			 nativeQuery = true)
	int countmember(String memberCode);
	
	@Query(name = "find_fooddto", nativeQuery = true)
	List<FoodDto> getMyFood(String memberCode);

	
	@Query(name= "find_memberfavoritedto",  nativeQuery = true)
	List<MemberFavoriteDto> getMyFavorite(String memberCode);
	
	
	@Query(name= "find_memberreviewdto",  nativeQuery = true)
	List<MemberReviewDto> getMyReview(String memberCode);
	

}

