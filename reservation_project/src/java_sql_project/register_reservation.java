package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class register_reservation extends JFrame{

	public JPanel centerpanel;

	public JLabel restaurant_name_label;
	public JTextField restaurant_name;
	public JLabel reservation_date_label;
	public JTextField reservation_date;
	public JLabel reservation_time_label;
	public JTextField reservation_time;
	public JLabel number_label;
	public JTextField number;
	
	public JButton registerbtn;
	
	public register_reservation(String userid, String username) {
		
		setTitle("신규 예약 등록");
        setSize(400, 400);
        setVisible(true);
		setLayout(new BorderLayout());

		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		restaurant_name_label=new JLabel("식당 이름");
		restaurant_name=new JTextField();
		restaurant_name.setMaximumSize(new Dimension(600,40));
		restaurant_name.setPreferredSize(new Dimension(600,40));
		
		reservation_date_label=new JLabel("예약 날짜('-'로 구별해서 입력)");
		reservation_date=new JTextField();
		reservation_date.setMaximumSize(new Dimension(600,40));
		reservation_date.setPreferredSize(new Dimension(600,40));
		
		reservation_time_label=new JLabel("예약 시간(':'로 구별해서 입력)");
		reservation_time=new JTextField();
		reservation_time.setMaximumSize(new Dimension(600,40));
		reservation_time.setPreferredSize(new Dimension(600,40));
		
		number_label=new JLabel("인원 수");
		number=new JTextField();
		number.setMaximumSize(new Dimension(200,40));
		number.setPreferredSize(new Dimension(200,40));
		
		registerbtn=new JButton("신규 예약 등록하기");
		
		centerpanel.add(restaurant_name_label);
		centerpanel.add(restaurant_name);
		centerpanel.add(reservation_date_label);
		centerpanel.add(reservation_date);
		centerpanel.add(reservation_time_label);
		centerpanel.add(reservation_time);
		centerpanel.add(number_label);
		centerpanel.add(number);
		centerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerpanel.add(registerbtn);
		
		add(centerpanel,BorderLayout.CENTER);
	}
}
