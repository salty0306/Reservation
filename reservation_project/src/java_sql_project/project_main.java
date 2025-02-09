package java_sql_project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class project_main extends JFrame{
	
	public JPanel mainpanel;
	public CardLayout cardlayout;
	public restaurant_selectview_1 restaurant=null;
	public consumer_selectview_1 consumer=null;
	
	
	public project_main() {
		setTitle("식당 예약 프로그램");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.restaurant=new restaurant_selectview_1();
		this.consumer=new consumer_selectview_1();
		
		JTabbedPane jtab=new JTabbedPane();
		jtab.add("식당",restaurant);
		jtab.add("손님",consumer);
		
		
		add(jtab);
		setVisible(true);
		jtab.addChangeListener(e -> {
			int selectindex=jtab.getSelectedIndex();
			if(selectindex==0) {
				consumer.resetfield();
			}else if(selectindex==1) {
				restaurant.resetfield();
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new project_main();
		
	}


}
