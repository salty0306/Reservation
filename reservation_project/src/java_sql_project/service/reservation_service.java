package java_sql_project.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java_sql_project.domain.reservation;
import java_sql_project.domain.reservation.ReservationStatus;
import java_sql_project.setting.database_connection;

public class reservation_service {
	
	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstd;
	public static ResultSet rst;
	
	public static String[] sql_list= {
			"SELECT * FROM Reservations",
			"SELECT * FROM reservations a inner join consumers b on a.consumer_id =b.consumer_id where (b.consumer_name = ?)",
			"SELECT * FROM reservations a inner join restaurants b on a.restaurant_id =b.restaurant_id where (b.restaurant_name = ?)",
			"SELECT * FROM Reservations where (consumer_id = ?) and (restaurant_id = ?)and (reservation_date = ?)",
			"SELECT * FROM Reservations where (consumer_id = ?) and (reservation_date = ?) and (reservation_time = ?)",
			"INSERT INTO Reservations VALUES (?, ?, ?, ?, ?, ?, 'confirmed', NOW())",
			"DELETE FROM Reservations WHERE CONCAT(reservation_date, ' ', reservation_time) < NOW()",
			"DELETE FROM Reservations where (consumer_id = ?) and (restaurant_id = ?) and (reservation_date = ?)",
			"DELETE FROM Reservations where ()",
			"SELECT COUNT(*) FROM Reservations"
	};
	
	public reservation_service() {
		
	}
	private static reservation_service reservation_service_provider=new reservation_service();
	
	public static reservation_service getInstance() {
		return reservation_service_provider;
	}
	
	//예약 전체 목록 조회(select 문)
	public List<reservation> allreservation(){
		Connection conn=database_connection.conection();
		List<reservation> reservation_list=new ArrayList<>();
		String sql=sql_list[0];
		
		try {
			stmt=conn.createStatement();
			rst=stmt.executeQuery(sql);
			while(rst.next()) {
				reservation reservationobj=new reservation();
				
				String reservation_id=rst.getString(1);
				String consumer_id=rst.getString(2);
				String restaurant_id=rst.getString(3);
				java.sql.Date date=rst.getDate(4);
				java.sql.Time time=rst.getTime(5);
				int num=rst.getInt(6);
				String status=rst.getString(7);
				java.sql.Timestamp create_at=rst.getTimestamp(8);
				

				System.out.println("예약 아이디: "+reservation_id+", 예약 식당 아이디: "+restaurant_id+", 예약 손님 아이디: "+consumer_id);
				
				reservationobj.setId(reservation_id);
				reservationobj.setConsumer_id(consumer_id);
				reservationobj.setRestaurant_id(restaurant_id);
				reservationobj.setReservation_date(date);
				reservationobj.setReservation_time(time);
				reservationobj.setNumber_of_people(num);
				reservationobj.setStatus(reservation.ReservationStatus.valueOf(status));
				reservationobj.setCreated_at(create_at);
				
				reservation_list.add(reservationobj);
			}

			database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("예약 전체 목록을 불러오는 것에 실패 하였습니다.");
			e.printStackTrace();
		}
		
		return reservation_list;
	}
	
	//예약 검색(손님 이름)(select 문)
	public List<reservation> reservation_consumer(String user_name){
		Connection conn=database_connection.conection();
		List<reservation> reservation_list=new ArrayList<>();
		String sql=sql_list[1];

		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, user_name);
			rst=pstd.executeQuery();
			
			while(rst.next()) {
				reservation reservationobj=new reservation();
				
				String reservation_id=rst.getString(1);
				String consumer_id=rst.getString(2);
				String restaurant_id=rst.getString(3);
				java.sql.Date date=rst.getDate(4);
				java.sql.Time time=rst.getTime(5);
				int num=rst.getInt(6);
				String status=rst.getString(7);
				java.sql.Timestamp create_at=rst.getTimestamp(8);

				System.out.println("예약 아이디: "+reservation_id+", 예약 식당 아이디: "+restaurant_id+", 예약 손님 아이디: "+consumer_id);
				
				reservationobj.setId(reservation_id);
				reservationobj.setConsumer_id(consumer_id);
				reservationobj.setRestaurant_id(restaurant_id);
				reservationobj.setReservation_date(date);
				reservationobj.setReservation_time(time);
				reservationobj.setNumber_of_people(num);
				reservationobj.setStatus(reservation.ReservationStatus.valueOf(status));
				reservationobj.setCreated_at(create_at);
				
				reservation_list.add(reservationobj);
			}

			database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(user_name+" 손님의 예약 목록을 불러오는 것에 실패 하였습니다.");
			e.printStackTrace();
		}
		
		return reservation_list;
	}
	
	//예약 검색(식당 이름)(select 문)
	public List<reservation> reservation_restaurant(String restaurant_name){
		
		Connection conn=database_connection.conection();
		List<reservation> reservation_list=new ArrayList<>();
		String sql=sql_list[2];

		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, restaurant_name);
			rst=pstd.executeQuery();
			
			while(rst.next()) {
				reservation reservationobj=new reservation();
				
				String reservation_id=rst.getString(1);
				String consumer_id=rst.getString(2);
				String restaurant_id=rst.getString(3);
				java.sql.Date date=rst.getDate(4);
				java.sql.Time time=rst.getTime(5);
				int num=rst.getInt(6);
				String status=rst.getString(7);
				java.sql.Timestamp create_at=rst.getTimestamp(8);
				
				System.out.println("예약 아이디: "+reservation_id+", 예약 식당 아이디: "+restaurant_id+", 예약 손님 아이디: "+consumer_id);
				
				reservationobj.setId(reservation_id);
				reservationobj.setConsumer_id(consumer_id);
				reservationobj.setRestaurant_id(restaurant_id);
				reservationobj.setReservation_date(date);
				reservationobj.setReservation_time(time);
				reservationobj.setNumber_of_people(num);
				reservationobj.setStatus(reservation.ReservationStatus.valueOf(status));
				reservationobj.setCreated_at(create_at);
				
				reservation_list.add(reservationobj);
			}
			database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(restaurant_name+" 식당의 예약 목록을 불러오는 것에 실패 하였습니다.");
			e.printStackTrace();
		}
		
		return reservation_list;
	}
	
	//예약 입력(insert 문)(예약한 날짜에 같은 식당에 예약한 기록이 있는지, 같은 시간에 다른 식당에 예약되었는지 등 다양한 조건 검사)
	public boolean reservation_register(reservation reserve,String restarant_name_value) {

		restaurant_service sub_service=restaurant_service.getInstance();
		String restaurant_id=sub_service.get_restaurant_name(restarant_name_value).getId();
		reserve.setRestaurant_id(restaurant_id);
		
		Connection conn=database_connection.conection();
		
		String sub_sql=sql_list[3];
		String sub_sql_2=sql_list[4];
		String sub_sql_3=sql_list[9];
		String sql=sql_list[5];

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        
		try {
			//이전 날짜에 예약하면 안된다!
			if(!nowDate.equals(reserve.getReservation_date().toLocalDate())
				&&!nowDate.isBefore(reserve.getReservation_date().toLocalDate())){
				return false;
				
			}else if(nowDate.equals(reserve.getReservation_date().toLocalDate())&&
					!nowTime.isBefore(reserve.getReservation_time().toLocalTime())) {
				return false;
			}
			
			pstd=conn.prepareStatement(sub_sql);
			pstd.setString(1, reserve.getConsumer_id());
			pstd.setString(2, reserve.getRestaurant_id());
			pstd.setDate(3, reserve.getReservation_date());
			rst=pstd.executeQuery();
			
			if(rst.getFetchSize()>0) {
				System.out.println("같은 날짜에 같은 식당에 두번 예약하였습니다.");
				return false;
			}
			pstd=conn.prepareStatement(sub_sql_2);
			pstd.setString(1, reserve.getConsumer_id());
			pstd.setDate(2, reserve.getReservation_date());
			pstd.setTime(3, reserve.getReservation_time());
			rst=pstd.executeQuery();
			
			if(rst.getFetchSize()>0) {
				System.out.println("이미 중복되는 예약이 존재합니다.");
				return false;
			}
			
			//"INSERT INTO Reservations VALUES (?, ?, ?, ?, ?, ?, ?)"
			stmt=conn.createStatement();
			rst=stmt.executeQuery(sub_sql_3);
			rst.next();
			int count=rst.getInt(1);
			System.out.println("현재 저장된 예약 수: "+count);
			
			pstd=conn.prepareStatement(sql);
			String reservation_id=String.format("RE%03d", count+1);
			reserve.setId(reservation_id);

			pstd.setString(1, reserve.getId());
			pstd.setString(2, reserve.getConsumer_id());
			pstd.setString(3, reserve.getRestaurant_id());
			pstd.setDate(4, reserve.getReservation_date());
			pstd.setTime(5, reserve.getReservation_time());
			pstd.setInt(6, reserve.getNumber_of_people());
			
			int insertcnt=pstd.executeUpdate();
			if(insertcnt>0) {
				System.out.println("예약 아이디: "+reservation_id
						+", 예약 식당 아이디: "+reserve.getRestaurant_id()
						+", 예약 손님 아이디: "+reserve.getConsumer_id());
				database_connection.close();
				return true;
			}else {
				System.out.println("예약이 처리되지 않았습니다.");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL 문 오류로 예약이 처리되지 않았습니다");
			return false;
		}
		
		
	}
	
	//예약 삭제(시간 지난 것들)(delete 문)
	public boolean reservation_delete_auto() {
		return true;
	}
	
	//예약 삭제(손님, 식당이 자발적으로)(delete 문)
	public boolean reservation_delete_consumer(String restaurant_id, String user_id, String reservation_date) {
		Connection conn=database_connection.conection();
		String sql=sql_list[7];
		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, user_id);
			pstd.setString(2, restaurant_id);
			pstd.setDate(3, Date.valueOf(reservation_date));
			
			int deletenum=pstd.executeUpdate();
			if(deletenum>0) {
				System.out.println(deletenum+" 건의 예약이 삭제되었습니다.");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
