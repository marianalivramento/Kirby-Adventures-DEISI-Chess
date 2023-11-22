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

}
