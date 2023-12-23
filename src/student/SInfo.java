package student;
import java.util.*;


public class SInfo {
	private String name;
	private String department;
	private int rating;
	private int number;
	
	public void setNumber(int num) {
		number = num;
	}
	
	
	public int getNumber() { return number; }
	
	public String getName() { return name;	}
	
	public String getDepartment() {
		return department;
	}
	
	public int getRating() { return rating; }
	
	public SInfo(int num, String name, String department, int rating) {
		this.number = num;
		this.name = name;
		this.department = department;
		this.rating = rating;
	}
	
	public void setRating(int rating) { 
		this.rating = rating; 
	}
}