package pt.ulusofona.lp2.deisichess;

import javax.swing.*;

public class Jogo {
    String resultado;
    Tabuleiro tabuleiro;
    Equipa equipaBranca;
    Equipa equipaPreta;
    JPanel creditos;

    public Jogo() {
    }

    void criaTabuleiro(int lado) {
        tabuleiro = new Tabuleiro();
        tabuleiro.tamanho = lado;
        // pensei que daria mais jeito se fizessemos diretamente o tabuleiro com o tamanho
    }

    //acrescentei isto pq pensei q fosse mais fácil
    void defineEquipa(Equipa equipa){
        if (equipa.pretoOuBranco == 1){
             equipaBranca = equipa;
        }
        equipaPreta = equipa;
    }

}
