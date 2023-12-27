package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


        assertEquals(0, gm.jogo.getEquipaPreta().getNrCapturas());

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

        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(2).getPontos(), 1000);
    }


    @Test
    public void savegame() {
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

        gm.move(0,0,0,1); //preta válida
        gm.move(1,7,1,7); //branca invalida
        gm.move(5,7,5,6); // branca valida
        gm.move(1,5,0,6); // preta valida - captura

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

        assertEquals(1, gm.jogo.getEquipaPreta().getNrCapturas());
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

        assertEquals(1, gm.jogo.getEquipaPreta().getNrCapturas());
    }

    @Test
    public void jogo_completo_video() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(3,0,0,3); //valida p
        gm.move(1,7,4,4); //valida b
        gm.move(1,0,1,1); //valida p
        gm.move(2,7,0,5); //valida b

        gm.move(5,0,5,7); //preta captura valida p
        gm.move(0,5,2,3); //valida b
        gm.move(6,0,5,1); //preta invalida - homer dorme
        gm.move(2,0,0,2); //valida p

        gm.move(4,4,4,2); //valida b
        gm.move(0,3,2,5); //valida p
        gm.move(0,7,1,7); //valida b
        gm.move(1,1,1,7); //preta invalida

        gm.move(4,2,3,3); //preta invalida
        gm.move(1,1,1,7); //preta invalida
        gm.move(6,0,5,1); //valida p
        gm.move(3,7,0,4); //valida b

        gm.move(7,0,7,3); //valida p
        gm.move(4,2,5,1); //branca captura valida b
        gm.move(1,1,1,5); //valida p
        gm.move(5,1,2,1); //valida b

        gm.move(1,5,1,7); //preta captura valida p



        assertEquals(2, gm.jogo.getEquipaPreta().getNrCapturas());
    }

   /* @Test
    public void movimento_padre_da_vila() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/teste_padre"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        assertTrue(gm.move(0,0,1,1));
    }
    
    */

    @Test
    public void movimento_padre_da_vila3() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        assertTrue(gm.move(3,0,6,3));
    }
    /*@Test
    public void movimento_padre_da_vila4() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        gm.move(0,0,0,1);
        gm.move(3,7,2,6);
        gm.move(0,1,0,2);

        assertTrue(gm.move(2,6,4,4));
    }

     */

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



        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(9).getPontos(), 8);
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



        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(10).getPontos(), 5);
    }

    @Test
    public void estatistica_top5() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5);
        gm.move(0,7,0,6);
        gm.move(1,5,0,6);


        ArrayList<String> arr = new ArrayList<>();
        arr.add("A Dama Selvagem (PRETA) tem 1000 pontos");

        assertEquals(arr, StatisticsKt.topCincoPontos(gm));
    }

    @Test
    public void estatistica_pecas_capturadas() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,5);
        gm.move(0,7,0,6);
        gm.move(1,5,0,6);


        ArrayList<String> arr = new ArrayList<>();
        arr.add("Rei");

        assertEquals(arr, StatisticsKt.tiposCapturados(gm));
    }

    @Test
    public void estatistica_pecas_baralhadas() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,0);
        gm.move(1,0,1,0);
        gm.move(1,0,1,0);
        gm.move(1,0,1,0);
        gm.move(1,0,1,0);
        gm.move(1,0,1,0);
        gm.move(5,0,5,4);
        gm.move(0,7,0,6);
        gm.move(4,0,4,0);
        gm.move(4,0,5,0);



        ArrayList<String> arr = new ArrayList<>();
        arr.add("10:A Dama Selvagem:6:0");
        arr.add("10:Artolas:1:1");

        assertEquals(arr, StatisticsKt.maisBaralhadas(gm));
    }

    @Test
    public void estatistica_top5_capturas() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(5,0,5,7);
        gm.move(4,7,5,7);
        gm.move(2,7,4,5);
        gm.move(1,5,4,5);

        assertEquals("O Maior Grande (PRETA) fez 1 capturas", StatisticsKt.topCincoCapturas(gm).get(0));
    }


    @Test
    public void get_hints_simples_sem_nada() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }


        assertEquals("(0,1) -> 0", gm.getHints(0,0).get(0).toString());
    }
}
