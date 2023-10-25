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

    void criaTabuleiro() {
        tabuleiro = new Tabuleiro();
    }

}
