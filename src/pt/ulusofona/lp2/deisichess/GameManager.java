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

        jogo.clearGame();
        if (file == null || !file.exists() || !file.isFile()) {
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
                            if (linha > 2 + jogo.getTabuleiro().getNumeroDePecas() + jogo.getTabuleiro().getTamanho()) {
                                return false;
                            }
                            parsePosicoes(line, leituraParse);
                            leituraParse++;
                            if (leituraParse == jogo.getTabuleiro().getTamanho()) {
                                jogo.leFicheiroComCapturados();
                            }
                        }
                        break;
                }
                linha++;
            }
            if (linha < 2 + jogo.getTabuleiro().getNumeroDePecas() + jogo.getTabuleiro().getTamanho()) {
                return false;
            }
            jogo.setEquipaBranca();
            jogo.setEquipaPreta();
            jogo.getEquipaPreta().setTurno(true);
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
        //PROBLEMA
        //-A PEÇCA QUE CAPTURA DESAPARECE
        Square sqPartida = jogo.getTabuleiro().retornoQuadrado(x0, y0);


        int boardSize = jogo.getTabuleiro().getTamanho();
        if (x0 < 0 || x0 >= boardSize || y0 < 0 || y0 >= boardSize || x1 < 0 || x1 >= boardSize || y1 < 0 || y1 >= boardSize) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false; // Coordinates out of bounds
        }

        if (sqPartida == null) {
            return false;
        } else if (sqPartida.getPeca() == null) {
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

        } else if (sqPartida.getPeca().getEquipa().getPretoOuBranco() != jogo.equipaAtual) {
            sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
            return false;
        } else {
            Square sqChegada = jogo.getTabuleiro().retornoQuadrado(x1, y1);

            if (sqChegada != null) {
                if (sqChegada.isOcupado()) {
                    //esta condição de == null é para quê? é pq o sq de chegada pode n consegue acedar à equipa da peca se n houver peca
                    if (sqChegada.getPeca() == null) {
                        return false;
                    }
                    if (sqChegada.getPeca().getEquipa().getPretoOuBranco() == sqPartida.getPeca().getEquipa().getPretoOuBranco()) {
                        sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
                        return false;
                    } else {
                        sqPartida.getPeca().getEquipa().setNrCapturas(sqPartida.getPeca().getEquipa().getNrCapturas() + 1);
                        sqPartida.getPeca().getEquipa().setTurno(false);
                        sqChegada.getPeca().setNaoCapturado(false);
                        //jogo.getTabuleiro().getPecas().remove(sqChegada.getPeca());

                        if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 1) {
                            jogo.getEquipaPreta().setTurno(true);
                        } else {
                            jogo.getEquipaBranca().setTurno(true);
                        }
                        sqPartida.getPeca().getEquipa().setNrJogadasValidas(sqPartida.getPeca().getEquipa().getNrJogadasValidas() + 1);
                        //sqPartida.resetQuadrado();

                    }
                    sqPartida.getPeca().coordenadas = sqChegada;
                    sqChegada.peca = sqPartida.peca;
                    sqPartida.resetQuadrado();
                    jogo.nrDeJogadasSemCaptura = 0;
                } else {
                    sqChegada.peca = sqPartida.peca;
                    sqChegada.setOcupado(true);
                    sqPartida.getPeca().getEquipa().setTurno(false);
                    if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 1) {
                        jogo.getEquipaPreta().setTurno(true);
                        jogo.getEquipaBranca().aumentarJogadas();
                    } else {
                        jogo.getEquipaBranca().setTurno(true);
                        jogo.getEquipaPreta().aumentarJogadas();
                    }

                    sqPartida.resetQuadrado();
                    jogo.nrDeJogadasSemCaptura++;
                }

            } else {
                sqPartida.getPeca().getEquipa().setNrTentativasInvalidas(sqPartida.getPeca().getEquipa().getNrTentativasInvalidas() + 1);
                return false;
            }
        }
        jogo.mudarEquipa();
        return true;
    }

    public String[] getSquareInfo(int x, int y) {
        String[] retorno = new String[5];

        if ((x < 0 || x > jogo.getTabuleiro().getTamanho()) || (y < 0 || y > jogo.getTabuleiro().getTamanho())) {
            return null;
        }

        Square sq = jogo.getTabuleiro().retornoQuadrado(x, y);

        if (sq != null) {
            if (sq.isOcupado()) {
                if (sq.getPeca() != null) {
                    retorno[0] = Integer.toString(sq.getPeca().getId());
                    retorno[1] = Integer.toString(sq.getPeca().getTipo());
                    retorno[2] = Integer.toString(sq.getPeca().getEquipa().getPretoOuBranco());
                    retorno[3] = sq.getPeca().getAlcunha();
                    if (sq.getPeca().getEquipa().getPretoOuBranco() == 1) {
                        retorno[4] = "crazy_emoji_white.png";
                    } else {
                        retorno[4] = "crazy_emoji_black.png";
                    }


                }
            } else {
                return null;
            }
        } else {
            return retorno;
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
        if (peca.getNaoCapturado()) {
            if (peca.getCoordenadas() != null) {
                retorno[4] = "em jogo";
                retorno[5] = Integer.toString(peca.getCoordenadas().getCoordenadaX());
                retorno[6] = Integer.toString(peca.getCoordenadas().getCoordenadaY());
            }

        } else {
            retorno[4] = "capturado";
            retorno[5] = "";
            retorno[6] = "";
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
        for (int i = 0; i < 4; i++) {
            retorno.append(string[i]);
            if (i < 3) {
                retorno.append(" | ");
            }
        }

        // Check if coordenadas is not null and append coordinates or "n/a"
        if (jogo.getTabuleiro().retornaPecaPorId(ID).getCoordenadas() != null) {
            retorno.append(" @ (");
            retorno.append(string[5]);
            retorno.append(", ");
            retorno.append(string[6]);
            retorno.append(")");
        } else {
            retorno.append(" @ (n/a)");
        }

        return String.valueOf(retorno);
    }

    public int getCurrentTeamID() {
        return jogo.equipaAtual;
    }

    public boolean gameOver() {
        ArrayList<Peca> pecasEmJogo = new ArrayList<>();
        int pecasBrancas = 0;
        boolean flagPecasBrancas = false;
        int pecasPretas = 0;
        boolean flagPecasPretas = false;
        for (Peca p : jogo.getTabuleiro().pecas) {
            if (p.getNaoCapturado()) {
                pecasEmJogo.add(p);
                if (p.getEquipa().getPretoOuBranco() == 0) {
                    pecasPretas++;
                    flagPecasPretas = true;
                } else {
                    pecasBrancas++;
                    flagPecasBrancas = true;
                }

            }
        }

        if (pecasPretas == 1 || pecasBrancas == 1) {
            getGameResults();
            return true;
        }



        if (jogo.nrDeJogadasSemCaptura == 10) {
            getGameResults();
            return true;
        }

        /*if (jogo.nrDeJogadasSemCaptura == 10) {
            if (jogo.getEquipaPreta().getNrCapturas() > 0 || jogo.getEquipaBranca().getNrCapturas() > 0) {
                getGameResults();
                return true;
            }
        } else if (jogo.nrDeJogadasSemCaptura > 20) {
            getGameResults();
            return true;
        }*/

        if (!flagPecasPretas || !flagPecasBrancas) {
            getGameResults();
            return true;
        }


        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> retorno = new ArrayList<>();
        retorno.add("JOGO DE CRAZY CHESS");
        retorno.add("Resultado: " + jogo.getResultado());
        retorno.add("---");
        retorno.add("Equipa das Pretas");
        retorno.add(String.valueOf(jogo.getEquipaPreta().getNrCapturas()));
        retorno.add(String.valueOf(jogo.getEquipaPreta().getNrJogadasValidas()));
        retorno.add(String.valueOf(jogo.getEquipaPreta().getNrTentativasInvalidas()));
        retorno.add("Equipa das Brancas");
        retorno.add(String.valueOf(jogo.getEquipaBranca().getNrCapturas()));
        retorno.add(String.valueOf(jogo.getEquipaBranca().getNrJogadasValidas()));
        retorno.add(jogo.getEquipaBranca().getNrTentativasInvalidas() + "");
        return retorno;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }
}
