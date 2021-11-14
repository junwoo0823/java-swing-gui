package com.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.domain.MemberVO;

// DAO(Data Access Object): 데이터 접근 오브젝트 역할

public class MemberDAO {
	// 싱글톤(singleton) 클래스 설계 : 객체 한개만 공유해서 사용하기
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}

	// 생성자를 private로 외부로부터 숨김
	private MemberDAO() {
	}
	// ========= 싱글톤 설계 완료 =========
	
	// DB접속정보
	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "myuser";
	private final String passwd = "1234";
	
	// DB접속 후 커넥션 객체 가져오는 메소드
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		
		// 1단계. JDBC 드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2단계. DB연결
		con = DriverManager.getConnection(url, user, passwd);
		
		return con;
	} // getConnection
	
	private void close(Connection con, PreparedStatement pstmt) {
		close(con, pstmt, null);
	}
	
	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		// JDBC 자원 닫기 (사용의 역순으로 닫음)
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // close
	
	
	/*
	INSERT INTO member (id, passwd, name, email, recv_email, reg_date) 
	VALUES (?, ?, ?, ?, ?, ?);
	*/
	public int insert(MemberVO memberVO) {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null; // sql문장객체 타입
		
		try {
			con = getConnection(); // 1단계, 2단계 수행 후 커넥션 가져옴
			// con.setAutoCommit(true); // 기본 커밋은 자동커밋으로 설정되있음.
			
			// 3단계. sql 생성
			String sql = "";
			sql  = "INSERT INTO member (id, passwd, name, email, recv_email, reg_date) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?) ";
			// sql문장객체 준비
			pstmt = con.prepareStatement(sql);
			
			// pstmt의 ? 자리에 값 설정
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPasswd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getRecvEmail());
			pstmt.setTimestamp(6, memberVO.getRegDate());
			
			// 4단계. sql문 실행
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return count;
	} // insert
	
	public int deleteAll() {
		int count = 0;
		
		// JDBC
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		
		try {
			con = getConnection();
			// sql문 준비
			String sql = "DELETE FROM member";
			// 3단계. pstmt 문장객체 생성
			pstmt = con.prepareStatement(sql);
			// 4단계. sql 문장 실행
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return count;
	} // deleteAll
	
	
	// 특정 아이디 해당하는 회원정보 삭제하는 메소드
	// DELETE FROM member WHERE id = ?;
	public int deleteById(String id) {
		int count = 0;
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		
		try {
			con = getConnection();
			
			String sql = "DELETE FROM member WHERE id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return count;
	} // deleteById
	
	// 특정 아이디 해당하는 회원정보 수정하는 메소드
	// UPDATE member
	// SET passwd = ?, name = ?, email = ?, recv_email = ?, reg_date = ?
	// WHERE id = ?;
	public void updateById(MemberVO memberVO) {
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		
		try {
			con = getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE member ");
			sb.append("SET passwd = ?, name = ?, email = ?, recv_email = ?, reg_date = ? ");
			sb.append("WHERE id = ? ");
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, memberVO.getPasswd());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getRecvEmail());
			pstmt.setTimestamp(5, memberVO.getRegDate());
			pstmt.setString(6, memberVO.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
	} // updateById
	
	// 특정 아이디 해당하는 회원 레코드(행)의 개수 가져오기
	public int getCountById(String id) {
		int count = 0;
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT count(*) AS cnt FROM member WHERE id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1); // rs.getInt("cnt")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	} // getCountById
	
	
	// SELECT COUNT(*) AS cnt FROM member;
	public int getCountAll() {
		int count = 0;
	
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT count(*) AS cnt FROM member";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1); // rs.getInt("cnt")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	} // getCountAll
	
	
	// 기본키 컬럼값을 기준으로 레코드(행) 최대 1개 가져오기
	// SELECT * FROM member WHERE id = 'bbb';
	public MemberVO getMemberById(String id) {
		MemberVO memberVO = null;
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM member WHERE id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRecvEmail(rs.getString("recv_email"));
				memberVO.setRegDate(rs.getTimestamp("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return memberVO;
	} // getMemberById
	
	
	// select * from member order by name;
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();
		
		Connection con = null; // 접속
		PreparedStatement pstmt = null; // sql문장객체 타입
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "SELECT * FROM member ORDER BY name";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRecvEmail(rs.getString("recv_email"));
				memberVO.setRegDate(rs.getTimestamp("reg_date"));
				
				list.add(memberVO);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	} // getMembers
	
	
	public static void main(String[] args) {
		// MemberDAO 객체 생성
		//MemberDAO dao = new MemberDAO();
		MemberDAO dao = MemberDAO.getInstance(); // 싱글톤 객체 참조 가져오기
		
		// member 테이블 레코드(행) 전체 삭제하기
		int count = dao.deleteAll();
		System.out.println("delete된 행 개수 : " + count);
		System.out.println("현재 행 개수 : " + dao.getCountAll());
		
		System.out.println("============================");
		
		Random random = new Random();
		for (int i=0; i<10; i++) {
			// MemberVO 샘플 데이터 준비
			MemberVO member = new MemberVO();
			member.setId("user" + i);
			member.setPasswd("1234");
			member.setName("홍길동" + i);
			member.setEmail("aaa@a"+ i + ".com");
			member.setRecvEmail( (random.nextBoolean()) ? "Y"  : "N" );
			member.setRegDate(new Timestamp(System.currentTimeMillis()));
			
			// insert 메소드 테스트하기
			count = dao.insert(member);
		} // for
		System.out.println("insert 10번 수행 후 현재 행 개수 : " + dao.getCountAll());
		
		System.out.println("============================");
		
		// getMemberById 메소드 테스트하기
		MemberVO dbMember = dao.getMemberById("user1");
		System.out.println(dbMember.toString());
		
		System.out.println("============================");
		
		// getMembers 메소드 테스트하기
		List<MemberVO> list = dao.getMembers();
		for (MemberVO m : list) {
			System.out.println(m.toString());
		}
		
		System.out.println("============================");
		
		// getCountAll 메소드 테스트
		System.out.println("전체 행 개수 : " + dao.getCountAll());
		
		System.out.println("============================");
		
		// update용 데이터 준비
		MemberVO member = new MemberVO();
		member.setId("user0"); // 수정할 대상 레코드(행)는 아이디 user0 기준
		member.setPasswd("5678");
		member.setName("성춘향");
		member.setEmail("bbb@b.com");
		member.setRecvEmail("Y");
		member.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		// updateById 메소드 테스트
		dao.updateById(member);
		
		MemberVO dbMember2 = dao.getMemberById(member.getId());
		System.out.println(dbMember2);
		
		System.out.println("============================");
		
		// deleteById 메소드 테스트
		dao.deleteById(member.getId()); // user0 아이디 회원정보 삭제
		
		System.out.println("============================");
		
		int rowCount = dao.getCountById(member.getId()); // 삭제한 user0 회원 행의 개수 가져오기
		System.out.println("user0 삭제한 행 개수 : " + rowCount);
		
		System.out.println("============================");
		
		System.out.println("=== 테스트 종료 ===");
	} // main
}








