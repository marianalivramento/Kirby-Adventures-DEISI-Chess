package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    int numeroDePecas;
    ArrayList<Peca> pecas;

    public Tabuleiro() {
        pecas = new ArrayList<>(); // a inicializar pq tou a usar o atributo e pode dar null
    }

    void adicionaPecas(int i) {
        numeroDePecas = i;
    }
}
