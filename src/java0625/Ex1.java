package java0625;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

// 컨테이너 : 컴포넌트를 포함할 수 있는 요소.  예) JFrame 윈도우창
// 컴포넌트 : 컨테이너 안에 포함되야만 나타나는 요소.  예) JButton 버튼

// GUI 프로그램에서는 main 스레드의 역할이 최소화됨.

public class Ex1 extends JFrame {
	
	public Ex1() {
		super();
		setTitle("창 제목"); // 창 제목 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창이 닫힐때 프로그램도 함께 종료하는 설정
		                                                // System.exit(0); // 자바프로그램 종료
		Container contentPane = getContentPane(); // 컨텐트팬 영역을 가져오기
		contentPane.setBackground(Color.ORANGE);  // 컨텐트팬 영역에 오렌지색 배경 설정
		
		contentPane.setLayout(new FlowLayout()); // 컨텐트팬에 FlowLayout 배치관리자를 설정
		
		contentPane.add(new JButton("OK"));     // 컨텐트팬에 버튼 달기
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));
		
		setLocationByPlatform(true);
		setSize(400, 300); // 창 크기 400X300 설정  (기본크기 가로세로 모두 0)
		setVisible(true);  // 창을 화면에 보이게 설정 (기본이 숨기기 되있음)
	}

	public static void main(String[] args) {
		new Ex1();
	}
}




