import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Compile extends JFrame {
      Container contentPane;
      JTextArea editArea;
      JTextArea resultArea; 
      JTabbedPane Pane;
      String fileName;
      String filePath;
      JFileChooser chooser;
      File file_open;
      JScrollPane scrollPane, scrollPane_r;
      
      Compile() {  
         setTitle("Create a Menu");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         contentPane = getContentPane();
   //    editArea = new JTextArea();
   //    resultArea = new JTextArea();
         
         getContentPane().setLayout(null);
        // getContentPane().add(editArea);
        // getContentPane().add(resultArea);
         

         editArea = new JTextArea();
         scrollPane = new JScrollPane(editArea);
         scrollPane.setBounds(0, 0, 282, 216);
         contentPane.add(scrollPane);
         
         resultArea = new JTextArea();
         scrollPane_r = new JScrollPane(resultArea);
         scrollPane_r.setBounds(0, 220, 282, 180);
         contentPane.add(scrollPane_r);
         
         Pane = new JTabbedPane();
         add(Pane);
         
         createMenu();
         setSize(300,473);
         setVisible(true);
            
      } //jmenu end
      void createMenu() {
         JMenuBar mb = new JMenuBar();
         mb.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
         mb.setOpaque(true);
         mb.setBackground(Color.ORANGE);
         
         JMenu fileMenu= new JMenu("File      ");
         JMenu fileRun = new JMenu("Run       ");
         JMenuItem[] menuItem = new JMenuItem[5];
         JMenuItem menuItem1 = new JMenuItem();
         String[] itemTitle = {"Open", "Close", "Save", "Save As", "Quit"};
         String itemTitle1 = "Compile";
         for(int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(itemTitle[i]);
            menuItem[i].addActionListener(new MenuActionListener());
            fileMenu.add(menuItem[i]);
         }
         menuItem[0].setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
         menuItem[1].setAccelerator(KeyStroke.getKeyStroke('C', Event.CTRL_MASK));
         menuItem[2].setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
         menuItem[3].setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK));
         menuItem[4].setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));
         
         mb.add(fileMenu);
         mb.add(fileRun); 
         menuItem1 = new JMenuItem(itemTitle1);
         menuItem1.addActionListener(new MenuActionListener());
         menuItem1.setAccelerator(KeyStroke.getKeyStroke('R',Event.CTRL_MASK));
         fileRun.add(menuItem1);
         
         this.setJMenuBar(mb);
      }
      class MenuActionListener implements ActionListener{
         JFileChooser chooser;
         MenuActionListener(){
            chooser = new JFileChooser();
         }
         public void actionPerformed(ActionEvent e) {
            String name = e.getActionCommand();
            if(name.equals("Open")) {
               FileNameExtensionFilter filter = new FileNameExtensionFilter("Java & txt Files", "java", "txt");
               chooser.setFileFilter(filter);
               int ret = chooser.showOpenDialog(Compile.this);
               if(ret != JFileChooser.APPROVE_OPTION) {
                  JOptionPane.showMessageDialog(null, "No file selected", "Warning", JOptionPane.WARNING_MESSAGE);
                  return;
               }
               filePath = chooser.getSelectedFile().getPath();
                fileName = chooser.getSelectedFile().getName();
                 pack();
                 if(ret == JFileChooser.APPROVE_OPTION) {
                       String fN;   
                      String dir;  
                      String str;  
                      file_open = chooser.getSelectedFile();
                      FileInputStream fis;   
                      ByteArrayOutputStream bo; 
                      try   {  
                         fis = new FileInputStream(file_open); 
                         bo = new ByteArrayOutputStream();     
                         int i = 0;  
                         while ((i = fis.read()) != -1) { 
                            bo.write(i);                  
                         }  
                         editArea.setText(bo.toString()); 
                         fis.close();                
                         bo.close();                   
                      }  
                      catch(FileNotFoundException fe) {}  
                      catch(IOException ie) {}  
                       }
                  }//open last
            else if(name.equals("Close")) {
               editArea.setText("");
               resultArea.setText("");
            }
            else if(name.equals("Save")) {
                 try   {  
                 PrintWriter pw   = new PrintWriter(new BufferedWriter(new FileWriter(file_open))); // PrintWriter객체를 생성해서  
                 pw.write(editArea.getText()); 
                 pw.close();  
                 } catch(FileNotFoundException ie2) {}  
                 catch(IOException ie) {} 
                 int result = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",JOptionPane. YES_NO_OPTION );      
                 if(result == JOptionPane. CLOSED_OPTION )      
                    editArea.setText("Just Closed without Selection");
                  else if(result == JOptionPane. YES_OPTION )      
                     editArea.setText("Yes");      
                  else      
                     editArea.setText("No");
                contentPane.add(new JScrollPane(editArea));  
            }
            else if(name.equals("Save As")) {
               String str_gettext=editArea.getText(); 
                 
               JFileChooser chooser = new JFileChooser();
               chooser.setCurrentDirectory( new File( "./") );
               int ret = chooser.showSaveDialog(Compile.this);
               if (ret == JFileChooser.APPROVE_OPTION)
               {
                   File fileName = new File(chooser.getSelectedFile( ) + "" );
                   if(fileName == null)
                       return;
                   if(fileName.exists()) {
                      ret =JOptionPane.showConfirmDialog(null,"There are already saved file. Do you still want to continue?","Warning", JOptionPane.WARNING_MESSAGE, ret);
                       if (ret == JOptionPane.NO_OPTION)
                           return;
                   }
                   try {
                         BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
                        out.write(str_gettext);
                        out.close();
                   }
                   catch(Exception ie) {
                        System.err.println("Error: " + ie.getMessage()); }
               }
            }//Save As
            else if(name.equals("Quit")) {
               dispose(); 
                System.exit(0); 
                  }
            else if(name.equals("Compile")) {
               Process ps = null; 
                 try {
                      Process Process = new ProcessBuilder("cmd.exe", "/c", "java", filePath).start(); 
                        BufferedReader stdOut   = new BufferedReader(new InputStreamReader(Process.getInputStream()));
                        BufferedReader stdError = new BufferedReader(new InputStreamReader(Process.getErrorStream()));
                        String s=null;
                        while ((s =  stdOut.readLine()) != null)    resultArea.setText(s); 
                        while ((s = stdError.readLine()) != null)    resultArea.setText(s);
                      //  System.out.println("Exit Code: " + Process.exitValue());
                      //  System.exit(Process.exitValue()); 
                      } catch (IOException exe) {
                         resultArea.setText("Error! External command execution failed.\n" + exe.getMessage());
                      }

               }
            }
         }//MenuActionListener
      
      
  /*   void createTabbedPane() {
        text1.remove(editArea);
        JPanel panel2 = new JPanel();
      JLabel label = new JLabel("Your program is working successfully.");
      panel2.add(label);
      text1.add(panel2);
      Pane.add(fileName, panel2);
        //    TextArea edit = new TextArea();
       //    FileInputStream fis;  
       //   ByteArrayOutputStream bo;  
       //   File file1 = new File(filePath);
       //   BufferedReader br = null;
      //    try {
      //          br = new BufferedReader(new FileReader(file1));
      //          String line;
      //          while ((line = br.readLine()) != null) {
      //             edit.append(line);
      //              edit.append("\n");
      //          }
      //    } catch (FileNotFoundException e1) {
      //               e1.printStackTrace();
      //    } catch (IOException e2) {
      //               e2.printStackTrace(); 
      //    }
       //    JScrollPane scroll_p = new JScrollPane(edit);
       //    Pane.add(scroll_p);
      }//createTabbedPane
      */
      public static void main(String[] args) {
            new Compile();
         }//main
}