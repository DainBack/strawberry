import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class run_com extends CmdClass{

	public static boolean main(String args[]){
		String output;
		String outputError;
		if(abc) {
			  try {
	               // print a message
	               System.out.println("Executing notepad.exe");

	               // create a process and execute notepad.exe
	               //Process process = Runtime.getRuntime().exec("notepad.exe");
	              
	               List<String> commands = new ArrayList<String>(); 
	               commands.add("cmd.exe");
	               commands.add("/C");
	               commands.add("C:/Users/home/eclipse-workspace/[171023] Term Project/src/" + MainClass.getFileName());
	               Process process = new ProcessBuilder(commands).start(); 
	               BufferedReader stdOut   = new BufferedReader(new InputStreamReader(process.getInputStream()));     
	            
	               while ((output =   stdOut.readLine()) != null) System.out.println(output);     
			  } catch (Exception ex) {
	               ex.printStackTrace();
	            }	         
	  	         abc =  true;  
		}
		else {
			// print another message
            System.out.println("Notepad should now open.");
			System.out.println("Compile Error! File does not exist");
			abc = false;
		}
		
		return abc;
		
		
	}
	
}
	
		

    /*   // print a message
     System.out.println("Executing notepad.exe");

      // create a process and execute notepad.exe
      Process process = Runtime.getRuntime().exec("notepad.exe");

      // print another message
      System.out.println("Notepad should now open.");

     */
		
		
		/*
		try {
			if(abc) {
				Process p = Runtime.getRuntime().exec("System Command");
			    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			    String line = null;
			   
			    while((line = br.readLine()) != null){
			        System.out.println(line);
			        }
            abc = true;
			}
			else {
				System.out.println("Think again : this is an uncompiled file.");
				abc = false;
			}
        } catch (IOException e) {
            System.err.println("IO error: " + e);
        } 
        
		return abc;
		
		
		
		
		
		
		*/
		
	/*	try {
			if(abc) {
			//List<String> commands = new ArrayList<String>(); 
		//	commands.add("cmd.exe");
				String[] command = {"cmd", "/c", "dir", "eplicpse-workspace", MainClass.getFileName()};
	            Process p = Runtime.getRuntime().exec(command);
	            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line = null;
	            while ((line = br.readLine()) != null) {
	            	System.out.println(line);
	            }
	        	abc = true;
				}
			 else {
				 System.out.println("Because of Compile Error, can not implement");
				 abc = false;
			 }
			} catch (Exception e) {
			 System.err.println(e);
			}
		return abc;
	*/
		/*if(abc) {
	
	List<String> commands = new ArrayList<String>(); 
	  commands.add("cmd.exe");
	  commands.add("/C");
	  commands.add("C:/Users/home/eclipse-workspace/[171023] Term Project/src/" + MainClass.getFileName());

		return true;  
}
	else {
		System.out.println("Because of Compile fail, can't implement");
	return false;
	}*/



//CmdClass 상속 생각해볼 것