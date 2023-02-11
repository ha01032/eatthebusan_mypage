package com.java.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.java.dto.FoodDto;
import com.java.dto.FoodReviewDto;
import com.java.dto.MemberDto;
import com.java.dto.MemberFavoriteDto;
import com.java.dto.MemberReviewDto;
import com.java.dto.PurchaseListDto2;
import com.java.entity.Food;
import com.java.entity.Member;
import com.java.repository.FavoriteRepository;
import com.java.repository.FoodRepository;
//import com.java.member.dto.MemberFavoriteDto;
//import com.java.purchase.dao.PurchaseDao;
//import com.java.purchase.dto.PurchaseListDto;
//import com.java.review.dto.ReviewDto;
//import com.java.aop.JejuAspect;
//import com.java.coupon.dto.CouponDto;
//import com.java.food.dao.FoodDao;
//import com.java.food.dto.FoodDto;
//import com.java.food.dto.FoodReviewDto;
import com.java.repository.MemberRepository;
import com.java.repository.PurchaseRepository;
import com.java.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImp implements MemberService{
	
	@Autowired
	private final MemberRepository memberRepository;
	@Autowired
	private final PurchaseRepository purchaseRepository;
	
	@Autowired
	private final ReviewRepository reviewRepository;
	@Autowired
	private final FavoriteRepository favoriteRepository;
	
	@Autowired
	private final FoodRepository foodRepository;
	
	//@Autowired
	//private PurchaseDao purchaseDao;

	
	// 이메일 로그인 성공 과정
	@Override
	public void memberMailLoginOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		String memberPwd=request.getParameter("pwd");
		String memberMail=request.getParameter("mail");
		
		int check = memberRepository.countByMemberMailAndMemberPwd(memberMail,memberPwd);
		Member member = null;
		MemberDto memberDto = null;
		if (check == 1) {
			member = memberRepository.findByMemberMail(memberMail);
			memberDto = MemberDto.of(member);
			//JejuAspect.logger.info(JejuAspect.logMsg + memberDto.toString());
		}
		
		//JejuAspect.logger.info(JejuAspect.logMsg + check);
		mav.addObject("check", check);
		mav.addObject("memberDto", memberDto);
		mav.setViewName("member/mailLoginOk.tiles");
	}
	
	
	// 마이페이지	
	@SuppressWarnings("null")
	@Override
		public void memberMypage(ModelAndView mav) {
			Map<String, Object> map=mav.getModelMap();
			HttpServletRequest request=(HttpServletRequest) map.get("request");
			HttpSession session = request.getSession();
			String memberCode = (String) session.getAttribute("memberCode");
			
			int couponCount = purchaseRepository.countpurchase(memberCode);
			
			int reviewCount = reviewRepository.countreview(memberCode);
			int favoriteCount = favoriteRepository.countfavorite(memberCode);
			mav.addObject("couponCount", couponCount);
			mav.addObject("reviewCount", reviewCount);
			mav.addObject("favoriteCount", favoriteCount);
			
//			JejuAspect.logger.info(JejuAspect.logMsg + memberCode);
			// 개인정보를 보기 위한
			 
			Member member= memberRepository.findmember(memberCode);
			MemberDto memberDto = MemberDto.of(member);
//			JejuAspect.logger.info(JejuAspect.logMsg + memberDto.toString());
			mav.addObject("memberDto", memberDto);
			
			// 내가 등록한 식당 리스트를 보기 위한 
			//List<FoodDto> foodList = memberRepository.getMyFood(memberCode);
			
			 
			List<FoodDto> foodList = memberRepository.getMyFood(memberCode);
			
			
//			JejuAspect.logger.info(JejuAspect.logMsg + foodList.size());
//			JejuAspect.logger.info(JejuAspect.logMsg + foodList.toString());
			mav.addObject("foodList", foodList);
			
			// 가고싶다
			//List<MemberFavoriteDto> favoriteList = memberDao.getMyFavorite(memberCode);
			List<MemberFavoriteDto> favoriteList = memberRepository.getMyFavorite(memberCode);
					
			//JejuAspect.logger.info(JejuAspect.logMsg + favoriteList.size());
			//JejuAspect.logger.info(JejuAspect.logMsg + favoriteList.toString());
			mav.addObject("favoriteList", favoriteList);
			
			// 리뷰
			List<MemberReviewDto> reviewList = memberRepository.getMyReview(memberCode);
			//JejuAspect.logger.info(JejuAspect.logMsg + reviewList.size());
			//JejuAspect.logger.info(JejuAspect.logMsg + reviewList.toString());
			mav.addObject("reviewList",reviewList);
			
			// EAT딜
			List<PurchaseListDto2> couponList = purchaseRepository.purchaseSelectAll(memberCode);
			//List<CouponDto> couponList = memberDao.getMyCoupon(memberCode);
			//JejuAspect.logger.info(JejuAspect.logMsg + couponList.size());
			//JejuAspect.logger.info(JejuAspect.logMsg + couponList.toString());
			mav.addObject("couponList", couponList);
			mav.setViewName("member/myPage.tiles");
			
	}
	
	
	
}



