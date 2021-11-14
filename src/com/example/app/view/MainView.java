package com.example.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.example.app.MemberManager;
import com.example.domain.MemberVO;
import com.example.repository.MemberDAO;

public class MainView implements Viewable, ActionListener {

	public static final String VIEW_NAME = "메인 화면";
	
	private Vector<String> columnNames = new Vector<>();
	
	private MemberDAO memberDAO = MemberDAO.getInstance();

	// 화면 이동시 cardLayout, container 필요함
	private CardLayout cardLayout;
	private Container container;
	private MemberManager frame;
	
	private JPanel panelMain; // 메인 화면 패널
	private JLabel lblNewLabel;
	private JPanel panelWest;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField tfId;
	private JPanel panel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;
	private JPanel panel_2;
	private JLabel lblNewLabel_3;
	private JTextField tfName;
	private JPanel panel_3;
	private JLabel lblNewLabel_4;
	private JTextField tfEmail;
	private JPanel panel_4;
	private JLabel lblNewLabel_5;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	
	private JTable table;
	private DefaultTableModel tableModel;
	
	private JPanel panelSouth;
	private JButton btnTotal;
	private JButton btnAdd;
	private JButton btnModify;
	private JButton btnRemove;
	private ButtonGroup buttonGroup;
	
	public MainView(CardLayout cardLayout, Container container, MemberManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;
		
		init(); // 멤버 초기화 메소드
		setupView(); // 화면 구성하는 메소드
		setupEvent(); // 이벤트 설정하는 메소드
	}

	private void init() {
		panelMain = new JPanel();
		lblNewLabel = new JLabel("메인화면 - 회원관리");
		
		panelWest = new JPanel();
		panel = new JPanel();
		lblNewLabel_1 = new JLabel("아이디");
		tfId = new JTextField();
		panel_1 = new JPanel();
		lblNewLabel_2 = new JLabel("비밀번호");
		passwordField = new JPasswordField();
		panel_2 = new JPanel();
		lblNewLabel_3 = new JLabel("이름");
		tfName = new JTextField();
		panel_3 = new JPanel();
		lblNewLabel_4 = new JLabel("이메일");
		tfEmail = new JTextField();
		panel_4 = new JPanel();
		
		lblNewLabel_5 = new JLabel("메일수신");
		rdbtnYes = new JRadioButton("예");
		rdbtnNo = new JRadioButton("아니오");
		buttonGroup = new ButtonGroup();
		
		table = new JTable();
		tableModel = new DefaultTableModel();
		
		panelSouth = new JPanel();
		
		btnTotal = new JButton("전체보기");
		btnAdd = new JButton("추가");
		btnModify = new JButton("수정");
		btnRemove = new JButton("삭제");
		
		// 컬럼이름 준비
		columnNames.add("아이디");
		columnNames.add("비밀번호");
		columnNames.add("이름");
		columnNames.add("이메일");
		columnNames.add("메일수신");
		columnNames.add("등록날짜");
	} // init
	
	private void setupView() {
		panelMain.setLayout(new BorderLayout());
		panelMain.setPreferredSize(new Dimension(700, 400));
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setOpaque(true);
		panelMain.add(lblNewLabel, BorderLayout.NORTH);
		
		panelMain.add(panelWest, BorderLayout.WEST);
		
		panelWest.setLayout(new GridLayout(5, 1, 0, 0));
		
		panelWest.add(panel);
		panel.add(lblNewLabel_1);
		panel.add(tfId);
		
		panelWest.add(panel_1);
		panel_1.add(lblNewLabel_2);
		passwordField.setColumns(9);
		panel_1.add(passwordField);
		
		panelWest.add(panel_2);
		panel_2.add(lblNewLabel_3);
		panel_2.add(tfName);
		
		panelWest.add(panel_3);
		panel_3.add(lblNewLabel_4);
		panel_3.add(tfEmail);
		
		panelWest.add(panel_4);
		panel_4.add(lblNewLabel_5);
		panel_4.add(rdbtnYes);
		panel_4.add(rdbtnNo);
		buttonGroup.add(rdbtnYes);
		buttonGroup.add(rdbtnNo);
		
		panelMain.add(new JScrollPane(table), BorderLayout.CENTER);
		panelMain.add(panelSouth, BorderLayout.SOUTH);
		
		panelSouth.add(btnTotal);
		panelSouth.add(btnAdd);
		panelSouth.add(btnModify);
		panelSouth.add(btnRemove);
		
		tfEmail.setColumns(10);
		tfName.setColumns(10);
		tfId.setColumns(10);
	} // setupView
	
	private void setupEvent() {
		// 버튼 4개 Action 이벤트 연결
		btnTotal.addActionListener(this);
		btnAdd.addActionListener(this);
		btnModify.addActionListener(this);
		btnRemove.addActionListener(this);
	}
	
	
	private void total() {
		
		List<MemberVO> list = memberDAO.getMembers();
		
		Vector<Vector<Object>> vector = getVectorFromList(list);
		
		// 첫번째 매개변수는 Vector 데이터, 두번째 매개변수는 Vector 열이름
		tableModel.setDataVector(vector, columnNames);
		// 테이블모델을 테이블 컴포넌트에 설정하여 테이블에 데이터 보이기
		table.setModel(tableModel);
	} // total
	
	
	private Vector<Vector<Object>> getVectorFromList(List<MemberVO> list) {
		// List<MemberVO> -> Vector<Vector<Object>> 로 변환하기
		Vector<Vector<Object>> vector = new Vector<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		for (MemberVO member : list) {
			Vector<Object> rowVector = new Vector<>();
			rowVector.add(member.getId());
			rowVector.add(member.getPasswd());
			rowVector.add(member.getName());
			rowVector.add(member.getEmail());
			rowVector.add(member.getRecvEmail());
			rowVector.add(sdf.format(member.getRegDate()));
			
			vector.add(rowVector);
		} // for
		
		return vector;
	} // listToVector
	
	
	
	private void add() {
		String id = tfId.getText().trim();
		String passwd = String.valueOf(passwordField.getPassword());
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String recvEmail = rdbtnYes.isSelected() ? "Y" : "N" ;
		
		MemberVO memberVO = new MemberVO(id, passwd, name, email, 
							recvEmail, new Timestamp(System.currentTimeMillis()));
		
		memberDAO.insert(memberVO);
		
		JOptionPane.showMessageDialog(frame, "회원가입성공", "회원가입성공", JOptionPane.INFORMATION_MESSAGE);
		
		tfId.setText("");
		passwordField.setText("");
		tfName.setText("");
		tfEmail.setText("");
		buttonGroup.clearSelection();
		//rdbtnYes.setSelected(false);
		
		total();
	} // add
	
	
	private void modify() {
		String id = tfId.getText().trim();
		
		int result = JOptionPane.showConfirmDialog(frame, 
				id + " 회원정보를 정말 수정하겠습니까?", 
				"회원정보 수정", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE);
		
		switch (result) {
		case JOptionPane.CANCEL_OPTION:
		case JOptionPane.NO_OPTION:
			return;
		}
		
		String passwd = String.valueOf(passwordField.getPassword());
		String name = tfName.getText().trim();
		String email = tfEmail.getText().trim();
		String recvEmail = rdbtnYes.isSelected() ? "Y" : "N" ;
		Timestamp regDate = new Timestamp(System.currentTimeMillis());
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPasswd(passwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		memberVO.setRecvEmail(recvEmail);
		memberVO.setRegDate(regDate);
		
		memberDAO.updateById(memberVO);
		
		JOptionPane.showMessageDialog(frame, id + " 회원정보를 수정했습니다.!", "회원정보 수정 성공", JOptionPane.INFORMATION_MESSAGE);
		
		tfId.setText("");
		passwordField.setText("");
		tfName.setText("");
		tfEmail.setText("");
		buttonGroup.clearSelection();
		//rdbtnYes.setSelected(false);
		
		total();
	} // modify
	
	
	private void remove() {
		
		String id = tfId.getText().trim();
		
		int result = JOptionPane.showConfirmDialog(frame, 
				id + " 회원정보를 정말 삭제하겠습니까?", 
				"회원정보 삭제", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.WARNING_MESSAGE);
		
//		if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.NO_OPTION) {
//			return;
//		}
		
		switch (result) {
		case JOptionPane.CANCEL_OPTION:
		case JOptionPane.NO_OPTION:
			return;
		}
		
		memberDAO.deleteById(id);
		
		JOptionPane.showMessageDialog(frame, id + " 회원정보를 삭제했습니다.!", "회원정보 삭제 성공", JOptionPane.INFORMATION_MESSAGE);
		
		total();
	} // remove
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnName = e.getActionCommand(); // 버튼 이름 가져오기
		System.out.println(btnName + " 버튼 눌러짐");
//		switch (btnName) {
//		case "전체보기": total(); break;
//		case "추가": add(); break;
//		case "수정": modify(); break;
//		case "삭제": remove(); break;
//		}
		
		JButton btn = (JButton) e.getSource();
		if (btn == btnTotal) {
			total();
		} else if (btn == btnAdd) {
			add();
		} else if (btn == btnModify) {
			modify();
		} else if (btn == btnRemove) {
			remove();
		}
	}

	@Override
	public JPanel getView() {
		return panelMain;
	}
}








