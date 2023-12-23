package student;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

import student.SInfo;

public class StudentInfoManager{
	static Scanner scanner = new Scanner(System.in);
    public StudentInfoManager() {
    	loadMembers();
	}
	private static final String FILE_NAME = "기우회재학생명부.txt";
    public static Map<Integer, SInfo> Members = new HashMap<Integer, SInfo>();
    
    public static Map<String, Integer> SIDs = new HashMap<String, Integer>(); 
    // 이름으로 학번 찾기
    
    public void loadMembers() { // 파일에서 멤버 목록을 가져와 HashMap에 저장

        // 현재 사용자의 홈 디렉토리를 얻어옴
        String userHome = System.getProperty("user.home");

        // 바탕화면 디렉토리 경로 조합
        Path desktopPath = Paths.get(userHome, "Desktop");

        // 파일의 전체 경로
        String filePath = desktopPath.resolve(FILE_NAME).toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 파일에서 한 줄을 읽어와서 Member 객체로 변환
            	parseMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseMember(String line) { // 파일에서 Member 가져오기 위함
        // 파일에서 읽어온 한 줄을 파싱하여 Member 객체로 변환
        String[] parts = line.split(","); 
        SInfo tmp;
        if (parts.length == 4) {
            String name = parts[0].trim();
            int StudentInfoId = Integer.parseInt(parts[1].trim());
            String department = parts[2].trim();
            int rating = Integer.parseInt(parts[3].trim());
            tmp = new SInfo(name, department, rating);
            Members.put(StudentInfoId, tmp);
            SIDs.put(name, StudentInfoId);
        } else if (parts.length == 3){
        	String name = parts[0].trim();
        	int SID = Integer.parseInt(parts[1].trim());
        	String department = parts[2].trim();
        	tmp = new SInfo(name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("잘못된 형식의 라인: " + line);
        }	
    }

    
    
    
    public String[][] getRanking() {
    	List<SInfo> list = new ArrayList<SInfo>(Members.values());
    	
    	Collections.sort(list, new Comparator<SInfo>() {
    		public int compare(SInfo s1, SInfo s2) {
    			return Integer.compare(s2.getRating(), s1.getRating());
    		}
		});
    	
    	String[][] result = new String[list.size()][4];
		int rank = 0;
    	for(int i = 0; i< list.size(); i++) {

    		
    		if(list.get(i).getRating() == 1000) continue;
  
    		result[++rank][0] = Integer.toString(rank);
    		result[rank][1] = list.get(i).getName();
    		result[rank][2] = list.get(i).getDepartment();
    		result[rank][3] = Integer.toString(list.get(i).getRating());
    	}
    	
    	return result;
    }
    

    
    public String[] searchMember(int id) {
    	String[][] rankInfo = getRanking();
    	SInfo info = Members.get((Integer)id);
    	int i;
    	for(i = 0; i < rankInfo.length; i++) {
    		if(info.getRating() == Integer.parseInt(rankInfo[i][3]))
    			return rankInfo[i];
    	}
    	return null;
    }
    
    public SInfo getSInfo(int id) {
    	return Members.get(id);
    }
    
    
    

    public void saveMembers() { // 현재 멤버 HashMap을 파일에 저장
       // 현재 사용자의 홈 디렉토리를 얻어옴
       String userHome = System.getProperty("user.home");

       // 바탕화면 디렉토리 경로 조합
       Path desktopPath = Paths.get(userHome, "Desktop");

       // 파일의 전체 경로
       String filePath = desktopPath.resolve(FILE_NAME).toString();

       try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
           // 부원 목록을 파일에 저장
    	   Iterator<Integer> keys = Members.keySet().iterator();
           while(keys.hasNext()) {
        	   int SID = keys.next();
        	   SInfo info = Members.get(SID);
               bw.write(info.getName() + "," + SID + ","
                       + info.getDepartment() + "," + info.getRating());
               bw.newLine();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    
    public void resetRating() { // 모든 사용자의 Rating을 1000으로.
                // 현재 사용자의 홈 디렉토리를 얻어옴
        String userHome = System.getProperty("user.home");

        // 바탕화면 디렉토리 경로 조합
        Path desktopPath = Paths.get(userHome, "Desktop");

        // 파일의 전체 경로
        String filePath = desktopPath.resolve(FILE_NAME).toString();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 파일에서 한 줄을 읽어와서 Member 객체로 변환
            	resetMember(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void resetMember(String line) { // 파일에서 member 가져와서 레이팅 1000으로 초기화
        // 파일에서 읽어온 한 줄을 파싱하여 Member 객체로 변환
        String[] parts = line.split(","); 
        SInfo tmp;
        if (parts.length == 4) {
            String name = parts[0].trim();
            int StudentInfoId = Integer.parseInt(parts[1].trim());
            String department = parts[2].trim();
            int rating = 1000;
            tmp = new SInfo(name, department, rating);
            Members.put(StudentInfoId, tmp);
            SIDs.put(name, StudentInfoId);
        } else if (parts.length == 3){
        	String name = parts[0].trim();
        	int SID = Integer.parseInt(parts[1].trim());
        	String department = parts[2].trim();
        	tmp = new SInfo(name, department, 1000);
        	Members.put(SID, tmp);
        	SIDs.put(name, SID);
        } else {
            System.out.println("잘못된 형식의 라인: " + line);
            return;
        }	
        System.out.println(tmp.getName()+" 레이팅 초기화 : 1000");
    }
    
    public void parseRecord(String line) { // line에서 승패정보 가져와서 rating update
    	String[] parts = line.split(",");
    	String bName;
    	String wName;
    	boolean matchresult;
    	try {
    	if(parts.length == 3) {
    		bName = parts[0].trim();
    		wName = parts[1].trim();
    		
    		if(Integer.parseInt(parts[2].trim()) == 1) matchresult = true;
    		else matchresult = false;
    		updateRating(bName, wName, matchresult);
    		
    	}else 
    		System.out.println("잘못된 형식의 라인 : " + line);
    		
    	} catch (NumberFormatException e) {
    		return;
    	}
    }
    
    public void updateRating(String bName, String wName, boolean matchresult) {
    	int bID = SIDs.get(bName);
    	int wID = SIDs.get(wName);
    	SInfo bInfo = Members.get(bID);
    	SInfo wInfo = Members.get(wID);
    	int tmpRatingB = bInfo.getRating();
    	int tmpRatingW = wInfo.getRating();
    	RatingManager rm = new RatingManager((double)bInfo.getRating(), (double)wInfo.getRating(), matchresult);
    	rm.updateRatings();
    	bInfo.setRating((int)rm.getNewRatingA());
    	wInfo.setRating((int)rm.getNewRatingB());
    	Members.put(bID, bInfo);
    	Members.put(wID, wInfo);
    	System.out.println("레이팅 업데이트 : " + bInfo.getName()+tmpRatingB+"->"+bInfo.getRating() + ", " + wInfo.getName()+tmpRatingW+"->"+wInfo.getRating());
    	saveMembers();
    	loadMembers();
    }
    
    public void updateAllRating() {
    	String FILE_NAME = "대국결과.txt";
    	String userHome = System.getProperty("user.home");
    	
    	Path desktopPath = Paths.get(userHome, "Desktop");
    	
    	String filePath = desktopPath.resolve(FILE_NAME).toString();
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    		String line;
    		
    		while((line = br.readLine()) != null) {
    			parseRecord(line);
    		}
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }

}