package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class GameManager {

    Jogo jogo = new Jogo();

    public GameManager() {
    }


    public void parsePecas(String linha) {

        String[] elementos = linha.split(":");

        Peca peca = new Peca();

        peca.setId(Integer.parseInt(elementos[0]));
        peca.setTipo(Integer.parseInt(elementos[1]));
        peca.setEquipa(new Equipa(Integer.parseInt(elementos[2])));

        jogo.defineEquipa(peca.getEquipa());
        peca.setAlcunha(elementos[3]);

        jogo.getTabuleiro().adicionaPecas(peca);

    }

    public boolean loadGame(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int linha = 0;

            while ((line = reader.readLine()) != null) {
                switch (linha) {
                    case 0:
                        jogo.criaTabuleiro(Integer.parseInt(line));
                        break;
                    case 1:
                        jogo.getTabuleiro().setNumeroDePecas(Integer.parseInt(line));
                        break;
                    default:
                        for (int i = 0; i < jogo.getTabuleiro().getNumeroDePecas(); i++) {
                            parsePecas(line);
                        }

                        break;
                }
                linha++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    public int getBoardSize() {
        return jogo.getTabuleiro().getTamanho();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        return false;
    }

    public String[] getSquareInfo(int x, int y) {
        String [] retorno = new String[5];
        Square sq = jogo.getTabuleiro().retornoPeca(x,y);

        if(sq == null) {
            return null;
        }
        if (!sq.ocupado) {
            return retorno;
        }

        retorno[0] = Integer.toString(sq.peca.id);
        retorno[1] = Integer.toString(sq.peca.tipo);
        retorno[2] = Integer.toString(sq.peca.equipa.pretoOuBranco);
        retorno[3] = sq.peca.alcunha;
        retorno[4] = null;

        return retorno;
    }

    public String[] getPieceInfo(int ID) {
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
