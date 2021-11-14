package com.example.app;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.example.app.view.JoinView;
import com.example.app.view.LoginPanel;
import com.example.app.view.MainView;

public class MemberManager extends JFrame {
	
	private LoginPanel loginPanel;
	private JoinView joinView;
	private MainView mainView;
	
	public MemberManager() {
		super("회원관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setPreferredSize(new Dimension(700, 500));
		
		setupMenu();
		setupViews();
		
		setResizable(false); // 창크기 조절 못하게 설정
		setVisible(true);
	}
	
	private void setupViews() {
		Container contentPane = getContentPane();
		
		CardLayout cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);
		
		loginPanel = new LoginPanel(cardLayout, contentPane, this); // 로그인 화면 객체 생성
		contentPane.add(loginPanel, LoginPanel.VIEW_NAME); // 패널을 꺼내서 컨텐트팬에 추가
		
		joinView = new JoinView(cardLayout, contentPane, this); // 회원가입 화면 객체 생성
		contentPane.add(joinView.getView(), JoinView.VIEW_NAME); // 패널을 꺼내서 컨텐트팬에 추가
		
		mainView = new MainView(cardLayout, contentPane, this); // 메인 화면 객체 생성
		contentPane.add(mainView.getView(), MainView.VIEW_NAME);
		
		cardLayout.show(contentPane, LoginPanel.VIEW_NAME); // 프로그램시작 첫화면은 로그인화면
		this.pack(); // 프레임창의 크기를 내부 요소에 딱맞게끔 사이즈 재조정(BorderLayout에서 사용됨)
	} // setupViews
	
	
	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("파일");
		JMenuItem item = new JMenuItem("끝내기");
		menu.add(item);
		menuBar.add(menu); // 메뉴바에 메뉴 추가
		
		JMenu menu2 = new JMenu("도움말");
		JMenuItem item1 = new JMenuItem("도움말 보기");
		JMenuItem item2 = new JMenuItem("피드백 보내기");
		JMenuItem item3 = new JMenuItem("메모장 정보");
		menu2.add(item1);
		menu2.add(item2);
		menu2.addSeparator(); // 구분선
		menu2.add(item3);
		menuBar.add(menu2); // 메뉴바에 메뉴 추가
		
		setJMenuBar(menuBar);
		
		// 메뉴아이템에 이벤트 연결
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 프로그램 강제로 정상종료 시키기
			}
		});
	} // setupMenu
	

	public static void main(String[] args) {
		new MemberManager();
	}
}





