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

        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        jFrame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        jFrame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
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

    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            FillField();
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
