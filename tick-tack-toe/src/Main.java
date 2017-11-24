public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Interface interfejs=new Interface();
        BackEnd game=new BackEnd();
        interfejs.enterBoardSize(game);
        interfejs.printBoard(game);
        while (true){
          //  game.move(game,1);
          //  game.move(game,2);
            interfejs.insert(game,1);
            interfejs.printBoard(game);
            if (game.isWin()){
                break;
            }
            interfejs.insert(game,2);
            interfejs.printBoard(game);
            if (game.isWin()){
                break;
            }
        }
        //interfejs.insert(game,1);

    }
}
