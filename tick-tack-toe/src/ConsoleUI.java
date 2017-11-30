import java.util.Scanner;

public class ConsoleUI implements UI {

    public void enterBoardSize(BackEnd game){
        Scanner sizeInput=new Scanner(System.in);
        System.out.println("Podaj ilość wierszy planszy");
        while (!sizeInput.hasNextInt()){
            System.out.println("Wprowadź LICZBĘ");
            sizeInput.next();
        }
        int x=sizeInput.nextInt();
        System.out.println("Podaj ilość kolumn planszy");
        while (!sizeInput.hasNextInt()){
            System.out.println("Wprowadź LICZBĘ");
            sizeInput.next();
        }
        int y=sizeInput.nextInt();
        game.createBoard(x,y);
    }

    public void printBoard(BackEnd game){
        for(int i=0;i<game.getSizeX();i++){
            for (int j=0;j<game.getSizeY();j++){
                System.out.print(game.getSingleFieldFromBoard(i,j).getMark());
            }
            System.out.println();
        }
    }

    public void insertNewMark(BackEnd game, Mark player){
        int x;
        int y;
        do {
            System.out.println("Gracz "+player.getMark()+" gdzie chcesz postawić znak?");
            x=insertRowNumber(game);
            y=insertColumnNumber(game);
        }while (!game.checkIfFieldIsEmpty(x,y));
        game.insertSign(x, y, player);
        if(game.checkIfWin(x,y,player)) {
            printWin(player);
        }
        if (game.isBoardFull()){
            printBoardIsFull();
        }
    }

    private int insertRowNumber(BackEnd game){
        Scanner input=new Scanner(System.in);
        int x;
        System.out.println("wiersz: ");
        while (!input.hasNextInt()){
            System.out.println("Wprowadź LICZBĘ");
            input.next();
        }
        x=input.nextInt()-1;
        while (!game.checkIfRowExists(x)) {
            System.out.println("Wprowadź poprawny wiersz");
            while (!input.hasNextInt()){
                System.out.println("Wprowadź LICZBĘ");
                input.next();
            }
            x = input.nextInt() - 1;
        }
        return x;
    }

    private int insertColumnNumber(BackEnd game){
        Scanner input=new Scanner(System.in);
        int y;
        System.out.println("kolumna: ");
        while (!input.hasNextInt()){
            System.out.println("Wprowadź LICZBĘ");
            input.next();
        }
        y = input.nextInt() - 1;
        while (!game.checkIfColumnExists(y)) {
            System.out.println("Wprowadź poprawną kolumnę");
            while (!input.hasNextInt()){
                System.out.println("Wprowadź LICZBĘ");
                input.next();
            }
            y = input.nextInt() - 1;
        }
        return y;
    }

    private void printWin(Mark player){
        System.out.println("Gracz "+player.getMark()+" wygrywa!!!");
        System.out.println("Koniec gry");
    }

    private void printBoardIsFull(){
        System.out.println("Nie ma możliwości dalszych ruchów");
        System.out.println("Koniec gry");
    }
}
