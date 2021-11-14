package java0702;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ex2 extends JFrame {

	public Ex2() {
		super("레이블");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// 텍스트 레이블
		JLabel textLabel = new JLabel("사랑합니다.");
		
		// 이미지 레이블
		ImageIcon beauty = new ImageIcon("images/beauty.jpg");
		JLabel imageLabel = new JLabel(beauty); // 이미지 레이블
		
		// 문자열과 이미지 모두 표현하는 레이블
		ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");
		JLabel label = new JLabel("보고 싶으면 전화하세요", normalIcon, SwingConstants.CENTER);
		
		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);
		
		JLabel label2 = new JLabel("");
		label2.setIcon(new ImageIcon("C:\\kjw\\workspace_new\\java-swing-gui\\images\\apple.jpg"));
		getContentPane().add(label2);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex2();
	}

}
