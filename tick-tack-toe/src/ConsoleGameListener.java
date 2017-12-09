import java.util.Scanner;

public class ConsoleGameListener implements GameListener {

    private TicTacToe ticTacToe;

    public ConsoleGameListener(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        changeDefaultBoardSize();
    }

    @Override
    public void changeDefaultBoardSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Domyślna wielkość planszy, to 3 x 3. Jeśli chcesz zmienić jej rozmiar wciśnij T. Jeśli nie - wpisz cokolwiek.");
        if (input.next().equals("T")){
            System.out.println("Podaj nowe wymiary planszy");
            int x = insertInt();
            int y = insertInt();
            ticTacToe.createBoard(x,y);
        }
    }

    @Override
    public void boardUpdated(Mark[][] board) {
        printBoard(board);
    }

    @Override
    public void playerMove(Mark mark) {
        int x;
        int y;
        System.out.println("Gracz " + mark.getMark() + " gdzie chcesz postawić znak?");
        x = insertInt() - 1;
        y = insertInt() - 1;
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

    private void printBoard(Mark[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getMark() + " ");
            }
            System.out.println();
        }
    }

    private int insertInt(){
        Scanner inputInt=new Scanner(System.in);
        while (!inputInt.hasNextInt()){
            System.out.println("Wprowadź LICZBĘ");
            inputInt.next();
        }
        return inputInt.nextInt();
    }
}
