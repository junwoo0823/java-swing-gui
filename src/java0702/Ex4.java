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

public class Ex4 extends JFrame {
	
	private JLabel sumLabel = new JLabel("현재 0 원 입니다.");
	private JCheckBox apple = new JCheckBox("사과");
	private JCheckBox pear = new JCheckBox("배");
	private JCheckBox cherry = new JCheckBox("체리");

	public Ex4() {
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
		MyItemListener itemListener = new MyItemListener();
		apple.addItemListener(itemListener);
		pear.addItemListener(itemListener);
		cherry.addItemListener(itemListener);
		
		setVisible(true);
	}
	
	
	class MyItemListener implements ItemListener {
		
		private int sum = 0;
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// itemStateChanged는 자신의 체크상태가 이전과 다르게 바꼈을때만 호출됨
			JCheckBox item = (JCheckBox) e.getItem();
			
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (item == apple) {
					sum += 100;
				} else if (item == pear) {
					sum += 500;
				} else { // cherry
					sum += 2000;
				}
			} else { // ItemEvent.DESELECTED
				if (item == apple) {
					sum -= 100;
				} else if (item == pear) {
					sum -= 500;
				} else { // cherry
					sum -= 2000;
				}
			}
			
			sumLabel.setText("현재 " + sum + "원 입니다.");
		}
		
	} // MyItemListener
	
	
	
	public static void main(String[] args) {
		new Ex4();
	} // main

}





