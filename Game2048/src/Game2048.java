import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Alex on 25.08.2016.
 */
public class Game2048 {
    private int X = 500, Y = 500;
    private Board board;
    private Canvas canvasPanel;

    public static void main(String[] args){
        new Game2048().go();
    }

    public void go(){
        board = new Board();
        board.newGame();

        JFrame jFrame = new JFrame("2048");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(100, 100);
        jFrame.setSize(X + 6, Y + 28);
        jFrame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(new Color(200, 200, 200));
        canvasPanel.setFocusable(true);
        canvasPanel.addKeyListener(new GameKeyListener());

        jFrame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        jFrame.setVisible(true);

    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int width = X / board.getX();
            int height = Y / board.getY();
            int offset = 6;
            int[][] squares = board.getBoard();

            if(board.gameState() == 0) {
                g.setFont(new Font("Verdana", Font.BOLD, 36));
                for (int i = 0; i < board.getX(); i++) {
                    for (int j = 0; j < board.getY(); j++) {
                        switch (squares[i][j]) {
                            case 0:
                                g.setColor(Colors.back0);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                break;
                            case 2:
                                g.setColor(Colors.back2);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front1);
                                g.drawString("2", i * width + offset + width / 2 - 20, j * height + offset + height / 2 + 7);
                                break;
                            case 4:
                                g.setColor(Colors.back4);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front1);
                                g.drawString("4", i * width + offset + width / 2 - 20, j * height + offset + height / 2 + 7);
                                break;
                            case 8:
                                g.setColor(Colors.back8);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("8", i * width + offset + width / 2 - 20, j * height + offset + height / 2 + 7);
                                break;
                            case 16:
                                g.setColor(Colors.back16);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("16", i * width + offset + width / 2 - 32, j * height + offset + height / 2 + 7);
                                break;
                            case 32:
                                g.setColor(Colors.back32);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("32", i * width + offset + width / 2 - 32, j * height + offset + height / 2 + 7);
                                break;
                            case 64:
                                g.setColor(Colors.back64);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("64", i * width + offset + width / 2 - 32, j * height + offset + height / 2 + 7);
                                break;
                            case 128:
                                g.setColor(Colors.back128);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("128", i * width + offset + width / 2 - 44, j * height + offset + height / 2 + 7);
                                break;
                            case 256:
                                g.setColor(Colors.back256);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("256", i * width + offset + width / 2 - 44, j * height + offset + height / 2 + 7);
                                break;
                            case 512:
                                g.setColor(Colors.back512);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("512", i * width + offset + width / 2 - 44, j * height + offset + height / 2 + 7);
                                break;
                            case 1024:
                                g.setColor(Colors.back1024);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("1024", i * width + offset + width / 2 - 58, j * height + offset + height / 2 + 7);
                                break;
                            case 2048:
                                g.setColor(Colors.back2048);
                                g.fillRect(i * width + offset, j * height + offset, width - 2 * offset, height - 2 * offset);
                                g.setColor(Colors.front2);
                                g.drawString("2048", i * width + offset + width / 2 - 58, j * height + offset + height / 2 + 7);
                                break;
                        }
                    }
                }
            }
            if(board.gameState() > 0) {
                g.setFont(new Font("Verdana", Font.BOLD, 60));
                g.setColor(Colors.back2048);
                g.fillRect(0, 0, X, Y);
                g.setColor(Colors.front3);
                g.drawString("YOU WON!!!", X / 2 - 200, Y / 2 + 30);
            }
            if(board.gameState() < 0) {
                g.setFont(new Font("Verdana", Font.BOLD, 60));
                g.setColor(Colors.back64);
                g.fillRect(0, 0, X, Y);
                g.setColor(Colors.front3);
                g.drawString("YOU LOST!!!", X / 2 - 200, Y / 2 + 30);
            }
        }
    }

    public class GameKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(board.gameState() == 0){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        board.swipe(Direction.LEFT);
                        canvasPanel.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        board.swipe(Direction.RIGHT);
                        canvasPanel.repaint();
                        break;
                    case KeyEvent.VK_UP:
                        board.swipe(Direction.UP);
                        canvasPanel.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        board.swipe(Direction.DOWN);
                        canvasPanel.repaint();
                        break;
                }
            }
            else {
                board.newGame();
                canvasPanel.repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
