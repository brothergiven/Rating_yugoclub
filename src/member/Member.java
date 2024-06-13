package member;

public class Member {
	private int id;
	private double rating;
	private String name;
	private String department;
	private String level;
	
	public Member(int id, String name, String department, String level) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.level = level;
		this.rating = 1000;
	}
	
	
	public int getID() { return id; }
	public String getName() { return name; }
	public String getDepartment() { return department; }
	public String getLevel() { return level; }
	public double getRating() { return rating; }
	
	
	public void setName(String name) { this.name = name; }
	public void setDepartment(String department) { this.department = department; }
	public void setLevel(String level) { this.level = level; }
	public void setRating(double rating) { this.rating = rating; }
	public String[] toOutputData() {
		return new String[] { "", name, department, String.format("%.1f", rating) }; 
	}

	
	public String toString() {
		return String.format("%d:%s:%s:%s:%.1f", id, name, department, level, rating);
	}
	
	
	
}
