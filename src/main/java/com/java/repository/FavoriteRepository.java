package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.entity.Favorite;
import com.java.entity.Purchase;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	
	
	@Query(value = "select count(*) FROM favorite WHERE member_code = :memberCode",
			 nativeQuery = true)
	int countfavorite(String memberCode);
	
	@Query(value = "select * FROM favorite WHERE member_code = :memberCode",
			 nativeQuery = true)
	List<Favorite> findfavorite(String memberCode);
	

}

