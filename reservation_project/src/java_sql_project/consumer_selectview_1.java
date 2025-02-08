package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java_sql_project.domain.consumer;
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
	
		Idlabel=new JLabel("아이디");
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
		combobox.addElement("식당 소유자 이름");
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
				
			}
		});
		refreshbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 이전에 예약 현황을 다시 가져온다
			}
		});
		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String type=comboboxitem.getSelectedItem().toString();
				String word=searchtext.getText();
				
			}
		});
		currentbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		reservationbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				consumer_service user_service=consumer_service.getInstance();
				String userid=user.getId();
				String username=user.getName();
				register_reservation reservation=new register_reservation(userid, username);
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
	        System.out.println(newmodel.getRowCount());
	        
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
}
