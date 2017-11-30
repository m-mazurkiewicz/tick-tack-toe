public class Main {

    public static void main(String[] args) {
        UI consoleUI=new ConsoleUI();
        BackEnd game=new BackEnd();
        consoleUI.enterBoardSize(game);
        consoleUI.printBoard(game);
        while (!game.isGameOver()){
            consoleUI.insertNewMark(game,Mark.CIRCLE);
            consoleUI.printBoard(game);
            if (!game.isGameOver()){
                consoleUI.insertNewMark(game,Mark.CROSS);
                consoleUI.printBoard(game);
            }
        }
    }
}
