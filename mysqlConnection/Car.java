package main;
/*
개별 차 정보를 나타내는 dto 
*/
public class Car {
	private int num;
	private String ctype;
	private int cyear;
	
	//// constructor using all fields
	public Car(int num, String ctype, int cyear) {
		this.num = num;
		this.ctype = ctype;
		this.cyear = cyear;
	}
	
	//// getters
	public int getNum() {return num;}
	public String getCtype() {return ctype;}
	public int getCyear() {return cyear;}
}
