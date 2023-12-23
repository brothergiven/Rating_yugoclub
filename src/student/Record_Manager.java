package student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.JFrame;

public class Record_Manager extends JFrame{
	private StudentInfoManager SIM;
	private Vector<Vector<String>> Records = new Vector<Vector<String>>();
	public Record_Manager(StudentInfoManager ref) {
		readRecord();
	}
	
	private static final String FILE_NAME = "대국결과.txt";
	String userHome = System.getProperty("user.home");
	Path desktopPath = Paths.get(userHome, "Desktop");
	String filePath = desktopPath.resolve(FILE_NAME).toString();
	
	public void readRecord() { // 대국결과 기록 파일을 배열에 저장
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            parseRecord(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    	
    	Iterator<Vector<String>> it = Records.iterator();
    	while(it.hasNext()) {
    		Iterator<String> it2 = it.next().iterator();
    		while(it2.hasNext()) {
    			System.out.print(it2.next());
    		}
    		System.out.println();
    	}
	}
	    
	public void parseRecord(String line) { // line에서 승패정보 가져와서 rating update
		Vector<String> lineV = new Vector<String>();
    	String[] parts = line.split(",");
    	try {
    		if(parts.length == 3) {
	    		lineV.add(parts[0].trim());
	    		lineV.add(parts[1].trim());
	    		lineV.add(parts[2].trim());
    		} else 
	    		System.out.println("잘못된 형식의 라인 : " + line);
	    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    	Records.add(lineV);
    }
	    
	    /*
	    public String[][] getRanking() {
	    	return ranking;
	    }
	    
	    public String[] searchMember(int id) {
	    	// 학번 정보 받아서 해당하는 랭킹 행 출력
	    	SInfo info = Members.get((Integer)id);
	    	int i;
	    	for(i = 0; i < ranking.length; i++) {
	    		System.out.println(info.getName().equals(ranking[i][1]));
	    		if(info.getName().equals(ranking[i][1]))
	    			return ranking[i];
	    	}
	    	return null;
	    }
	    */
	//전적 저장
	//파일에서 가저오는 함수
	//사용자에게 입력 받아서 저장하는 함수
	//저장된 데이터를 파일에 쓰는 함수
}
