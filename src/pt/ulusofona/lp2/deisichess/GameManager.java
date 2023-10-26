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

    public void parsePosicoes(String linha, int x) {
        String[] elementos = linha.split(":");

        for (int i = 0; i < elementos.length; i++) {
            Square quadrado = new Square();
            quadrado.setCoordenadaX(i);
            quadrado.setCoordenadaY(x);

            if (Integer.parseInt(elementos[i]) == 0) {
                quadrado.setOcupado(false);
            } else {
                if (jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])) != null) {
                    quadrado.setOcupado(true);
                    jogo.getTabuleiro().getPecas().get(Integer.parseInt(elementos[i])).setCoordenadas(quadrado);
                    quadrado.setPeca(jogo.getTabuleiro().getPecas().get(Integer.parseInt(elementos[i])));
                }
            }
            jogo.getTabuleiro().adicionaQuadrado(quadrado);
        }
    }


    public boolean loadGame(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int linha = 0;
            int leituraParse = 0;
            boolean jaLeu = false;

            while ((line = reader.readLine()) != null) {

                switch (linha) {
                    case 0:
                        jogo.criaTabuleiro(Integer.parseInt(line));
                        break;
                    case 1:
                        jogo.getTabuleiro().setNumeroDePecas(Integer.parseInt(line));
                        break;
                    default:
                        if (!jaLeu) {
                            if (leituraParse <= jogo.getTabuleiro().getNumeroDePecas()) {
                                parsePecas(line);
                                leituraParse++;

                                if (leituraParse == jogo.getTabuleiro().getNumeroDePecas()) {
                                    parsePecas(line);
                                    leituraParse = 0;
                                    jaLeu = true;
                                }
                            }
                        } else {
                            parsePosicoes(line, leituraParse);
                            leituraParse++;
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
        String[] retorno = new String[5];
        Square sq = jogo.getTabuleiro().retornoPeca(x, y);

        if (sq == null) {
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
        String[] retorno = new String[7];
        Peca peca = jogo.getTabuleiro().retornaPecaPorId(ID);

        if (peca == null) {
            return null;
        }

        retorno[0] = Integer.toString(peca.id);
        retorno[1] = Integer.toString(peca.tipo);
        retorno[2] = Integer.toString(peca.equipa.pretoOuBranco);
        retorno[3] = peca.alcunha;
        if (peca.estado) {
            retorno[4] = "1";
            retorno[5] = Integer.toString(peca.coordenadas.coordenadaX);
            retorno[6] = Integer.toString(peca.coordenadas.coordenadaY);
        } else {
            retorno[4] = "0";
        }

        return retorno;
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
