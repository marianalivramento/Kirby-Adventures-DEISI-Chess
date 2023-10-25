package pt.ulusofona.lp2.deisichess;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;



public class GameManager {

    public GameManager() {
    }

    public void parseFicheiro4x4() {

    }
    public void parseFicheiro8x8() {

    }


    public boolean loadGame(File file) {

        BufferedReader buffered = null;
        String linha = null;

        try {
            buffered = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            linha = buffered.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (linha != null) {

            switch (linha) {
                case "4":
                    System.out.println("li ficheiro 4x4");
                    parseFicheiro4x4();
                    return true;
                case "8":
                    parseFicheiro8x8();
                    return true;
                default:
                    return false;
                }
            }
        return false;
    }


   public int getBoardSize() {
        return 0;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        return false;
    }

    public String [] getSquareInfo(int x, int y) {
        return null;
    }

    public String [] getPieceInfo(int ID) {
        return null;
    }


    public String getPieceInfoAsString(int ID) {
        return null;
    }

    public int getCurrentTeamID() {
        return 0;
    }

    public boolean gameOver() {
        return false;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }
}
