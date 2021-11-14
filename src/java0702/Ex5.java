package java0702;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ex5 extends JFrame {
	
	private JLabel sumLabel = new JLabel("현재 0 원 입니다.");
	private JCheckBox apple = new JCheckBox("사과");
	private JCheckBox pear = new JCheckBox("배");
	private JCheckBox cherry = new JCheckBox("체리");
	
	private int sum = 0;

	public Ex5() {
		setTitle("체크박스");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationByPlatform(true);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("사과 100원, 배 500원, 체리 2000원"));
		
		apple.setBorderPainted(true);
		pear.setBorderPainted(true);
		cherry.setBorderPainted(true); // 체크박스 경계선 보이기 설정
		
		c.add(apple);
		c.add(pear);
		c.add(cherry);
		c.add(sumLabel);
		
		// 체크박스 이벤트 연결
		apple.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					sum += 100;
				} else { // ItemEvent.DESELECTED
					sum -= 100;
				}
				sumLabel.setText("현재 " + sum + "원 입니다.");
			}
		});
		
		pear.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				sum += 500;
			} else { // ItemEvent.DESELECTED
				sum -= 500;
			}
			sumLabel.setText("현재 " + sum + "원 입니다.");
		});
		
		cherry.addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				sum += 2000;
			} else { // ItemEvent.DESELECTED
				sum -= 2000;
			}
			sumLabel.setText("현재 " + sum + "원 입니다.");
		});
		
		
		setVisible(true);
	} // 생성자
	
	public static void main(String[] args) {
		new Ex5();
	} // main
}





