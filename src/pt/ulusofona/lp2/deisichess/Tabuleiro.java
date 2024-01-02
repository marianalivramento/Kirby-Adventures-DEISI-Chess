package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    int numeroDePecas;
    ArrayList<Square> quadrados = new ArrayList<>();
    ArrayList<Peca> pecas = new ArrayList<>();


    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public ArrayList<Square> getQuadrados() {
        return quadrados;
    }

    public int getNumeroDePecas() {
        return numeroDePecas;
    }

    public void setNumeroDePecas(int numeroDePecas) {
        this.numeroDePecas = numeroDePecas;
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    void adicionaPeca(Peca p) {
        pecas.add(p);
    }

    void adicionaQuadrado(Square s){
        quadrados.add(s);
    }

    Peca retornaPecaPorId(int id){
        for (Peca p : pecas) {
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    Square retornoQuadrado(int x, int y) {

        for (Square s : quadrados) {
            if (s.getCoordenadaX() == x && s.getCoordenadaY() == y) {
                return s;
            }
        }

        return new Square(x,y);
    }

    int nrDePecasBrancasEmJogo() {
        int retorno = 0;
        for (Peca p : pecas) {
            if (p.getNaoCapturado() && p.getEquipa().getPretoOuBranco() == 20) {
                retorno++;
            }
        }
        return retorno;
    }

    int nrDePecasPretasEmJogo() {
        int retorno = 0;
        for (Peca p : pecas) {
            if (p.getNaoCapturado() && p.getEquipa().getPretoOuBranco() == 10) {
                retorno++;
            }
        }
        return retorno;
    }

    public void undoClassTabuleiro(String lastMove) {
        String[] moveInfo = lastMove.split(":");
        int id = Integer.parseInt(moveInfo[0]);
        int x0 = Integer.parseInt(moveInfo[1]);
        int y0 = Integer.parseInt(moveInfo[2]);
        int x1 = Integer.parseInt(moveInfo[3]);
        int y1 = Integer.parseInt(moveInfo[4]);

        Peca pecaAtual =  retornaPecaPorId(id);

        if (moveInfo.length == 7) {
            //18:2:5:1:5:PecaCapturada:2
            Peca pecaCapturada = retornaPecaPorId(Integer.parseInt(moveInfo[6]));

            if (pecaAtual.getTipo() == 8) {
                if (pecaAtual.getVersoesKirby().size() == 1) {
                    pecaAtual.setTipoDeKirby("");
                    pecaAtual.getVersoesKirby().remove(0);
                    Kirby kirby = (Kirby) pecaAtual;
                    kirby.setPecaComida(new Rei());
                } else {
                    pecaAtual.setTipoDeKirby(pecaAtual.getVersoesKirby().get(pecaAtual.getVersoesKirby().size() - 1));
                    pecaAtual.getVersoesKirby().remove(pecaAtual.getVersoesKirby().size() - 1);
                    Kirby kirby = (Kirby) pecaAtual;
                    switch (pecaAtual.getTipoDeKirby()) {
                        case "Rei":
                            kirby.setPecaComida(new Rei());
                            break;
                        case "Rainha":
                            kirby.setPecaComida(new Rainha());
                            break;
                        case "Ponei":
                            kirby.setPecaComida(new PoneiMagico());
                            break;
                        case "Padre":
                            kirby.setPecaComida(new PadreDaVila());
                            break;
                        case "TorreV":
                            kirby.setPecaComida(new TorreVertical());
                            break;
                        case "TorreH":
                            kirby.setPecaComida(new TorreHorizontal());
                            break;
                        case "Homer":
                            kirby.setPecaComida(new HomerSimpson());
                            break;
                        case "Joker":
                            kirby.setPecaComida(new Joker());
                            break;
                    }
                }
            }

            //peca atual
            pecaAtual.diminuiNumeroDeCapturas();
            pecaAtual.diminuiPontos(pecaCapturada.getValor());
            pecaAtual.setCoordenadas(retornoQuadrado(x0, y0));
            retornoQuadrado(x0, y0).setPeca(pecaAtual);


            //peca capturada
            pecaCapturada.setNaoCapturado(true);
            retornoQuadrado(x1, y1).setPeca(pecaCapturada);
            pecaCapturada.setCoordenadas(retornoQuadrado(x1, y1));


        } else {

            pecaAtual.setCoordenadas(retornoQuadrado(x0, y0));
            retornoQuadrado(x0, y0).setPeca(pecaAtual);
            retornoQuadrado(x1, y1).resetQuadrado();

        }
    }
}
