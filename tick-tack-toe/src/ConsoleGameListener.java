import java.util.Scanner;

public class ConsoleGameListener implements GameListener {

    private TicTacToe ticTacToe;

    public ConsoleGameListener(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        changeDefaultBoardSize();
    }

    @Override
    public void changeDefaultBoardSize() {
        System.out.println("Domyślna wielkość planszy, to 3 x 3. Jeśli chcesz zmienić jej rozmiar wciśnij T. Jeśli nie - wpisz cokolwiek.");
        if (new Scanner(System.in).next().equals("T")){
            System.out.println("Podaj nowe wymiary planszy");
            int x = new Scanner(System.in).nextInt();
            int y = new Scanner(System.in).nextInt();
            ticTacToe.createBoard(x,y);
        }
    }

    @Override
    public void boardUpdated(Mark[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getMark() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void playerMove(Mark mark) {
        int x;
        int y;
        System.out.println("Gracz " + mark.getMark() + " gdzie chcesz postawić znak?");
        x = new Scanner(System.in).nextInt() -1;
        y = new Scanner(System.in).nextInt() -1;
        ticTacToe.playerMove(x, y);
    }

    @Override
    public void filedOccupied(int x, int y) {
        System.out.println("Pole " + (x+1) + ", " + (y+1) + " jest już zajęte!");
    }

    @Override
    public void fieldOutsideBoard(int x, int y) {
        System.out.println("Pole " + (x+1) + ", " + (y+1) + " znajduje się poza planszą. Wprowadź poprawną wartość");
    }

    @Override
    public void noMoreMoves() {
        System.out.println("Nie ma możliwości dalszych ruchów. Koniec gry.");
    }

    @Override
    public void theWinnerIs(Mark mark) {
        System.out.println(mark + " wygrywa!");
    }

}
