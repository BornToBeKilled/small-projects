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

    volatile private boolean onIdle = false;
    volatile private boolean showGrid = false;

    private int generation = 1;

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
        canvasPanel.setBackground(new Color(230, 230, 230));

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
                jFrame.setTitle(Const.NAME + "     Generation : " + generation);
            }
        });

        JButton gridButton = new JButton();
        gridButton.setIcon(icoGrid);
        gridButton.setPreferredSize(new Dimension(Const.BTN_WIDTH, Const.BTN_HEIGHT));
        gridButton.setToolTipText("Show grid");
        gridButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGrid = !showGrid;
                canvasPanel.repaint();
            }
        });

        JButton startButton = new JButton();
        startButton.setIcon(icoGo);
        startButton.setPreferredSize(new Dimension(Const.BTN_WIDTH, Const.BTN_HEIGHT));
        startButton.setToolTipText("Start idle");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onIdle = !onIdle;
                startButton.setToolTipText((!onIdle) ? "Start idle" : "Stop idle");
                startButton.setIcon((onIdle) ? icoStop : icoGo);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(startButton);
        btnPanel.add(gridButton);
        jFrame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        jFrame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        jFrame.setVisible(true);

        fillField();
        canvasPanel.repaint();

        while (true) {
            if (onIdle) {
                nextGeneration();
                canvasPanel.repaint();
                jFrame.setTitle(Const.NAME + "     Generation : " + generation);
                try {
                    Thread.sleep(Const.DELAY);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
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
        generation = 1;
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
        generation++;
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
            jFrame.setTitle(Const.NAME + "     Generation : " + generation);
        }
    }

    private class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if(showGrid){
                g.setColor(new Color(150, 150, 150));
                for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
                    g.drawLine(0, Const.OFFSET / 2 + i * Const.DIAMETER + Const.DIAMETER / 2,
                            Const.SIZE_X, Const.OFFSET / 2 + i * Const.DIAMETER + Const.DIAMETER / 2);
                }
                for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                    g.drawLine(Const.OFFSET / 2 + j * Const.DIAMETER + Const.DIAMETER / 2, 0,
                            Const.OFFSET / 2 + j * Const.DIAMETER + Const.DIAMETER / 2, Const.SIZE_Y);
                }
            }
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
