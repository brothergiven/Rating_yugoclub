package student;
import java.util.*;


public class SInfo {
	private String name;
	private String department;
	private int rating;
	
	
	public String getName() { return name;	}
	
	public String getDepartment() {
		return department;
	}
	
	public int getRating() { return rating; }
	
	public SInfo(String name, String department, int rating) {
		this.name = name;
		this.department = department;
		this.rating = rating;
	}
	
	public void setRating(int rating) { this.rating = rating; }
	
	@Override
	public String toString() {
		return "이름 : " + name + " 학과 : " + department + " 레이팅 : " + rating;
	}
}