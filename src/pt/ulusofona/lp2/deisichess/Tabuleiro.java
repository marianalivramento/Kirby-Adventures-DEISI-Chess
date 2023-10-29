package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    int numeroDePecas;
    ArrayList<Square> quadrados = new ArrayList<>();
    ArrayList<Peca> pecas = new ArrayList<>();


    public ArrayList<Square> getQuadrados() {
        return quadrados;
    }

    public void setQuadrados(ArrayList<Square> quadrados) {
        this.quadrados = quadrados;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
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

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    void adicionaPecas(Peca p) {
        pecas.add(p);
    }

    void adicionaQuadrado(Square s){
        quadrados.add(s);
    }

    Peca retornaPecaPorId(int id){
        for (Peca p:pecas) {
            if (p.id == id){
                return p;
            }
        }
        return null;
    }



    Square retornoPeca(int x, int y) {
        for (Peca p : pecas) {
            if (p.coordenadas != null) {
                if (p.coordenadas.coordenadaX == x && p.coordenadas.coordenadaY == y) {
                    return p.coordenadas;
                }
            }
        }
        return null;
    }

    // para obter um quadrado especifico dentro de mts quadrados
    Square retornoQuadrado(int x, int y) {

        for (Square s : quadrados) {
            if (s.getCoordenadaX() == x && s.getCoordenadaY() == y) {
                return s;
            }
        }
        return null;
    }

    int nrDePecasBrancasEmJogo() {
        int retorno = 0;
        for (Peca p : pecas) {
            if (p.getNaoCapturado() && p.getEquipa().pretoOuBranco == 1) {
                retorno++;
            }
        }
        return retorno;
    }

    int nrDePecasPretasEmJogo() {
        int retorno = 0;
        for (Peca p : pecas) {
            if (p.getNaoCapturado() && p.getEquipa().pretoOuBranco == 0) {
                retorno++;
            }
        }
        return retorno;
    }


}
