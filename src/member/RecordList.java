package member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class RecordList {
	private ArrayList<Record> recordList = new ArrayList<>();
	
	private final String LIST_FILE_NAME = "RecordList.txt";
	private final String CONFIRM_FILE_NAME = "ConfirmRecord.txt";
	// 프로그램 실행 중에 기록 입력한다면?
	public RecordList() {
		readRecords();
		updateAllRating();
	}
	
	public void inputRecords(Record r) { 
		// 프로그램 실행 중에 사용자의 기록 입력.
		System.out.println("INPUT RECORDS : " + r);
		recordList.add(r);
		writeLine(r, LIST_FILE_NAME);
		updateRating(r);
	}
	

	
	
	
	public String[][] recordData(){
		Collections.sort(recordList, (o1, o2) -> o1.date.compareTo(o2.date));

		String[][] recordData = new String[recordList.size()][];
		for(int i = 0; i < recordList.size(); i++) {
			String[] data = recordList.get(i).toOutputData();
			data[0] = Integer.toString(i + 1);
			recordData[i] = data;
		}
		
		return recordData;
	}
	
	
	void readRecords() { // RecordList에 불러오는 최초 작업
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(LIST_FILE_NAME), "utf-8"))){
			String line;
			while((line = br.readLine()) != null) {
				Record r = parseLine(line);
				if(r != null) recordList.add(r);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void writeLine(Record record, String fileName) { // 이어쓰기
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), "utf-8"))){
			bw.write(record.toString());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Record parseLine(String line) { 
		// line example : 22010939:22010123,W,year:month:day
		String[] parts = line.split(",");
		if(parts.length != 3) {
			System.out.println("INVALID LINE : RECORD");
			return null;
		}
		
		String idPart = parts[0].trim();
		String resultPart = parts[1].trim();
		String datePart = parts[2].trim();
		
		int bID, wID, year, month, day;
		boolean matchResult = resultPart.equals("B");
		
		String ids[] = idPart.split(":");
		String dates[] = datePart.split(":");
		
		bID = Integer.parseInt(ids[0]);
		wID = Integer.parseInt(ids[1]);
		
		year = Integer.parseInt(dates[0]);
		month = Integer.parseInt(dates[1]);
		day = Integer.parseInt(dates[2]);
		
		Record record = new Record(bID, wID, matchResult, new GregorianCalendar(year, month, day));
		
		
		return record;
	}
	
	private void writeRecords() { // recordList에 있는 모든 기록 파일에 덮어쓰기
		
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LIST_FILE_NAME, false), "utf-8"))){ // 덮어쓰기
			for(Record r : recordList) {
				bw.write(r.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateRating(Record r) {
		int bID = r.getbID();
		int wID = r.getwID();
		boolean matchResult= r.getResult();
		Member b = Main.memberRef.members.get(bID);
		Member w = Main.memberRef.members.get(wID);
		double[] res = RatingCalculator.calculateRatings(b.getRating(), w.getRating(), matchResult);
		b.setRating(res[0]);
		w.setRating(res[1]);
	}
	
	
	
	
	public void updateAllRating() { 
		// recordList 바탕으로 모든 레이팅 업데이트
		// memberList의 initRating() 선행되어야 한다

		// 모든 레코드에 대해서 memberRef의 members 접근
		for(Record r : recordList) {
			updateRating(r);
		}
	}
	
	
	
	
}
