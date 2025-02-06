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

public class register_consumer extends JFrame{
	
	public JPanel centerpanel;
	
	//유저 아이디 값은 자동 생성 후 삽입
	
	public JLabel name_label;
	public JTextField name;
	public JLabel pw_label;
	public JTextField pw;
	public JLabel email_label;
	public JTextField email;
	public JLabel location_state_label;
	public JTextField location_state;
	public JLabel location_city_label;
	public JTextField location_city;
	
	public JButton registerbtn;
	
	public register_consumer() {
		setTitle("신규 고객 등록");
        setSize(500, 500);
        setVisible(true);
		setLayout(new BorderLayout());
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		name_label=new JLabel("고객 이름");
		name=new JTextField();
		name.setMaximumSize(new Dimension(600,40));
		name.setPreferredSize(new Dimension(600,40));
		
		
		pw_label=new JLabel("비밀번호");
		pw=new JTextField();
		pw.setMaximumSize(new Dimension(600,40));
		pw.setPreferredSize(new Dimension(600,40));

		email_label=new JLabel("이메일");
		email=new JTextField();
		email.setMaximumSize(new Dimension(600,40));
		email.setPreferredSize(new Dimension(600,40));
		
		location_state_label=new JLabel("위치(큰 도시)");
		location_state=new JTextField();
		location_state.setMaximumSize(new Dimension(600,40));
		location_state.setPreferredSize(new Dimension(600,40));
		
		location_city_label=new JLabel("위치(작은 도시)");
		location_city=new JTextField();
		location_city.setMaximumSize(new Dimension(600,40));
		location_city.setPreferredSize(new Dimension(600,40));
		
		
		registerbtn=new JButton("등록하기");
		
		centerpanel.add(name_label);
		centerpanel.add(name);
		centerpanel.add(name_label);
		centerpanel.add(name);
		centerpanel.add(pw_label);
		centerpanel.add(pw);
		centerpanel.add(email_label);
		centerpanel.add(email);
		centerpanel.add(location_state_label);
		centerpanel.add(location_state);
		centerpanel.add(location_city_label);
		centerpanel.add(location_city);
		centerpanel.add(Box.createRigidArea(new Dimension(0, 20)));
		centerpanel.add(registerbtn);
		
		add(centerpanel,BorderLayout.CENTER);
		
	}
}
