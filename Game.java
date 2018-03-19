package playconnect4;

public class Game {

    private int cells_left = 0;
    private int max;
    private int xsize;
    private int ysize;
    PlayingBoard playBoard;

    public Game(PlayingBoard tempGrid) 
    {
        max = 4;//connect 4 or n
        playBoard = tempGrid;
        cells_left = playBoard.get_cells_left();
        xsize = playBoard.get_xsize();
        ysize = playBoard.get_ysize();
    }

    public boolean set_and_check(int x, int y, int player) 
    {
        playBoard.set_matrix(x, y, player);
        cells_left--;
        return check_one(x, y, 0, 1, player) 
                || check_one(x, y, -1, 1, player) 
                || check_one(x, y, -1, 0, player) 
                || check_one(x, y, 1, 1, player);
    }

    //checks for draw game
    public boolean draw_game() 
    {
        return cells_left == 0;
    }

    private boolean check_one(int x, int y, int dx, int dy, int player) 
    {
        int count = 0;
        int tempx = x;
        int tempy = y;

        while (count < max && valid(tempx, tempy)) 
        {
            if (!playBoard.matrix_equals(tempx, tempy, player)) 
            {
                break;
            }
            tempx += dx;
            tempy += dy;
            count++;
        }
        tempx = x - dx;
        tempy = y - dy;
        while (count < max && valid(tempx, tempy)) 
        {
            if (!playBoard.matrix_equals(tempx, tempy, player)) 
            {
                break;
            }
            tempx -= dx;
            tempy -= dy;
            count++;
        }
        return count == max;
    }

    private boolean valid(int x, int y) {
        //if the bounds are set to be >0 only then first row and column doesnt work
        return x >= 0 && x < xsize && y >= 0 && y < ysize;
    }
}