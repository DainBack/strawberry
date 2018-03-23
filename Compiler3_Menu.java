import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Menu extends JFrame  {

   Container contentPane;
   JTextArea  resultArea, editArea; 
   JLabel imageLabel = new JLabel();
   String fileName; 
   String filePath;
   File file_open;
   JScrollPane scroll, scroll1;
   JTabbedPane Pane;
    
     public Menu() { 
      setTitle("Create a Menu");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      contentPane = getContentPane();
      getContentPane().setLayout(null);
      
      resultArea = new JTextArea();
      scroll = new JScrollPane(resultArea);
      scroll.setBounds(0, 0, 282, 216);
      getContentPane().add(scroll);
      
      
      editArea = new JTextArea();
      scroll1 = new JScrollPane(editArea);
      scroll1.setBounds(0, 220, 282, 180);
      getContentPane().add(scroll1);
   
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
   
   JMenu fileMenu= new JMenu("File      "); //1
   JMenuItem open = new  JMenuItem("Open"); //1-1 
   open.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
   open.addActionListener(new OpenActionListener());
   fileMenu.add(open);
   
   JMenuItem close = new  JMenuItem("Close");//1-2
   close.setAccelerator(KeyStroke.getKeyStroke('X', Event.CTRL_MASK));
   fileMenu.add(close);
   close.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
          resultArea.setText("");
          editArea.setText("");
       }
});   
  
   
   JMenuItem save = new  JMenuItem("Save");//1-3
   save.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
   fileMenu.add(save);
   save.addActionListener(new ActionListener()  
   {  JFileChooser chooser = new JFileChooser();
      public void actionPerformed(ActionEvent e)  
      {  
         int ret = chooser.showOpenDialog(Menu.this);
       if (ret==JFileChooser.APPROVE_OPTION)  
       {  
        File file_open = chooser.getSelectedFile(); 
        try  
        {  
        PrintWriter pw   = new PrintWriter(new BufferedWriter(new FileWriter(file_open))); 
        pw.write( resultArea.getText()); 
        pw.close();  
        }  
          
        catch(FileNotFoundException ie2)  
           {}  
        catch(IOException ie)   
           {} 
        
        int result = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",JOptionPane. YES_NO_OPTION );      
        if(result == JOptionPane. CLOSED_OPTION )      
            resultArea.setText("Just Closed without Selection");
         else if(result == JOptionPane. YES_OPTION )      
            resultArea.setText("Yes");      
         else      
            resultArea.setText("No");
       }
       contentPane.add(new JScrollPane( resultArea));  
      }  
     });  
   
   
   JMenuItem save_as = new  JMenuItem("Save as");//1-4
   save_as.setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK));
   fileMenu.add(save_as);
   save_as.addActionListener(new ActionListener()  
   { 
      
      JFileChooser chooser = new JFileChooser();
   public void actionPerformed(ActionEvent e)  
   {  String str_gettext= resultArea.getText(); 
      
   
   JFileChooser chooser = new JFileChooser();
   chooser.setCurrentDirectory( new File( "./") );
   int ret = chooser.showOpenDialog(Menu.this);
   if (ret == JFileChooser.APPROVE_OPTION) 
   {
       File fileName = new File(chooser.getSelectedFile( ) + "" );
       if(fileName == null) 
           return;
       if(fileName.exists())
       {
          ret =JOptionPane.showConfirmDialog(null,"There are already saved file. Do you still want to continue?");
           if (ret == JOptionPane.NO_OPTION)
               return;
       }
       try
       {
           BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
               out.write(str_gettext);
               out.close();
       }
       catch(Exception ie)
       {
            System.err.println("Error: " + ie.getMessage());}
   }}});  

 JMenuItem quit = new  JMenuItem("Quit");//1-5
 quit.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));
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
   compile.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
   compile.addActionListener(new OpenActionListener());
   fileRun.add(compile);
  
   JDesktopPane desk = new JDesktopPane();
   
   fileMenu.setBackground(Color.ORANGE);
   fileRun.setBackground(Color.ORANGE);

   mb.add(fileMenu);
   mb.add(fileRun); 
 
   this.setJMenuBar(mb);
   
 } //creat menu end
 
    class OpenActionListener implements ActionListener{
        JFileChooser chooser;
        
        OpenActionListener(){
            chooser = new JFileChooser();
         }
         
        public void actionPerformed(ActionEvent e) {
             String name = e.getActionCommand();
             if(name.equals("Open")) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Java & txt Files", "java", "txt");
                chooser.setFileFilter(filter);
                int ret = chooser.showOpenDialog(Menu.this);
                if(ret != JFileChooser.APPROVE_OPTION) {
                   JOptionPane.showMessageDialog(null,"No file selected", "Warning", JOptionPane.WARNING_MESSAGE);
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
                          resultArea.setText(bo.toString());
                          fis.close();                
                          bo.close();                 
                       }  
                       catch(FileNotFoundException fe) {}  
                       catch(IOException ie) {}  
                        }
                   }//open last
             else if(name.equals("Close")) {
                resultArea.setText("");
                editArea.setText("");
             }
          else if(name.equals("Compile")) {
                Process ps = null; 
                  try {
                       Process Process = new ProcessBuilder("cmd.exe", "/c", "java", filePath).start(); 
                         BufferedReader stdOut   = new BufferedReader(new InputStreamReader(Process.getInputStream()));
                         BufferedReader stdError = new BufferedReader(new InputStreamReader(Process.getErrorStream()));
                         String s=null;
                         while ((s =  stdOut.readLine()) != null)     editArea.setText(s); 
                         while ((s = stdError.readLine()) != null)     editArea.setText(s);
                       } catch (IOException exe) {
                          editArea.setText("Error! External command execution failed.\n" + exe.getMessage());
                       }
                } //compile end 
             } //actionPerformed end
        }//openactionlistener end 
    
 

  public static void main(String[] args) {
      new Menu();
   }
}//jframe end