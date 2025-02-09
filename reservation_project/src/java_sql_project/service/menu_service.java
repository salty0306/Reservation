package java_sql_project.service;

public class menu_service {
	public static String[] sql_list = {
			
	};

	public menu_service() {
		
	}
	private static menu_service menu_service_provider=new menu_service();
	
	public static menu_service getInstance() {
		return menu_service_provider;
	}
	/*
	 * 식당 아이디와 메뉴 이름을 입력 받고 해당 메뉴를 출력하는 select 문
	 */
	
	//메뉴 전체 목록 조회(select 문)
	
	//메뉴 등록(insert 문)
	
	//메뉴 검색(식당 이름)(select 문)
	
	//메뉴 검색(종류)(select 문)
	
	//메뉴 삭제(식당 아이디, 메뉴 이름)(delete 문)
	
	
}
