import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class CmdClass {
	static boolean abc;			//������ �����ϸ� true, �����ϸ� false
	
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
        // �ܺ� ���α׷� ��� �б�    
        // "ǥ�� ���"�� "ǥ�� ���� ���"�� ��� 
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
              System.out.println("������ ����");
        }
        // �ܺ� ���α׷� ��ȯ�� ��� (�� �κ��� �ʼ��� �ƴ�)     
        //System.out.println("Exit Code: " + oProcess.exitValue());     

        //System.exit(oProcess.exitValue()); // �ܺ� ���α׷��� ��ȯ����, �� �ڹ� ���α׷� ��ü�� ��ȯ������ ���  
    	return true;
        } 
        catch (IOException e) { // ���� ó��      
           System.err.println("����! �ܺ� ��� ���࿡ �����߽��ϴ�.\n" + e.getMessage());       
         //  System.exit(-1);  
       	return false;
        }//end try/catch 
   }//end main


}