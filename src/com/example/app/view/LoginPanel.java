package com.example.app.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.app.MemberManager;
import com.example.domain.MemberVO;
import com.example.repository.MemberDAO;

// 로그인 화면(View) 클래스
public class LoginPanel extends JPanel {

	public static final String VIEW_NAME = "로그인 화면";
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	private CardLayout cardLayout;
	private Container container;
	private MemberManager frame;
	
	//private JPanel panelLogin; // 로그인 화면 패널
	
	private JLabel lblNewLabel_3;
	private JTextField tfLoginId;
	private JLabel lblNewLabel_1_2;
	private JPasswordField pfLoginPwd;
	private JButton btnLogin;  // 로그인하기
	private JButton btnGoJoin;
	
	// 생성자
	public LoginPanel(CardLayout cardLayout, Container container, MemberManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;
		
		init(); // 멤버 초기화 메소드
		setupView(); // 화면 구성하는 메소드
		setupEvent(); // 이벤트 설정하는 메소드
	}

	private void init() {
		//panelLogin = new JPanel();
		
		lblNewLabel_3 = new JLabel("아이디");
		tfLoginId = new JTextField();
		
		lblNewLabel_1_2 = new JLabel("비밀번호");
		pfLoginPwd = new JPasswordField();
		
		btnLogin = new JButton("로그인");
		btnGoJoin = new JButton("회원가입");
	} // init
	
	private void setupView() {
		setLayout(null); // 배치관리자 제거(절대absolute 레이아웃)
		setPreferredSize(new Dimension(700, 400)); // 로그인화면 패널크기 설정
		
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setBounds(185, 76, 75, 35);
		add(lblNewLabel_3);
		
		tfLoginId.setColumns(10);
		tfLoginId.setBounds(283, 79, 175, 32);
		add(tfLoginId);
		
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(185, 124, 75, 33);
		add(lblNewLabel_1_2);
		
		pfLoginPwd.setBounds(283, 132, 175, 25);
		add(pfLoginPwd);
		
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(102, 204, 51));
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnLogin.setBounds(208, 196, 250, 53);
		add(btnLogin);
		
		btnGoJoin.setBounds(321, 292, 121, 35);
		add(btnGoJoin);
	} // setupView
	
	
	private void setupEvent() {
		// 회원가입 버튼 눌렀을때
		btnGoJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 다른화면에 넘겨줄 데이터를 공유 컬렉션에 저장 예시
				//SharedData.MAP.put("aaa", 100);
				
				cardLayout.show(container, JoinView.VIEW_NAME);
				frame.pack();
				frame.setVisible(true);
			}
		});
		
		// 로그인 버튼 눌렀을때
		btnLogin.addActionListener(event -> {
			boolean isOk = verifyInput();
			
			if (!isOk) { // isOk == false
				return;
			}
			
			processLogin();
		});
		
	} // setupEvent
	
	// 로그인 처리
	// 아이디, 비밀번호 모두 입력값 있으면, MemberDAO로 테이블 정보와 비교
	//   -> 아이디와 비밀번호가 모두 테이블정보와 일치하면, 메인화면으로 화면전환
	//       일치하지 않으면, "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다." 대화상자로 알림
	//       MemberDAO의 getMemberById 메소드 리턴값과 비교하기
	private void processLogin() {
		// 사용자가 입력한 아이디, 패스워드 가져오기
		String id = tfLoginId.getText().trim();
		String passwd = String.valueOf(pfLoginPwd.getPassword());
		// DB 테이블로부터 아이디, 패스워드 가져오기
		MemberVO memberVO = memberDAO.getMemberById(id);
		if (memberVO != null) { // 아이디 일치함
			if (passwd.equals(memberVO.getPasswd())) { // 비밀번호 일치함
				// 로그인 성공이면 메인화면으로 전환
				cardLayout.show(container, MainView.VIEW_NAME);
			} else {
				JOptionPane.showMessageDialog(frame, "잘못된 비밀번호입니다.", "에러", JOptionPane.ERROR_MESSAGE);
			}
		} else { // memberVO == null
			JOptionPane.showMessageDialog(frame, "가입하지 않은 아이디입니다.", "에러", JOptionPane.ERROR_MESSAGE);
		}
	} // processLogin
	
	
	// 로그인 입력값 검증하는 메소드
	private boolean verifyInput() {
		// 아이디 입력이 없으면(빈문자열), "아이디를 입력해주세요" 대화상자로 알림
		String id = tfLoginId.getText().trim();
		int idLength = id.length();
		if (idLength == 0) { // id.equals("")
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (!(3 <= idLength && idLength <= 10)) {
			JOptionPane.showMessageDialog(frame, "아이디는 3 ~ 10글자 사이만 가능합니다.", "에러", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		// 비밀번호 입력이 없으면, "비밀번호를 입력해주세요" 대화상자로 알림
		char[] chArr = pfLoginPwd.getPassword();
		String passwd = String.valueOf(chArr); // new String(chArr);
		System.out.println(passwd);
		if (passwd.length() == 0) {
			JOptionPane.showMessageDialog(frame, "비밀번호를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	} // verifyInput

}







