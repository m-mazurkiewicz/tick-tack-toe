public class BackEnd implements TicTacToe {
    private Mark[][] board;
    private int sizeX;
    private int sizeY;
    private int movesCounter;
    private int maxMovesPerGame;
    private Mark currentPlayer;
    private GameListener gameListener;
    private static final int DEFAULT_BOARD_WIDTH=3;
    private static final int DEFAULT_BOARD_HEIGHT=3;

    public BackEnd(){
        this(DEFAULT_BOARD_WIDTH,DEFAULT_BOARD_HEIGHT);
    }

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

    private void updateBoard(){
        if (hasListener()) {
            gameListener.boardUpdated(board);
        }
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
        updateBoard();
    }

    private void insertSign(int x, int y) {
        if (checkIfRowExists(x) & checkIfColumnExists(y)) {
            tryInsertPlayersMarkIntoField(x, y);
        }
        else {
            actionWhenFieldIsOutsideBoard(x, y);
        }
    }

    private void tryInsertPlayersMarkIntoField(int x, int y){
        if (board[x][y] == Mark.EMPTY) {
            board[x][y] = currentPlayer;
            movesCounter++;
            updateBoard();
            isGameOver(x, y);
        }
        else {
            actionWhenFieldIsOccupied(x, y);
        }
    }

    private void isGameOver(int x, int y){
        if (checkIfWin(x, y)) {
            gameListener.theWinnerIs(currentPlayer);
        }
        else {
            isBoardFull();
        }
    }

    private void isBoardFull() {
        if(maxMovesPerGame <= movesCounter){
            gameListener.noMoreMoves();
        }
        else {
            changeCurrentPlayer();
            gameListener.playerMove(currentPlayer);
        }
    }

    private void actionWhenFieldIsOccupied(int x, int y){
        gameListener.filedOccupied(x, y);
        gameListener.playerMove(currentPlayer);
    }

    private void actionWhenFieldIsOutsideBoard(int x, int y){
        gameListener.fieldOutsideBoard(x, y);
        gameListener.playerMove(currentPlayer);
    }

    @Override
    public void playerMove(int x, int y) {
        insertSign(x, y);
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

    private boolean checkIfRowHasWinningCombination(int x, int y) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x , y - 1, x, y - 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x, y - 1, x, y - 2)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x, y + 1, x, y + 2);
    }

    private boolean checkIfColumnHasWinningCombination(int x, int y) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1 , y, x - 2, y)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y, x + 1, y)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y, x + 2, y);
    }

    private boolean checkIf1stDiagonalHasWinningCombination(int x, int y) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x -1 , y - 1, x - 2, y - 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y - 1, x - 2, y - 2)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y + 1, x + 2, y + 2);
    }

    private boolean checkIf2ndDiagonalHasWinningCombination(int x, int y) {
        return checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1 , y + 1, x - 2, y + 2 )
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x - 1, y + 1, x + 1, y - 1)
                || checkIfTwoFieldsHaveTheSameMarkAsInserted(x + 1, y - 1, x + 2, y - 2);
    }

    private boolean checkIfTwoFieldsHaveTheSameMarkAsInserted(int firstRowNumber, int firstColNumber, int secondRowNumber, int secondColNumber){
        return  (checkIfFieldExistAndPlayerMarkIsEqualToInserted(firstRowNumber, firstColNumber)
                && checkIfFieldExistAndPlayerMarkIsEqualToInserted(secondRowNumber, secondColNumber));
    }

    private boolean checkIfFieldExistAndPlayerMarkIsEqualToInserted(int x, int y){
        return checkIfRowExists(x) & checkIfColumnExists(y)
                && isPlayerMarkEqualToInserted(x, y, currentPlayer);
    }

    private boolean checkIfWin(int x, int y) {
        return checkIfRowHasWinningCombination(x, y)
                || checkIfColumnHasWinningCombination(x, y)
                || checkIf1stDiagonalHasWinningCombination(x, y)
                || checkIf2ndDiagonalHasWinningCombination(x, y);
    }
}
