package java0705;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ex1 extends JFrame {
	
	private final JPanel panel = new JPanel();
	private final JRadioButton rdbtnApple = new JRadioButton("사과");
	private final JRadioButton rdbtnPear = new JRadioButton("배");
	private final JRadioButton rdbtnCherry = new JRadioButton("체리");
	private final JLabel lblImage = new JLabel("");
	
	private ImageIcon[] imageArr = {
			new ImageIcon("images/apple.jpg"),
			new ImageIcon("images/pear.jpg"),
			new ImageIcon("images/cherry.jpg")
	};

	public Ex1() {
		setTitle("라디오버튼 ItemEvent");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationByPlatform(true);
		
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		// 라디오버튼 3개를 버튼그룹 한개로 그룹화
		ButtonGroup g = new ButtonGroup();
		g.add(rdbtnApple);
		g.add(rdbtnPear);
		g.add(rdbtnCherry);
		
		panel.add(rdbtnApple);
		panel.add(rdbtnPear);
		panel.add(rdbtnCherry);
		rdbtnCherry.setSelected(true);
		
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblImage, BorderLayout.CENTER);
		
		// 라디오버튼 3개에 ItemListener 이벤트 리스너 연결하기
		MyItemListener listener = new MyItemListener();
		rdbtnApple.addItemListener(listener);
		rdbtnPear.addItemListener(listener);
		rdbtnCherry.addItemListener(listener);
		
		setVisible(true);
	}
	
	class MyItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				return;
			}
			System.out.println("itemStateChanged 호출됨");
			
//			JRadioButton rdbtn = (JRadioButton) e.getItem();
//			if (rdbtn == rdbtnApple) {
//				lblImage.setIcon(imageArr[0]);
//			} else if (rdbtn == rdbtnPear) {
//				lblImage.setIcon(imageArr[1]);
//			} else { // rdbtn == rdbtnCherry
//				lblImage.setIcon(imageArr[2]);
//			}
			
			if (rdbtnApple.isSelected()) {
				lblImage.setIcon(imageArr[0]);
			} else if (rdbtnPear.isSelected()) {
				lblImage.setIcon(imageArr[1]);
			} else { // rdbtnCherry.isSelected()
				lblImage.setIcon(imageArr[2]);
			}
		}
	} // MyItemListener class
	
	
	public static void main(String[] args) {
		new Ex1();
	}

}
