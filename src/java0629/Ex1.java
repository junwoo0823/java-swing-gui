package java0629;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ex1 extends JFrame {
	
	private JLabel la = new JLabel("hello");
	
	public Ex1() {
		super("창 제목");  // setTitle("창 제목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(null); // 컨텐트팬의 배치관리자 삭제
		
		la.setOpaque(true); // 레이블에 배경색이 보이도록 설정
		la.setBackground(Color.YELLOW);
		la.setSize(60, 20);
		la.setLocation(30, 30);
		c.add(la);
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX(); // 마우스 클릭 좌표 x
				int y = e.getY(); // 마우스 클릭 좌표 y
				System.out.println(x + ", " + y);
				
				la.setLocation(x, y); // 레이블 위치를 x,y 로 이동
				
				// 눌러진 마우스 버튼 구분하기
				switch (e.getButton()) {
				case MouseEvent.BUTTON1: System.out.println("BUTTON1 눌러짐"); break;
				case MouseEvent.BUTTON2: System.out.println("BUTTON2 눌러짐"); break;
				case MouseEvent.BUTTON3: System.out.println("BUTTON3 눌러짐"); break;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					System.out.println("한번 클릭됨");
				} else if (e.getClickCount() == 2) {
					System.out.println("더블 클릭됨");
				}
			}
			
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex1();
	}
}
