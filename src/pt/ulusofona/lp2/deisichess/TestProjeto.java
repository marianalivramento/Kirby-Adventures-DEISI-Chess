package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProjeto {

   @Test
   public void teste_loadGame_1() {

       GameManager gm = new GameManager();

       assertTrue(gm.loadGame(new File("4x4.txt")));
   }

    @Test
    public void teste_getSquareInfoPiece() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("4x4.txt"));
        String[] u = gm.getSquareInfo(1,0);

        String[] r = new String[5];
        r[0] = "1";
        r[1] = "0";
        r[2] = "0";
        r[3] = "Chefe";
        r[4] = null;

        assertEquals(Arrays.toString(r), Arrays.toString(u));
    }


    @Test
    public void teste_getInfoPiece() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("4x4.txt"));
        String[] u = gm.getPieceInfo(2);

        String[] r = new String[7];
        r[0] = "2";
        r[1] = "0";
        r[2] = "0";
        r[3] = "Selvagem";
        r[4] = "0";
        r[5] = "3";
        r[6] = "0";


        assertEquals(Arrays.toString(r), Arrays.toString(u));

    }



}
