package playconnect4;

public class PlayingBoard {
	private int xsize;
    private int ysize;
    private int[][] matrix;
    private int cells_left = 0;

    public PlayingBoard() 
    {
        xsize = 7;
        ysize = 6;

        matrix = new int[xsize][ysize];
        for (int i = 0; i < xsize; i++) 
        {
            for (int j = 0; j < ysize; j++) 
            {
                matrix[i][j] = 0;
                cells_left++;
            }
        }
    }
    
    //Return number of cells left
    public int get_cells_left() 
    {
        return cells_left;
    }

    //Return Matrix
    public int[][] get_matrix() 
    {
        return matrix;
    }

    
    public boolean matrix_equals(int a, int b, int c) 
    {
        return matrix [a][b] == c;
    }
    
    //Set the player to a cell to indicate his move
    public void set_matrix(int a, int b, int temp_player) 
    {
        matrix[a][b] = temp_player;
    }

    
    public int get_xsize() 
    {//returns the xsize
        return xsize;
    }

    public int get_ysize() 
    {//returns the xsize
        return ysize;
    }

    public int find_y(int x) 
    {
        //Check for free cells in column and return it.
        int y = -1;
        for (int i = 0; i < ysize; i++) {
            if (matrix[x][i] == 0) {
                y = i;
            }
        }
        return y;
    }

    //Change the player
    public int changeplayer(int player, int max_players) 
    {
        player++;
        if (player > max_players) {
            return 1;
        }
        return player;
    }
}


