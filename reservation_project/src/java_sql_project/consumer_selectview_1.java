package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class consumer_selectview_1 extends JPanel{
	public JPanel leftpanel;
	public JPanel centerpanel;
	public JPanel toppanel;
	
	public JButton registerbtn;
	public JButton signtbtn;
	public JLabel Idlabel;
	public JTextField Idfield;
	public JLabel Passlabel;
	public JTextField Passfield;
	
	public JLabel searchlabel;
	public JTextField searchtext;
	
	
	public JLabel currentlabel;
	public JButton currentbtn;
	public JLabel refreshlabel;
	public JButton refreshbtn;
	
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
		
		leftpanel.add(registerbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftpanel.add(signtbtn);
		leftpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		leftpanel.add(Idlabel);
		leftpanel.add(Idfield);
		leftpanel.add(Passlabel);
		leftpanel.add(Passfield);
		       
		add(leftpanel, BorderLayout.WEST);
		
		
		
		centerpanel=new JPanel();
		centerpanel.setLayout(new BoxLayout(centerpanel,BoxLayout.Y_AXIS));
		centerpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		centerpanel.setBackground(Color.WHITE);
		
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
		
		searchlabel=new JLabel("검색");
		searchtext=new JTextField();
		searchtext.setMaximumSize(new Dimension(200,40));
		searchtext.setPreferredSize(new Dimension(200,40));
		
		currentlabel=new JLabel("현황");
		currentbtn=new JButton("예약 보기");
		
		refreshlabel=new JLabel("새로고침");
		refreshbtn=new JButton("refresh");
		
		toppanel.add(searchlabel);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(searchtext);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(currentlabel);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(currentbtn);
		toppanel.add(Box.createRigidArea(new Dimension(20, 0)));
		toppanel.add(refreshlabel);
		toppanel.add(refreshbtn);
		add(toppanel,BorderLayout.NORTH);
		
		registerbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		signtbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
