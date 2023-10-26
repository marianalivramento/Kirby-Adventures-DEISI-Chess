package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    int numeroDePecas;
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

    public void setPecas(ArrayList<Peca> pecas) {
        this.pecas = pecas;
    }

    void adicionaPecas(Peca p) {
        pecas.add(p);
    }


}
