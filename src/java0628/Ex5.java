package java0628;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// static Ex5 클래스
public class Ex5 extends JFrame {
	
	int a = 10;
	static int b = 20;
	
	static interface Movable {
		public static final int RED = 1;
		public static final int BLUE = 2;
	}
	
	// 클래스단위(스태틱) 내부클래스는 외부 독립클래스와 동일함. 위치만 내부에 존재함.
	static class MyActionListener5 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("a = " + a); // 외부클래스의 non-static 멤버는 사용불가능
			System.out.println("b = " + b); // 외부클래스의 static 멤버만 사용가능 
		}
	}
	
	private JButton btn = new JButton("Action");
	private JTextArea ta = new JTextArea(7, 20);
	
	public Ex5() {
		setTitle("이벤트 리스너 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		ta.setEditable(false); // 편집 못하게 막음
		
		c.add(btn);
		c.add(new JScrollPane(ta)); // ta에 스크롤바 부착해서 컨텐트팬에 추가
		
		//btn.addActionListener(new MyActionListener2()); // 버튼에게 이벤트 핸들러(처리)를 객체로 등록
		
		setVisible(true); // 창을 눈에 보이기. 화면갱신됨
	}
	
	public static void main(String[] args) {
		new Ex5();
	}

}
