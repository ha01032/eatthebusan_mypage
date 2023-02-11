package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.PurchaseListDto2;
import com.java.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	
	@Query(value = "select count(*) FROM purchase WHERE member_code = :memberCode",
			 nativeQuery = true)
	int countpurchase(String memberCode);
	
	@Query(name= "find_purchaselistdto2",  nativeQuery = true)
	List<PurchaseListDto2> purchaseSelectAll(String memberCode);
}

