package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class register_menu_restaurant extends JFrame{
	public String menu_id;
	public String restarantid;
	public String restarantname;
	public String menu_name;
	public int menu_price;
	
	public JPanel centerpanel;
	
	public JLabel name_label;
	public JTextField name_field;
	public JLabel price_label;
	public JTextField price_field;
	public JLabel type_label;
	public JTextField type_field;
	
	public JButton registerbtn;
	
	public register_menu_restaurant(String restarantid, String restarantname) {

		setTitle("신규 메뉴 등록");
        setSize(500, 300);
        setVisible(true);
		setLayout(new BorderLayout());
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		name_label=new JLabel("메뉴 이름");
		name_field=new JTextField();
		name_field.setMaximumSize(new Dimension(600,40));
		name_field.setPreferredSize(new Dimension(600,40));
		
		price_label=new JLabel("가격");
		price_field=new JTextField();
		price_field.setMaximumSize(new Dimension(600,40));
		price_field.setPreferredSize(new Dimension(600,40));
		
		type_label=new JLabel("메뉴 종류");
		type_field=new JTextField();
		type_field.setMaximumSize(new Dimension(600,40));
		type_field.setPreferredSize(new Dimension(600,40));
		
		registerbtn=new JButton("등록 하기");
		
		centerpanel.add(name_label);
		centerpanel.add(name_field);
		centerpanel.add(price_label);
		centerpanel.add(price_field);
		centerpanel.add(type_label);
		centerpanel.add(type_field);
		centerpanel.add(registerbtn);
		
		add(centerpanel,BorderLayout.CENTER);
	}

}
