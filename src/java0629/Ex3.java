package java0629;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

// <F1> 키를 입력받으면 컨텐트팬의 배경색을 초록색으로, % 키를 입력받으면 노란색으로 변경
public class Ex3 extends JFrame {
	
	private JLabel la = new JLabel();
	
	public Ex3() {
		super("keyCode 확인: F1키는 초록색, %키 노란색");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(la);
		
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				la.setText(e.getKeyCode() + "키가 입력되었음");
				
				if (e.getKeyChar() == '%') { // 입력된 키가 '%'인지 확인
					c.setBackground(Color.YELLOW);
				} else if (e.getKeyCode() == KeyEvent.VK_F1) {
					c.setBackground(Color.GREEN);
				}
			}
		});
		
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
	}
	
	public static void main(String[] args) {
		new Ex3();
	}

}
