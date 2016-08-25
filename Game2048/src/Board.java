import java.util.Random;

/**
 * Created by Alex on 25.08.2016.
 */
public class Board {
    final private int sizeX = 4; // Число столбцов доски
    final private int sizeY = 4; // Число строк доски
    final private double probabilityOf2 = 0.8; // Вероятность появления 2 (после сдвига в случайной пустой клетке появляется либо 2, либо 4).
    private Random random = new Random(System.currentTimeMillis());

    private int[][] squares; // Доска,
    private int freeSquares; // Число свобободных клеток на доске

    private void fillRandomSquare(){
        int m = 0, n = random.nextInt(freeSquares);
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY; j++)
                if (squares[i][j] == 0){
                    if(m == n) squares[i][j] = (random.nextFloat() < probabilityOf2) ? 2 : 4;
                    m++;
                }
        freeSquares--;
    }

    public void swipe(Direction direction){
        boolean changed = false;
        switch (direction){
            case LEFT:
                for(int j = 0; j < sizeY; j++)
                    changed = (shiftRowLeft(j)) ? true : changed;
                break;
            case RIGHT:
                for(int j = 0; j < sizeY; j++)
                    changed = (shiftRowRight(j)) ? true : changed;
                break;
            case UP:
                for(int i = 0; i < sizeX; i++)
                    changed = (shiftColumnUp(i)) ? true : changed;
                break;
            case DOWN:
                for(int i = 0; i < sizeX; i++)
                    changed = (shiftColumnDown(i)) ? true : changed;
                break;
        }
        if(changed) fillRandomSquare();
    }

    private boolean shiftRowRight(int j){
        boolean changed = false;
        for(int i = sizeX - 1; i >= 0; i--){
            if(squares[i][j] == 0) {
                for(int k = i - 1; k >= 0; k--)
                    if(squares[k][j] != 0){
                        squares[i][j] = squares[k][j];
                        squares[k][j] = 0;
                        changed = true;
                        break;
                    }
            }
            if(squares[i][j] != 0) {
                for(int k = i - 1; k >= 0; k--)
                    if(squares[k][j] !=0){
                        if(squares[k][j]== squares[i][j]){
                            squares[i][j] *= 2;
                            squares[k][j] = 0;
                            freeSquares++;
                            changed = true;
                        }
                        break;
                    }
            }
            else break;
        }
        return changed;
    }

    private boolean shiftRowLeft(int j){
        boolean changed = false;
        for(int i = 0; i < sizeX; i++){
            if(squares[i][j] == 0) {
                for(int k = i + 1; k < sizeX; k++)
                    if(squares[k][j] != 0){
                        squares[i][j] = squares[k][j];
                        squares[k][j] = 0;
                        changed = true;
                        break;
                    }
            }
            if(squares[i][j] != 0) {
                for(int k = i + 1; k < sizeX; k++)
                    if(squares[k][j] !=0){
                        if(squares[k][j]== squares[i][j]){
                            squares[i][j] *= 2;
                            squares[k][j] = 0;
                            freeSquares++;
                            changed = true;
                        }
                        break;
                    }
            }
            else break;
        }
        return changed;
    }

    private boolean shiftColumnUp(int i){
        boolean changed = false;
        for(int j = 0; j < sizeY; j++){
            if(squares[i][j] == 0) {
                for(int k = j + 1; k < sizeY; k++)
                    if(squares[i][k] != 0){
                        squares[i][j] = squares[i][k];
                        squares[i][k] = 0;
                        changed = true;
                        break;
                    }
            }
            if(squares[i][j] != 0) {
                for(int k = j + 1; k < sizeY; k++)
                    if(squares[i][k] !=0){
                        if(squares[i][k]== squares[i][j]){
                            squares[i][j] *= 2;
                            squares[i][k] = 0;
                            freeSquares++;
                            changed = true;
                        }
                        break;
                    }
            }
            else break;
        }
        return changed;
    }

    private boolean shiftColumnDown(int i){
        boolean changed = false;
        for(int j = sizeY - 1; j >= 0; j--){
            if(squares[i][j] == 0) {
                for(int k = j - 1; k >=0; k--)
                    if(squares[i][k] != 0){
                        squares[i][j] = squares[i][k];
                        squares[i][k] = 0;
                        changed = true;
                        break;
                    }
            }
            if(squares[i][j] != 0) {
                for(int k = j - 1; k >= 0; k--)
                    if(squares[i][k] !=0){
                        if(squares[i][k]== squares[i][j]){
                            squares[i][j] *= 2;
                            squares[i][k] = 0;
                            freeSquares++;
                            changed = true;
                        }
                        break;
                    }
            }
            else break;
        }
        return changed;
    }

    public void newGame(){
        squares = new int[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY; j++)
                squares[i][j] = 0;
        freeSquares = sizeX * sizeY;

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
        if(max == 2048) return 1;
        for(int i = 0; i < sizeX; i++)
            for(int j = 0; j < sizeY - 1; j++)
                if(squares[i][j] == squares[i][j+1])
                    return 0;
        for(int j = 0; j < sizeY; j++)
            for(int i = 0; i < sizeX - 1; i++)
               if(squares[i][j] == squares[i+1][j])
                    return 0;
        return (freeSquares > 0) ? 0 : -1;
    }

    public int getX(){
        return sizeX;
    }

    public int getY(){
        return sizeY;
    }

    public int[][] getBoard(){
        return squares;
    }
}
