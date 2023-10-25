package pt.ulusofona.lp2.deisichess;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;



public class GameManager {

    Jogo jogo = new Jogo();

    public GameManager() {
    }

    public void parseFicheiro4x4(File file) {
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


    public boolean loadGame(File file) {

        BufferedReader buffered = null;
        String linha = null;

        try {
            buffered = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        try {
            linha = buffered.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (linha != null) {

            switch (linha) {
                case "4":
                    parseFicheiro4x4(file);
                    break;
                case "8":
                    parseFicheiro8x8(file);
                    break;
                default:
                    return false;
                }
            }
        return true;
    }


   public int getBoardSize() {
        return jogo.tabuleiro.tamanho;
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
