package pt.ulusofona.lp2.deisichess;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();

        System.out.println(gm.loadGame(new File("4x4.txt")));
        System.out.println(gm.getBoardSize());
    }
}
