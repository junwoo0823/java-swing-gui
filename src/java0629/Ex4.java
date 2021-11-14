package java0629;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ex4 extends JFrame {
	
	private JLabel la = new JLabel("HELLO");
	
	final int MOVE_UNIT = 10; // 레이블이 한번 움직이는 단위는 10픽셀
	
	public Ex4() {
		super("상,하,좌,우 키를 이용하여 텍스트 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(null); // 레이블이 자유롭게 위치하도록 배치관리자 제거
		
		la.setSize(100, 20);
		la.setLocation(50, 50);
		c.add(la);
		
		setVisible(true);
		
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 레이블의 현재 좌표위치에서 단위픽셀 만큼 화살표 키 방향으로 레이블 좌표 이동시키기
				// getX()  getY()     현재좌표 값 가져오기
				// setLocation(x, y)  좌표 설정하기
				
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
					la.setLocation(la.getX(), la.getY() - MOVE_UNIT);
					break;
				case KeyEvent.VK_DOWN:
					la.setLocation(la.getX(), la.getY() + MOVE_UNIT);
					break;
				case KeyEvent.VK_LEFT:
					la.setLocation(la.getX() - MOVE_UNIT, la.getY());
					break;
				case KeyEvent.VK_RIGHT:
					la.setLocation(la.getX() + MOVE_UNIT, la.getY());
					break;
				}
			}
		});
		
		c.setFocusable(true);
		c.requestFocus();
	}
	
	public static void main(String[] args) {
		new Ex4();
	}

}
