package pt.ulusofona.lp2.deisichess;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;



public class GameManager {

    public GameManager() {
    }



    public boolean loadGame(File file) {
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
