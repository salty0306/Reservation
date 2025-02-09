package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java_sql_project.domain.consumer;
import java_sql_project.domain.reservation;
import java_sql_project.domain.restaurant;
import java_sql_project.service.consumer_service;
import java_sql_project.service.reservation_service;
import java_sql_project.service.restaurant_service;
import java_sql_project.setting.database_connection;

public class consumer_selectview_1 extends JPanel{
	public static consumer user;
	public restaurant_service rest_service;
	
	public JPanel leftpanel;
	public JPanel centerpanel;
	public JPanel toppanel;
	
	public JButton registerbtn;
	public JButton signtbtn;
	public JLabel Idlabel;
	public JTextField Idfield;
	public JLabel Passlabel;
	public JTextField Passfield;
	public JLabel userlabel;
	public JTextField userfield;
	
	public DefaultComboBoxModel combobox;
	public JComboBox comboboxitem;
	public JButton searchbtn;
	public JTextField searchtext;
	
	public JLabel currentlabel;
	public JButton currentbtn;
	public JLabel refreshlabel;
	public JButton refreshbtn;
	public JButton reservationbtn;
	
	public JLabel cancellabel;
	public JTextField cancelres;
	public JTextField canceldate;
	public JButton reservationcancelbtn;
	
	public DefaultTableModel model;
	public JTable datatable;
	public consumer_selectview_1() {
		setLayout(new BorderLayout());
		
		
		leftpanel=new JPanel();
		leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
		leftpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		registerbtn=new JButton("신규 등록");
		signtbtn=new JButton("접속");
		
		registerbtn.setPreferredSize(new Dimension(100, 30));
		registerbtn.setMaximumSize(new Dimension(100, 30));
		signtbtn.setPreferredSize(new Dimension(100, 30));
		signtbtn.setMaximumSize(new Dimension(100, 30));
	
		registerbtn.setAlignmentX(Component.LEFT_ALIGNMENT);
		signtbtn.setAlignmentX(Component.LEFT_ALIGNMENT);
	
		Idlabel=new JLabel("아이디(손님 이름)");
		Idfield=new JTextField();
		Idfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		Idfield.setPreferredSize(new Dimension(100, 20));
		Passlabel=new JLabel("비밀번호");
		Passfield=new JTextField();
		Passfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		Passfield.setPreferredSize(new Dimension(100, 20));
		userlabel=new JLabel("접속된 손님 이름");
		userfield=new JTextField();
		userfield.setEditable(false);
		userfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		userfield.setPreferredSize(new Dimension(100, 20));
		refreshlabel=new JLabel("새로고침");
		refreshbtn=new JButton("refresh");
		
		reservationbtn=new JButton("예약하기");
		
		cancellabel=new JLabel("예약 취소");
		cancellabel.setToolTipText("식당의 아이디를 입력하고 예약 날짜를 '2000-01-01' 형태로 입력해주세요");
		cancelres=new JTextField();
		cancelres.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		cancelres.setPreferredSize(new Dimension(100, 20));
		canceldate=new JTextField();
		canceldate.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		canceldate.setPreferredSize(new Dimension(100, 20));
		reservationcancelbtn=new JButton("예약취소 하기");
				
		leftpanel.add(registerbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(signtbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(Idlabel);
		leftpanel.add(Idfield);
		leftpanel.add(Passlabel);
		leftpanel.add(Passfield);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(userlabel);
		leftpanel.add(userfield);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(refreshlabel);
		leftpanel.add(refreshbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(reservationbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(cancellabel);
		leftpanel.add(cancelres);
		leftpanel.add(canceldate);
		leftpanel.add(reservationcancelbtn);
		add(leftpanel, BorderLayout.WEST);
		
		
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerpanel.setBackground(Color.WHITE);
		
		//초기에 실행시 예약 가능한 식당 목록 출력
		rest_service=restaurant_service.getInstance();
		List<restaurant> restaurant_list=new ArrayList<>();
		restaurant_list=rest_service.restaurant_list();
		
		model=restauranttable(restaurant_list);
		
		datatable=new JTable(model);
		datatable.setEnabled(false);
		datatable.setBackground(Color.WHITE); 
		datatable.getTableHeader().setBackground(Color.LIGHT_GRAY);
		datatable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)); 
		
		JScrollPane scrollpane = new JScrollPane(datatable);
		scrollpane.setBackground(Color.WHITE);
		scrollpane.getViewport().setBackground(Color.WHITE); 

		centerpanel.add(scrollpane);
		
		add(centerpanel,BorderLayout.CENTER);
		
		
		
		toppanel=new JPanel();
		toppanel.setSize(300, 300);
		toppanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toppanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		combobox=new DefaultComboBoxModel<String>();
		combobox.addElement("메뉴");
		combobox.addElement("위치(큰 도시)");
		combobox.addElement("위치(작은 도시)");
		combobox.addElement("식당 이름");
		combobox.addElement("소유자 이름");
		comboboxitem=new JComboBox<>(combobox);
		
		searchbtn=new JButton("검색");
		searchtext=new JTextField();
		searchtext.setMaximumSize(new Dimension(200,40));
		searchtext.setPreferredSize(new Dimension(200,40));
		
		currentlabel=new JLabel("현황");
		currentbtn=new JButton("예약 보기");
		
		toppanel.add(comboboxitem);
		toppanel.add(searchtext);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(searchbtn);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(currentlabel);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(currentbtn);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		add(toppanel,BorderLayout.NORTH);
		
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				register_consumer register=new register_consumer();
			}
		});
		signtbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//select로 pw, name에 해당하는 유저 정보를 가져오고, 예약 현황도 보여준다.
				consumer_service user_service=consumer_service.getInstance();
				String name=Idfield.getText();
				String pw=Passfield.getText();
				user=user_service.login_consumer(name, pw);
				userfield.setText(user.getName());
				Idfield.setText("");
				Passfield.setText("");
			}
		});
		refreshbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 새롭게 식당 목록을 업데이트 한다.
				List<restaurant> refresh_list=rest_service.restaurant_list();
				model=restauranttable(refresh_list);
				datatable.setModel(model);
			}
		});
		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String type=comboboxitem.getSelectedItem().toString();
				String word=searchtext.getText();
				List<restaurant> search_list=new ArrayList<>();
				restaurant search_one=null;
				switch(type) {
				case "메뉴":
					search_list=rest_service.get_restaurant_type(word);
					break;
				case "위치(큰 도시)":
					search_list=rest_service.get_restaurant_state(word);
					break;
				case "위치(작은 도시)":
					search_list=rest_service.get_restaurant_city(word);
					break;
				case "식당 이름":
					search_one=rest_service.get_restaurant_name(word);
					break;
				case "소유자 이름":
					search_list=rest_service.get_restaurant_owner(word);
					break;
				}
				if(search_list.size()>0) {
					model=restauranttable(search_list);
				}else if(search_one!=null){
					search_list.add(search_one);
					model=restauranttable(search_list);
				}
				datatable.setModel(model);
			}
		});
		currentbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reservation_service user_reserve_service=reservation_service.getInstance();
				String username=userfield.getText();
				
				List<reservation> reserve_list=user_reserve_service.reservation_consumer(username);
				model=reservationtable(reserve_list);
				datatable.setModel(model);
			}
		});
		reservationbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user!=null)
				{				
					consumer_service user_service=consumer_service.getInstance();
					String userid=user.getId();
					String username=user.getName();
					register_reservation reservation=new register_reservation(userid, username);
				}else {
					JOptionPane.showMessageDialog(null,"로그인을 해주세요!!","예약 실패!!", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		
		reservationcancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reservation_service cacelservice=reservation_service.getInstance();
				if(user!=null) {
					String userid=user.getId();
					String resid=cancelres.getText();
					String date=canceldate.getText();
					
					if(cacelservice.reservation_delete_consumer(resid, userid, date)) {
						JOptionPane.showMessageDialog(null,"예약 삭제가 정상적으로 처리되었습니다.","예약 삭제!!", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null,"예약 취소에 실패했습니다!!","예약 취소 실패!!", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}
	
	public DefaultTableModel restauranttable(List<restaurant> listdata) {
		DefaultTableModel newmodel=new DefaultTableModel();

		newmodel.addColumn("rownum");
		newmodel.addColumn("restaurant name");
		newmodel.addColumn("owner name");
		newmodel.addColumn("location(state)");
		newmodel.addColumn("location(city)");
		newmodel.addColumn("food type");
		for(int i=0;i<listdata.size();i++) {
			
			restaurant data=listdata.get(i);
	        
			String resname=data.getName();
			String resowner=data.getOwner_name();
			String resloc_state=data.getLocation_state();
			String resloc_city=data.getLocation_city();
			String restype=data.getDescription();
			Vector<String> row=new Vector<>(); 

			row.add(String.valueOf(i+1));
			row.add(resname);
			row.add(resowner);
			row.add(resloc_state);
			row.add(resloc_city);
			row.add(restype);
			
			newmodel.addRow(row);
		}
		
		return newmodel;
	}
	public DefaultTableModel reservationtable(List<reservation> reservation_list) {
		DefaultTableModel newmodel=new DefaultTableModel();
		newmodel.addColumn("rownum");
		newmodel.addColumn("restaurant id");
		newmodel.addColumn("reservation date");
		newmodel.addColumn("reservation time");
		newmodel.addColumn("number");
		newmodel.addColumn("status");
		newmodel.addColumn("created_at");
		for(int i=0;i<reservation_list.size();i++) {
			reservation data=reservation_list.get(i);
			
			String reservation_restaurant_id=data.getRestaurant_id();
			String reservation_date=String.valueOf(data.getReservation_date());
			String reservation_time=String.valueOf(data.getReservation_time());
			int reservation_num=data.getNumber_of_people();
			String reservation_status=String.valueOf(data.getStatus());
			String reservation_create=String.valueOf(data.getCreated_at());
			Vector<String> row=new Vector<>(); 

			row.add(String.valueOf(i+1));
			row.add(reservation_restaurant_id);
			row.add(reservation_date);
			row.add(reservation_time);
			row.add(String.valueOf(reservation_num));
			row.add(reservation_status);
			row.add(reservation_create);
			
			newmodel.addRow(row);
		}
		return newmodel;
	}
}
