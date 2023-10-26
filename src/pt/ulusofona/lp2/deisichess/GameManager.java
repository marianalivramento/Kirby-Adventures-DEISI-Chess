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
                    jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])).setCoordenadas(quadrado);
                    quadrado.setPeca(jogo.getTabuleiro().retornaPecaPorId(Integer.parseInt(elementos[i])));
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
        //coordenadas que nao existem
        if ((x0 < 0 || x0 > 3) || (y0 < 0 || y0 > 3) || (x1 < 0 || x1 > 3) || (y1 < 0 || y1 > 3)) {
            return false;
        }

        //nao posso mover a peça para cima dela propria
        if( x0 == x1 && y0 == y1) {
            return false;
        }

        Square sqPartida = jogo.getTabuleiro().retornoPeca(x0,y0);
        Square sqChegada = jogo.getTabuleiro().retornoQuadrado(x1,y1);
        //sqChegada.getPeca().setEquipa(jogo.equipaAtual);



        if (!sqPartida.isOcupado()) {
            //não há nenhuma peça no quadro para se mover
            return false;
        } else if (jogo.equipaAtual == sqChegada.peca.equipa.pretoOuBranco) {
            //estou a mover uma peça para cima de uma peça da mesma equipa
            //!!! esta condição dá null pointer execption porque se nao houver nenhuma peça a equipa está a null !!!
            return false;
        } else if (!(x1 == x0 + 1 || x1 == x0 - 1) && (y1 == y0 + 1 || y1 == y0 - 1)){
            //condição para controlar se só anda uma casa para os lados
            return false;
        } else {
            //tiramos a peça do quadrado e adicionar no novo quadrado
            Peca p = sqPartida.getPeca();
            p.setCoordenadas(sqChegada);
            sqPartida.resetQuadrado();

        }

        return true;
    }

    public String[] getSquareInfo(int x, int y) {

        if((x < 0 || x > 3) || (y < 0 || y > 3)) {
            return null;
        }
        String[] retorno = new String[5];
        Square sq = jogo.getTabuleiro().retornoPeca(x, y);

        if (sq == null) {
            return retorno;
        }
        if (!sq.isOcupado()) {
            return retorno;
        }
        retorno[0] = Integer.toString(sq.getPeca().getId());
        retorno[1] = Integer.toString(sq.getPeca().getTipo());
        retorno[2] = Integer.toString(sq.getPeca().getEquipa().getPretoOuBranco());
        retorno[3] = sq.getPeca().getAlcunha();
        retorno[4] = null;

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
            retorno[4] = "em jogo";
            retorno[5] = Integer.toString(peca.getCoordenadas().getCoordenadaX());
            retorno[6] = Integer.toString(peca.getCoordenadas().getCoordenadaY());
        } else {
            retorno[4] = "capturada";
        }

        return retorno;
    }


    public String getPieceInfoAsString(int ID) {
        String retorno = "";

        String[] string = getPieceInfo(ID);

        if(string == null) {
            return null;
        }
        //o meu intelij dá avisos aqui a dizer que i nunca é atualizado (???) mas passa no teste unitario so idk
        for (int i = 0; i < string.length; i++) {
            if (i <= 2) {
                retorno += string[i] + " | ";
            }

            if (i == 3) {
                retorno += string[i] + " @ ";
            }

            if (i == 5 && string[i] != null) {
                retorno += "(" + string[i] + ", ";
            }

            if (i == 6 && string[i] != null) {
                retorno +=  string[i] + ")";
            }
        }
        return retorno;
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
