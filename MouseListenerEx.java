import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MouseListenerEx extends JFrame {
	JLabel la;
	
	MouseListenerEx(){
		setTitle("Mouse Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		setLayout(null);
		contentPane.addMouseListener(new MyMouseListener());
		
		la = new JLabel("hello");
		la.setSize(50, 20);
		la.setLocation(20, 30);
		contentPane.add(la);
		setSize(200, 200);
		setVisible(true);
	}
	class MyMouseListener implements MouseListener {
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			la.setLocation(x, y);
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	public static void main(String args[]) {
		new MouseListenerEx();
	}
}
