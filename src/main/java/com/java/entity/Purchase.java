package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.java.dto.PurchaseListDto2;

import lombok.Getter;
import lombok.Setter;



@NamedNativeQuery(	
name = "find_purchaselistdto2",
query ="	SELECT * FROM	"+
"	(SELECT ROWNUM AS numb, coupon_code AS couponCode, coupon_name AS couponName, coupon_startdate AS couponStartDate, coupon_enddate AS couponEndDate, coupon_costori AS couponCostori, coupon_costsale AS couponCostsale, purchase_date AS purchaseDate, purchase_phone AS purchasePhone,  purchase_code AS purchaseCode, purchase_status AS purchaseStatus, image_name AS imageName, image_path AS imagePath, image_code AS imageCode FROM (	"+
"	SELECT o coupon_code, coupon_name, coupon_startdate, coupon_enddate, coupon_costori, coupon_costsale, purchase_date, purchase_phone, purchase_code, purchase_status, image_name, image_path, image_code FROM(	"+
"	SELECT purchase.coupon_code o, purchase.*, coupon.*, image.* FROM purchase	"+
"	INNER JOIN (coupon INNER JOIN image ON image.refer_code = coupon.coupon_code) ON purchase.coupon_code = coupon.coupon_code)	" +
"   WHERE  member_code = :memberCode ORDER BY purchase_date DESC) A) B ",
resultSetMapping = "purchaselistdto2"
)
@SqlResultSetMapping(
name = "purchaselistdto2",
classes = @ConstructorResult(
targetClass = PurchaseListDto2.class,
columns = {
		@ColumnResult(name = "numb", type =  int.class),
		@ColumnResult(name = "couponCode", type = String.class),
		@ColumnResult(name = "couponName", type =  String.class),
		@ColumnResult(name = "couponStartdate", type = String.class),
		@ColumnResult(name = "couponEndDate", type =  String.class),
		@ColumnResult(name = "couponCostori", type =  int.class),
		@ColumnResult(name = "couponCostsale", type =  int.class),
		@ColumnResult(name = "purchaseDate", type =  Date.class),
		@ColumnResult(name = "purchasePhone", type =  String.class),
		@ColumnResult(name = "purchaseCode", type =  String.class),
		@ColumnResult(name = "purchaseStatus", type =  String.class),
		@ColumnResult(name = "imageName", type =  String.class),
		@ColumnResult(name = "imagePath", type =  String.class),
		@ColumnResult(name = "imageCode", type =  String.class)
}
))

@Entity
@Table(name = "purchase")
@Getter 
@Setter
public class Purchase {

    @Id 
    @Column(name = "purchase_code", length = 10)
	private String purchaseCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_code")
    private Coupon coupon;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_code")
    private Member member;
    
    @Column(name = "purchase_phone", nullable = false, length = 20)
    private String purchasePhone;
    
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;
    
    @Column(name = "purchase_cost", nullable = false, length = 20)
    private int purchaseCost;
    
    @Column(name = "purchase_status", nullable = false, length = 20)
    private String purchaseStatus;


}