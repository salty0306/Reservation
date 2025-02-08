package java_sql_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java_sql_project.domain.restaurant;
import java_sql_project.setting.database_connection;

public class restaurant_service {

	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstd;
	public static ResultSet rst;
	
	public static String[] sql_list = {
			"SELECT * FROM restaurants",
			"SELECT * FROM restaurants where (restaurant_name = ?)"
	};

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
	public restaurant get_restaurant_name(String restaurant_name_value) {
		conn=database_connection.conection();
		restaurant search=new restaurant();
		String sql=sql_list[1];
		
		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, restaurant_name_value);
			rst=pstd.executeQuery();
			rst.next();
            
			String restaurant_id=rst.getString("restaurant_id");
            String restaurant_name=rst.getString("restaurant_name");
            String owner_name=rst.getString("owner_name");
            String owner_pw=rst.getString("owner_pw");
            String res_location_state=rst.getString("res_location_state");
            String res_location_city=rst.getString("res_location_city");
            String description=rst.getString("description");
            
            search.setId(restaurant_id);
            search.setName(restaurant_name);
            search.setOwner_name(owner_name);
            search.setOwner_pw(owner_pw);
            search.setLocation_state(res_location_state);
            search.setLocation_city(res_location_city);
            search.setDescription(description);
            database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return search;
	}
	
	//식당 검색(식당 소유자 이름)(select 문)
	
	//식당 검색(위치(큰 도시))(select 문)
	
	//식당 검색(위치(작은 도시))(select 문)
}
