package student;



import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class RecordManager extends JFrame{
	private Vector<Vector<String>> Records = new Vector<Vector<String>>();
	public RecordManager() {
		readRecord();
	}
	private static final String FILE_Record = "대국결과.txt";
	// 현재 사용자의 홈 디렉토리를 얻어옴
	String userHome = System.getProperty("user.home");
	// 바탕화면 디렉토리 경로 조합
	Path desktopPath = Paths.get(userHome, "Desktop");
	String fileRecord = desktopPath.resolve(FILE_Record).toString();
	
	public void readRecord() { // 대국결과 기록 파일을 배열에 저장
		Records = new Vector<Vector<String>>();
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileRecord), StandardCharsets.UTF_8))) {
    		String line;	
    		while((line = br.readLine()) != null) {
    			parseRecord(line);
    		}
    	} catch(IOException e) {
    		System.out.println("ReadRecord Failed : IOException");
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
	    		else if(parts[2].trim().equals("흑 승")) result = parts[2].trim();
	    		else if(parts[2].trim().equals("백 승")) result = parts[2].trim();
	    		else result = "무승부";
	    		System.out.println("Read Record : "+bName+wName+result);
	    		addRecord(bName, wName, result);
    		} else 
	    		System.out.println("잘못된 형식의 라인 : " + line);
	    		
    	} catch (NumberFormatException e) {
    		System.out.println("parseRecord failed : " + line);
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
		System.out.println("RecordManager : writeRecord");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileRecord))) {
			// 전적 기록을 파일에 저장
				String line;
				for(int i = 0; i < Records.size(); i++) {
					line = Records.get(i).get(0)+","+Records.get(i).get(1)+","+Records.get(i).get(2);
					bw.write(line);
					bw.newLine();
					System.out.println("Write Record : "+line);
				}
				
		} catch (IOException e) {
			e.printStackTrace();
        }
    }
	
	
	
}