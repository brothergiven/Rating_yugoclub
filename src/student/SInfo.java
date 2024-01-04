package student;
import java.util.*;


public class SInfo {
	private String name;
	private String department;
	private int rating;
	private int number;
	
	public SInfo(int num, String name, String department, int rating) {
		this.number = num;
		this.name = name;
		this.department = department;
		this.rating = rating;
	}
	
	public int getNumber() { return number; }
	public String getName() { return name;	}
	public String getDepartment() {	return department; }
	public int getRating() { return rating; }
	
	public void setRating(int rating) { this.rating = rating; }
	public void setNumber(int number) { this.number = number; }
	
}