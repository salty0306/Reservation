# Reservation
MySQL과 Java로 만드는 식당 온라인 예약 프로그램입니다.

To Do List
-
1. 무엇을 위한 온라인 예약 시스템인가 (식당, 영화, 병원, 숙소, 항공편 등) -> 캐치테이블 (식당 예약 앱)
2. 어떠한 기능들이 필요한가 (예약, 예약취소, 예약일 당일 알림기능 등)
3. ERD 설계
4. 파트 분배

Features
-
<손님>
필수
1. 회원가입
2. 식당검색 (메뉴)
3. 예약 (날짜 + 시간 + 인원수)
4. 예약기록 조회
5. 예약기록에서 재주문
6. 예약취소

부가적인 기능
1. 식당 즐겨찾기 기능
2. 식당 리뷰
3. 위치기반 식당 검색 (주변 식당)

------------------------------------------
<식당>
필수
1. 회원가입
2. 식당 등록 (식당정보: 식당이름, 주소, 메뉴, 가격, 리뷰 등)
3. 들어온 예약 열람 (예약일, 인원수, 예약자 성함 등)
4. 예약취소 -> 취소된 예약 update

부가적인 기능
