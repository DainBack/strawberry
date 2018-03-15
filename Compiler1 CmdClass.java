import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class CmdClass {
	static boolean abc;			//컴파일 성공하면 true, 실패하면 false
	
	public static boolean main(String[] args) {          
     /* List<String> commands = new ArrayList<String>();                
      commands.add("cmd.exe");
      commands.add("/C");
      commands.add("C:/Users/home/eclipse-workspace/[171023] Term Project/src/" + MainClass.getFileName());      
      */
       String output; 
       String outputError;
       
        try {     
           
          
        Process oProcess = new ProcessBuilder("cmd", "/c", "dir", "eplicpse-workspace", MainClass.getFileName()).start();
        // 외부 프로그램 출력 읽기    
        // "표준 출력"과 "표준 에러 출력"을 출력 
        if(oProcess.isAlive()) {
        	BufferedReader stdOut = new BufferedReader(new InputStreamReader(oProcess.getInputStream()));
            while ((output = stdOut.readLine()) != null) 
            	System.out.println(output);  	
            System.out.println("compiled successfully");
            abc = true;
        }
        else {
        	  BufferedReader stdError = new BufferedReader(new InputStreamReader(oProcess.getErrorStream()));  
              while ((outputError = stdError.readLine()) != null) System.err.println(outputError);                     	
              abc = false;
              System.out.println("컴파일 오류");
        }
        // 외부 프로그램 반환값 출력 (이 부분은 필수가 아님)     
        //System.out.println("Exit Code: " + oProcess.exitValue());     

        //System.exit(oProcess.exitValue()); // 외부 프로그램의 반환값을, 이 자바 프로그램 자체의 반환값으로 삼기  
    	return true;
        } 
        catch (IOException e) { // 에러 처리      
           System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());       
         //  System.exit(-1);  
       	return false;
        }//end try/catch 
   }//end main


}