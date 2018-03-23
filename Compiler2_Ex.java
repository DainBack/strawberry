import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Compiler_Ex extends JFrame {
   Container contentPane;
   JButton j1, j2, j3, j4, j5, j6;
   JTextField textField;
   JTextField textField_1;
   TextArea textArea, textArea_1;
   String thePath;
   String s;
   public Compiler_Ex() {
    
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = getContentPane();
      contentPane.setLayout(null);
      
      textField = new JTextField();
      textField.setBounds(14, 12, 240, 24);
      contentPane.add(textField);
      textField.setColumns(20);
      
      j1 = new JButton("Open");
      j1.setBounds(294, 11, 105, 27);
      j1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thePath = textField.getText();
                File file = new File(thePath);
                if(file.exists()) {
                  textArea_1.append("file exist!\n");
                  BufferedReader br = null;
                     try {
                         br = new BufferedReader(new FileReader(file));
                         String line;
                         while ((line = br.readLine()) != null) {
                            textArea.append(line);
                            textArea.append("\n");
                         }
                     } catch (FileNotFoundException e1) {
                         e1.printStackTrace();
                     } catch (IOException e2) {
                         e2.printStackTrace(); 
                         }
             }
             else 
                textArea_1.append("file not exist\n");
            }
      });
      contentPane.add(j1);
      
      textField_1 = new JTextField();
      textField_1.setBounds(14, 48, 240, 24);
      contentPane.add(textField_1);
      textField_1.setColumns(20);
      
      j2 = new JButton("Save");
      j2.setBounds(294, 47, 105, 27);
      contentPane.add(j2);
      j2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(textField_1.getText(), true));
                File f = new File(textField_1.getText()); 
                if(f.isFile()) {
                   textArea_1.setText("Save Success");
                   out.write(textArea.getText());
                   out.close();
                } else {
                   textArea_1.setText("Save Error");
                }
              } catch (IOException e01) {
                  System.err.println(e01);
                  System.exit(1);
              }
         }
      });
      
      textArea = new TextArea();
      textArea.setBounds(10, 105, 405, 190);
      contentPane.add(textArea);
      
      j3 = new JButton("Compile");
      j3.setBounds(10, 319, 87, 25);
      j3.addActionListener(new ActionListener() {  
          public void actionPerformed(ActionEvent e) {
             Process ps = null; 
           try {
                Process Process = new ProcessBuilder("cmd.exe", "/c", "java", thePath).start(); 
                  BufferedReader stdOut   = new BufferedReader(new InputStreamReader(Process.getInputStream()));
                  BufferedReader stdError = new BufferedReader(new InputStreamReader(Process.getErrorStream()));
                  String s=null;
                  while ((s =  stdOut.readLine()) != null)    textArea_1.append(s); 
                  while ((s = stdError.readLine()) != null)    textArea_1.append(s);
                //  System.out.println("Exit Code: " + Process.exitValue());
                //  System.exit(Process.exitValue());
                } catch (IOException exe) {
                  textArea_1.append("Error! External command execution failed\n" + exe.getMessage());
                }
          }//end actionPerformed() 

       private TextArea Process() {
          return null;
       }
       });
      contentPane.add(j3);
      
      j4 = new JButton("Save Errors");
      j4.setBounds(111, 319, 87, 25);
      contentPane.add(j4);
      j4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(textField.getText()+".error", true));
                   out.write(textArea_1.getText());
                   out.close();
              } catch (IOException e01) {
                  System.err.println(e01);
                  System.exit(1);
              }
         }
      });
      
      j5 = new JButton("Delete");
      j5.setBounds(217, 319, 87, 25);
      j5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String filePath = textField.getText();
            File file = new File(filePath);
            System.gc();
            if(file.exists()) {
               if(file.delete())
               textArea_1.append("File Delete Success\n");
               else
               textArea_1.append("File Delete Fail\n");
            }
            else
               textArea_1.append("File Not Exist\n");
               }
         });
      contentPane.add(j5);
      
      j6 = new JButton("Clear");
      j6.setBounds(321, 319, 87, 25);
      j6.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             textField.setText("");
            textField_1.setText("");
            textArea.setText("");
            textArea_1.setText("");
         }
      });
      contentPane.add(j6);
      
      textArea_1 = new TextArea();
      textArea_1.setBounds(10, 391, 405, 253);
      contentPane.add(textArea_1);
      
      setSize(506, 698);
      setVisible(true);
   }

   public static void main(String[] args) {
         new Compiler_Ex();
      }
      
}