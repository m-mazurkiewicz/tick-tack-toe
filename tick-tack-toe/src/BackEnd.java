public class BackEnd {
    private Mark[][] board;
    private int sizeX;
    private int sizeY;
    private int movesCounter;
    private int maxMovesPerGame;
    private boolean win=false;

    public void createBoard(int x, int y) {
        board = new Mark[x][];
        for (int i = 0; i < x; i++) {
            board[i] = new Mark[y];
            for (int j = 0; j < y; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
        sizeX = x;
        sizeY = y;
        movesCounter=0;
        maxMovesPerGame=sizeX*sizeY;
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public Mark getSingleFieldFromBoard(int x, int y) {
        return board[x][y];
    }

    public void insertSign(int x, int y, Mark player) {
        board[x][y]=player;
        movesCounter++;
    }

    public boolean checkIfRowExists(int x) {
        return (x>=0 & x < sizeX);
    }

    public boolean checkIfColumnExists(int y) {
        return  (y >= 0 & y < sizeY);
    }

    private boolean isPlayerMarkEqualToInserted(int x, int y, Mark player) {
        return board[x][y]==player;
    }

    public boolean checkIfFieldIsEmpty(int x, int y){
        return board[x][y]==Mark.EMPTY;
    }

    private boolean checkIfRowHasWinningCombination(int x, int y, Mark player) {
        if (checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x, y - 1, player)) {
                if (checkIfColumnExists(y - 2)) {
                    if (isPlayerMarkEqualToInserted(x, y - 2, player)) {
                        win=true;
                        return true;
                    }
                }
                if (checkIfColumnExists(y + 1)) {
                    if (isPlayerMarkEqualToInserted(x, y + 1, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x, y + 1, player)) {
                if (checkIfColumnExists(y + 2)) {
                    if (isPlayerMarkEqualToInserted(x, y + 2, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkIfColumnHasWinningCombination(int x, int y, Mark player){
        if (checkIfRowExists(x - 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y, player)) {
                if (checkIfRowExists(x - 2)) {
                    if (isPlayerMarkEqualToInserted(x - 2, y, player)) {
                        win=true;
                        return true;
                    }
                }
                if (checkIfRowExists(x + 1)) {
                    if (isPlayerMarkEqualToInserted(x + 1, y, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkIfRowExists(x + 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y, player)) {
                if (checkIfRowExists(x + 2)) {
                    if (isPlayerMarkEqualToInserted(x + 2, y, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkIf1stDiagonalHasWinningCombination(int x, int y, Mark player) {
        if (checkIfRowExists(x - 1) & checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y - 1, player)) {
                if (checkIfRowExists(x - 2) & checkIfColumnExists(y - 2)) {
                    if (isPlayerMarkEqualToInserted(x - 2, y - 2, player)) {
                        win=true;
                        return true;
                    }
                }
                if (checkIfRowExists(x + 1) & checkIfColumnExists(y + 1)) {
                    if (isPlayerMarkEqualToInserted(x + 1, y + 1, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkIfRowExists(x + 1) & checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y + 1, player)) {
                if (checkIfRowExists(x + 2) & checkIfColumnExists(y + 2)) {
                    if (isPlayerMarkEqualToInserted(x + 2, y + 2, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkIf2ndDiagonalHasWinningCombination(int x, int y, Mark player){
        if (checkIfRowExists(x - 1) & checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y + 1, player)) {
                if (checkIfRowExists(x - 2) & checkIfColumnExists(y + 2)) {
                    if (isPlayerMarkEqualToInserted(x - 2, y + 2, player)) {
                        win=true;
                        return true;
                    }
                }
                if (checkIfRowExists(x + 1) & checkIfColumnExists(y - 1)) {
                    if (isPlayerMarkEqualToInserted(x + 1, y - 1, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkIfRowExists(x + 1) & checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y - 1, player)) {
                if (checkIfRowExists(x + 2) & checkIfColumnExists(y - 2)) {
                    if (isPlayerMarkEqualToInserted(x + 2, y - 2, player)) {
                        win=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIfWin(int x, int y, Mark player) {
        if (checkIfRowHasWinningCombination(x,y,player)){
            return true;
        }
        if (checkIfColumnHasWinningCombination(x,y,player)){
            return true;
        }
        if (checkIf1stDiagonalHasWinningCombination(x,y,player)){
            return true;
        }
        if (checkIf2ndDiagonalHasWinningCombination(x,y,player)){
            return true;
        }
        return false;
        //tak, wiem, można to zrobić w jednej linijce robiąc return (alternatywa tych wszystkich), ale w ten sposób jest mniej porównań
    }

    /*public boolean isWin(){
        return win;
    }*/

    public boolean isBoardFull(){
        return maxMovesPerGame<=movesCounter;
    }

    public boolean isGameOver() {
        return (win | isBoardFull());
    }
}
