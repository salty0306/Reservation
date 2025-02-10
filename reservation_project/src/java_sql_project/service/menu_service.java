package java_sql_project.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java_sql_project.domain.menu;
import java_sql_project.setting.database_connection;

public class menu_service {
	
	public static Connection conn;
	public static Statement stmt;
	public static PreparedStatement pstd;
	public static ResultSet rst;
	
	public static String[] sql_list = {
			"select * from menus",
			"select * from menus m inner join restaurants r on m.restaurant_id=r.restaurant_id where (r.restaurant_name = ?)",
			"SELECT * FROM Menus WHERE restaurant_id = ? AND menu_name = ?",
			"INSERT INTO Menus(menu_id, restaurant_id, menu_name, price, description) VALUES(?,?,?,?,?)",
			"SELECT * FROM Menus WHERE restaurant_id = ?",
	        "SELECT * FROM Menus WHERE menu_name LIKE ?",
	        "DELETE FROM Menus WHERE menu_id = ?",
			"SELECT COUNT(*) FROM Menus"
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
	
	public int menu_count() {
		Connection conn = database_connection.conection();
		String sql = sql_list[7];
		int count = 0;

		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if(rst.next()) {
				count = rst.getInt(1);
			}
			database_connection.close();
		} catch (SQLException e) {
			System.out.println("메뉴 전체 목록 조회 실패.");
			e.printStackTrace();
		}
		return count;
	}


	// 모든 메뉴 목록 조회
    public List<menu> listAllMenus() {
        List<menu> menus = new ArrayList<>();
		String sql=sql_list[0];

        try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while(rst.next()) {
				menu menuobj = new menu();
				menuobj.setId(rst.getString("menu_id"));	
				menuobj.setRestaurant_id(rst.getString("restaurant_id"));
				menuobj.setName(rst.getString("menu_name"));
				menuobj.setPrice(rst.getInt("price"));
				menuobj.setDescription(rst.getString("description"));
				menus.add(menuobj);

			}
			database_connection.close();
        } catch (SQLException e) {
			System.out.println("메뉴 전체 목록 조회 실패.");
            e.printStackTrace();
        }

        return menus;
    }

	
	//메뉴 등록(insert 문)
	public boolean insertMenu(menu menu, int count) {

		try {
			String menu_id = String.format("M%03d", count + 1);
			menu.setId(menu_id);
			conn = database_connection.conection(); // 올바른 메소드 이름을 사용하여 데이터베이스 연결
			pstd = conn.prepareStatement(sql_list[3]);
			pstd.setString(1, menu.getId());
			pstd.setString(2, menu.getRestaurant_id());
			pstd.setString(3, menu.getName());
			pstd.setInt(4, menu.getPrice());
			pstd.setString(5, menu.getDescription());

			int result = pstd.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.err.println("SQL 실행 오류: " + e.getMessage());
			return false;
		} finally {
			if (pstd != null) try { pstd.close(); } catch (SQLException e) { e.printStackTrace(); }
			if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}

	
	//메뉴 검색(식당 아이디)(select 문)
	public List<menu> getMenusByRestaurantId(String restaurantId) {
        List<menu> menus = new ArrayList<>();
        try {
            conn = database_connection.conection(); 
            String sql = sql_list[4];
            pstd = conn.prepareStatement(sql);
            pstd.setString(1, restaurantId);
            rst = pstd.executeQuery();

            while (rst.next()) {
                menu m = new menu();
                m.setId(rst.getString("menu_id"));
                m.setRestaurant_id(rst.getString("restaurant_id"));
                m.setName(rst.getString("menu_name"));
                m.setPrice(rst.getInt("price"));
                m.setDescription(rst.getString("description"));
                menus.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rst != null) rst.close();
                if (pstd != null) pstd.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return menus;
    }

    // 특정 식당 ID와 메뉴 이름으로 메뉴 항목 검색
    public menu getMenu(String restaurantId, String menuName) {
        menu menu = new menu(); // 메뉴 객체 초기화

        try {
            pstd = conn.prepareStatement(sql_list[2]);
            pstd.setString(1, restaurantId);
            pstd.setString(2, menuName);
            rst = pstd.executeQuery();

            if (rst.next()) {
                menu.setId(rst.getString("menu_id"));
                menu.setRestaurant_id(rst.getString("restaurant_id"));
                menu.setName(rst.getString("menu_name"));
                menu.setPrice(rst.getInt("price"));
                menu.setDescription(rst.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database_connection.close();
        }

        return menu;
    }
    // 메뉴 이름으로 메뉴 검색
    public List<menu> searchMenusByName(String menuName) {
        conn = database_connection.conection();
        List<menu> menus = new ArrayList<>();

        try {
            pstd = conn.prepareStatement(sql_list[5]);
            pstd.setString(1, "%" + menuName + "%");
            rst = pstd.executeQuery();

            while (rst.next()) {
                menu menu = new menu();
                menu.setId(rst.getString("menu_id"));
                menu.setRestaurant_id(rst.getString("restaurant_id"));
                menu.setName(rst.getString("menu_name"));
                menu.setPrice(rst.getInt("price"));
                menu.setDescription(rst.getString("description"));
                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database_connection.close();
        }

        return menus;
    }
	
	//메뉴 삭제(메뉴 아이디)(delete 문)
	 public boolean deleteMenu(String menuId) {
		int deletemenu=0;
        conn = database_connection.conection(); 
        String sql=sql_list[6];
        try {
            pstd = conn.prepareStatement(sql_list[5]);
            pstd.setString(1, menuId);

            int result = pstd.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            database_connection.close();
        }
    }

	
}
