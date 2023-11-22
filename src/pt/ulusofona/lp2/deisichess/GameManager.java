package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    Jogo jogo = new Jogo();

    public GameManager() {
    }

    public void parsePecas(String linha, int numeroLinha) throws InvalidGameInputException, IOException {

        String[] elementos = linha.split(":");
        if (elementos.length < 4) {
            throw new InvalidGameInputException((numeroLinha + 1), ("DADOS A MENOS (Esperava 4; Obtive " + elementos.length) + ")");
        } else if (elementos.length > 4) {
            throw new InvalidGameInputException((numeroLinha + 1), ("DADOS A MAIS (Esperava 4; Obtive " + elementos.length + ")"));
        }

        Peca peca;
        switch(elementos[1]) {
            case "0":
                peca = new Rei();
                break;
            case "1":
                peca = new Rainha();
                break;
            case "2":
                peca = new PoneiMagico();
                break;
            case "3":
                peca = new PadreDaVila();
                break;
            case "4":
                peca = new TorreHorizontal();
                break;
            case "5":
                peca = new TorreVertical();
                break;
            case "6":
                peca = new HomerSimpson();
                break;
            case "7":
                peca = new Joker();
                break;
            default:
                throw new IOException();

        }
        peca.setId(Integer.parseInt(elementos[0]));
        peca.setTipo(Integer.parseInt(elementos[1]));
        peca.setEquipa(new Equipa(Integer.parseInt(elementos[2])));

        jogo.defineEquipa(peca.getEquipa());
        peca.setAlcunha(elementos[3]);

        jogo.getTabuleiro().adicionaPeca(peca);

    }


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


    public void loadGame(File file) throws InvalidGameInputException, IOException {


        jogo.clearGame();
        if (file == null || !file.exists() || !file.isFile()) {
            throw new IOException();
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
                                parsePecas(line, linha);
                                leituraParse++;

                                if (leituraParse == jogo.getTabuleiro().getNumeroDePecas()) {
                                    leituraParse = 0;
                                    jaLeu = true;
                                }
                            }
                        } else {

                            //ESTE IF COMENTADO ERA PARA QUÊ?
                            /*if (linha > 2 + jogo.getTabuleiro().getNumeroDePecas() + jogo.getTabuleiro().getTamanho()) {
                                throw new InvalidGameInputException(linha, "Bad line");
                                //return false;
                            }*/

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

            //ESTE IF COMENTADO ERA PARA QUÊ?
            /*if (linha < 2 + jogo.getTabuleiro().getNumeroDePecas() + jogo.getTabuleiro().getTamanho()) {
                throw new InvalidGameInputException(linha, "Bad line");
                //return false;
            }*/

            jogo.setEquipaBranca();
            jogo.setEquipaPreta();
            jogo.getEquipaPreta().setTurno(true);

        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    public void saveGame(File file) throws IOException {

    }

    void undo() {

    }


    List<Comparable> getHints(int x, int y) {
        return null;
    }


    public int getBoardSize() {
        return jogo.getTabuleiro().getTamanho();
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        Square sqPartida = jogo.getTabuleiro().retornoQuadrado(x0, y0);


        int boardSize = jogo.getTabuleiro().getTamanho();
        if (x0 < 0 || x0 >= boardSize || y0 < 0 || y0 >= boardSize || x1 < 0 || x1 >= boardSize || y1 < 0 || y1 >= boardSize) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;
        }

        if (sqPartida == null || sqPartida.getPeca() == null) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;
        } else if ((x0 == x1) && (y0 == y1)) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;
        } else if ((x1 != x0 + 1) && (x1 != x0 - 1) && (x1 != x0)) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;
        } else if ((y1 != y0 + 1) && (y1 != y0 - 1) && (y1 != y0)) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;

        } else if (sqPartida.getPeca().getEquipa().getPretoOuBranco() != jogo.getEquipaAtual()) {
            jogo.aumentaTentativasInvalidasPorEquipa();

            return false;
        } else {
            Square sqChegada = jogo.getTabuleiro().retornoQuadrado(x1, y1);

            if (sqChegada != null) {
                if (sqChegada.isOcupado()) {
                    if (sqChegada.getPeca() == null) {
                        return false;
                    }
                    if (sqChegada.getPeca().getEquipa().getPretoOuBranco() == sqPartida.getPeca().getEquipa().getPretoOuBranco()) {
                        jogo.aumentaTentativasInvalidasPorEquipa();
                        return false;
                    } else {
                        sqChegada.getPeca().setNaoCapturado(false);

                        if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 20) {

                            jogo.getEquipaBranca().aumentarJogadasValidas();
                            jogo.getEquipaBranca().aumentarPecasCapturadas();
                        } else {

                            jogo.getEquipaPreta().aumentarJogadasValidas();
                            jogo.getEquipaPreta().aumentarPecasCapturadas();

                        }

                    }

                    sqPartida.getPeca().getCoordenadas().setOcupado(false);

                    sqPartida.getPeca().setCoordenadas(sqChegada);
                    //sqPartida.getPeca().coordenadas = sqChegada;

                    sqChegada.setPeca(sqPartida.getPeca());
                    //sqChegada.peca = sqPartida.peca;
                    sqPartida.resetQuadrado();

                    jogo.resetJogadasSemCaptura();
                    //jogo.nrDeJogadasSemCaptura = 0;
                } else {

                    sqPartida.getPeca().getEquipa().setTurno(false);
                    if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 20) {
                        jogo.getEquipaBranca().aumentarJogadasValidas();
                    } else {

                        jogo.getEquipaPreta().aumentarJogadasValidas();
                    }

                    sqChegada.setPeca(sqPartida.getPeca());
                    //sqChegada.peca = sqPartida.peca;
                    sqPartida.getPeca().setCoordenadas(sqChegada);
                    //sqPartida.peca.coordenadas = sqChegada; //faltava isto
                    sqChegada.setOcupado(true);
                    sqPartida.resetQuadrado();
                    jogo.aumentaJogadasSemCaptura();
                    //jogo.nrDeJogadasSemCaptura++;
                }

            } else {
                jogo.aumentaTentativasInvalidasPorEquipa();
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
                    if (sq.getPeca().getEquipa().getPretoOuBranco() == 20) {
                        retorno[4] = "crazy_emoji_white.png";
                    } else {
                        retorno[4] = "crazy_emoji_black.png";
                    }


                }
            } else {
                String[] arrayVazio = new String[0];
                return arrayVazio;
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

        for (int i = 0; i < 4; i++) {
            retorno.append(string[i]);
            if (i < 3) {
                retorno.append(" | ");
            }
        }






        if (jogo.getTabuleiro().retornaPecaPorId(ID).getCoordenadas() != null && jogo.getTabuleiro().retornaPecaPorId(ID).getCoordenadas().isOcupado()) {
            String coordX = string[5];
            String coordY = string[6];

            if ((coordX != null && !coordX.isEmpty()) && (coordY != null && !coordY.isEmpty())) {
                retorno.append(" @ (").append(coordX).append(", ").append(coordY).append(")");
            } else {
                retorno.append(" @ (n/a)");
            }
        } else {
            retorno.append(" @ (n/a)");
        }
        return String.valueOf(retorno);
    }


    public int getCurrentTeamID() {
        return jogo.getEquipaAtual();
    }

    public boolean gameOver() {
        ArrayList<Peca> pecasEmJogo = new ArrayList<>();
        int pecasBrancas = 0;
        boolean flagPecasBrancas = false;
        int pecasPretas = 0;
        boolean flagPecasPretas = false;
        for (Peca p : jogo.getTabuleiro().getPecas()) {
            if (p.getNaoCapturado()) {
                pecasEmJogo.add(p);
                if (p.getEquipa().getPretoOuBranco() == 10) {
                    pecasPretas++;
                    flagPecasPretas = true;
                } else {
                    pecasBrancas++;
                    flagPecasBrancas = true;
                }

            }
        }

        if (pecasBrancas == 1 && pecasPretas == 1) {
            getGameResults();
            return true;
        }

        if (jogo.getNrDeJogadasSemCaptura() == 10) {
            if (jogo.getEquipaPreta().getNrCapturas() > 0 || jogo.getEquipaBranca().getNrCapturas() > 0) {
                getGameResults();
                return true;
            }
        } else if (jogo.getNrDeJogadasSemCaptura() > 20) {
            getGameResults();
            return true;
        }

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

        ImageIcon icon = new ImageIcon("src/images/SPOILER_creditos.png");
        JPanel panel = new JPanel();
        panel.add(new JLabel(icon));
        return panel;

    }

    public Map<String, String> customizeBoard() {
        Map<String, String> map = new HashMap<>();
        return map;
    }
}
