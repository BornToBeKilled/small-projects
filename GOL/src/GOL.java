/**
 * Created by Alex on 24.08.2016.
 */
import javax.swing.*;


public class GOL {
    private JFrame jFrame;

    private void go(){
        jFrame = new JFrame(Const.NAME);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(Const.LOCATION_X, Const.LOCATION_Y);
        jFrame.setSize(Const.SIZE_X, Const.SIZE_Y);
        jFrame.setVisible(true);
    }

    public static void main(String[] args){
        new GOL().go();
    }
}
