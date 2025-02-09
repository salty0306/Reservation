package java_sql_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java_sql_project.domain.restaurant;
import java_sql_project.setting.database_connection;

public class restaurant_service {

	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstd;
	public static ResultSet rst;
	
	public static String[] sql_list = {
			"SELECT * FROM restaurants",
			"SELECT * FROM restaurants where (restaurant_name = ?)",
			"SELECT * FROM restaurants where (description = ?)",
			"SELECT * FROM restaurants where (owner_name = ?)",
			"SELECT * FROM restaurants where (res_location_state = ?)",
			"SELECT * FROM restaurants where (res_location_city = ?)",
			"SELECT * FROM restaurants where (restaurant_name = ?) AND (owner_pw = ?)",
			"INSERT INTO restaurants VALUES ( ? , ? , ? , ? , ? , ? , ? )",
			"DELETE FROM restaurants WHERE (restaurant_name = ?) AND (owner_pw = ?)",
			"SELECT COUNT(*) FROM restaurants"
	};

	public restaurant_service() {
		
	}
	private static restaurant_service restaurant_service_provider=new restaurant_service();
	
	public static restaurant_service getInstance() {
		return restaurant_service_provider;
	}
	
	public int restaurant_count() {
		Connection conn=database_connection.conection();
		String sql=sql_list[9];
		int count=0;

		try {
			stmt=conn.createStatement();
			rst=stmt.executeQuery(sql);
			rst.next();
			count=rst.getInt(1);
			database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL문 실행 도중 오류 발생");
		}
		
		return count;
	}
	//식당 전체 목록 출력
	public List<restaurant> restaurant_list(){
		Connection conn=database_connection.conection();
		List<restaurant> list=new ArrayList<>();
		String sql=sql_list[0];
		
		try {
			stmt=conn.createStatement();
			rst=stmt.executeQuery(sql);
			while(rst.next()) {
				
				restaurant element=new restaurant();

				String restaurant_id=rst.getString("restaurant_id");
	            String restaurant_name=rst.getString("restaurant_name");
	            String owner_name=rst.getString("owner_name");
	            String owner_pw=rst.getString("owner_pw");
	            String res_location_state=rst.getString("res_location_state");
	            String res_location_city=rst.getString("res_location_city");
	            String description=rst.getString("description");

	            element.setId(restaurant_id);
	            element.setName(restaurant_name);
	            element.setOwner_name(owner_name);
	            element.setOwner_pw(owner_pw);
	            element.setLocation_state(res_location_state);
	            element.setLocation_city(res_location_city);
	            element.setDescription(description);
				list.add(element);
			}

	        database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	//식당 등록(insert 문)(같은 이름의 식당이 존재하는지, 일부 속성값이 빠졌는지 여부를 검증)
	public boolean sign_restaurant(restaurant new_restaurant,int count) {
		Connection conn=database_connection.conection();
		String sql=sql_list[7];
		
		try {
			if(new_restaurant.getName().length()==0||new_restaurant.getOwner_name().length()==0||
					new_restaurant.getOwner_pw().length()==0||new_restaurant.getLocation_state().length()==0||
					new_restaurant.getLocation_city().length()==0||new_restaurant.getDescription().length()==0) {
				System.out.println("일부 속성값이 비어있습니다.");
				return false;
			}
			
			if(get_restaurant_name(new_restaurant.getName())!=null) {
				System.out.println("이미 존재하는 식당 이름입니다.");
				return false;
			}
			
			String restaurant_id=String.format("R%03d", count+1);
			new_restaurant.setId(restaurant_id);
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, new_restaurant.getId());
			pstd.setString(2, new_restaurant.getName());
			pstd.setString(3, new_restaurant.getOwner_name());
			pstd.setString(4, new_restaurant.getOwner_pw());
			pstd.setString(5, new_restaurant.getLocation_state());
			pstd.setString(6, new_restaurant.getLocation_city());
			pstd.setString(7, new_restaurant.getDescription());
			
			int insertcnt=pstd.executeUpdate();
			if(insertcnt>0) {
				System.out.println(insertcnt+" 개의 데이터가 식당 테이블에 입력되었습니다.");
				return true;
			}else {
				System.out.println("INSERT 문이 비정상적으로 실행되었습니다.");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//식당 로그인(식당 이름, 비밀번호로 검색)(select 문)
	public restaurant login_restaurant(String name, String pw) {
		Connection conn=database_connection.conection();
		restaurant restaurant_user=new restaurant();
		String sql=sql_list[6];

		try {
			
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, name);
			pstd.setString(2, pw);
			rst=pstd.executeQuery();
			rst.next();
			String restaurant_id=rst.getString("restaurant_id");
	        String restaurant_name=rst.getString("restaurant_name");
	        String owner_name=rst.getString("owner_name");
	        String owner_pw=rst.getString("owner_pw");
	        String res_location_state=rst.getString("res_location_state");
	        String res_location_city=rst.getString("res_location_city");
	        String description=rst.getString("description");

	        restaurant_user.setId(restaurant_id);
	        restaurant_user.setName(restaurant_name);
	        restaurant_user.setOwner_name(owner_name);
	        restaurant_user.setOwner_pw(owner_pw);
	        restaurant_user.setLocation_state(res_location_state);
	        restaurant_user.setLocation_city(res_location_city);
	        restaurant_user.setDescription(description);

	        database_connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return restaurant_user;
	}
	//식당 검색(음식 종류)(select 문)
	public List<restaurant> get_restaurant_type(String type){
		Connection conn=database_connection.conection();
		List<restaurant> list=new ArrayList<>();
		String sql=sql_list[2];
		
		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, type);
			rst=pstd.executeQuery();
			while(rst.next()) {
				
				restaurant element=new restaurant();

				String restaurant_id=rst.getString("restaurant_id");
	            String restaurant_name=rst.getString("restaurant_name");
	            String owner_name=rst.getString("owner_name");
	            String owner_pw=rst.getString("owner_pw");
	            String res_location_state=rst.getString("res_location_state");
	            String res_location_city=rst.getString("res_location_city");
	            String description=rst.getString("description");

	            element.setId(restaurant_id);
	            element.setName(restaurant_name);
	            element.setOwner_name(owner_name);
	            element.setOwner_pw(owner_pw);
	            element.setLocation_state(res_location_state);
	            element.setLocation_city(res_location_city);
	            element.setDescription(description);
				list.add(element);
			}

	        database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	//식당 검색(식당 이름)(select 문)
	public restaurant get_restaurant_name(String restaurant_name_value) {
		conn=database_connection.conection();
		String sql=sql_list[1];
		restaurant search=new restaurant();
		
		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, restaurant_name_value);
			rst=pstd.executeQuery();
			if(rst.next()) {
			
				String restaurant_id=rst.getString("restaurant_id");
				String restaurant_name=rst.getString("restaurant_name");
				String owner_name=rst.getString("owner_name");

				System.out.println(owner_name);
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
				return search;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//식당 검색(식당 소유자 이름)(select 문)
	public List<restaurant> get_restaurant_owner(String owner_name){
		Connection conn=database_connection.conection();
		List<restaurant> list=new ArrayList<>();
		String sql=sql_list[3];

		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, owner_name);
			rst=pstd.executeQuery();
			while(rst.next()) {
				
				restaurant element=new restaurant();

				String restaurant_id=rst.getString("restaurant_id");
	            String restaurant_name=rst.getString("restaurant_name");
	            String owner_name_value=rst.getString("owner_name");
	            String owner_pw=rst.getString("owner_pw");
	            String res_location_state=rst.getString("res_location_state");
	            String res_location_city=rst.getString("res_location_city");
	            String description=rst.getString("description");

	            element.setId(restaurant_id);
	            element.setName(restaurant_name);
	            element.setOwner_name(owner_name_value);
	            element.setOwner_pw(owner_pw);
	            element.setLocation_state(res_location_state);
	            element.setLocation_city(res_location_city);
	            element.setDescription(description);
				list.add(element);
			}

	        database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	//식당 검색(위치(큰 도시))(select 문)
	public List<restaurant> get_restaurant_state(String state){
		Connection conn=database_connection.conection();
		List<restaurant> list=new ArrayList<>();
		String sql=sql_list[4];

		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, state);
			rst=pstd.executeQuery();
			while(rst.next()) {
				
				restaurant element=new restaurant();

				String restaurant_id=rst.getString("restaurant_id");
	            String restaurant_name=rst.getString("restaurant_name");
	            String owner_name_value=rst.getString("owner_name");
	            String owner_pw=rst.getString("owner_pw");
	            String res_location_state=rst.getString("res_location_state");
	            String res_location_city=rst.getString("res_location_city");
	            String description=rst.getString("description");

	            element.setId(restaurant_id);
	            element.setName(restaurant_name);
	            element.setOwner_name(owner_name_value);
	            element.setOwner_pw(owner_pw);
	            element.setLocation_state(res_location_state);
	            element.setLocation_city(res_location_city);
	            element.setDescription(description);
				list.add(element);
			}

	        database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	//식당 검색(위치(작은 도시))(select 문)
	public List<restaurant> get_restaurant_city(String city){
		Connection conn=database_connection.conection();
		List<restaurant> list=new ArrayList<>();
		String sql=sql_list[5];

		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, city);
			rst=pstd.executeQuery();
			while(rst.next()) {
				
				restaurant element=new restaurant();

				String restaurant_id=rst.getString("restaurant_id");
	            String restaurant_name=rst.getString("restaurant_name");
	            String owner_name_value=rst.getString("owner_name");
	            String owner_pw=rst.getString("owner_pw");
	            String res_location_state=rst.getString("res_location_state");
	            String res_location_city=rst.getString("res_location_city");
	            String description=rst.getString("description");

	            element.setId(restaurant_id);
	            element.setName(restaurant_name);
	            element.setOwner_name(owner_name_value);
	            element.setOwner_pw(owner_pw);
	            element.setLocation_state(res_location_state);
	            element.setLocation_city(res_location_city);
	            element.setDescription(description);
				list.add(element);
			}

	        database_connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
