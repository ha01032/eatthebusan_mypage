2302111134
마이페이지 오류 해결

1)서버 실행 안되는 오류 : ByMemberCode로 메소드명을 하면 오류가 뜨는거같음
Repository 5개에 ByMemberCode가 붙은 메서드들 다 메서드명을 수정하고
nativequery로 변경 후, MemberServiceImp도 그것에 맞게 다 수정 => 실행됨
(다행히 Entity는 안바꿔도 됐음)

2)마이페이지를 누르니 안들어가짐
마이페이지 뷰가 없어서 그럼
MemberServiceImp에 memberMypage 메소드 마지막에
mav.setViewName("member/myPage.tiles"); 넣고,
myPage.jsp파일 views/member폴더에 넣음 

3) ORA-00900: invalid SQL statement
이런문제가 자주 발생하는 이유는 오타 아니면 SQL문맨뒤에 세미콜론(;)이 붙어있는경우이다.
역시나, member와 purchase의 query문에 ;붙음 > ;지움
(java 쿼리문엔 세미클론을 붙이지 않는다 : 주의)

이러니까 DB 비어있는 마이페이지 뷰는 정상적으로 뜸
하지만, legacy파일로 DB에 4가지 값들을 다 넣은뒤에 실행시키니 또 조회에서 오류들이 발생

4)memberservice : nullpoint exception 발생
foodRepository.findfoodlist를 getMyfavorite처럼 nativequery/Dto매핑 방식으로 변경
(반복문 구조로 foodlist에 넣는것이 null오류를 발생시키는 듯)


5)memberRepository getMyReview 메소드 List<MemberReviewDto>로 받아야하는데, 
List<FoodReviewDto>로 받고있어서 수정
>받는 controller도 수정

6)Property [couponStartdate] not found on type [com.java.dto.PurchaseListDto2]
Purchase에 query문을 원본 마이바티스 'purchasemapper'의 purchaselistAll이 아닌
purchaselist로 들고와야함.
purchaselist에 맞게 쿼리문과 @ColumnResult들 PurchaseListDto2 전부 수정

마이페이지 조회 잘됨
