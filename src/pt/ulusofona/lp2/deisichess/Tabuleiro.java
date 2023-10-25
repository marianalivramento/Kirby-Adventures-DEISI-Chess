package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    int numeroDePecas;
    ArrayList<Peca> pecas;

    public Tabuleiro() {

    }

    void adicionaPecas(int i) {
        numeroDePecas = i;
    }
}
