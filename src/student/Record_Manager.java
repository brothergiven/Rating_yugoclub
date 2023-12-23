package student;



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Record_Manager extends JFrame{
	private StudentInfoManager SIM;
	private Vector<Vector<String>> Records = new Vector<Vector<String>>();
	public Record_Manager(StudentInfoManager ref) {
		SIM = ref;
		readRecord();
	}
	private static final String FILE_Record = "대국결과.txt";
	// 현재 사용자의 홈 디렉토리를 얻어옴
	String userHome = System.getProperty("user.home");
	// 바탕화면 디렉토리 경로 조합
	Path desktopPath = Paths.get(userHome, "Desktop");
	String fileRecord = desktopPath.resolve(FILE_Record).toString();
	
	public void readRecord() { // 대국결과 기록 파일을 배열에 저장
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRecord), StandardCharsets.UTF_8))) {
    		String line;	
    		while((line = br.readLine()) != null) {
    			parseRecord(line);
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
	}
	    
	public void parseRecord(String line) { // line에서 승패정보 가져와서 rating update
    	String[] parts = line.split(",");
    	String bName;
    	String wName;
    	String result;
    	try {
    		if(parts.length == 3) {
	    		bName = parts[0].trim();
	    		wName = parts[1].trim();
	    		if(parts[2].trim().equals("1")) result = "흑 승";
	    		else if(parts[2].trim().equals("0")) result = "백 승";
	    		else result = "무승부";
	    		addRecord(bName, wName, result);
    		} else 
	    		System.out.println("잘못된 형식의 라인 : " + line);
	    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    }
	    
	public void addRecord(String bName, String wName, String matchresult) {
		Vector<String> lineV = new Vector<String>();
		lineV.add(bName);
		lineV.add(wName);
		lineV.add(matchresult);
		Records.add(lineV);
	}
	
	public Vector<Vector<String>> getRecord(){
		return Records;
	}
	
	public void writeRecord() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRecord))) {
			// 전적 기록을 파일에 저장
				String line;
				Iterator<Vector<String>> it1 = Records.iterator();
				while(it1.hasNext()) {
					Iterator<String> it2 = it1.next().iterator();
					line = it2.next()+","+it2.next()+","+it2.next();
				}
		} catch (IOException e) {
			e.printStackTrace();
        }
    }
	
}