public interface GameListener {

    void changeDefaultBoardSize();
    void boardUpdated(Mark[][] board);
    void playerMove(Mark mark);
    void filedOccupied(int x, int y);
    void fieldOutsideBoard(int x, int y);
    void noMoreMoves();
    void theWinnerIs(Mark mark);
}
