package java0702;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ex1 extends JFrame {
	private final JButton b1 = new JButton("Magenta / Yellow Button");
	private final JButton b2 = new JButton("Disabled Button");
	private final JButton b3 = new JButton("getX(), getY()");
	
	public Ex1() {
		setTitle("JComponent의 공통 메소드");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationByPlatform(true);
		//setResizable(false);
		
		Container c = getContentPane();
		
		c.setLayout(new FlowLayout());
		
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		b1.setForeground(new Color(165, 42, 42));
		b1.setBackground(Color.YELLOW);
		//b1.setOpaque(true);
		
		getContentPane().add(b1);
		b2.setEnabled(false);
		
		getContentPane().add(b2);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				int x = b.getX();
				int y = b.getY();
				
 				JFrame frame = (JFrame) b.getTopLevelAncestor();
 				//frame.setTitle(x + ", " + y);
 				
 				// 내부클래스 에서 외부클래스 객체를 this로 참조하려면
 				// 외부클래스이름.this 로 참조하면 된다.
 				Ex1.this.setTitle(x + ", " + y);
			}
		});
		
		getContentPane().add(b3);
		
		//setVisible(true);
	}

	public static void main(String[] args) {
		Ex1 ex1 = new Ex1();
		ex1.setVisible(true);
	}

}
