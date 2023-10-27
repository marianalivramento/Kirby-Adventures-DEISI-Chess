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
        if (!file.exists() || !file.isFile()) {
            return false; // File doesn't exist or is not a regular file
        }

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
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }


    public int getBoardSize() {
        return jogo.getTabuleiro().getTamanho();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        Square sqPartida = jogo.getTabuleiro().retornoPeca(x0, y0);

        int boardSize = jogo.getTabuleiro().getTamanho();
        if (x0 < 0 || x0 >= boardSize || y0 < 0 || y0 >= boardSize || x1 < 0 || x1 >= boardSize || y1 < 0 || y1 >= boardSize) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false; // Coordinates out of bounds
        }

        if (sqPartida == null) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false;
        } else if ((x0 == x1) && (y0 == y1)) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false;
        } else if ((x1 != x0 + 1) && (x1 != x0 - 1) && (x1 != x0)) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false;
        } else if ((y1 != y0 + 1) && (y1 != y0 - 1) && (y1 != y0)) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false;
        } else {
            Square sqChegada = jogo.getTabuleiro().retornoQuadrado(x1, y1);

            if (sqChegada != null) {
                if (sqChegada.isOcupado()) {
                    if (sqChegada.getPeca() != null) {
                        sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
                        return false;
                    }
                    if (sqChegada.getPeca().getEquipa() == sqPartida.getPeca().getEquipa()) {
                        sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
                        return false;
                    } else {
                        sqPartida.getPeca().getEquipa().setNrCapturas(sqPartida.getPeca().getEquipa().getNrCapturas() + 1);
                        sqPartida.getPeca().getEquipa().setTurno(false);
                        jogo.getTabuleiro().getPecas().remove(sqChegada.getPeca());

                        if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 1) {
                            jogo.getEquipaPreta().setTurno(true);
                        } else {
                            jogo.getEquipaBranca().setTurno(true);
                        }
                        sqPartida.getPeca().getEquipa().setNrJogadasValidas(sqPartida.getPeca().getEquipa().getNrJogadasValidas() + 1);
                        sqPartida.resetQuadrado();

                    }

                } else {
                    sqChegada.peca = sqPartida.peca;
                    sqChegada.setOcupado(true);
                    sqPartida.getPeca().getEquipa().setTurno(false);
                    if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 1) {
                        jogo.getEquipaPreta().setTurno(true);
                    } else {
                        jogo.getEquipaBranca().setTurno(true);
                    }
                    sqPartida.getPeca().getEquipa().setNrJogadasValidas(sqPartida.getPeca().getEquipa().getNrJogadasValidas() + 1);
                    sqPartida.resetQuadrado();
                }

            } else {
                sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
                return false;
            }
        }

        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        String[] retorno = new String[5];

        if ((x < 0 || x > jogo.getTabuleiro().getTamanho()) || (y < 0 || y > jogo.getTabuleiro().getTamanho())) {
            return retorno;
        }

        Square sq = jogo.getTabuleiro().retornoQuadrado(x, y);

        if (sq != null) {
            if (sq.isOcupado()) {
                if (sq.getPeca() != null) {
                    retorno[0] = Integer.toString(sq.getPeca().getId());
                    retorno[1] = Integer.toString(sq.getPeca().getTipo());
                    retorno[2] = Integer.toString(sq.getPeca().getEquipa().getPretoOuBranco());
                    retorno[3] = sq.getPeca().getAlcunha();
                    retorno[4] = null;

                }
            } else {
                return null;
            }
        } else {
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
