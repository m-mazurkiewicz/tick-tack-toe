public class BackEnd implements TicTacToe {
    private Mark[][] board;
    private int sizeX;
    private int sizeY;
    private int movesCounter;
    private int maxMovesPerGame;
    //private boolean win = false;
    private Mark currentPlayer;
    private GameListener gameListener;

    public BackEnd(int sizeX, int sizeY) {
        currentPlayer = Mark.CIRCLE;
        createBoard(sizeX, sizeY);
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
        gameListener.boardUpdated(board);
        gameListener.playerMove(currentPlayer);
    }

    private boolean hasListener() {
        return gameListener != null;
    }

    private void changeCurrentPlayer() {
        currentPlayer = currentPlayer == Mark.CIRCLE ? Mark.CROSS : Mark.CIRCLE;
    }

    public void createBoard(int x, int y) {
        sizeX=x;
        sizeY=y;
        board = new Mark[x][];
        for (int i = 0; i < x; i++) {
            board[i] = new Mark[y];
            for (int j = 0; j < y; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
        movesCounter = 0;
        maxMovesPerGame = sizeX * sizeY;
        if (hasListener()) {
            gameListener.boardUpdated(board);
        }
    }

    private void insertSign(int x, int y, Mark player) {
        if (checkIfRowExists(x) & checkIfColumnExists(y)) {
            if (board[x][y] == Mark.EMPTY) {
                board[x][y] = player;
                movesCounter++;
                if (hasListener()) {
                    gameListener.boardUpdated(board);
                }
                if (checkIfWin(x, y, player)) {
                    //win = true;
                    gameListener.theWinnerIs(player);
                }
                else {
                    if(isBoardFull()){
                        gameListener.noMoreMoves();
                    }
                    else {
                        changeCurrentPlayer();
                        gameListener.playerMove(currentPlayer);
                    }
                }
            } else {
                gameListener.filedOccupied(x, y);
                gameListener.playerMove(currentPlayer);
            }
        }
        else {
            gameListener.fieldOutsideBoard(x, y);
            gameListener.playerMove(currentPlayer);
        }
    }

    @Override
    public void playerMove(int x, int y) {
        insertSign(x, y, currentPlayer);
    }

    private boolean checkIfRowExists(int x) {
        return (x >= 0 & x < sizeX);
    }

    private boolean checkIfColumnExists(int y) {
        return (y >= 0 & y < sizeY);
    }

    private boolean isPlayerMarkEqualToInserted(int x, int y, Mark player) {
        return board[x][y] == player;
    }

    private boolean checkIfRowHasWinningCombination(int x, int y, Mark player) {
        if (checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x, y - 1, player)) {
                if (checkIfColumnExists(y - 2)) {
                    return isPlayerMarkEqualToInserted(x, y - 2, player);
                }
                if (checkIfColumnExists(y + 1)) {
                    return isPlayerMarkEqualToInserted(x, y + 1, player);
                }
            }
        }
        if (checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x, y + 1, player)) {
                if (checkIfColumnExists(y + 2)) {
                    return isPlayerMarkEqualToInserted(x, y + 2, player);
                }
            }
        }
        return false;
    }

    private boolean checkIfColumnHasWinningCombination(int x, int y, Mark player) {
        if (checkIfRowExists(x - 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y, player)) {
                if (checkIfRowExists(x - 2)) {
                    return isPlayerMarkEqualToInserted(x - 2, y, player);
                }
                if (checkIfRowExists(x + 1)) {
                    return isPlayerMarkEqualToInserted(x + 1, y, player);
                }
            }
        }
        if (checkIfRowExists(x + 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y, player)) {
                if (checkIfRowExists(x + 2)) {
                    return isPlayerMarkEqualToInserted(x + 2, y, player);
                }
            }
        }
        return false;
    }

    private boolean checkIf1stDiagonalHasWinningCombination(int x, int y, Mark player) {
        if (checkIfRowExists(x - 1) & checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y - 1, player)) {
                if (checkIfRowExists(x - 2) & checkIfColumnExists(y - 2)) {
                    return isPlayerMarkEqualToInserted(x - 2, y - 2, player);
                }
                if (checkIfRowExists(x + 1) & checkIfColumnExists(y + 1)) {
                    return isPlayerMarkEqualToInserted(x + 1, y + 1, player);
                }
            }
        }
        if (checkIfRowExists(x + 1) & checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y + 1, player)) {
                if (checkIfRowExists(x + 2) & checkIfColumnExists(y + 2)) {
                    return isPlayerMarkEqualToInserted(x + 2, y + 2, player);
                }
            }
        }
        return false;
    }

    private boolean checkIf2ndDiagonalHasWinningCombination(int x, int y, Mark player) {
        if (checkIfRowExists(x - 1) & checkIfColumnExists(y + 1)) {
            if (isPlayerMarkEqualToInserted(x - 1, y + 1, player)) {
                if (checkIfRowExists(x - 2) & checkIfColumnExists(y + 2)) {
                    return isPlayerMarkEqualToInserted(x - 2, y + 2, player);
                }
                if (checkIfRowExists(x + 1) & checkIfColumnExists(y - 1)) {
                    return isPlayerMarkEqualToInserted(x + 1, y - 1, player);

                }
            }
        }
        if (checkIfRowExists(x + 1) & checkIfColumnExists(y - 1)) {
            if (isPlayerMarkEqualToInserted(x + 1, y - 1, player)) {
                if (checkIfRowExists(x + 2) & checkIfColumnExists(y - 2)) {
                    return isPlayerMarkEqualToInserted(x + 2, y - 2, player);

                }
            }
        }
        return false;
    }

    private boolean checkIfWin(int x, int y, Mark player) {
        return checkIfRowHasWinningCombination(x, y, player)
                || checkIfColumnHasWinningCombination(x, y, player)
                || checkIf1stDiagonalHasWinningCombination(x, y, player)
                || checkIf2ndDiagonalHasWinningCombination(x, y, player);
    }

    private boolean isBoardFull() {
        return maxMovesPerGame <= movesCounter;
    }

}
