/**
 * Created by Alex on 24.08.2016.
 */
import javax.swing.*;
import java.util.Random;


public class GOL {
    enum Point{
        Blank,
        Filled,
        Create,
        Destroy
    }

    private Random random = new Random();

    private JFrame jFrame;

    private Point[][] field;

    private void go(){
        jFrame = new JFrame(Const.NAME);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocation(Const.LOCATION_X, Const.LOCATION_Y);
        jFrame.setSize(Const.SIZE_X, Const.SIZE_Y);
        jFrame.setVisible(true);

        FillField();
    }

    public static void main(String[] args){
        new GOL().go();
    }

    private void FillField(){
        field = new Point[Const.FIELD_SIZE_Y][Const.FIELD_SIZE_X];
        for(int i = 0; i < Const.FIELD_SIZE_Y; i++) {
            for (int j = 0; j < Const.FIELD_SIZE_X; j++) {
                field[i][j] = (random.nextBoolean()) ? Point.Filled : Point.Blank;
            }
        }
    }
}
