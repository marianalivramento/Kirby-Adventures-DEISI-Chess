package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class GameManager {

    Jogo jogo = new Jogo();

    public GameManager() {
    }

    /*public void parseFicheiro4x4(File file) {
        jogo.criaTabuleiro();
        jogo.tabuleiro.tamanho = 4;

        BufferedReader buffered = null;

        String linha = null;
        int i = 1;


        try {
            buffered = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw  new RuntimeException();
        }

        try {
            linha = buffered.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (linha != null) {

            if (i == 2) {
                jogo.tabuleiro.adicionaPecas(Integer.parseInt(linha));


            }
            try {
                linha = buffered.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }

    }
    public void parseFicheiro8x8(File file) {
        jogo.tabuleiro = new Tabuleiro();
        jogo.tabuleiro.tamanho = 8;
    }

     */


    public void parsePecas(String linha) {
        String[] elementos = linha.split(":");

        Peca peca = new Peca();
        peca.id = Integer.parseInt(elementos[0]);
        peca.tipo = Integer.parseInt(elementos[1]);
        peca.equipa = new Equipa(Integer.parseInt(elementos[2]));
        jogo.defineEquipa(peca.equipa);
        peca.alcunha = elementos[3];

        jogo.tabuleiro.pecas.add(peca);
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
                        jogo.tabuleiro.adicionaPecas(Integer.parseInt(line));
                        break;
                    default:
                        for (int i = 0; i < jogo.tabuleiro.numeroDePecas; i++) {
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
        return jogo.tabuleiro.tamanho;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        return false;
    }

    public String[] getSquareInfo(int x, int y) {
        return null;
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
