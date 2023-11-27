package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {

    Jogo jogo = new Jogo();

    ArrayList<String> gameFileInfo = new ArrayList<>();
    ArrayList<String> moveHistory = new ArrayList<>();

    ArrayList<ArrayList<String>> fileHistory = new ArrayList<ArrayList<String>>();
    public GameManager() {
    }

    public void alteraFile(Tabuleiro tabuleiro) {
        int count = 0;
        for (int i = 18; i < gameFileInfo.size(); i++) {
            String[] linhaY = gameFileInfo.get(i).split(":");
            for (Square s : tabuleiro.getQuadrados()) {
                if (s.isOcupado() && s.getCoordenadaX() == count) {
                    linhaY[s.getCoordenadaY()] = String.valueOf(s.getPeca().getId());
                } else {
                    linhaY[s.getCoordenadaY()] = String.valueOf(0);
                }
            }
            gameFileInfo.set(i, String.join(":", linhaY));
            count++;
        }
        fileHistory.add(new ArrayList<>(gameFileInfo));  // Create a copy to store in fileHistory
    }


    public void parsePecas(String linha, int numeroLinha) throws InvalidGameInputException, IOException {

        String[] elementos = linha.split(":");
        if (elementos.length < 4) {
            throw new InvalidGameInputException((numeroLinha + 1), ("DADOS A MENOS (Esperava: 4 ; Obtive: " + elementos.length) + ")");
        } else if (elementos.length > 4) {
            throw new InvalidGameInputException((numeroLinha + 1), ("DADOS A MAIS (Esperava: 4 ; Obtive: " + elementos.length + ")"));
        }

        Peca peca;
        switch (elementos[1]) {
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

    public void parsePosicoesJogoGuardado(String line) {
        String[] elementos = line.split(":");

        Peca p = jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[0]));
        int col = Integer.parseInt(elementos[3]);
        int row = Integer.parseInt(elementos[4]);

        move(Integer.parseInt(elementos[1]), Integer.parseInt(elementos[2]), col, row);
    }


    public void loadGame(File file) throws InvalidGameInputException, IOException {


        jogo.clearGame();
        gameFileInfo.clear();
        moveHistory.clear();

        if (file == null || !file.exists() || !file.isFile()) {
            throw new IOException();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int linha = 0;
            int leituraParse = 0;
            boolean jaLeu = false;
            boolean jaMeteuPosicoesOriginais = false;

            while ((line = reader.readLine()) != null) {
                gameFileInfo.add(line);
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

                            if (!jaMeteuPosicoesOriginais) {

                                parsePosicoes(line, leituraParse);
                                leituraParse++;
                                if (leituraParse == jogo.getTabuleiro().getTamanho()) {
                                    jogo.leFicheiroComCapturados();
                                    jaMeteuPosicoesOriginais = true;
                                }

                            } else {
                                parsePosicoesJogoGuardado(line);
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

        if (file == null) {
            throw new IOException("Invalid file");
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {

            for (int i = 0; i < gameFileInfo.size(); i++) {
                bufferedWriter.write(gameFileInfo.get(i));
                bufferedWriter.newLine();
            }


            for (int i = 0; i < moveHistory.size(); i++) {
                bufferedWriter.write(moveHistory.get(i));
                bufferedWriter.newLine();
            }



/*
            for (Peca p : jogo.getTabuleiro().getPecas()) {
                if (!p.getNaoCapturado()) {
                    bufferedWriter.write("" + p.id);
                    bufferedWriter.newLine();
                    continue;
                }

                if (p.numeroJogadas > 0) {
                    bufferedWriter.write("" + p.id + ":" + p.getCoordenadas().coordenadaX + ":" + p.getCoordenadas().coordenadaY);
                    bufferedWriter.newLine();
                }
            }


 */


        } catch (IOException e) {
            throw new IOException("Error writing to the file", e);
        }

    }


    public void undo() {

        if (moveHistory.isEmpty()) {
            return;
        }


        String lastMove = moveHistory.get(moveHistory.size() - 1);
        String[] moveInfo = lastMove.split(":");
        int id = Integer.parseInt(moveInfo[0]);
        int x0 = Integer.parseInt(moveInfo[1]);
        int y0 = Integer.parseInt(moveInfo[2]);
        int x1 = Integer.parseInt(moveInfo[3]);
        int y1 = Integer.parseInt(moveInfo[4]);

        jogo.getTabuleiro().retornaPecaPorId(id).setCoordenadas(jogo.getTabuleiro().retornoQuadrado(x0, y0));
        jogo.getTabuleiro().retornoQuadrado(x0, y0).setPeca(jogo.getTabuleiro().retornaPecaPorId(id));
        jogo.getTabuleiro().retornoQuadrado(x1, y1).resetQuadrado();
        moveHistory.remove(moveHistory.size() - 1);


//fileHistory.get(fileHistory.size() -2);

    }


    public List<Comparable> getHints(int x, int y) {
        List<Comparable> hints = new ArrayList<>();
        Square square = jogo.getTabuleiro().retornoQuadrado(x, y);
        if (square.getPeca() != null) {
            hints = square.getPeca().jogadasPermitidas(jogo.getTabuleiro());
            Collections.sort(hints);
        }
        return hints;
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
        } else if (sqPartida.getPeca().tipo == 6 && !jogo.homerPodeMexer()) {
            jogo.aumentaTentativasInvalidasPorEquipa();
            return false;
        } else if (!sqPartida.getPeca().move(x0, y0, x1, y1)) {
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
                    } else if (sqPartida.getPeca().getClass().equals(Rainha.class) && sqChegada.getPeca().getClass().equals(Rainha.class)) {
                        jogo.aumentaTentativasInvalidasPorEquipa();
                        return false;

                    } else {
                        sqChegada.getPeca().setNaoCapturado(false);

                        if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 20) {

                            jogo.getEquipaBranca().aumentarJogadasValidas();
                            jogo.getEquipaBranca().aumentarPecasCapturadas();
                            jogo.getEquipaBranca().numeroDoTurno++;
                        } else {

                            jogo.getEquipaPreta().aumentarJogadasValidas();
                            jogo.getEquipaPreta().aumentarPecasCapturadas();
                            jogo.getEquipaPreta().numeroDoTurno++;

                        }

                    }

                    moveHistory.add(sqPartida.getPeca().id + ":" + x0 + ":" + y0 + ":" + x1 + ":" + y1);

                    sqPartida.getPeca().getCoordenadas().setOcupado(false);

                    sqPartida.getPeca().setCoordenadas(sqChegada);
                    //sqPartida.getPeca().coordenadas = sqChegada;

                    sqChegada.setPeca(sqPartida.getPeca());
                    //sqChegada.peca = sqPartida.peca;
                    sqPartida.resetQuadrado();

                    jogo.resetJogadasSemCaptura();

                    //jogo.nrDeJogadasSemCaptura = 0;
                } else {
                    moveHistory.add(sqPartida.getPeca().id + ":" + x0 + ":" + y0 + ":" + x1 + ":" + y1);
                    sqPartida.getPeca().getEquipa().setTurno(false);
                    if (sqPartida.getPeca().getEquipa().getPretoOuBranco() == 20) {
                        jogo.getEquipaBranca().aumentarJogadasValidas();
                        jogo.getEquipaBranca().numeroDoTurno++;
                    } else {
                        jogo.getEquipaPreta().aumentarJogadasValidas();
                        jogo.getEquipaPreta().numeroDoTurno++;
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
        alteraFile(jogo.getTabuleiro());
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
                        switch (sq.getPeca().getTipo()) {
                            case 0:
                                retorno[4] = "crazy_emoji_white.png";
                                break;
                            case 1:
                                retorno[4] = "rainha_white.png";
                                break;
                            case 2:
                                retorno[4] = "ponei_magico_white.png";
                                break;
                            case 3:
                                retorno[4] = "padre_vila_white.png";
                                break;
                            case 4:
                                retorno[4] = "torre_h_white.png";
                                break;
                            case 5:
                                retorno[4] = "torre_v_white.png";
                                break;
                            case 6:
                                retorno[4] = "homer_white.png";
                                break;
                            case 7:
                                retorno[4] = "joker_white.png";
                                break;
                            default:
                                retorno[4] = "unknown-piece.png";
                                break;
                        }

                    } else {
                        switch (sq.getPeca().getTipo()) {
                            case 0:
                                retorno[4] = "crazy_emoji_black.png";
                                break;
                            case 1:
                                retorno[4] = "rainha_black.png";
                                break;
                            case 2:
                                retorno[4] = "ponei_magico_black.png";
                                break;
                            case 3:
                                retorno[4] = "padre_vila_black.png";
                                break;
                            case 4:
                                retorno[4] = "torre_h_black.png";
                                break;
                            case 5:
                                retorno[4] = "torre_v_black.png";
                                break;
                            case 6:
                                retorno[4] = "homer_black.png";
                                break;
                            case 7:
                                retorno[4] = "joker_black.png";
                                break;
                            default:
                                retorno[4] = "unknown-piece.png";
                                break;
                        }
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
            if (i == 0) {
                retorno.append(string[i]);
            } else if (i == 1) {
                switch (string[i]) {
                    case "0":
                        retorno.append("Rei | (infinito)");
                        break;
                    case "1":
                        retorno.append("Rainha | 8");
                        break;
                    case "2":
                        retorno.append("Ponei Mágico | 5");
                        break;
                    case "3":
                        retorno.append("Padre da Vila | 3");
                        break;
                    case "4":
                        retorno.append("TorreHor | 3");
                        break;
                    case "5":
                        retorno.append("TorreVert | 3");
                        break;
                    case "6":
                        if (string[2].equals("10")) {
                            if ((jogo.getEquipaPreta().numeroDoTurno % 3) == 0 && jogo.getEquipaPreta().numeroDoTurno != 0 && jogo.getEquipaAtual() == 10) {
                                retorno.append("Homer Simpson");
                            } else {
                                return "Doh! zzzzzz";
                            }
                        } else {
                            if ((jogo.getEquipaBranca().numeroDoTurno % 3) == 0 && jogo.getEquipaBranca().numeroDoTurno != 0 && jogo.getEquipaAtual() == 20) {
                                retorno.append("Homer Simpson");
                            } else {
                                return "Doh! zzzzzz";
                            }
                        }
                        break;
                    case "7":
                        retorno.append("Joker");
                        break;
                    default:
                        retorno.append("Desconhecido");
                        break;
                }
            } else if (i == 2) {
                retorno.append(string[i]);
            } else {
                retorno.append(string[i]);
            }
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
