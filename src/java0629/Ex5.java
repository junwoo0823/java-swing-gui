package java0629;

import java.awt.Container;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ex5 extends JFrame {
	private final JTextField textField = new JTextField();
	private final JButton btnNewButton = new JButton("New button");
	private final JLabel lblNewLabel = new JLabel("New label");
	
	public Ex5() {
		super("창 제목");
		textField.setBounds(162, 76, 96, 21);
		textField.setColumns(10);
		getContentPane().setBackground(new Color(204, 255, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(878, 514);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		getContentPane().setLayout(null);
		
		getContentPane().add(textField);
		btnNewButton.setBounds(181, 143, 91, 23);
		
		getContentPane().add(btnNewButton);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 204, 0));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBackground(new Color(204, 51, 51));
		lblNewLabel.setBounds(342, 143, 175, 73);
		lblNewLabel.setOpaque(true); // 불투명 속성 설정 (배경색 나타나게함)
		
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}


	public static void main(String[] args) {
		new Ex5();
	}
}
