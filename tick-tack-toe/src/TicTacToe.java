public interface TicTacToe {

    void playerMove(int x, int y);

    void setGameListener(GameListener gameListener);

    void createBoard(int x, int y);
}
