package member;

import java.util.GregorianCalendar;

import main.Main;

public class Record {
	private int wID;
	private int bID;
	GregorianCalendar date;
	private boolean matchResult;
	public Record(int bID, int wID, boolean matchResult, GregorianCalendar date) {
		this.wID = wID; this.bID = bID; this.matchResult = matchResult;
		this.date = date;
	}
	
	public int getwID() { return wID; }
	
	public int getbID() { return bID; }
	
	public boolean getResult() { return matchResult; }
	
	
	public GregorianCalendar getDate() { return date; }
	
	public String[] toOutputData() {
		Member w = Main.memberRef.members.get(wID);
		Member b = Main.memberRef.members.get(bID);
		String result = matchResult ? "흑 승" : "백 승";
		return new String[] { "", b.getName(), w.getName(), result, String.format("%d년 %d월 %d일", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)) };
	}
	
	
	public String toString() {
		String s1 = String.format("%d:%d,", bID,wID);
		String s2 = String.format(",%d:%d:%d", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH), date.get(GregorianCalendar.DAY_OF_MONTH));
		return matchResult ? s1+"B"+s2 : s1+"W"+s2;
	}
}
