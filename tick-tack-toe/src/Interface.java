import java.util.Scanner;

public class Interface {

    public void enterBoardSize(BackEnd game){
        Scanner sizeInput=new Scanner(System.in);
        System.out.println("Podaj ilość wierszy planszy");
        int x=sizeInput.nextInt();
        System.out.println("Podaj ilość kolumn planszy");
        int y=sizeInput.nextInt();
        //BackEnd game=new BackEnd();
        game.createBoard(x,y);
    }
    public void printBoard(BackEnd game){
        for(int i=0;i<game.sizeX;i++){
            for (int j=0;j<game.sizeY;j++){
                System.out.print(game.board[i][j]);
            }
            System.out.println();
        }
    }
    public void insert(BackEnd game, int player){
        Scanner input=new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.println("Gracz "+player+" gdzie chcesz postawić znak?");
            System.out.println("wiersz: ");
            x=input.nextInt()-1;
            while (!game.checkRow(x)) {
                System.out.println("Wprowadź jeszcze raz wiersz");
                x = input.nextInt() - 1;
            }
            System.out.println("kolumna: ");
            y = input.nextInt() - 1;
            while (!game.checkColumn(y)) {
                System.out.println("Wprowadź jeszcze raz kolumnę");
                y = input.nextInt() - 1;
            }
        }while (!game.checkIfEmpty(x,y));
        game.insertSign(x, y, player);
       // int[] output={x,y};
      //  return output;
        game.checkIfWin(x,y,player);
    }
    public static void printWin(int player){
        System.out.println("Gracz "+player+" wygrywa!!!");
        System.out.println("Koniec gry");
    }
}
