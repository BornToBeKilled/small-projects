/**
 * Created by Alex on 24.08.2016.
 */

import java.awt.*;
import javax.swing.*;
import java.util.Random;


public class GOL {

    private Random random = new Random();

    private JFrame jFrame;
    private Canvas canvasPanel;
    private boolean[][] field;

    private void go(){
        jFrame = new JFrame(Const.NAME);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(Const.LOCATION_X, Const.LOCATION_Y);
        jFrame.setSize(Const.SIZE_X, Const.SIZE_Y);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(new Color(200, 200, 200));

        jFrame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        jFrame.setVisible(true);

        FillField();
        canvasPanel.repaint();
    }

    public static void main(String[] args){
        new GOL().go();
    }

    private void FillField(){
        field = new boolean[Const.FIELD_SIZE_Y][Const.FIELD_SIZE_X];
        for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
            for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                field[i][j] = random.nextBoolean();
            }
        }
    }

    private class Canvas extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(0, 100, 0));
            for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
                for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                    if(field[i][j]){
                        g.fillOval( Const.OFFSET / 2 + j * Const.DIAMETER,
                                Const.OFFSET / 2 + i * Const.DIAMETER,
                                Const.DIAMETER,
                                Const.DIAMETER);
                    }
                }
            }
        }
    }

}
