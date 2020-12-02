import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Actions extends JPanel implements ActionListener {
	 private boolean leftDirection = false;
	 private boolean rightDirection = true;
	 private boolean upDirection = false;
	 private boolean downDirection = false;
	 private int dots;
	 private int apple_x;
	 private int apple_y;
	 private int z=0;
	 private boolean inGame = true;
	 private final int B_WIDTH = 800;
	 private final int B_HEIGHT = 600;
	 private final int DOT_SIZE = 10;
	 private final int ALL_DOTS = 900;
	 private final int x[] = new int[ALL_DOTS];
	 private final int y[] = new int[ALL_DOTS];
	 private Timer timer;
	 private final int DELAY=140;
	 private final int RAND_POS = 29;
	public Actions() {
		initBoard();
	}
	private void initBoard() {
		 addKeyListener(new TAdapter());
		 setBackground(Color.black);
		 setFocusable(true);
		 setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		 initGame();
		 }
	private void initGame() {
		 dots = 1;
		 for ( z = 0; z < dots; z++) {
		 x[z] = 50 - z * 100;
		 y[z] = 50;
		 }
		 locateApple();
		 timer = new Timer(DELAY, this);
		 timer.start();
	 	}
	@Override
	 public void paintComponent(Graphics g) {
	 super.paintComponent(g);
	 doDrawing(g);
	 }
		
		
		 private void doDrawing(Graphics g) {

			 if (inGame) {
				 g.setColor(Color.RED);
			 g.fillOval(apple_x, apple_y,30,30);
			 for (int z = 0; z < dots; z++) {
			 if (z == 0) {
			 g.setColor(Color.GREEN);
			 g.fillRect(x[z], y[z], 40,30);
			 } else {
			 g.setColor(Color.GREEN);
			 g.fillRect(x[z], y[z], 40,30);
			 }
			 }
			 Toolkit.getDefaultToolkit().sync();
			 } else {
			 gameOver(g);
			 }
			 }
		 private void gameOver(Graphics g) {

			 String msg = "Game Over";
			 Font small = new Font("Helvetica", Font.BOLD, 14);
			 FontMetrics metr = getFontMetrics(small);
			 g.setColor(Color.white);
			 g.setFont(small);
			 g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
			 }
		 private void checkApple() {
			 if ((x[0] == apple_x) && (y[0] == apple_y)) {
			 dots++;
			 locateApple();
			 }
			 }
		
		 private void move() {
			 for (int z = dots; z > 0; z--) {
			 x[z] = x[(z - 1)];
			 y[z] = y[(z - 1)];
			 }
			 if (leftDirection) {
			 x[0] -= DOT_SIZE;
			 }
			 if (rightDirection) {
			 x[0] += DOT_SIZE;
			 }
			 if (upDirection) {
			 y[0] -= DOT_SIZE;
			 }
			 if (downDirection) {
			 y[0] += DOT_SIZE;
			 }
			 }
		 private void locateApple() {
			 int r = (int) (Math.random() * RAND_POS);
			 apple_x = ((r * DOT_SIZE));
			 r = (int) (Math.random() * RAND_POS);
			 apple_y = ((r * DOT_SIZE));
			 }
		 private void checkCollision() {
			 for (int z = dots; z > 0; z--) {
			 if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
			 inGame = false;
			 }
			 }
			 if (y[0] >= B_HEIGHT) {
			 inGame = false;
			 }
			 if (y[0] < 0) {
			 inGame = false;
			 }
			 if (x[0] >= B_WIDTH) {
			 inGame = false;
			 }
			 if (x[0] < 0) {
			 inGame = false;
			 }

			 if (!inGame) {
			 timer.stop();
			 }
			 }
		 @Override
			public void actionPerformed(ActionEvent e) {
				if(inGame) {
					checkApple();
					checkCollision();
					move();
				}
				repaint();
				
			}
		
		
private class TAdapter extends KeyAdapter {	
	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		 if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
		 leftDirection = true;
		 upDirection = false;
		 downDirection = false;
		 }
		 if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
		 rightDirection = true;
		 upDirection = false;
		 downDirection = false;
		 }
		 if ((key == KeyEvent.VK_UP) && (!downDirection)) {
		 upDirection = true;
		 rightDirection = false;
		 leftDirection = false;
		 }
		 if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
		 downDirection = true;
		 rightDirection = false;
		 leftDirection = false;
		 }
		 if((key == KeyEvent.VK_SPACE) && (inGame==false)) {
			 inGame=true;
			 dots=1;
			 z=0;
			 x[0]=0;
			 y[0]=0;
			 upDirection = false;
			 rightDirection = false;
			 leftDirection = false;
			 downDirection = false;
			 initBoard();
			 timer.restart();
		 }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		
	}
}
	

}
