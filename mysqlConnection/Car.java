package main;
/*
차 
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
