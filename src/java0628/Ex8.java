package java0628;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ex8 extends JFrame {
	
	private JTextField tfOperand1 = new JTextField(7);
	private JTextField tfOperator = new JTextField(5);
	private JTextField tfOperand2 = new JTextField(7);
	private JLabel lable = new JLabel(" = ");
	private JTextField tfResult = new JTextField(10);
	private JButton btn = new JButton("계산");
	
	public Ex8() {
		setTitle("초간단 사칙연산 계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 200);
		setLocationByPlatform(true);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(tfOperand1);
		c.add(tfOperator);
		c.add(tfOperand2);
		c.add(lable);
		c.add(tfResult);
		c.add(btn);
		
		btn.addActionListener(event -> {
			
			String strNum1 = tfOperand1.getText().trim(); // "20"
			String strNum2 = tfOperand2.getText().trim();
			String strOp = tfOperator.getText().trim();
			
			int num1 = Integer.parseInt(strNum1); // 20
			int num2 = Integer.parseInt(strNum2);
			char chOp = strOp.charAt(0);
			int num3 = 0;
			
			switch (chOp) {
			case '+': num3 = num1 + num2; break;
			case '-': num3 = num1 - num2; break;
			case '*': num3 = num1 * num2; break;
			case '/': num3 = num1 / num2; break;
			}
			
			tfResult.setText(Integer.toString(num3)); // String.valueOf(num3);
		});
		// 버튼 클릭하면 tfOperand1 와 tfOperand2의 입력값을 tfOperator 로 사칙연산해서
		// 결과값을 tfResult에 나타내기
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ex8();
	}
}











