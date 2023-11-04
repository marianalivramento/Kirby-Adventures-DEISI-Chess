package pt.ulusofona.lp2.deisichess;

public class Jogo {
    String resultado = "";
    Tabuleiro tabuleiro = new Tabuleiro();
    int equipaAtual = 0;
    Equipa equipaBranca = new Equipa(1);
    Equipa equipaPreta = new Equipa(0);
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

        if (tabuleiro.nrDePecasPretasEmJogo() == 0) {
            this.resultado = "VENCERAM AS BRANCAS";
        } else if (tabuleiro.nrDePecasBrancasEmJogo() == 0) {
            this.resultado = "VENCERAM AS PRETAS";
        } else {
            this.resultado = "EMPATE";
        }

        return resultado;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    void criaTabuleiro(int lado) {
        tabuleiro = new Tabuleiro();
        tabuleiro.setTamanho(lado);
    }

    public Equipa getEquipaBranca() {
        return equipaBranca;
    }

    public Equipa getEquipaPreta() {
        return equipaPreta;
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

    void defineEquipa(Equipa equipa) {
        if (equipa.getPretoOuBranco() == 1) {
            equipaBranca = equipa;
        }
        equipaPreta = equipa;
    }

    void leFicheiroComCapturados() {
        for (Peca p : getTabuleiro().getPecas()) {
            if (p.getCoordenadas() == null) {
                p.setNaoCapturado(false);
            }
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

    void aumentaTentativasInvalidasPorEquipa() {
        if (equipaAtual == 1) {
            equipaBranca.aumentarTenativasInvalidas();
        } else {
            equipaPreta.aumentarTenativasInvalidas();
        }
    }

    void aumentaJogadasSemCaptura() {
        nrDeJogadasSemCaptura++;
    }

    public int getNrDeJogadasSemCaptura() {
        return nrDeJogadasSemCaptura;
    }

    void resetJogadasSemCaptura() {
        nrDeJogadasSemCaptura = 0;
    }

}
