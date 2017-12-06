public class Main {

    public static void main(String[] args) {
        TicTacToe game = new BackEnd(3, 3);
        game.setGameListener(new ConsoleGameListener(game));
    }
}