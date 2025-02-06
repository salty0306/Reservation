package java_sql_project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class select_view extends JPanel{
	//손님, 식당 버튼 두개
	public select_view() {
		setLayout(new BorderLayout());
		
		JLabel titlelabel=new JLabel("접속 유형 선택", SwingConstants.CENTER);
		titlelabel.setFont(new Font("맑은 고딕",Font.BOLD,15));
		add(titlelabel,BorderLayout.NORTH);
		
		JPanel bottompanel=new JPanel();
		bottompanel.setLayout(new BoxLayout(bottompanel, BoxLayout.Y_AXIS));
		bottompanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		
		JButton consumerbtn=new JButton("손님으로 접속");
		JButton restaurantbtn=new JButton("식당으로 접속");

		consumerbtn.setPreferredSize(new Dimension(200, 50));
		consumerbtn.setMaximumSize(new Dimension(200, 50));
		restaurantbtn.setPreferredSize(new Dimension(200, 50));
		restaurantbtn.setMaximumSize(new Dimension(200, 50));
		
		consumerbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		restaurantbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		consumerbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		restaurantbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		bottompanel.add(Box.createVerticalGlue());
		bottompanel.add(consumerbtn);
		bottompanel.add(Box.createRigidArea(new Dimension(0, 15)));
		bottompanel.add(restaurantbtn);
		bottompanel.add(Box.createVerticalGlue());
        
		add(bottompanel, BorderLayout.CENTER);
		
		
	}
}
