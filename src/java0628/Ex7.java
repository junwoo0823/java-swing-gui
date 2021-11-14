package java0628;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Ex7 extends JFrame {
	
	// 이벤트 메소드정의를 가진 리스너 인터페이스가
	// 추상메소드 선언을 2개 이상 가질 경우
	// 대응되는 Adapter 클래스를 제공해줌
	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("버튼이 눌렀다가 떼어짐(클릭됨)");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("마우스 버튼이 눌러짐");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("눌렀던 마우스 버튼이 떼어짐");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("마우스 포인터가 버튼 영역 안으로 들어감");
			
			JButton btn = (JButton) e.getSource();
			btn.setBackground(Color.RED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("마우스 포인터가 버튼 영역 밖으로 나옴");
			
			JButton btn = (JButton) e.getSource();
			btn.setBackground(Color.YELLOW);
		}
	}
	
	public Ex7() {
		setTitle("버튼에 마우스 이벤트 리스너 달기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 지역내부클래스에서 참조하는 지역변수는 final 상수로 자동 컴파일됨
		final JButton btn = new JButton("MouseEvent 테스트 버튼");
		c.add(btn);
		
		//btn.addMouseListener(new MyMouseListener()); // 마우스 리스너 달기
		
		// 익명의 내부 지역클래스로 객체생성 후 전달
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(Color.YELLOW);
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex7();
	}

}
