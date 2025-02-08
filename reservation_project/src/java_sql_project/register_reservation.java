package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java_sql_project.domain.reservation;
import java_sql_project.service.reservation_service;

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
		
		reservation_date_label=new JLabel("예약 날짜('-'로 구별해서 입력(2000-01-01))");
		reservation_date=new JTextField();
		reservation_date.setMaximumSize(new Dimension(600,40));
		reservation_date.setPreferredSize(new Dimension(600,40));
		
		reservation_time_label=new JLabel("예약 시간(':'로 구별해서 입력(15:00:00))");
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
		
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reservation_service reserve_service=reservation_service.getInstance();
				
				reservation newreserve=new reservation();
				String restarant_name_value=restaurant_name.getText();
				String restarant_data_value=reservation_date.getText();
				String reservation_time_value=reservation_time.getText();
				int num=Integer.parseInt(number.getText());
				
				SimpleDateFormat datafmt=new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat timefmt=new SimpleDateFormat("HH:mm:ss");
				Date utilDate;
				Date utilTime;
				try {
					utilDate = datafmt.parse(restarant_data_value);
					utilTime=  timefmt.parse(reservation_time_value);
					java.sql.Date sqldate=new java.sql.Date(utilDate.getTime());
					java.sql.Time sqltime=new java.sql.Time(utilTime.getTime());
					newreserve.setReservation_date(sqldate);
					newreserve.setReservation_time(sqltime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					System.out.println("날짜나 시간 입력 형식이 잘못되었습니다.");
					JOptionPane.showMessageDialog(null,"날짜나 시간 입력 형식이 잘못되었습니다.","예약 실패!!", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
					return;
				}
				
				newreserve.setConsumer_id(userid);
				newreserve.setNumber_of_people(num);
				if(reserve_service.reservation_register(newreserve,restarant_name_value)) {
					System.out.println("예약이 정상적으로 처리되었습니다.");
					JOptionPane.showMessageDialog(null,restarant_name_value+"에 예약이 정상적으로 처리되었습니다.","예약 완료!!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					System.out.println("예약이 입력되지 않았습니다.");
					JOptionPane.showMessageDialog(null,restarant_name_value+"에 예약하는 것에 실패하였습니다.","예약 실패!!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
