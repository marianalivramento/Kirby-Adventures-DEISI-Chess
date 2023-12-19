package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestProjeto {

    @Test
    public void undo_1_movimento() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(0,0,0,1);
        gm.undo();

        String[] u = gm.getSquareInfo(0, 0);

        String[] r = new String[5];
        r[0] = "1";
        r[1] = "0";
        r[2] = "10";
        r[3] = "O Poderoso Chefao";
        r[4] = "crazy_emoji_black.png";

        assertEquals(Arrays.toString(u), Arrays.toString(r));

    }


    @Test
    public void undo_movimento_com_captura_peca_volta_para_jogo() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(0,0,0,1);
        gm.move(1,7,1,2);
        gm.move(0,1,1,2);

        gm.undo();

        String[] str = gm.getPieceInfo(10);

        assertEquals(str[4], "em jogo");

    }

    @Test
    public void undo_ate_o_inicio_do_jogo() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(0,0,0,1);
        gm.move(1,7,1,2);
        gm.move(0,1,1,2);
        gm.move(0,7,0,6);
        gm.move(1,2,2,2);


        gm.undo();
        gm.undo();
        gm.undo();
        gm.undo();
        gm.undo();

        String[] str = gm.getSquareInfo(0,0);

        String[] esperado = new String[5];
        esperado[0] = "1";
        esperado[1] = "0";
        esperado[2] = "10";
        esperado[3] = "O Poderoso Chefao";
        esperado[4] = "crazy_emoji_black.png";

        assertEquals(Arrays.toString(str), Arrays.toString(esperado));

    }

    @Test
    public void joker_muda_turno() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(0,0,0,1);
        gm.move(1,7,1,2);


        String str = gm.getPieceInfoAsString(8);

        assertEquals(str, "8 | Joker/Padre da Vila | 4 | 10 | O Beberolas @ (7, 0)");

    }

    @Test
    public void conta_numero_capturas() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5);
        gm.move(0,7,0,6);
        gm.move(2,0,4,2);
        gm.move(2,7,4,5);
        gm.move(5,0,5,5);
        gm.move(5,7,5,5);
        gm.move(7,0,7,7);


        assertEquals(0, gm.jogo.equipaPreta.nrCapturas);

    }

    @Test
    public void padre_da_vila_move() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }


        assertEquals(true, gm.move(3,0,4,1));

    }
    @Test
    public void conta_pontos_rei() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5);
        gm.move(0,7,0,6);
        gm.move(1,5,0,6);

        assertEquals(gm.jogo.tabuleiro.retornaPecaPorId(2).pontos, 1000);
    }


    @Test
    public void acaba_um_jogo_guardado() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5); // preta válida
        gm.move(0,7,0,3); // branca inválida
        gm.move(0,7,0,7); // branca inválida
        gm.move(0,7,0,6); //branca válida

        try {
            gm.saveGame(new File("test-files/new.txt"));
        } catch (IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        try {
            gm.loadGame(new File("test-files/new.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        //1-3-0 //0-2-3

        gm.move(0,0,0,1); //preta válida
        gm.move(1,7,1,7); //branca invalida
        gm.move(5,7,5,6); // branca valida
        gm.move(1,5,0,6); // preta valida - captura

        assertEquals(3, gm.jogo.equipaBranca.nrTentativasInvalidas);
    }


    @Test
    public void conta_pontos_rainha() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5);
        gm.move(0,7,0,6);
        gm.move(0,0,0,1);
        gm.move(0,6,1,5);



        assertEquals(gm.jogo.tabuleiro.retornaPecaPorId(9).pontos, 8);
    }

    @Test
    public void conta_pontos_ponei() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(2,0,4,2);
        gm.move(1,7,1,2);
        gm.move(0,0,0,1);
        gm.move(1,2,4,2);



        assertEquals(gm.jogo.tabuleiro.retornaPecaPorId(10).pontos, 5);
    }

    /*@Test
    public void getHints() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        List<Comparable> boo = gm.getHints(5,0);
        Collections.sort(boo);
        assertEquals(boo, "");
    }

    @Test
    public void getHintsDepoisDeMove() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        gm.move(5,0,5,5);
        List<Comparable> boo = gm.getHints(5,7);
        Collections.sort(boo);
        assertEquals(boo, "[(5,5) -> 3, (5,6) -> 0]");
    }

     */

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
