package java0628;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Ex4 extends JFrame {
	
	private JButton btn = new JButton("Action");
	private JTextArea ta = new JTextArea(7, 20);
	
	public Ex4() { // 생성자(메소드)
		setupView(); // 화면 모양 설정
		
		// 이름이 있는 지역 내부클래스. 실행흐름 중에 클래스 로딩이 일어남
		class MyActionListener3 implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = ta.getText();
				ta.setText(str + "버튼 클릭됨\n");
				
				if (btn.getText().equals("Action")) {
					btn.setText("액션");
				} else {
					btn.setText("Action");
				}
			}
		} // class MyActionListener3
		//btn.addActionListener(new MyActionListener3());
		
		
		// 익명의(이름이 없는) 지역 내부클래스.
		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = ta.getText();
				ta.setText(str + "버튼 클릭됨\n");
				
				if (btn.getText().equals("Action")) {
					btn.setText("액션");
				} else {
					btn.setText("Action");
				}
			}
		};
		//btn.addActionListener(l);
		
		
		// 익명의 내부클래스로 객체생성해서 해당타입의 매개변수로 전달하기
		/*
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = ta.getText();
				ta.setText(str + "버튼 클릭됨\n");
				
				if (btn.getText().equals("Action")) {
					btn.setText("액션");
				} else {
					btn.setText("Action");
				}
			}
		});
		*/
		
		// 람다식 : () -> {} 로 함수정의를 전달하는 표현방식
		//   인터페이스가 추상메소드 1개만 가졌을 경우만 람다식을 사용 가능함
		btn.addActionListener(e -> {
			String str = ta.getText();
			ta.setText(str + "버튼 클릭됨\n");
			
			if (btn.getText().equals("Action")) {
				btn.setText("액션");
			} else {
				btn.setText("Action");
			}
		});
		
		setVisible(true); // 창을 눈에 보이기. 화면갱신됨
	} // 생성자
	
	private void setupView() {
		setTitle("이벤트 리스너 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		ta.setEditable(false); // 편집 못하게 막음
		
		c.add(btn);
		c.add(new JScrollPane(ta)); // ta에 스크롤바 부착해서 컨텐트팬에 추가
	}
	
	public static void main(String[] args) {
		new Ex4();
	}

}




