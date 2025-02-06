package java_sql_project;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class register_menu_restaurant extends JFrame{
	public String restarantid;
	public String restarantname;
	public register_menu_restaurant(String restarantid, String restarantname) {

		setTitle("신규 메뉴 등록");
        setSize(500, 500);
        setVisible(true);
		setLayout(new BorderLayout());
		
	}

}
