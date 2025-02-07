package java_sql_project.service;

import java.util.ArrayList;
import java.util.List;

import java_sql_project.domain.reservation;

public class reservation_service {
	public static String[] sql_list;
	
	public reservation_service() {
		
	}
	private static reservation_service reservation_service_provider=new reservation_service();
	
	public static reservation_service getInstance() {
		return reservation_service_provider;
	}
	
	//예약 전체 목록 조회(select 문)
	public List<reservation> allreservation(){
		List<reservation> reservation_list=new ArrayList<>();
		return reservation_list;
	}
	
	//예약 검색(손님 이름)(select 문)
	public List<reservation> reservation_consumer(String user_name){
		List<reservation> reservation_list=new ArrayList<>();
		return reservation_list;
	}
	
	//예약 검색(식당 이름)(select 문)
	public List<reservation> reservation_restaurant(String restaurant_name){
		List<reservation> reservation_list=new ArrayList<>();
		return reservation_list;
	}
	
	//예약 입력(insert 문)
	public boolean reservation_register(reservation reserve) {
		
		
		return true;
	}
	
	//예약 삭제(시간 지난 것들)(delete 문)
	public boolean reservation_delete_auto() {
		return true;
	}
	
	//예약 삭제(식당이 자발적으로)(delete 문)
	public boolean reservation_delete_restaurant() {
		return true;
	}
	//예약 삭제(손님이 자발적으로)(delete 문)
	public boolean reservation_delete_consumer() {
		return true;
	}
}
