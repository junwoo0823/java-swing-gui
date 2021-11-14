package java0702;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ex3 extends JFrame {

	public Ex3() {
		setTitle("체크박스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 250);
		setLocationByPlatform(true);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 세번째 체크박스의 기본상태 이미지
		ImageIcon cherryIcon = new ImageIcon("images/cherry.jpg");
		// 세번째 체크박스의 선택상태 이미지
		ImageIcon selectedCherryIcon = new ImageIcon("images/selectedCherry.jpg");
		
		JCheckBox apple = new JCheckBox("사과");
		JCheckBox pear = new JCheckBox("배", true);
		JCheckBox cherry = new JCheckBox("체리", cherryIcon);
		
		JButton btn = new JButton("버튼");
		btn.addActionListener(event -> {
			
			String str = "";
			str += "사과 : " + apple.isSelected();
			str += "\n배 : " + pear.isSelected();
			str += "\n체리 : " + cherry.isSelected();
			
			JOptionPane.showMessageDialog(Ex3.this, str, "대화상자 제목", JOptionPane.INFORMATION_MESSAGE);
		});
		
		cherry.setSelectedIcon(selectedCherryIcon); // 선택상태 이미지 등록
		cherry.setBorderPainted(true); // 체크박스 경계선 보이기 설정
		
		c.add(apple);
		c.add(pear);
		c.add(cherry);
		c.add(btn);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex3();
	} // main

}
