package com.example.app.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.app.MemberManager;
import com.example.domain.MemberVO;
import com.example.domain.SharedData;
import com.example.repository.MemberDAO;

public class JoinView implements Viewable {

	public static final String VIEW_NAME = "회원가입 화면";

	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	// 화면 이동시 cardLayout, container 필요함
	private CardLayout cardLayout;
	private Container container;
	private MemberManager frame;

	private JPanel panelJoin; // 회원가입 화면 패널

	private JTextField tfJoinId;
	private JLabel lblNewLabel;
	private JLabel lblId;

	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JPasswordField pfPasswd;
	private JPasswordField pfPasswd2;
	private JLabel lblPasswd;

	private JLabel lblNewLabel_2;
	private JTextField tfName;

	private JLabel lblNewLabel_2_1;
	private JTextField tfEmail;

	private JLabel lblNewLabel_2_1_1;
	private JRadioButton rdbtnRecvEmail;
	private JRadioButton rdbtnRecvEmailNo;
	private ButtonGroup g;

	private JButton btnJoin = new JButton("가입하기");

	public JoinView(CardLayout cardLayout, Container container, MemberManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;

		init(); // 멤버 초기화 메소드
		setupView(); // 화면 구성하는 메소드
		setupEvent(); // 이벤트 설정하는 메소드
	}

	private void init() {
		panelJoin = new JPanel();

		lblNewLabel = new JLabel("아이디");
		tfJoinId = new JTextField();
		lblId = new JLabel("New label");

		lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1_1 = new JLabel("비밀번호 재확인");
		pfPasswd = new JPasswordField();
		pfPasswd2 = new JPasswordField();
		lblPasswd = new JLabel("New label");

		lblNewLabel_2 = new JLabel("이름");
		tfName = new JTextField();

		lblNewLabel_2_1 = new JLabel("이메일");
		tfEmail = new JTextField();

		lblNewLabel_2_1_1 = new JLabel("알림메일 수신여부");
		rdbtnRecvEmail = new JRadioButton("예");
		rdbtnRecvEmailNo = new JRadioButton("아니오");
		g = new ButtonGroup();

		btnJoin = new JButton("가입하기");
	} // init

	private void setupView() {
		panelJoin.setLayout(null);
		panelJoin.setPreferredSize(new Dimension(700, 400)); // 회원가입화면 패널크기 설정

		tfJoinId.setBounds(273, 15, 175, 32);
		tfJoinId.setColumns(10);
		panelJoin.add(tfJoinId);

		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(175, 12, 75, 35);
		panelJoin.add(lblNewLabel);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(175, 90, 75, 33);
		panelJoin.add(lblNewLabel_1);

		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(138, 135, 112, 25);
		panelJoin.add(lblNewLabel_1_1);

		lblId.setForeground(Color.RED);
		lblId.setBounds(200, 58, 248, 22);
		panelJoin.add(lblId);

		pfPasswd.setBounds(273, 98, 175, 25);
		panelJoin.add(pfPasswd);

		pfPasswd2.setBounds(273, 135, 175, 25);
		panelJoin.add(pfPasswd2);

		lblPasswd.setForeground(Color.RED);
		lblPasswd.setBounds(200, 170, 248, 22);
		panelJoin.add(lblPasswd);

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setBounds(175, 204, 75, 32);
		panelJoin.add(lblNewLabel_2);

		tfName.setColumns(10);
		tfName.setBounds(273, 202, 175, 34);
		panelJoin.add(tfName);

		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(175, 248, 75, 32);
		panelJoin.add(lblNewLabel_2_1);

		tfEmail.setColumns(10);
		tfEmail.setBounds(273, 246, 175, 34);
		panelJoin.add(tfEmail);

		rdbtnRecvEmail.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnRecvEmail.setBounds(316, 300, 53, 23);
		panelJoin.add(rdbtnRecvEmail);

		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(138, 292, 133, 32);
		panelJoin.add(lblNewLabel_2_1_1);

		rdbtnRecvEmailNo.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnRecvEmailNo.setBounds(373, 300, 75, 23);
		panelJoin.add(rdbtnRecvEmailNo);

		g.add(rdbtnRecvEmail); // 라디오버튼 2개를 그룹화
		g.add(rdbtnRecvEmailNo);

		btnJoin.setForeground(new Color(255, 255, 255));
		btnJoin.setBackground(new Color(51, 204, 51));
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnJoin.setBounds(200, 339, 248, 35);
		panelJoin.add(btnJoin);
	}

	private void setupEvent() {
//		tfJoinId.addFocusListener(new FocusAdapter() {
//		@Override
//		public void focusLost(FocusEvent e) {
//			String joinId = tfJoinId.getText().trim();
//			
//			int count = dao.getCountById(joinId);
//			if (count > 0) { // count == 1
//				lblId.setText("이미 사용중인 아이디입니다.");
//				lblId.setForeground(Color.RED);
//			} else { // count == 0
//				lblId.setText("멋진 아이디네요!");
//				lblId.setForeground(Color.GREEN);
//			}
//		}
//	});

		tfJoinId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String joinId = tfJoinId.getText().trim();

				int count = memberDAO.getCountById(joinId);
				if (count > 0) { // count == 1
					lblId.setText("이미 사용중인 아이디입니다.");
					lblId.setForeground(Color.RED);
				} else { // count == 0
					lblId.setText("멋진 아이디네요!");
					lblId.setForeground(Color.GREEN);
				}
			}
		});
		
		// 패스워드 재확인 입력상자에 포커스가 사라졌을때
		pfPasswd2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String passwd1 = new String(pfPasswd.getPassword());
				String passwd2 = new String(pfPasswd2.getPassword());
				
				if (!passwd1.equals(passwd2)) { // passwd1.equals(passwd2) == false
					lblPasswd.setText("비밀번호가 일치하지 않습니다.");
					lblPasswd.setForeground(Color.RED);
				} else {
					lblPasswd.setText("비밀번호가 일치합니다.");
					lblPasswd.setForeground(Color.GREEN);
				}
			}
		});

		
		// 가입하기 버튼 눌렀을때
		btnJoin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// 다른 화면으로부터 넘겨받은 데이터 찾기 예시
				//int num = (int) SharedData.MAP.get("aaa");
				//System.out.println("num = " + num);
				
				processJoin(); // 회원가입 처리하기
				
				JOptionPane.showMessageDialog(frame, "회원가입이 성공했습니다!", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);

				// 로그인 화면으로 전환
				cardLayout.show(container, LoginPanel.VIEW_NAME);
				//frame.pack();
			}
		});
	} // setupEvent
	
	
	// 회원가입 처리 메소드
	private void processJoin() {
		String id = tfJoinId.getText().trim();
		// 아이디 입력값이 없을때
		if (id.length() == 0) {  // id.equals("")
			JOptionPane.showMessageDialog(frame, "아이디는 필수 입력 요소입니다.", "에러", JOptionPane.ERROR_MESSAGE);
			return; // 작업취소
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(tfJoinId.getText().trim());
		memberVO.setPasswd(new String(pfPasswd.getPassword())); // String.valueOf(pfPasswd.getPassword())
		memberVO.setName(tfName.getText().trim());
		memberVO.setEmail(tfEmail.getText().trim());
		memberVO.setRecvEmail( (rdbtnRecvEmail.isSelected()) ? "Y" : "N" );
		memberVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		memberDAO.insert(memberVO); // DB에 회원가입 insert 처리
	}

	@Override
	public JPanel getView() {
		return panelJoin;
	}
}







