package java_sql_project.service;

public class restaurant_service {

	public restaurant_service() {
		
	}
	private static restaurant_service restaurant_service_provider=new restaurant_service();
	
	public static restaurant_service getInstance() {
		return restaurant_service_provider;
	}
	
	//식당 등록(insert 문)
	
	//식당 로그인(식당 이름, 비밀번호로 검색)(select 문)
	
	//식당 검색(음식 종류)(select 문)
	
	//식당 검색(식당 이름)(select 문)
	
	//식당 검색(식당 소유자 이름)(select 문)
	
	//식당 검색(위치(큰 도시))(select 문)
	
	//식당 검색(위치(작은 도시))(select 문)
}
