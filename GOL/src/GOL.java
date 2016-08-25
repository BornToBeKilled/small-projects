/**
 * Created by Alex on 24.08.2016.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GOL {

    private Random random = new Random();

    private JFrame jFrame;
    private Canvas canvasPanel;
    private boolean[][] field;
    final private ImageIcon icoFill = new ImageIcon(new ImageIcon(GOL.class.getResource("btnFill.png")).getImage().getScaledInstance(Const.BTN_WIDTH, Const.BTN_HEIGHT,Image.SCALE_SMOOTH));
    final private ImageIcon icoGo = new ImageIcon(new ImageIcon(GOL.class.getResource("btnGo.png")).getImage().getScaledInstance(Const.BTN_WIDTH, Const.BTN_HEIGHT,Image.SCALE_SMOOTH));
    final private ImageIcon icoGrid = new ImageIcon(new ImageIcon(GOL.class.getResource("btnGrid.png")).getImage().getScaledInstance(Const.BTN_WIDTH, Const.BTN_HEIGHT,Image.SCALE_SMOOTH));
    final private ImageIcon icoStep = new ImageIcon(new ImageIcon(GOL.class.getResource("btnStep.png")).getImage().getScaledInstance(Const.BTN_WIDTH, Const.BTN_HEIGHT,Image.SCALE_SMOOTH));
    final private ImageIcon icoStop = new ImageIcon(new ImageIcon(GOL.class.getResource("btnStop.png")).getImage().getScaledInstance(Const.BTN_WIDTH, Const.BTN_HEIGHT,Image.SCALE_SMOOTH));

    private void go(){
        jFrame = new JFrame(Const.NAME);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(Const.LOCATION_X, Const.LOCATION_Y);
        jFrame.setSize(Const.SIZE_X, Const.SIZE_Y);
        jFrame.setResizable(false);

        canvasPanel = new Canvas();
        canvasPanel.setBackground(new Color(200, 200, 200));

        JButton fillButton = new JButton();
        fillButton.setIcon(icoFill);
        fillButton.setPreferredSize(new Dimension(Const.BTN_WIDTH, Const.BTN_HEIGHT));
        fillButton.setToolTipText("Fill randomly");
        fillButton.addActionListener(new FillButtonListener());

        JButton stepButton = new JButton();
        stepButton.setIcon(icoStep);
        stepButton.setPreferredSize(new Dimension(Const.BTN_WIDTH, Const.BTN_HEIGHT));
        stepButton.setToolTipText("Show next generation");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextGeneration();
                canvasPanel.repaint();
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        jFrame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        jFrame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        jFrame.setVisible(true);

        fillField();
        canvasPanel.repaint();
    }

    public static void main(String[] args){
        new GOL().go();
    }

    private void fillField(){
        field = new boolean[Const.FIELD_SIZE_Y][Const.FIELD_SIZE_X];
        for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
            for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                field[i][j] = random.nextBoolean();
            }
        }
    }

    private void nextGeneration(){
        boolean[][] newField = new boolean[Const.FIELD_SIZE_Y][Const.FIELD_SIZE_X];
        for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
            for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                newField[i][j] = (countNeighbors(i, j) == 3) || ( (countNeighbors(i, j) == 2) && field[i][j]);
            }
        }
        for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
            for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                field[i][j] = newField[i][j];
            }
        }
    }

    private int countNeighbors(int i, int j){
        int count = 0;
        int x = Const.FIELD_SIZE_X;
        int y = Const.FIELD_SIZE_Y;

        if ( field[(i-1 == -1) ? y-1 : i-1][(j-1 < 0) ? x-1 : j-1] ) count++;
        if ( field[(i-1 == -1) ? y-1 : i-1][j] ) count++;
        if ( field[(i-1 == -1) ? y-1 : i-1][(j+1 == x) ? 0 : j+1] ) count++;

        if ( field[i][(j-1 < 0) ? x-1 : j-1] ) count++;
        if ( field[i][(j+1 == x) ? 0 : j+1] ) count++;

        if ( field[(i+1 == y) ? 0 : i+1][(j-1 < 0) ? x-1 : j-1] ) count++;
        if ( field[(i+1 == y) ? 0 : i+1][j] ) count++;
        if ( field[(i+1 == y) ? 0 : i+1][(j+1 == x) ? 0 : j+1] ) count++;

        return count;
    }

    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            fillField();
            canvasPanel.repaint();
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
