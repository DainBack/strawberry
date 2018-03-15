import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {

   public static String fileName;
   public static String path;
   public static void setFileName(String value){
      fileName = value;
   }
   
   public static String getFileName(){
      return fileName;
   }
   public static String getPaths() {
	   path = "C:/Users/home/eclipse-workspace/[171023] Term Project/src/" + MainClass.getFileName();
	   return path;
	   
   }
   
   public static void main(String[] args) {
      delete_file deletefile = new delete_file();
      CmdClass cmdClass = new CmdClass();
      run_com runcom = new run_com();
      Scanner a = new Scanner(System.in);
      Scanner b = new Scanner(System.in);
               
       int number;
       String fileName;
         do {
           System.out.println("--------------------------------");
            System.out.println("1. Java File Upload");
            System.out.println("2. Compile");
            System.out.println("3. Run");
            System.out.println("4. Reset");
            System.out.println("5. Compile Error File");
            System.out.println("6. The End");            
            System.out.println("--------------------------------");
            number = a.nextInt();
            
            switch(number) {
                  case 1 :                
                    System.out.println("Enter to file Name");
                    fileName = b.nextLine();
                    System.out.println("Type Java FileName :"+ fileName);
                    setFileName(fileName);
                     break;
                     
                     
                  case 2 :
                      cmdClass.main(args);
                    break;
                     
                  case 3 :
                	 runcom.main(args);
                     break;  
                     
                  case 4 :
                	  deletefile.main(args);
                     break; 
                     
                  case 5 :               
                     break;
                     
                  case 6 :
                     break;
                  }
            
         } while(number != 6);

   }//end Main

}//end Class