package java0705;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Ex2 extends JFrame {
	
	private CardLayout cardLayout = new CardLayout(); // 카드 레이아웃 배치관리자
	
	private final JPanel panel1 = new JPanel(); // 첫번째 화면 패널
	private final JLabel lblNewLabel = new JLabel("첫번째 화면");
	private final JButton btn1 = new JButton("두번째 화면으로 이동");
	
	private final JPanel panel2 = new JPanel(); // 두번째 화면 패널
	private final JLabel lblNewLabel_1 = new JLabel("두번째 화면");
	private final JButton btn2 = new JButton("세번째 화면으로 이동");
	
	private final JPanel panel3 = new JPanel();
	private final JLabel lblNewLabel_2 = new JLabel("세번째 화면");

	public Ex2() {
		setTitle("카드레이아웃으로 화면전환하기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(cardLayout);
		
		c.add(panel1, "첫번째 화면");
		panel1.add(lblNewLabel);
		panel1.add(btn1);
		
		c.add(panel2, "두번째 화면");
		panel2.add(lblNewLabel_1);
		panel2.add(btn2);
		
		c.add(panel3, "세번째 화면");
		panel3.add(lblNewLabel_2);
		
		// 버튼 이벤트 연결하기
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(c, "두번째 화면");
			}
		});
		
		btn2.addActionListener(event -> {
			cardLayout.show(c, "세번째 화면");
		});
		
		cardLayout.show(c, "첫번째 화면");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex2();
	}

}
