import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class GameManager {
    boolean loadGame(File file) {
        return false;
    }

    int getBoardSize() {
        return 0;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        return false;
    }

    String [] getSquareInfo(int x, int y) {
        return null;
    }

    String [] getPieceInfo(int ID) {
        return null;
    }


    String getPieceInfoAsString(int ID) {
        return null;
    }

    int getCurrentTeamID() {
        return 0;
    }

    boolean gameOver() {
        return false;
    }

    ArrayList<String> getGameResults() {
        return null;
    }

    JPanel getAuthorsPanel() {
        return null;
    }
}
