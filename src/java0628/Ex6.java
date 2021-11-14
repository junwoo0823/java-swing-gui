package java0628;

public class Ex6 {

	public static void main(String[] args) {
		
		System.out.println(Ex5.b);
		
		System.out.println(Ex5.Movable.RED);
		
		// 정적 내부 클래스(static inner class)는 외부클래스이름.내부클래스이름 타입으로 사용함
		Ex5.MyActionListener5 l = new Ex5.MyActionListener5();
		
	}

}
