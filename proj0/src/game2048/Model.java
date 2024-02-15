package game2048;

import java.util.Formatter;


/** The state of a game of 2048.
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board. */
    private final Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore) {
        board = new Board(rawValues);
        this.score = score;
        this.maxScore = maxScore;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board. */
    public int size() {
        return board.size();
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        return maxTileExists(board) || !atLeastOneMoveExists(board);
    }

    /** Checks if the game is over and sets the maxScore variable
     *  appropriately.
     */
    private void checkGameOver() {
        if (gameOver()) {
            maxScore = Math.max(score, maxScore);
        }
    }
    
    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int rowNum= b.size();
        boolean result = false;
        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j< rowNum; j++)
            {
                if (b.tile(i,j)==null)
                {
                   result = true;
                   break;
                }
            }
        }

        return result;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by this.MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.

        int rowNum= b.size();
        boolean result = false;
        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j< rowNum; j++)
            {
                Tile t = b.tile(i,j);
                if ( t != null && t.value() == MAX_PIECE)
                {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        boolean result = false;
        if (emptySpaceExists(b) || hasTwoAdjacentSameTile(b))
        {
            result = true;
        }
        return result;
    }

    public static boolean hasTwoAdjacentSameTile(Board b){
        boolean result = false;
        int rowNum = b.size();
        for (int i = 0; !result && i < rowNum; i++)
        {
            for (int j = 0; !result && j < rowNum; j++ )
            {
                Tile currentTile = b.tile(i,j);
                Tile[] neighborTiles= getNeighborTiles(b, currentTile);
                for (Tile neighbor : neighborTiles)
                {
                    if(neighbor != null)
                    {
                        if (  neighbor.value() == currentTile.value())
                        {
                            result = true;
                        }
                    }

                }
            }
        }
        return result;
    }

    public static Tile[] getNeighborTiles(Board b, Tile t)
    {
        Tile[] result = new Tile[4];
        int idx = 0;
        int rowNum = b.size();
        int row = t.row();
        int col = t.col();

        if(isValidIndex(rowNum, row+1))
        {
            result[idx] = b.tile(col, row+1);
            idx++;
        }

        if(isValidIndex(rowNum, row-1))
        {
            result[idx] = b.tile(col, row-1);
            idx++;
        }
        if(isValidIndex(rowNum, col+1))
        {
            result[idx] = b.tile(col+1, row);
            idx++;
        }
        if(isValidIndex(rowNum, col-1))
        {
            result[idx] = b.tile(col-1, row);
            idx++;
        }
        return result;

    }

    public static boolean isValidIndex(int maxIndex, int index)
    {

        return index< maxIndex && index >=0;
    }



    /** Tilt the board toward SIDE.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public void tilt(Side side) {
        // TODO: Modify this.board (and if applicable, this.score) to account
        // for the tilt to the Side SIDE.

        // treat as if the direction is North

        board.setViewingPerspective(side);
        int size = board.size();
        for (int colNumber=0; colNumber<size; colNumber++)
        {
            moveUpPerCol(colNumber);
        }

        checkGameOver();
        board.setViewingPerspective(Side.NORTH);
    }

    public void moveUpPerCol(int colNumber)
    {
        int size = board.size();
        int[] limitMergeIndex = new int[size];
        // iterate from row size-1 down
        for(int rowNumber =size-1; rowNumber>=0 ; rowNumber--)
        {
            Tile t = board.tile(colNumber, rowNumber);
            if(t!= null)
            {
                int replacedRowNumber = getNextRow(t,colNumber,rowNumber);
                if(replacedRowNumber!=-1)
                {
                    if(limitMergeIndex[replacedRowNumber] == 1)
                    {
                        board.move(colNumber, replacedRowNumber-1,t);
                    }else{
                        if(board.move(colNumber, replacedRowNumber,t))
                        {
                            limitMergeIndex[replacedRowNumber]=1;
                            Tile mergedTile = board.tile(colNumber, replacedRowNumber);
                            score += mergedTile.value();
                        }
                    }

                }
            }
        }
    }

    public int getNextRow(Tile t, int col, int row){
        int size = board.size();
        int value = t.value();
        int targetRow = row + 1;
        boolean isEqual = false;
//        System.out.println(row);
        for(; targetRow<size; targetRow++)
        {
            Tile tTmp = board.tile(col, targetRow);
            if(tTmp != null)
            {
                if(tTmp.value() == value)
                {
                    isEqual = true;
                }
                break;
            }
        }

        if(!isEqual)
        {
            if(targetRow-1!= row)
            {
                targetRow-=1;
            }else{
                targetRow= -1;
            }
        }


        return targetRow;

    }



    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

