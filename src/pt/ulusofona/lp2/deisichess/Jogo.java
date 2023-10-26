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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Equipa getEquipaBranca() {
        return equipaBranca;
    }

    public void setEquipaBranca(Equipa equipaBranca) {
        this.equipaBranca = equipaBranca;
    }

    public Equipa getEquipaPreta() {
        return equipaPreta;
    }

    public void setEquipaPreta(Equipa equipaPreta) {
        this.equipaPreta = equipaPreta;
    }

    public JPanel getCreditos() {
        return creditos;
    }

    public void setCreditos(JPanel creditos) {
        this.creditos = creditos;
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
