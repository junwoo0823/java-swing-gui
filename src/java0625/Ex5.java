package java0625;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


// 배치관리자 없는 컨테이너에 컴포넌트의 위치와 크기를 절대값으로 지정해야 함.
public class Ex5 extends JFrame {
	
	public Ex5() {
		setTitle("GridLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null); // 컨텐트팬의 배치관리자 제거하기
		
		JLabel jLabel = new JLabel("안녕하세요~");
		jLabel.setLocation(130, 50);
		jLabel.setSize(200, 20);
		contentPane.add(jLabel);
		
		// 버튼 컴포넌트 9개를 동일한 크기로 설정
		// 위치는 서로 다르게 설정
		for (int i=1; i<=9; i++) {
			JButton btn = new JButton(String.valueOf(i));
			btn.setLocation(i*15, i*15); // 버튼 위치 설정
			btn.setSize(50, 20);
			contentPane.add(btn); // 버튼을 컨텐트팬 영역에 부착하기
		}
		
		setLocationByPlatform(true);
		setSize(300, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex5();
	}

}
