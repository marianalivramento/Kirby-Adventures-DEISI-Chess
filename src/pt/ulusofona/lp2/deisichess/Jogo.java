package pt.ulusofona.lp2.deisichess;

import javax.swing.*;

public class Jogo {
    String resultado = "";
    Tabuleiro tabuleiro = new Tabuleiro();
    int equipaAtual = 0;
    Equipa equipaBranca = new Equipa(1);
    Equipa equipaPreta = new Equipa(0);
    JPanel creditos;

    int nrDeJogadasSemCaptura = 0;

    public Jogo() {
    }

    void clearGame() {
        resultado = "";
        tabuleiro = new Tabuleiro();
        equipaAtual = 0;
        equipaBranca = new Equipa(0);
        equipaPreta = new Equipa(1);
    }

    public String getResultado() {


        if (tabuleiro.nrDePecasBrancasEmJogo() > tabuleiro.nrDePecasPretasEmJogo()) {
            this.resultado = "VENCERAM AS BRANCAS";
        } else if (tabuleiro.nrDePecasBrancasEmJogo() < tabuleiro.nrDePecasPretasEmJogo()) {
            this.resultado = "VENCERAM AS PRETAS";
        } else {
            this.resultado = "EMPATE";
        }

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

    public Equipa getEquipaPreta() {
        return equipaPreta;
    }

    public JPanel getCreditos() {
        return creditos;
    }

    public void setCreditos(JPanel creditos) {
        this.creditos = creditos;
    }

    public int getEquipaAtual() {
        return equipaAtual;
    }

    void mudarEquipa() {
        if (equipaAtual == 0) {
            equipaBranca.setTurno(false);
            equipaPreta.setTurno(true);
            equipaAtual = 1;
        } else {
            equipaPreta.setTurno(false);
            equipaBranca.setTurno(true);
            equipaAtual = 0;
        }
    }

    void criaTabuleiro(int lado) {
        tabuleiro = new Tabuleiro();
        tabuleiro.tamanho = lado;
        // pensei que daria mais jeito se fizessemos diretamente o tabuleiro com o tamanho
    }

    //acrescentei isto pq pensei q fosse mais fácil
    void defineEquipa(Equipa equipa) {
        if (equipa.pretoOuBranco == 1) {
            equipaBranca = equipa;
        }
        equipaPreta = equipa;
    }

    void leFicheiroComCapturados() {
        for (Peca p : getTabuleiro().pecas) {
            if (p.getCoordenadas() == null) {
                p.setNaoCapturado(false);
                /*if (p.getEquipa().getPretoOuBranco() == 1) {
                    getEquipaPreta().setNrCapturas(getEquipaPreta().getNrCapturas() + 1);
                } else {
                    getEquipaBranca().setNrCapturas(getEquipaBranca().getNrCapturas() + 1);
                }*/
            }

        }
    }




    void atualizarEquipas(Equipa equipaDasPecas){
        if (equipaDasPecas.getPretoOuBranco() == 1) {
            equipaBranca.setNrCapturas(equipaBranca.getNrCapturas() + equipaDasPecas.getNrCapturas());
            equipaBranca.setNrJogadasValidas(equipaBranca.getNrJogadasValidas() + equipaDasPecas.getNrJogadasValidas());
            equipaBranca.setNrJogadasValidas(equipaBranca.getNrTentativasInvalidas() + equipaDasPecas.getNrTentativasInvalidas());
        }else{
            equipaPreta.setNrCapturas(equipaPreta.getNrCapturas() + equipaDasPecas.getNrCapturas());
            equipaPreta.setNrJogadasValidas(equipaPreta.getNrJogadasValidas() + equipaDasPecas.getNrJogadasValidas());
            equipaPreta.setNrJogadasValidas(equipaPreta.getNrTentativasInvalidas() + equipaDasPecas.getNrTentativasInvalidas());
        }

    }
    public void setEquipaBranca() {
        for (Peca peca : tabuleiro.getPecas()) {
            if (peca.getEquipa().getPretoOuBranco() == 1) {
                this.equipaBranca = peca.getEquipa();
            }
        }
    }

    public void setEquipaPreta() {
        for (Peca peca : tabuleiro.getPecas()) {
            if (peca.getEquipa().getPretoOuBranco() == 0) {
                this.equipaPreta = peca.getEquipa();
            }
        }
    }


}
