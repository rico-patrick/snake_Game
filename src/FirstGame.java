import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FirstGame {
	

	public static void main(String[] args) {
		
		
			JFrame frame;
			Actions act=new Actions();
			frame = new JFrame();
			frame.setSize(800,660);
			frame.add(act);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
		

	}

}
