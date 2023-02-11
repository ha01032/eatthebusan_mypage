package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.java.dto.CouponDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="coupon")
 
@Getter
@Setter
@ToString

public class Coupon {

	// 기본키 pk = not null + unique
    @Id 
    @Column(name="coupon_code")
    private String couponCode;       //상품 코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_code")
    private Food food;    
    
    @Column(name = "coupon_name", nullable = false, length = 500)
    private String couponName;
    
    @Column(name = "coupon_startdate", nullable = false)
    private Date couponStartdate;
    
    @Column(name = "coupon_enddate", nullable = false)
    private Date couponEnddate;

    @Column(name = "coupon_costori", nullable = false, length = 20)
    private int couponCostori;	
    
    @Column(name = "coupon_costsale", nullable = false, length = 3)
    private int couponCostsale;
    
    @Column(name = "coupon_salerate", nullable = false, length = 20)
	private int couponSalerate;
    
    
    @Column(name = "coupon_intro", length = 4000)
    private String couponIntro;
    
    @Column(name = "coupon_status", length = 20)
	private String couponStatus;
	 

}





