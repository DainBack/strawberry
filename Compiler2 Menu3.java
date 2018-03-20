import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.ErrorManager;

import javax.swing.filechooser.*;

public class Menu3 extends JFrame  {

   Container contentPane;
   JTextArea editArea, resultArea; 
   JTextPane text1, text2;
   JLabel imageLabel = new JLabel();
   String thePath;
   JDesktopPane desk = new JDesktopPane();
     public Menu3() {
      setTitle("Create Menu");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      contentPane = getContentPane();
     
      editArea = new JTextArea(" ", 7, 20);
      resultArea = new JTextArea(" ", 7, 20);
      
      getContentPane().setLayout(null);
      getContentPane().add(editArea);
      getContentPane().add(resultArea);  
      
      text1 = new JTextPane();
      text1.setBounds(0, 0, 282, 216);
      getContentPane().add(text1);
      
      text2 = new JTextPane();
      text2.setBounds(0, 220, 282, 180);
      getContentPane().add(text2);
      
      
      createMenu();
      setSize(300,473);
      setVisible(true);
         
   } //jmenu end

      
 
    void createMenu() {
       
   
   JMenuBar mb = new JMenuBar();
   
   mb.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); //designed Color 
   mb.setOpaque(true);
   mb.setBackground(Color.ORANGE);
   
   JMenu fileMenu= new JMenu("File      "); //1
   JMenuItem open = new  JMenuItem("Open"); //1-1 
   fileMenu.add(open);
   open.addActionListener(new ActionListener()  
   {     JFileChooser chooser;
         JTabbedPane tab;
         Menu menu;
      public void actionPerformed(ActionEvent e)  
      {   chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
             "Java & txt Files", "java", "txt");
       chooser.setFileFilter(filter);
       int ret = chooser.showOpenDialog(null);
       if(ret != JFileChooser.APPROVE_OPTION){
          JOptionPane.showMessageDialog(null, "Do not Choose file", "error",
                JOptionPane.WARNING_MESSAGE);
          return;
       } //end actionPerformed(ActionEvent e)
       String filePath  = chooser.getSelectedFile().getPath();
       String fileName = chooser.getSelectedFile().getName();
       pack();
       
       if(ret == JFileChooser.APPROVE_OPTION){
           
          String fN;   
           String dir;  
           String str;  
           File file_open = chooser.getSelectedFile();
           FileInputStream fis;  
           ByteArrayOutputStream bo; 
      
      try  
      {  
       fis = new FileInputStream(file_open); 
       bo = new ByteArrayOutputStream();     
       int i = 0;  
       while ((i = fis.read()) != -1) 
       {  
        bo.write(i);                  
       }  
        text1.setText(bo.toString()); 
       fis.close();                               
       bo.close();                 
      }  
      catch(FileNotFoundException fe)  
      {}  
      catch(IOException ie)  
      {}  
       }//if end
       

       if(e.getSource() == open){
          contentPane.add(desk,BorderLayout.CENTER);
           ErrorManager sub = new ErrorManager();
           sub.tab.setSelectedIndex(1);
            
           try{
               sub.setMaximum(true);
           }catch (Exception ex) {
               // TODO: handle exception
           }
       }
       class EmpManager extends JInternalFrame{
          public EmpManager(){
                tab = new JTabbedPane(JTabbedPane.TOP);
                tab.add(fileName, menu);
                getContentPane().add(tab,BorderLayout.CENTER);
                 
            }
          
       }
      }  //end actionPerformed(ActionEvent e)
      
      
     });  
   
   JMenuItem close = new  JMenuItem("Close");//1-2
   fileMenu.add(close);
   close.addActionListener(new ActionListener()  
   {   
      public void actionPerformed(ActionEvent e)  
      {  
       
      }  
     });  
   
   
   JMenuItem save = new  JMenuItem("Save");//1-3
   fileMenu.add(save);
   save.addActionListener(new ActionListener()  
   {  JFileChooser chooser = new JFileChooser();
      public void actionPerformed(ActionEvent e)  
      {  
         int ret = chooser.showOpenDialog(Menu3.this);
       if (ret==JFileChooser.APPROVE_OPTION) 
       {  
        File file_open = chooser.getSelectedFile(); 
      
        try  
        {  
        PrintWriter pw   = new PrintWriter(new BufferedWriter(new FileWriter(file_open))); // PrintWriter객체를 생성해서  
        pw.write(text1.getText()); 
        pw.close();  
        }  
          
        catch(FileNotFoundException ie2)  
           {}  
        catch(IOException ie)   
           {} 
        
        int result = JOptionPane.showConfirmDialog(null, "Will you continue?", "Confirm",JOptionPane. YES_NO_OPTION );      
        if(result == JOptionPane. CLOSED_OPTION )      
           text1.setText("Just Closed without Selection");
         else if(result == JOptionPane. YES_OPTION )      
            text1.setText("Yes");      
         else      
            text1.setText("No");
       }
       contentPane.add(new JScrollPane(text1));  
      }  
     });  
   
   
   JMenuItem save_as = new  JMenuItem("Save as");//1-4
   fileMenu.add(save_as);
   save_as.addActionListener(new ActionListener()  
   {   
      public void actionPerformed(ActionEvent e)  
      {  
       
      }  
     });  
   
   
   JMenuItem quit = new  JMenuItem("Quit");//1-5
   fileMenu.add(quit); 
   quit.addActionListener(new ActionListener()  
   {   
      public void actionPerformed(ActionEvent e)  
      {  
       dispose();
       System.exit(0);   
      }  
     });  
  
   
   JMenu fileRun = new JMenu("Run       ");//2
   
   JMenuItem compile = new  JMenuItem("Compile");//2-1
   compile.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));
   fileRun.add(compile);
   
   compile.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          Process ps = null; 
        try {//C:\Dev\java\TermPro\Hello.java
          String s=null;
               Process Process = new ProcessBuilder("cmd.exe", "/c", "java", thePath).start(); 
               // 외부 프로그램 출력 읽기
               BufferedReader stdOut   = new BufferedReader(new InputStreamReader(Process.getInputStream()));
               BufferedReader stdError = new BufferedReader(new InputStreamReader(Process.getErrorStream()));
               
            
               while ((s =  stdOut.readLine()) != null) { 
                  text2.setText(s);  } 
               while ((s = stdError.readLine()) != null) { text2.setText(s);}
              
                   
               text2.setText("Exit Code: " + Process.exitValue());
               System.exit(Process.exitValue()); // 외부 프로그램의 반환값을, 이 자바 프로그램 자체의 반환값으로 삼기
               

             } catch (IOException exe) { 
                text2.setText("Erorr! External command execution failed.\n" + exe.getMessage());
             }
        text2.setText(thePath);
       }
   });//end actionPerformed() 
  
   JDesktopPane desk = new JDesktopPane();
   
   fileMenu.setBackground(Color.ORANGE);
   fileRun.setBackground(Color.ORANGE);

   mb.add(fileMenu);
   mb.add(fileRun); 
 
   this.setJMenuBar(mb);
   
 } //creat menu end
 
   
    

  public static void main(String[] args) {
      new Menu3();
   }
}//jframe end