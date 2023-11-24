package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestProjeto {
/*
    @Test
    public void teste_loadGame_1() {

        GameManager gm = new GameManager();
        assertEquals(true, gm.loadGame(new File("test-files/4x4.txt")));


    }

    @Test
    public void teste_get_board_size() {

        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        assertEquals(4, gm.getBoardSize());


    }

    @Test
    public void teste_loadGame_2() {

        GameManager gm = new GameManager();
        assertEquals(false, gm.loadGame(new File("naoExiste.txt")));

    }

    @Test
    public void teste_getSquareInfoPiece() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("test-files/4x4.txt"));
        String[] u = gm.getSquareInfo(1, 0);

        String[] r = new String[5];
        r[0] = "1";
        r[1] = "0";
        r[2] = "0";
        r[3] = "Chefe";
        r[4] = "crazy_emoji_black.png";

        assertEquals(Arrays.toString(r), Arrays.toString(u));
    }

    @Test
    public void teste_getPieceInfoAsString() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("test-files/4x4.txt"));
        String u = gm.getPieceInfoAsString(1);

        assertEquals("1 | 0 | 0 | Chefe @ (1, 0)", u);
    }

    @Test
    public void teste_getSquareInfoNoPiece() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("test-files/4x4.txt"));
        String[] exp = gm.getSquareInfo(0, 0);

        String[] ret = new String[0];

        assertEquals(Arrays.toString(ret), Arrays.toString(exp));
    }


    @Test
    public void teste_getInfoPiece() {

        GameManager gm = new GameManager();

        gm.loadGame(new File("test-files/4x4.txt"));
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
    public void teste_get_current_team_id() {
        GameManager gm = new GameManager();
        gm.jogo.mudarEquipa();

        assertEquals(1, gm.getCurrentTeamID());
    }

    @Test
    public void teste_move_coordenada_validas() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        assertEquals(true, gm.move(1,0,0,0));


    }


    @Test
    public void teste_move_coordenada_validas2() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/4x4.txt"));
            gm.move(2, 0, 0, 0);

            String[] ret = new String[5];
            ret[0] = "2";
            ret[1] = "0";
            ret[2] = "10";
            ret[3] = "Chefe";
            ret[4] = "crazy_emoji_black.png";

            assertEquals(Arrays.toString(ret), Arrays.toString(gm.getSquareInfo(0, 0)));
        } catch (InvalidGameInputException | IOException e) {
            // Handle the exception (print a message, fail the test, etc.)
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void teste_move_captura_peca_da_mesma_equipa() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        assertEquals(false, gm.move(1,0,2,1));
    }

    @Test
    public void teste_move_peca_capturada_muda_estado() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        gm.move(2,1,1,2);

        String[] ret = gm.getPieceInfo(6);

        assertEquals("capturado", ret[4]);
    }

    @Test
    public void teste_move_muda_peca_depois_de_capturar() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        gm.move(2,1,1,2);

        String[] ret = gm.getSquareInfo(1,2);

        assertEquals("Grande Artista", ret[3]);
    }

    @Test
    public void teste_move_acabar_o_jgo() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        gm.move(2,1,1,2);
        gm.move(2,3,2,2);
        gm.move(1,2,2,2);
        gm.move(1,3,1,2);
        gm.move(2,2,1,2);
        gm.gameOver();

        assertEquals(true, gm.gameOver());
    }

    @Test
    public void nr_jogadas_sem_captura() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        gm.move(1,0,0,1);
        gm.move(1,2,0,2);
        gm.move(0,1,0,2);


        assertEquals(0, gm.jogo.nrDeJogadasSemCaptura);
    }



    @Test
    public void teste_vitoria_brancas() {
        GameManager gm = new GameManager();
        gm.loadGame(new File("test-files/4x4.txt"));

        gm.move(2,1,1,1);
        gm.move(1,2,1,1);

        gm.move(3,0,2,1);
        gm.move(1,1,1,0);

        gm.move(2,1,2,2);
        gm.move(1,3,2,2);


        assertEquals(3, gm.jogo.equipaBranca.nrCapturas);
    }
*/
}
