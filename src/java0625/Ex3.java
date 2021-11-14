package java0625;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Ex3 extends JFrame {
	
	public Ex3() {
		setTitle("BorderLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout()); // BorderLayout 배치관리자 설정
		
		contentPane.add(new JButton("계산하기"), BorderLayout.CENTER);
		contentPane.add(new JButton("덧셈"), BorderLayout.NORTH);
		contentPane.add(new JButton("뺄셈"), BorderLayout.SOUTH);
		contentPane.add(new JButton("곱셈"), BorderLayout.EAST);
		contentPane.add(new JButton("나눗셈"), BorderLayout.WEST);
		
		setLocationByPlatform(true);
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex3();
	}

}
