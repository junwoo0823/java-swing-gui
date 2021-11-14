package java0625;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ex4 extends JFrame {
	
	public Ex4() {
		setTitle("GridLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		
		GridLayout gridLayout = new GridLayout(4, 2);
		contentPane.setLayout(gridLayout); // GridLayout 배치관리자 설정
		
		contentPane.add(new JLabel("이름"));
		contentPane.add(new JTextField());
		contentPane.add(new JLabel("학번"));
		contentPane.add(new JTextField());
		contentPane.add(new JLabel("학과"));
		contentPane.add(new JTextField());
		contentPane.add(new JLabel("과목"));
		contentPane.add(new JTextField());
		
		setLocationByPlatform(true);
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex4();
	}

}
