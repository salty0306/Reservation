package java_sql_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java_sql_project.domain.consumer;
import java_sql_project.setting.database_connection;

public class consumer_service {
	
	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstd;
	public static ResultSet rst;
	
	public static String[] sql_list= {
			"SELECT COUNT(*) FROM Consumers",
			"INSERT INTO Consumers(consumer_id, consumer_name, consumer_pw, email, location_state, location_city) "
			+ "VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
			+ "consumer_name = VALUES(consumer_name), consumer_pw = VALUES(consumer_pw), email = VALUES(email), "
			+ "location_state = VALUES(location_state), location_city = VALUES(location_city)",
			"SELECT COUNT(*) FROM Consumers WHERE (consumer_name=?)",
			"SELECT * FROM Consumers WHERE (consumer_name=?) and (consumer_pw=?)",
			"SELECT * FROM Consumers",
			"SELECT * FROM Consumers where (consumer_id = ?)",
			"SELECT * FROM Consumers where (consumer_name = ?)"};
	
	public consumer_service() {
		
	}
	private static consumer_service user_service_provider=new consumer_service();
	
	public static consumer_service getInstance() {
		return user_service_provider;
	}
	
	//회원 전체 리스트 출력(select 문)
	public List<consumer> consumer_list(){
		Connection conn=database_connection.conection();
		List<consumer> user_list=new ArrayList<>();
		String userlistsql=sql_list[4];
		
		try {
			stmt=conn.createStatement();
			rst=stmt.executeQuery(userlistsql);
			while(rst.next()) {
	            consumer consumer_element=new consumer();
	            
				String consumer_id=rst.getString("consumer_id");
	            String consumer_name=rst.getString("consumer_name");
	            String consumer_pw=rst.getString("consumer_pw");
	            String email=rst.getString("email");
	            String location_state=rst.getString("location_state");
	            String location_city=rst.getString("location_city");
	            
	            System.out.println(consumer_id+" "+consumer_name+" "+consumer_pw
	            		+" "+email+" "+location_state+" "+location_city);
	            
	            consumer_element.setId(consumer_id);
	            consumer_element.setName(consumer_name);
	            consumer_element.setPw(consumer_pw);
	            consumer_element.setEmail(email);
	            consumer_element.setLocation_state(location_state);
	            consumer_element.setLocation_city(location_city);
	            user_list.add(consumer_element);
	            
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user_list;
	}
	
	//회원 가입(insert 문)
	public boolean insert_consumer(consumer user) {
		conn=database_connection.conection();
		String countsql=sql_list[0];
		String insertsql=sql_list[1];
		String isExist=sql_list[2];
		
		try {
			stmt=conn.createStatement();
			
			//같은 이름의 존재여부 확인
			pstd=conn.prepareStatement(isExist);
			pstd.setString(1, user.getName());
			rst=pstd.executeQuery();
			rst.next();
			int isEx=rst.getInt(1);
			if(isEx>0) {
				System.out.println("이미 존재하는 계정입니다.");
				return false;
			}
			
			//현재 손님 회원 수 출력
			rst=stmt.executeQuery(countsql);
			rst.next();
			int count=rst.getInt(1);
			System.out.println("현재 가입된 회원 수: "+count);

			//회원가입 진행
			pstd=conn.prepareStatement(insertsql);
			String userid=String.format("U%03d", count+1);
			user.setId(userid);
			pstd.setString(1, user.getId());
			pstd.setString(2, user.getName());
			pstd.setString(3, user.getPw());
			pstd.setString(4, user.getEmail());
			pstd.setString(5, user.getLocation_state());
			pstd.setString(6, user.getLocation_city());
			
			int insertcnt=pstd.executeUpdate();
			
			if(insertcnt>0) {
				database_connection.close();
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("회원가입 도중 오류 발생!!");
			e.printStackTrace();
			return false;
		}
		
	}
	
	//로그인, 이름과 비밀번호로 검색후 해당 회원 출력(select 문)
	public consumer login_consumer(String name, String pw) {
		Connection conn=database_connection.conection();
		String login_sql=sql_list[3];
		
		consumer login_user=new consumer();
		
		try {
			pstd=conn.prepareStatement(login_sql);
			pstd.setString(1, name);
			pstd.setString(2, pw);
			rst=pstd.executeQuery();
			rst.next();

			String consumer_id=rst.getString("consumer_id");
            String consumer_name=rst.getString("consumer_name");
            String consumer_pw=rst.getString("consumer_pw");
            String email=rst.getString("email");
            String location_state=rst.getString("location_state");
            String location_city=rst.getString("location_city");

            System.out.println(consumer_id+" "+consumer_name+" "+consumer_pw
            		+" "+email+" "+location_state+" "+location_city);
            
            login_user.setId(consumer_id);
            login_user.setName(consumer_name);
            login_user.setPw(consumer_pw);
            login_user.setEmail(email);
            login_user.setLocation_state(location_state);
            login_user.setLocation_city(location_city);
            System.out.println("환영합니다. "+login_user.getName()+" 님");
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login_user;
	}
	
	
	//회원 정보 출력, 아이디를 입력받고 해당 회원 출력(select 문)
	public consumer get_consumer(String userid) {
		consumer user_info=new consumer();
		String sql=sql_list[5];
		Connection conn=database_connection.conection();
		
		try {
			pstd=conn.prepareStatement(sql);
			pstd.setString(1, userid);
			rst=pstd.executeQuery();
			rst.next();

			String consumer_id=rst.getString("consumer_id");
            String consumer_name=rst.getString("consumer_name");
            String consumer_pw=rst.getString("consumer_pw");
            String email=rst.getString("email");
            String location_state=rst.getString("location_state");
            String location_city=rst.getString("location_city");
            
            user_info.setId(consumer_id);
            user_info.setName(consumer_name);
            user_info.setPw(consumer_pw);
            user_info.setEmail(email);
            user_info.setLocation_state(location_state);
            user_info.setLocation_city(location_city);
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("회원이 존재하지 않습니다.");
			e.printStackTrace();
		}
		
		return user_info;
	}
	
	//회원 정보 출력, 이름을 입력받고 해당 회원 출력(select 문)
	public consumer get_consumer_2(String username) {
			consumer user_info=new consumer();
			String sql=sql_list[6];
			Connection conn=database_connection.conection();
			
			try {
				pstd=conn.prepareStatement(sql);
				pstd.setString(1, username);
				rst=pstd.executeQuery();
				rst.next();

				String consumer_id=rst.getString("consumer_id");
	            String consumer_name=rst.getString("consumer_name");
	            String consumer_pw=rst.getString("consumer_pw");
	            String email=rst.getString("email");
	            String location_state=rst.getString("location_state");
	            String location_city=rst.getString("location_city");
	            
	            user_info.setId(consumer_id);
	            user_info.setName(consumer_name);
	            user_info.setPw(consumer_pw);
	            user_info.setEmail(email);
	            user_info.setLocation_state(location_state);
	            user_info.setLocation_city(location_city);
	            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("회원이 존재하지 않습니다.");
				e.printStackTrace();
			}
			
			return user_info;
		}
}
