package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestProjeto {

   @Test
   public void teste_loadGame() {
       GameManager gm = new GameManager();
       assertEquals(true,gm.loadGame(new File("4x4.txt")));

   }

    @Test
    public void teste_get_square_info_piece() {

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
    public void teste_get_square_info_no_piece() {

        GameManager gm = new GameManager();
        gm.loadGame(new File("4x4.txt"));
        String[] exp = gm.getSquareInfo(0,0);
        String[] ret = new String[5];

        assertEquals(Arrays.toString(ret), Arrays.toString(exp));
    }


    @Test
    public void teste_get_info_piece() {

        GameManager gm = new GameManager();
        gm.loadGame(new File("4x4.txt"));
        String[] s = gm.getPieceInfo(2);

        String[] r = new String[7];
        r[0] = "2";
        r[1] = "0";
        r[2] = "0";
        r[3] = "Selvagem";
        r[4] = "em jogo";
        r[5] = "3";
        r[6] = "0";

        assertEquals(Arrays.toString(r), Arrays.toString(s));

    }

    @Test
    public void teste_get_info_piece_as_string() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("4x4.txt"));
        String s = gm.getPieceInfoAsString(7);

        assertEquals(null ,s);

    }

    @Test
    public void teste_get_current_team_id() {
        GameManager gm = new GameManager();
        gm.jogo.mudarEquipa();

        assertEquals(1 ,gm.getCurrentTeamID());
    }


    @Test
    public void teste_move_coordenada_invalidas_no_tabuleiro() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("4x4.txt"));

        assertEquals(false, gm.move(4,0,0,1));
    }

    @Test
    public void teste_move_coordenada_invalidas_mudar_peça_que_nao_existe() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("4x4.txt"));

        assertEquals(false, gm.move(0,0,0,0));
    }


}
