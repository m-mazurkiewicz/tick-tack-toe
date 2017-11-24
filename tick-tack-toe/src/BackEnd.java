import java.util.Arrays;

public class BackEnd {
    public char[][] board;
    public int sizeX;
    public int sizeY;
    private boolean win=false;

    public void createBoard(int x, int y) {
        board = new char[x][];
        for (int i = 0; i < x; i++) {
            board[i] = new char[y];
            for (int j = 0; j < y; j++) {
                board[i][j] = '_';
            }
        }
        sizeX = x;
        sizeY = y;
    }

    public void insertSign(int x, int y, int player) {
        if (player == 1) {
            board[x][y] = 'o';
        }
        if (player == 2) {
            board[x][y] = 'x';
        }
    }

    public boolean checkRow(int x) {
        if (x >= 0 & x < sizeX)
            return true;
        else
            return false;
    }

    public boolean checkColumn(int y) {
        if (y >= 0 & y < sizeY)
            return true;
        else
            return false;
    }

    private boolean isEqual(int x, int y, int player) {
        if (player == 1 & board[x][y] == 'o') {
            return true;
        }
        if (player == 2 & board[x][y] == 'x') {
            return true;
        }
        return false;
    }

    public boolean checkIfEmpty(int x, int y){
        if (board[x][y]=='_'){
            return true;
        }
        return false;
    }

/*    public boolean move(BackEnd game, int player){
        int[] coordinates=Interface.insert(game,player);
        if(checkIfWin(coordinates[0],coordinates[2],player)){
            Interface.printWin(player);
            return false;
        }
        return true;
    }*/

    public boolean checkIfWin(int x, int y, int player) {
        if (checkRow(x - 1) & checkColumn(y - 1)) {
            if (isEqual(x - 1, y - 1, player)) {
                if (checkRow(x - 2) & checkColumn(y - 2)) {
                    if (isEqual(x - 2, y - 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
                if (checkRow(x + 1) & checkColumn(y + 1)) {
                    if (isEqual(x + 1, y + 1, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkRow(x + 1) & checkColumn(y + 1)) {
            if (isEqual(x + 1, y + 1, player)) {
                if (checkRow(x + 2) & checkColumn(y + 2)) {
                    if (isEqual(x + 2, y + 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }

        if (checkRow(x - 1)) {
            if (isEqual(x - 1, y, player)) {
                if (checkRow(x - 2)) {
                    if (isEqual(x - 2, y, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
                if (checkRow(x + 1)) {
                    if (isEqual(x + 1, y, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkRow(x + 1)) {
            if (isEqual(x + 1, y, player)) {
                if (checkRow(x + 2)) {
                    if (isEqual(x + 2, y, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }

        if (checkRow(x - 1) & checkColumn(y + 1)) {
            if (isEqual(x - 1, y + 1, player)) {
                if (checkRow(x - 2) & checkColumn(y + 2)) {
                    if (isEqual(x - 2, y + 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
                if (checkRow(x + 1) & checkColumn(y - 1)) {
                    if (isEqual(x + 1, y - 1, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkRow(x + 1) & checkColumn(y - 1)) {
            if (isEqual(x + 1, y - 1, player)) {
                if (checkRow(x + 2) & checkColumn(y - 2)) {
                    if (isEqual(x + 2, y - 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }

        if (checkColumn(y - 1)) {
            if (isEqual(x, y - 1, player)) {
                if (checkColumn(y - 2)) {
                    if (isEqual(x, y - 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
                if (checkColumn(y + 1)) {
                    if (isEqual(x, y + 1, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }
        if (checkColumn(y + 1)) {
            if (isEqual(x, y + 1, player)) {
                if (checkColumn(y + 2)) {
                    if (isEqual(x, y + 2, player)) {
                        Interface.printWin(player);
                        win=true;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isWin(){
        if (win){
            return true;
        }
        return false;
    }
}
