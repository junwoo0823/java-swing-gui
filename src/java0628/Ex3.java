package java0628;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// 3. 내부 클래스(inner class)로 작성하는 방법
//    내부 클래스 종류: 멤버 내부클래스(member inner class), 지역 내부클래스(local inner class)

class A {
	
	int num = 100; // 멤버변수
	
	void methodA() { // 메소드
		int a = 10; // 지역변수
		class E { // 지역 내부클래스
			
		}
	}
	
	class B { // 멤버 내부클래스
		void methodB() {
			num++;
			System.out.println(num);
		}
		
		class D {
			
		}
	}
	class C {
		
	}
}


public class Ex3 extends JFrame {
	
	private JButton btn = new JButton("Action");
	private JTextArea ta = new JTextArea(7, 20);

	public Ex3() {
		setTitle("이벤트 리스너 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		ta.setEditable(false); // 편집 못하게 막음
		
		c.add(btn);
		c.add(new JScrollPane(ta)); // ta에 스크롤바 부착해서 컨텐트팬에 추가
		
		btn.addActionListener(new MyActionListener2()); // 버튼에게 이벤트 핸들러(처리)를 객체로 등록
		
		setVisible(true); // 창을 눈에 보이기. 화면갱신됨
	}
	
	// 멤버 내부클래스는 외부클래스 멤버를 자기것처럼 바로 접근 가능함
	class MyActionListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = ta.getText();
			ta.setText(str + "버튼 클릭됨\n");
			
			//JButton button = (JButton) e.getSource(); // 이벤트 소스 가져오기
			
			if (btn.getText().equals("Action")) {
				btn.setText("액션");
			} else {
				btn.setText("Action");
			}
		}
	} // class MyActionListener2
	
	
	public static void main(String[] args) {
		new Ex3();
	}

}
