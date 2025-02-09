package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java_sql_project.domain.consumer;
import java_sql_project.domain.reservation;
import java_sql_project.domain.restaurant;
import java_sql_project.service.consumer_service;
import java_sql_project.service.reservation_service;
import java_sql_project.service.restaurant_service;

public class restaurant_selectview_1 extends JPanel{
	
	public static restaurant login_restaurant;
	public JPanel leftpanel;
	public JPanel centerpanel;
	public JPanel toppanel;
	
	public JButton registerbtn;
	public JButton signtbtn;
	public JLabel Idlabel;
	public JTextField Idfield;
	public JLabel Passlabel;
	public JTextField Passfield;
	public JButton Menubtn;
	public JButton Menulist;
	public JLabel reservelabel;
	public JTextField reserve_cancel_txt_1;
	public JTextField reserve_cancel_txt_2;
	public JButton reservecancelbtn;
	
	public JLabel restaurant_name_label;
	public JTextField restaurant_name;
	
	//public JLabel seatlabel;
	//public JTextField seat;
	public JLabel openlabel;
	public JCheckBox openchbox;
	public JLabel refreshlabel;
	public JButton refreshbtn;
	
	public DefaultTableModel model;
	public JTable datatable;
	
	public restaurant_selectview_1() {
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
	
		Idlabel=new JLabel("아이디(식당이름)");
		Idfield=new JTextField();
		Idfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		Idfield.setPreferredSize(new Dimension(100, 20));
		Passlabel=new JLabel("비밀번호");
		Passfield=new JTextField();
		Passfield.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		Passfield.setPreferredSize(new Dimension(100, 20));
		Menubtn=new JButton("신규 메뉴 등록");
		Menulist=new JButton("메뉴 조회");
		
		reservelabel=new JLabel("예약 취소");
		reservelabel.setToolTipText("예약자의 아이디, 예약 날짜(2000-01-01)를 입력하면 됩니다");
		reserve_cancel_txt_1=new JTextField();
		reserve_cancel_txt_1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		reserve_cancel_txt_1.setPreferredSize(new Dimension(100, 20));
		reserve_cancel_txt_2=new JTextField();
		reserve_cancel_txt_2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); 
		reserve_cancel_txt_2.setPreferredSize(new Dimension(100, 20));
		reservecancelbtn=new JButton("취소하기");
		
		leftpanel.add(registerbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(signtbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(Idlabel);
		leftpanel.add(Idfield);
		leftpanel.add(Passlabel);
		leftpanel.add(Passfield);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(Menulist);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(Menubtn);
		
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(reservelabel);
		leftpanel.add(reserve_cancel_txt_1);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(reserve_cancel_txt_2);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(reservecancelbtn);
		
		add(leftpanel, BorderLayout.WEST);
		
		
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerpanel.setBackground(Color.WHITE);
		
		//컬럼은 나중에 동적으로 추가
		model=new DefaultTableModel();
		model.addColumn("col1");
		model.addColumn("col2");
		model.addColumn("col3");
		model.addColumn("col4");
		model.addColumn("col5");
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
		
		restaurant_name_label=new JLabel("접속된 식당 이름:");
		restaurant_name=new JTextField();
		restaurant_name.setEditable(false);
		restaurant_name.setMaximumSize(new Dimension(100, 20));
		restaurant_name.setPreferredSize(new Dimension(100, 20));
		
		
		//seatlabel=new JLabel("남은 자리");
		//seat=new JTextField("0");
		//seat.setEditable(false);
		//seat.setMaximumSize(new Dimension(40,20));
		//seat.setPreferredSize(new Dimension(40,20));
		
		openlabel=new JLabel("영업 여부");
		openchbox=new JCheckBox();
		
		refreshlabel=new JLabel("새로고침");
		refreshbtn=new JButton("refresh");
		
		toppanel.add(restaurant_name_label);
		toppanel.add(Box.createRigidArea(new Dimension(10, 0)));
		toppanel.add(restaurant_name);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		//toppanel.add(seatlabel);
		//toppanel.add(Box.createRigidArea(new Dimension(10, 0)));
		//toppanel.add(seat);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(openlabel);
		toppanel.add(Box.createRigidArea(new Dimension(10, 0)));
		toppanel.add(openchbox);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(refreshlabel);
		toppanel.add(refreshbtn);
		add(toppanel,BorderLayout.NORTH);
		
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				register_restaurant register=new register_restaurant();
				
			}
		});
		signtbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//select로 pw, name에 해당하는 식당 정보를 가져오고, 예약 현황도 보여준다.
				restaurant_service res_service=restaurant_service.getInstance();
				reservation_service reserve_service=reservation_service.getInstance();
				List<reservation> reservation_list=new ArrayList<>();
				
				String name=Idfield.getText();
				String pw=Passfield.getText();
				restaurant login=res_service.login_restaurant(name,pw);
				login_restaurant=login;
				if(name.length()==0||pw.length()==0) {
					JOptionPane.showMessageDialog(null,"아이디나 비밀번호를 확인해 주세요!!","로그인 실패!!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(login_restaurant!=null) {
					JOptionPane.showMessageDialog(null,"안녕하세요."+name+" 씨","로그인 성공!!", JOptionPane.INFORMATION_MESSAGE);
					reservation_list=reserve_service.reservation_restaurant(login_restaurant.getName());
					model=reservation_table(reservation_list);
					
					restaurant_name.setText(name);
					datatable.setModel(model);
					Idfield.setText("");
					Passfield.setText("");
				}else {
					JOptionPane.showMessageDialog(null,"아이디나 비밀번호를 확인해 주세요!!","로그인 실패!!", JOptionPane.WARNING_MESSAGE);
					Idfield.setText("");
					Passfield.setText("");
				}
				
				System.out.println(name+": "+pw);
			}
		});
		
		Menubtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String name=Idfield.getText();
				String pw=Passfield.getText();
				register_menu_restaurant register=new register_menu_restaurant(name, pw);
			}
		});
		refreshbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				restaurant_service res_service=restaurant_service.getInstance();
				reservation_service reserve_service=reservation_service.getInstance();
				List<reservation> reservation_list=new ArrayList<>();
				
				if(login_restaurant!=null) {
					reservation_list=reserve_service.reservation_restaurant(login_restaurant.getName());
					model=reservation_table(reservation_list);
					datatable.setModel(model);
					
				}else {
					JOptionPane.showMessageDialog(null,"식당으로 로그인해주세요!!","로그인!!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		reservecancelbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reservation_service reserve_service=reservation_service.getInstance();
				String consumer_id=reserve_cancel_txt_1.getText();
				String date_value=reserve_cancel_txt_2.getText();
				if(consumer_id.length()==0||date_value.length()==0) {
					JOptionPane.showMessageDialog(null,"예약이 취소되지 않았습니다.","예약 취소 오류!!", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(reserve_service.reservation_delete_consumer(login_restaurant.getId(), consumer_id, date_value)) {
					JOptionPane.showMessageDialog(null,"예약이 정상 취소되었습니다","예약 취소!!!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"예약이 취소되지 않았습니다.","예약 취소 오류!!", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	
	public DefaultTableModel reservation_table(List<reservation> list) {
		DefaultTableModel newmodel=new DefaultTableModel();
		consumer_service consumerservice=consumer_service.getInstance();
		
		List<consumer> consumer_list=new ArrayList<>();
		
		newmodel.addColumn("rownum");
		newmodel.addColumn("consumer id");
		newmodel.addColumn("consumer name");
		newmodel.addColumn("reservation date");
		newmodel.addColumn("reservation time");
		newmodel.addColumn("number");
		newmodel.addColumn("status");
		newmodel.addColumn("created_at");
		
		for(int i=0;i<list.size();i++) {
			reservation element=list.get(i);
			consumer consumer_element=consumerservice.get_consumer(element.getConsumer_id());
			
			String reservation_consumer_id=element.getConsumer_id();
			String reservation_consumer_name=consumer_element.getName();
			String reservation_date=String.valueOf(element.getReservation_date());
			String reservation_time=String.valueOf(element.getReservation_time());
			int reservation_num=element.getNumber_of_people();
			String reservation_status=String.valueOf(element.getStatus());
			String reservation_create=String.valueOf(element.getCreated_at());
			Vector<String> row=new Vector<>(); 

			row.add(String.valueOf(i+1));
			row.add(reservation_consumer_id);
			row.add(reservation_consumer_name);
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
