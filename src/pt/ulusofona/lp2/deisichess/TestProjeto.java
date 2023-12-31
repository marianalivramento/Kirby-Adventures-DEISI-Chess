package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

public class TestProjeto {

    @Test
    public void get_board_size() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        assertEquals(8, gm.getBoardSize());
    }

    @Test
    public void get_current_team_id() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }


        assertEquals(10, gm.getCurrentTeamID());

    }

    @Test
    public void game_over_simples() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(1, 5, 0, 6);


        assertTrue(gm.gameOver());

    }


    @Test
    public void kirby_undo_volta_ao_estado_anterior() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,4);
        gm.move(3,6,2,5);
        gm.move(1,4,2,4);
        gm.move(2,5,2,4);
        gm.undo();

        assertFalse(gm.move(2,5,6,5));

    }

    @Test
    public void erro_null_pointer_exception() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1,0,1,4);
        gm.move(3,6,2,5);
        gm.move(1,4,2,4);
        gm.move(2,5,2,4);
        gm.undo();
        gm.move(2,5,3,5);
        gm.move(5,0,5,5);
        gm.move(3,5,4,5);
        gm.move(3,1,3,2);

        assertTrue(gm.move(4,5,5,5));

    }

    @Test
    public void mensagem_vitoria() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(1, 5, 0, 6);
        gm.getGameResults().get(1);


        assertEquals("Resultado: VENCERAM AS PRETAS", gm.getGameResults().get(1));

    }

    @Test
    public void jokerRainha_captura_rainha() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(7, 0, 4, 3);
        gm.move(1, 7, 1, 3);
        gm.move(0, 0, 0, 1);
        gm.move(1, 3, 3, 3);
        gm.move(0, 1, 0, 2);
        gm.move(0, 7, 0, 6);

        assertFalse(gm.move(4, 3, 3, 3));

    }


    @Test
    public void undo_1_movimento() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(0, 0, 0, 1);
        gm.undo();

        String[] u = gm.getSquareInfo(0, 0);

        String[] r = new String[5];
        r[0] = "1";
        r[1] = "0";
        r[2] = "10";
        r[3] = "O Poderoso Chefao";
        r[4] = "rei-preto.png";

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

        gm.move(0, 0, 0, 1);
        gm.move(1, 7, 1, 2);
        gm.move(0, 1, 1, 2);

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

        gm.move(0, 0, 0, 1);
        gm.move(1, 7, 1, 2);
        gm.move(0, 1, 1, 2);
        gm.move(0, 7, 0, 6);
        gm.move(1, 2, 2, 2);


        gm.undo();
        gm.undo();
        gm.undo();
        gm.undo();
        gm.undo();

        String[] str = gm.getSquareInfo(0, 0);

        String[] esperado = new String[5];
        esperado[0] = "1";
        esperado[1] = "0";
        esperado[2] = "10";
        esperado[3] = "O Poderoso Chefao";
        esperado[4] = "rei-preto.png";

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

        gm.move(0, 0, 0, 1);
        gm.move(1, 7, 1, 2);


        String str = gm.getPieceInfoAsString(8);

        assertEquals(str, "8 | Joker/Padre da Vila | 4 | 10 | O Beberolas @ (7, 0)");

    }

    @Test
    public void joker_Rainha_nao_pode_capturar_Rainha() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        gm.move(7, 0, 3, 4);
        gm.move(1, 7, 1, 4);
        gm.move(0, 0, 0, 1);
        gm.move(1, 4, 2, 4);

        gm.move(0, 1, 0, 2);
        gm.move(0, 7, 0, 6);

        assertEquals(false, gm.move(3, 4, 2, 4));

    }

    @Test
    public void conta_numero_capturas() {

        GameManager gm = new GameManager();

        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(2, 0, 4, 2);
        gm.move(2, 7, 4, 5);
        gm.move(5, 0, 5, 5);
        gm.move(5, 7, 5, 5);
        gm.move(7, 0, 7, 7);


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


        assertEquals(true, gm.move(3, 0, 4, 1));

    }

    @Test
    public void conta_pontos_rei() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(1, 5, 0, 6);

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

        gm.move(1, 0, 1, 5); // preta válida
        gm.move(0, 7, 0, 3); // branca inválida
        gm.move(0, 7, 0, 7); // branca inválida
        gm.move(0, 7, 0, 6); //branca válida

        gm.move(0, 0, 0, 1); //preta válida
        gm.move(1, 7, 1, 7); //branca invalida
        gm.move(5, 7, 5, 6); // branca valida
        gm.move(1, 5, 0, 6); // preta valida - captura

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

        gm.move(1, 0, 1, 5); // preta válida
        gm.move(0, 7, 0, 3); // branca inválida
        gm.move(0, 7, 0, 7); // branca inválida
        gm.move(0, 7, 0, 6); //branca válida

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

        gm.move(0, 0, 0, 1); //preta válida
        gm.move(1, 7, 1, 7); //branca invalida
        gm.move(5, 7, 5, 6); // branca valida
        gm.move(1, 5, 0, 6); // preta valida - captura

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

        gm.move(3, 0, 0, 3); //valida p
        gm.move(1, 7, 4, 4); //valida b
        gm.move(1, 0, 1, 1); //valida p
        gm.move(2, 7, 0, 5); //valida b

        gm.move(5, 0, 5, 7); //preta captura valida p
        gm.move(0, 5, 2, 3); //valida b
        gm.move(6, 0, 5, 1); //preta invalida - homer dorme
        gm.move(2, 0, 0, 2); //valida p

        gm.move(4, 4, 4, 2); //valida b
        gm.move(0, 3, 2, 5); //valida p
        gm.move(0, 7, 1, 7); //valida b
        gm.move(1, 1, 1, 7); //preta invalida

        gm.move(4, 2, 3, 3); //preta invalida
        gm.move(1, 1, 1, 7); //preta invalida
        gm.move(6, 0, 5, 1); //valida p
        gm.move(3, 7, 0, 4); //valida b

        gm.move(7, 0, 7, 3); //valida p
        gm.move(4, 2, 5, 1); //branca captura valida b
        gm.move(1, 1, 1, 5); //valida p
        gm.move(5, 1, 2, 1); //valida b

        gm.move(1, 5, 1, 7); //preta captura valida p


        assertEquals(2, gm.jogo.getEquipaPreta().getNrCapturas());
    }

    @Test
    public void movimento_padre_da_vila3() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        assertTrue(gm.move(3, 0, 6, 3));
    }

    @Test
    public void conta_pontos_rainha() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(0, 0, 0, 1);
        gm.move(0, 6, 1, 5);


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

        gm.move(2, 0, 4, 2);
        gm.move(1, 7, 1, 2);
        gm.move(0, 0, 0, 1);
        gm.move(1, 2, 4, 2);
        gm.move(3, 0, 4, 2);


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

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(1, 5, 0, 6);


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

        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);
        gm.move(1, 5, 0, 6);


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

        gm.move(1, 0, 1, 0);
        gm.move(1, 0, 1, 0);
        gm.move(1, 0, 1, 0);
        gm.move(1, 0, 1, 0);
        gm.move(1, 0, 1, 0);
        gm.move(1, 0, 1, 0);
        gm.move(5, 0, 5, 4);
        gm.move(0, 7, 0, 6);
        gm.move(4, 0, 4, 0);
        gm.move(4, 0, 5, 0);


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

        gm.move(5, 0, 5, 7);
        gm.move(4, 7, 5, 7);
        gm.move(2, 7, 4, 5);
        gm.move(1, 5, 4, 5);
        gm.move(1, 0, 1, 5);
        gm.move(3, 7, 1, 5);


        assertEquals("O Maior Grande (PRETA) fez 1 capturas", StatisticsKt.topCincoCapturas(gm).get(0));
    }

    @Test
    public void estatistica_pecas_mais_5_capturas() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(3, 0, 1, 2);
        gm.move(1, 7, 1, 2);
        gm.move(2, 0, 0, 2);
        gm.move(1, 2, 0, 2);
        gm.move(5, 0, 5, 2);
        gm.move(0, 2, 5, 2);
        gm.move(4, 0, 5, 0);
        gm.move(5, 2, 5, 0);
        gm.move(7, 0, 6, 1);
        gm.move(5, 0, 6, 1);
        gm.move(0, 0, 1, 1);
        gm.move(6, 1, 6, 0);


        ArrayList<String> arr = new ArrayList<>();
        arr.add("BRANCA:A Barulhenta do Bairro:6");

        assertEquals(arr, StatisticsKt.maisCincoCapturas(gm));
    }

    @Test
    public void get_hints_simples_sem_nada() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }


        assertEquals("(0,1) -> 0", gm.getHints(0, 0).get(0).toString());
    }

    @Test
    public void nomes_dos_tipos() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        ArrayList<String> arr = new ArrayList<>();
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(1).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(2).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(3).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(4).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(5).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(6).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(7).nomeDoTipo(gm.jogo));
        arr.add(gm.jogo.tabuleiro.retornaPecaPorId(8).nomeDoTipo(gm.jogo));


        ArrayList<String> expectedArr = new ArrayList<>();
        expectedArr.add("Rei");
        expectedArr.add("Rainha");
        expectedArr.add("Ponei Mágico");
        expectedArr.add("Padre da Vila");
        expectedArr.add("TorreHor");
        expectedArr.add("TorreVert");
        expectedArr.add("Homer Simpson");
        expectedArr.add("Joker/Rainha");


        assertEquals(expectedArr, arr);
    }

    @Test
    public void getHints_jogadas_permitidas_atualiza_pontos() {
        GetHints um = new GetHints(1, 2);
        um.valorPeca = 3;

        GetHints dois = new GetHints(3, 4);
        dois.valorPeca = 2;

        Assertions.assertEquals(um.compareTo(dois), -1);
    }

    @Test
    public void getHints_compareTo_maior() {
        GetHints um = new GetHints(1, 2);
        um.valorPeca = 3;

        GetHints dois = new GetHints(3, 4);
        dois.valorPeca = 2;

        Assertions.assertEquals(um.compareTo(dois), -1);
    }


    @Test
    public void getHints_compareTo_menor() {
        GetHints um = new GetHints(1, 2);
        um.valorPeca = 2;

        GetHints dois = new GetHints(3, 4);
        dois.valorPeca = 5;

        Assertions.assertEquals(um.compareTo(dois), 1);
    }

    // ************* aumenta e diminui valor ***************

    @Test
    public void aumenta_valor_rei() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(1).aumentaValor(10);
        gm.jogo.getTabuleiro().retornaPecaPorId(1).diminuiValor(100);
        assertEquals(910, gm.jogo.getTabuleiro().retornaPecaPorId(1).getValor());

    }

    @Test
    public void aumenta_valor_rainha() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(2).aumentaValor(2);
        gm.jogo.getTabuleiro().retornaPecaPorId(2).diminuiValor(10);
        assertEquals(0, gm.jogo.getTabuleiro().retornaPecaPorId(2).getValor());

    }

    @Test
    public void aumenta_valor_ponei() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(3).aumentaValor(10);
        gm.jogo.getTabuleiro().retornaPecaPorId(3).diminuiValor(5);
        assertEquals(10, gm.jogo.getTabuleiro().retornaPecaPorId(3).getValor());

    }

    @Test
    public void aumenta_valor_padre() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(4).aumentaValor(10);
        gm.jogo.getTabuleiro().retornaPecaPorId(4).diminuiValor(5);
        assertEquals(8, gm.jogo.getTabuleiro().retornaPecaPorId(4).getValor());

    }

    @Test
    public void aumenta_valor_torreV() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(5).aumentaValor(0);
        gm.jogo.getTabuleiro().retornaPecaPorId(5).diminuiValor(0);
        assertEquals(3, gm.jogo.getTabuleiro().retornaPecaPorId(5).getValor());

    }

    @Test
    public void aumenta_valor_torreH() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(6).aumentaValor(1);
        gm.jogo.getTabuleiro().retornaPecaPorId(6).diminuiValor(3);
        assertEquals(1, gm.jogo.getTabuleiro().retornaPecaPorId(6).getValor());

    }

    @Test
    public void aumenta_valor_Homer() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(7).aumentaValor(2);
        gm.jogo.getTabuleiro().retornaPecaPorId(7).diminuiValor(4);
        assertEquals(0, gm.jogo.getTabuleiro().retornaPecaPorId(7).getValor());

    }

    @Test
    public void aumenta_valor_Joker() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(8).aumentaValor(1);
        gm.jogo.getTabuleiro().retornaPecaPorId(8).diminuiValor(3);
        assertEquals(2, gm.jogo.getTabuleiro().retornaPecaPorId(8).getValor());

    }

    @Test
    public void aumenta_valor_kirby() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.jogo.getTabuleiro().retornaPecaPorId(17).aumentaValor(100);
        gm.jogo.getTabuleiro().retornaPecaPorId(17).diminuiValor(50);
        assertEquals(50, gm.jogo.getTabuleiro().retornaPecaPorId(17).getValor());

    }


    // ******************** TESTES KIRBY ************************

    @Test
    public void valor_kirby() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 4);
        gm.move(3, 6, 2, 5);
        gm.move(1, 4, 2, 4);
        gm.move(2, 5, 2, 4);

        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(18).getValor(), 8);
    }

    @Test
    public void conta_ponto_kirby() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 4);
        gm.move(3, 6, 2, 5);
        gm.move(1, 4, 2, 4);
        gm.move(2, 5, 2, 4);
        gm.move(0, 0, 1, 1);
        gm.move(2, 4, 1, 3);
        gm.move(1, 1, 1, 2);
        gm.move(1, 3, 1, 2);

        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(18).getPontos(), 2016);
    }

    @Test
    public void kirby_muda_estado() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x8-kirby.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        gm.move(1, 0, 1, 4);
        gm.move(3, 6, 2, 5);
        gm.move(1, 4, 2, 4);
        gm.move(2, 5, 2, 4);

        assertEquals(gm.jogo.getTabuleiro().retornaPecaPorId(18).kirbs, "Rainha");
    }


    @Test
    public void ficheiro_invalido() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/ficheiro-invalido.txt"));
            fail("Expected InvalidGameInputException, but no exception was thrown");
        } catch (IOException e) {
            fail("Unexpected IOException: " + e.getMessage());
        } catch (InvalidGameInputException e) {
            assertEquals("DADOS A MENOS (Esperava: 4 ; Obtive: 1)", e.getProblemDescription());
            assertEquals(3, e.getLineWithError());
        }
    }

    @Test
    public void getPieceinfoAsString() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        assertEquals("1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)", gm.getPieceInfoAsString(1));
        assertEquals("2 | Rainha | 8 | 10 | A Dama Selvagem @ (1, 0)", gm.getPieceInfoAsString(2));
        assertEquals("3 | Ponei Mágico | 5 | 10 | O Grande Artista @ (2, 0)", gm.getPieceInfoAsString(3));
        assertEquals("4 | Padre da Vila | 3 | 10 | Amante de Praia @ (3, 0)", gm.getPieceInfoAsString(4));
        assertEquals("5 | TorreHor | 3 | 10 | Artolas @ (4, 0)", gm.getPieceInfoAsString(5));
        assertEquals("6 | TorreVert | 3 | 10 | O Maior Grande @ (5, 0)", gm.getPieceInfoAsString(6));

    }

    @Test
    public void getPieceinfoAsString_homerAcordado() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        gm.move(1, 0, 1, 5);
        gm.move(0, 7, 0, 6);

        assertEquals("7 | Homer Simpson | 2 | 10 | O Hommie @ (6, 0)", gm.getPieceInfoAsString(7));

    }

    @Test
    public void getPieceinfoAsString_homerADormir() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }
        if (gm.jogo.homerADormir()) {
            assertEquals("Doh! zzzzzz", gm.getPieceInfoAsString(7));
        }
    }

    @Test
    public void getPieceinfoAsString_joker() {
        GameManager gm = new GameManager();
        try {
            gm.loadGame(new File("test-files/8x16.txt"));
        } catch (InvalidGameInputException | IOException e) {
            fail("Exception not expected: " + e.getMessage());
        }

        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(3, 0, 1, 2);
        assertEquals("8 | Joker/Ponei Mágico | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(1, 7, 1, 2);
        assertEquals("8 | Joker/Padre da Vila | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(2, 0, 0, 2);
        assertEquals("8 | Joker/TorreHor | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(1, 2, 0, 2);
        assertEquals("8 | Joker/TorreVert | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(5, 0, 5, 2);
        assertEquals("8 | Joker/Homer Simpson | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));
        gm.move(0, 2, 5, 2);
        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (7, 0)", gm.getPieceInfoAsString(8));

    }

}


