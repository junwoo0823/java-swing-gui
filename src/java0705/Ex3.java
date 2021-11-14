package java0705;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.example.repository.MemberDAO;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*

===== 로그인 화면(첫번째 화면) =====
tfId - JTextField
pfPasswd - JPasswordField
btnLogin(버튼명은 "로그인") - JButton
=======================================

===== 회원가입 화면(두번째 화면) =====
tfId - JTextField
lblId - JLabel

pfPasswd - JPasswordField
pfPasswd2 - JPasswordField
lblPasswd - JLabel

tfName - JTextField
tfEmail - JTextField
recvEmail(rdbtnRecv, rdbtnNoRecv) - JRadioButton
btnJoin(버튼명은 "가입하기") - JButton
=======================================

===== 회원가입 화면(세번째 화면) =====
레이블 "메인화면" 텍스트 보여주기
=======================================

*/

public class Ex3 extends JFrame {
	
	public static final String LOGIN_VIEW = "로그인화면";
	public static final String JOIN_VIEW = "회원가입화면";
	public static final String MAIN_VIEW = "메인화면";

	private MemberDAO dao = MemberDAO.getInstance();
	
	private Container c;
	private CardLayout cardLayout = new CardLayout(); // 카드 레이아웃 배치관리자

	private final JPanel panelJoin = new JPanel(); // 회원가입 화면 패널
	private final JPanel panelLogin = new JPanel(); // 로그인 화면 패널
	private final JPanel panelMain = new JPanel(); // 메인 화면 패널

	private final JTextField tfJoinId = new JTextField();
	private final JLabel lblNewLabel = new JLabel("아이디");
	private final JLabel lblId = new JLabel("New label");
	
	private final JLabel lblNewLabel_1 = new JLabel("비밀번호");
	private final JLabel lblNewLabel_1_1 = new JLabel("비밀번호 재확인");
	private final JPasswordField pfPasswd = new JPasswordField();
	private final JPasswordField pfPasswd2 = new JPasswordField();
	private final JLabel lblPasswd = new JLabel("New label");
	
	private final JLabel lblNewLabel_2 = new JLabel("이름");
	private final JTextField tfName = new JTextField();
	
	private final JLabel lblNewLabel_2_1 = new JLabel("이메일");
	private final JTextField tfEmail = new JTextField();
	
	private final JLabel lblNewLabel_2_1_1 = new JLabel("알림메일 수신여부");
	private final JRadioButton rdbtnRecvEmail = new JRadioButton("예");
	private final JRadioButton rdbtnRecvEmailNo = new JRadioButton("아니오");
	private final ButtonGroup g = new ButtonGroup();
	
	private final JButton btnJoin = new JButton("가입하기");
	
	
	private final JLabel lblNewLabel_3 = new JLabel("아이디");
	private final JTextField tfLoginId = new JTextField();
	private final JLabel lblNewLabel_1_2 = new JLabel("비밀번호");
	private final JPasswordField pfLoginPwd = new JPasswordField();
	private final JButton btnLogin = new JButton("로그인");
	
	private final JButton btnGoJoin = new JButton("회원가입");
	
	private final JLabel lblNewLabel_4 = new JLabel("메인 화면");
	
	

	public Ex3() {

		setTitle("화면전환");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLocationByPlatform(true);

		c = getContentPane();
		c.setLayout(cardLayout);
		
		// 화면 레이아웃 준비
		setupViewLogin(); // 로그인 화면 준비
		setupViewJoin(); // 회원가입 화면 준비
		setupViewMain(); // 메인 화면 준비
		
		// 컴포넌트에 이벤트 연결
		
		
		
		cardLayout.show(c, LOGIN_VIEW); // 프로그램시작 첫화면은 로그인화면
		setVisible(true);
	}
	
	private void setupViewLogin() {
		c.add(panelLogin, LOGIN_VIEW);
		
		panelLogin.setLayout(null);
		
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setBounds(43, 133, 75, 35);
		
		panelLogin.add(lblNewLabel_3);
		
		
		tfLoginId.setColumns(10);
		tfLoginId.setBounds(141, 136, 175, 32);
		
		panelLogin.add(tfLoginId);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(43, 181, 75, 33);
		
		panelLogin.add(lblNewLabel_1_2);
		pfLoginPwd.setBounds(141, 189, 175, 25);
		
		panelLogin.add(pfLoginPwd);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(102, 204, 51));
		btnLogin.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnLogin.setBounds(66, 253, 234, 53);
		panelLogin.add(btnLogin);
		
		btnGoJoin.setBounds(216, 349, 84, 23);
		panelLogin.add(btnGoJoin);
		
		btnGoJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(c, JOIN_VIEW);
			}
		});
	}

	private void setupViewJoin() {
		c.add(panelJoin, JOIN_VIEW);
		panelJoin.setLayout(null);
		
//		tfJoinId.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				String joinId = tfJoinId.getText().trim();
//				
//				int count = dao.getCountById(joinId);
//				if (count > 0) { // count == 1
//					lblId.setText("이미 사용중인 아이디입니다.");
//					lblId.setForeground(Color.RED);
//				} else { // count == 0
//					lblId.setText("멋진 아이디네요!");
//					lblId.setForeground(Color.GREEN);
//				}
//			}
//		});
		
		tfJoinId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String joinId = tfJoinId.getText().trim();
				
				int count = dao.getCountById(joinId);
				if (count > 0) { // count == 1
					lblId.setText("이미 사용중인 아이디입니다.");
					lblId.setForeground(Color.RED);
				} else { // count == 0
					lblId.setText("멋진 아이디네요!");
					lblId.setForeground(Color.GREEN);
				}
			}
		});

		tfJoinId.setBounds(147, 43, 175, 32);
		tfJoinId.setColumns(10);
		panelJoin.add(tfJoinId);

		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(49, 40, 75, 35);
		panelJoin.add(lblNewLabel);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(49, 118, 75, 33);
		panelJoin.add(lblNewLabel_1);

		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(12, 163, 112, 25);
		panelJoin.add(lblNewLabel_1_1);

		lblId.setForeground(Color.RED);
		lblId.setBounds(74, 86, 248, 22);
		panelJoin.add(lblId);

		pfPasswd.setBounds(147, 126, 175, 25);
		panelJoin.add(pfPasswd);

		pfPasswd2.setBounds(147, 163, 175, 25);
		panelJoin.add(pfPasswd2);

		lblPasswd.setForeground(Color.RED);
		lblPasswd.setBounds(74, 198, 248, 22);
		panelJoin.add(lblPasswd);

		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setBounds(49, 232, 75, 32);
		panelJoin.add(lblNewLabel_2);

		tfName.setColumns(10);
		tfName.setBounds(147, 230, 175, 34);
		panelJoin.add(tfName);

		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(49, 276, 75, 32);
		panelJoin.add(lblNewLabel_2_1);

		tfEmail.setColumns(10);
		tfEmail.setBounds(147, 274, 175, 34);
		panelJoin.add(tfEmail);

		rdbtnRecvEmail.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnRecvEmail.setBounds(177, 336, 53, 23);
		panelJoin.add(rdbtnRecvEmail);

		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(12, 328, 133, 32);
		panelJoin.add(lblNewLabel_2_1_1);

		rdbtnRecvEmailNo.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnRecvEmailNo.setBounds(234, 336, 75, 23);
		panelJoin.add(rdbtnRecvEmailNo);
		
		g.add(rdbtnRecvEmail); // 라디오버튼 2개를 그룹화
		g.add(rdbtnRecvEmailNo);
		
		// 가입하기 버튼 눌렀을때
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// rdbtnRecvEmail.isSelected();
				
				
				
				dao.insert(null); // 회원가입 처리

				// 로그인 화면으로 전환
			}
		});

		btnJoin.setForeground(new Color(255, 255, 255));
		btnJoin.setBackground(new Color(51, 204, 51));
		btnJoin.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		btnJoin.setBounds(74, 394, 248, 35);
		panelJoin.add(btnJoin);
	}
	
	private void setupViewMain() {
		c.add(panelMain, MAIN_VIEW);
		panelMain.setLayout(null);
		
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(255, 153, 204));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(103, 77, 160, 76);
		
		panelMain.add(lblNewLabel_4);
	}

	public static void main(String[] args) {
		new Ex3();
	}
}



