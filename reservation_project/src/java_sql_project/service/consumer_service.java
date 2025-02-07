package java_sql_project.service;

import java.util.ArrayList;
import java.util.List;

import java_sql_project.domain.consumer;

public class consumer_service {
	
	public consumer_service() {
		
	}
	private static consumer_service user_service_provider=new consumer_service();
	
	public static consumer_service getInstance() {
		return user_service_provider;
	}
	
	//회원 전체 리스트 출력(select 문)
	public List<consumer> consumer_list(){
		List<consumer> user_list=new ArrayList<>();
		
		return user_list;
	}
	
	//회원 가입(insert 문)
	public void insert_consumer(consumer user) {
		
	}
	
	//로그인, 이름과 비밀번호로 검색후 해당 회원 출력(select 문)
	public consumer login_consumer(String name, String id) {
		consumer login_user=new consumer();
		
		return login_user;
	}
	
	//회원 정보 출력,이름과 아이디를 입력받고 해당 회원 출력(select 문)
	
}
