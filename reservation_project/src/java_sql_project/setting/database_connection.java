package java_sql_project.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database_connection {
	
	public static String classname="com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/cookdb?serverTimezone=UTC";
	public static String id="root";
	public static String pw="0000";
	public static Connection conn=null;
	
	public static Connection conection(){

		try {
			Class.forName(classname);
			System.out.println(url+" "+id+" "+pw);
			conn=DriverManager.getConnection(url, id, pw);
			
			if(conn!=null) System.out.println("DB 연결 완료!!!");
			else System.out.println("연결이 존재하지 않습니다.");
			
		}catch (Exception e) {
			System.out.println("DB 연결 실패!!!");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close() {
		try {
			if(conn!=null) {
				conn.close();
				System.out.println("DB 연결 종료");
			}else {
				System.out.println("DB 연결이 존재하지 않습니다!");
			}
			
		}catch (Exception e) {
			System.out.println("DB 접속 해제 중 예외 발생");
		}
	}
}
