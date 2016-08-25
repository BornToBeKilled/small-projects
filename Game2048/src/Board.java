import java.util.Random;

/**
 * Created by Alex on 25.08.2016.
 */
public class Board {
    final private int sizeX = 4; // Число столбцов доски
    final private int sizeY = 4; // Число строк доски
    private Random random = new Random(System.currentTimeMillis());
    final private double probabilityOf2 = 0.8; // Вероятность появления 2 (после сдвига в случайной пустой клетке появляется либо 2, либо 4).
    private int[][] squares; // Доска,
    private int freeSquares; // Число свобободных клеток на доске

    private void fillRandomSquare(){
        ;
    }

    public void swipe(Direction direction){
        ;
    }

    public void Board(){
        squares = new int[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY; j++)
                squares[i][j] = 0;
        freeSquares = 0;

        fillRandomSquare();
        fillRandomSquare();
    }

    /*Возвращает состояние игры
     * 0 - играем дальше
     * -1 - проигрыш
     * 1 - победа
     */
    public int gameState(){
        int max = 0;
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY; j++)
                if (squares[i][j] > max) max = squares[i][j];

        return (max >= 2048) ? 1 : (freeSquares > 0) ? 0 : -1;
    }

    public int[][] getBoard(){
        return squares;
    }
}
