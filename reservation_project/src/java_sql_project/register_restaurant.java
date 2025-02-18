package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java_sql_project.domain.restaurant;
import java_sql_project.service.restaurant_service;

public class register_restaurant extends JFrame{
	
	public JPanel centerpanel;
	
	public JLabel name_label;
	public JTextField name;
	public JLabel owner_name_label;
	public JTextField owner_name;
	public JLabel owner_pw_label;
	public JTextField owner_pw;
	public JLabel location_state_label;
	public JTextField location_state;
	public JLabel location_city_label;
	public JTextField location_city;
	public JLabel description_label;
	public JTextField description;
	
	public JButton registerbtn;
	
	public register_restaurant() {
		setTitle("신규 식당 등록");
        setSize(500, 500);
        setVisible(true);
		setLayout(new BorderLayout());
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		//식당 아이디 값은 자동 생성후 삽입
		
		name_label=new JLabel("식당 이름");
		name=new JTextField();
		name.setMaximumSize(new Dimension(600,40));
		name.setPreferredSize(new Dimension(600,40));
		
		owner_name_label=new JLabel("식당 주인 이름");
		owner_name=new JTextField();
		owner_name.setMaximumSize(new Dimension(600,40));
		owner_name.setPreferredSize(new Dimension(600,40));
		
		owner_pw_label=new JLabel("식당 비밀번호");
		owner_pw=new JTextField();
		owner_pw.setMaximumSize(new Dimension(600,40));
		owner_pw.setPreferredSize(new Dimension(600,40));
		
		location_state_label=new JLabel("식당 위치(큰 도시)");
		location_state=new JTextField();
		location_state.setMaximumSize(new Dimension(600,40));
		location_state.setPreferredSize(new Dimension(600,40));
		
		location_city_label=new JLabel("식당 위치(작은 도시)");
		location_city=new JTextField();
		location_city.setMaximumSize(new Dimension(600,40));
		location_city.setPreferredSize(new Dimension(600,40));
		
		description_label=new JLabel("음식 종류");
		description_label.setToolTipText("가급적 '일식','양식','중식' 이런식으로 작성해주세요!!");
		description=new JTextField();
		description.setMaximumSize(new Dimension(600,40));
		description.setPreferredSize(new Dimension(600,40));
		
		
		registerbtn=new JButton("등록하기");
		
		centerpanel.add(name_label);
		centerpanel.add(name);
		centerpanel.add(owner_name_label);
		centerpanel.add(owner_name);
		centerpanel.add(owner_pw_label);
		centerpanel.add(owner_pw);

		centerpanel.add(location_state_label);
		centerpanel.add(location_state);
		centerpanel.add(location_city_label);
		centerpanel.add(location_city);
		centerpanel.add(description_label);
		centerpanel.add(description);
		centerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerpanel.add(registerbtn);
		
		add(centerpanel,BorderLayout.CENTER);
		
		registerbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restaurant_service restservice=restaurant_service.getInstance();
				int cnt=restservice.restaurant_count();
				restaurant newrestaurant=new restaurant();
				
				String restaurant_name_value=name.getText();
				String owner_name_value=owner_name.getText();
				String pw_value=owner_pw.getText();
				String restaurant_state=location_state.getText();
				String restaurant_city=location_city.getText();
				String type=description.getText();
				
				newrestaurant.setName(restaurant_name_value);
				newrestaurant.setOwner_name(owner_name_value);
				newrestaurant.setOwner_pw(pw_value);
				newrestaurant.setLocation_state(restaurant_state);
				newrestaurant.setLocation_city(restaurant_city);
				newrestaurant.setDescription(type);
				
				if(restservice.sign_restaurant(newrestaurant, cnt)) {
					JOptionPane.showMessageDialog(null,"회원가입이 정상적으로 처리되었습니다.","회원 가입 성공!!", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"알 수 없는 오류로 회원가입이 실패하였습니다.","회원 가입 실패!!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
