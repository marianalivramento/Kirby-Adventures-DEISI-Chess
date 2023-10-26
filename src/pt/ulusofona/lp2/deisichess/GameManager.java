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

    // acrescentei uma condicao para n aceitar valores null e vazios
    public void parsePosicoes(String linha, int y) {
        String[] elementos = linha.split(":");
        for (int i = 0; i < elementos.length; i++) {
            Square quadrado = new Square();
            quadrado.setCoordenadaX(i);
            quadrado.setCoordenadaY(y);

            if (elementos[i] != null && !elementos[i].isEmpty()) {
                if (Integer.parseInt(elementos[i]) == 0) {
                    quadrado.setOcupado(false);
                } else {
                    if (jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])) != null) {
                        quadrado.setOcupado(true);
                        jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])).setCoordenadas(quadrado);
                        quadrado.setPeca(jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])));
                    }
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
            return false;
        }
        return true;
    }


    public int getBoardSize() {
        return jogo.getTabuleiro().getTamanho();
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        int boardSize = jogo.getTabuleiro().getTamanho();

        if (x0 < 0 || x0 >= boardSize || y0 < 0 || y0 >= boardSize || x1 < 0 || x1 >= boardSize || y1 < 0 || y1 >= boardSize) {
            return false; // Coordinates out of bounds
        }

        Square sqPartida = jogo.getTabuleiro().retornoPeca(x0, y0);

        if (sqPartida == null) {
            return false;
        } else {
            sqPartida.resetQuadrado();
            //Square sqDestino = jogo.getTabuleiro().retornoPeca(x1,y1);
        }


        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        String[] retorno = new String[5];

        if ((x < 0 || x > jogo.getTabuleiro().getTamanho()) || (y < 0 || y > jogo.getTabuleiro().getTamanho())) {
            return retorno;
        }

        Square sq = jogo.getTabuleiro().retornoPeca(x, y);

        if (sq != null) {
            if (sq.isOcupado()) {
                if (sq.getPeca() != null) {
                    retorno[0] = Integer.toString(sq.getPeca().getId());
                    retorno[1] = Integer.toString(sq.getPeca().getTipo());
                    retorno[2] = Integer.toString(sq.getPeca().getEquipa().getPretoOuBranco());
                    retorno[3] = sq.getPeca().getAlcunha();
                    retorno[4] = null;

                }
            }
        }else {
            return null;
        }
        return retorno;
    }

    public String[] getPieceInfo(int ID) {
        String[] retorno = new String[7];
        Peca peca = jogo.getTabuleiro().retornaPecaPorId(ID);

        if (peca == null) {
            return null;
        }

        retorno[0] = Integer.toString(peca.getId());
        retorno[1] = Integer.toString(peca.getTipo());
        retorno[2] = Integer.toString(peca.getEquipa().getPretoOuBranco());
        retorno[3] = peca.getAlcunha();
        if (peca.getEstado()) {
            if (peca.getCoordenadas() != null) {
                retorno[4] = "em jogo";
                retorno[5] = Integer.toString(peca.getCoordenadas().getCoordenadaX());
                retorno[6] = Integer.toString(peca.getCoordenadas().getCoordenadaY());
            }

        } else {
            retorno[4] = "capturada";
        }

        return retorno;
    }


    public String getPieceInfoAsString(int ID) {
        StringBuilder retorno = new StringBuilder();
        String[] string = getPieceInfo(ID);

        if (string == null) {
            return "";
        }
        //o meu intelij dá avisos aqui a dizer que i nunca é atualizado (???) mas passa no teste unitario so idk
        for (int i = 0; i < string.length; i++) {
            if (i <= 2) {
                retorno.append(string[i]).append(" | ");
            }

            if (i == 3) {
                retorno.append(string[i]).append(" @ ");
            }

            if (i == 5 && string[i] != null) {
                retorno.append("(").append(string[i]).append(", ");
            }

            if (i == 6 && string[i] != null) {
                retorno.append(string[i]).append(")");
            }
        }
        return String.valueOf(retorno);
    }

    public int getCurrentTeamID() {
        return jogo.equipaAtual;
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
