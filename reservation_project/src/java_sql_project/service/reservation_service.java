package java_sql_project.service;

public class reservation_service {

	public reservation_service() {
		
	}
	private static reservation_service reservation_service_provider=new reservation_service();
	
	public static reservation_service getInstance() {
		return reservation_service_provider;
	}
	
	//예약 전체 목록 조회(select 문)
	
	//예약 검색(손님 이름)(select 문)
	
	//예약 검색(식당 이름)(select 문)
	
	//예약 입력(insert 문)
	
	//예약 삭제(시간 지난 것들)(delete 문)
	
	//예약 삭제(식당이 자발적으로)(delete 문)
	
	//예약 삭제(손님이 자발적으로)(delete 문)
	
}
