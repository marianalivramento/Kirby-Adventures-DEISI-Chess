package pt.ulusofona.lp2.deisichess;

public class Jogo {
    String resultado = "";
    Tabuleiro tabuleiro = new Tabuleiro();
    int equipaAtual = 10;
    Equipa equipaBranca = new Equipa(20);
    Equipa equipaPreta = new Equipa(10);
    int nrDeJogadasSemCaptura = 0;
    int turnoClasse = 0;


    public Jogo() {
    }

    void clearGame() {
        resultado = "";
        tabuleiro = new Tabuleiro();
        equipaAtual = 10;
        equipaBranca = new Equipa(20);
        equipaPreta = new Equipa(10);
        turnoClasse = 0;
    }

    public String getResultado() {

        boolean flagReiPreto = false;
        boolean flagReiBranco = false;

        for (Peca p : tabuleiro.getPecas()) {
            if (p.getTipo() == 0 && p.getNaoCapturado() && p.getEquipa().getPretoOuBranco() == 10) {
                flagReiPreto = true;
            } else if (p.getTipo() == 0 && p.getNaoCapturado() && p.getEquipa().getPretoOuBranco() == 20) {
                flagReiBranco = true;
            }
        }

        if (tabuleiro.nrDePecasPretasEmJogo() == 0 || (!flagReiPreto && flagReiBranco)) {
            this.resultado = "VENCERAM AS BRANCAS";
        } else if (tabuleiro.nrDePecasBrancasEmJogo() == 0 || (!flagReiBranco && flagReiPreto)) {
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

    public Equipa getClassEquipaAtual() {
        if (equipaAtual == 10) {
            return equipaPreta;
        } else {
            return equipaBranca;
        }
    }

    void mudarEquipa() {
        if (equipaAtual == 10) {
            equipaBranca.setTurno(false);
            equipaPreta.setTurno(true);
            equipaAtual = 20;
        } else {
            equipaPreta.setTurno(false);
            equipaBranca.setTurno(true);
            equipaAtual = 10;
        }
    }

    void leFicheiroComCapturados() {
        for (Peca p : getTabuleiro().getPecas()) {
            if (p.getCoordenadas() == null) {
                p.setNaoCapturado(false);
            }
        }
    }

    void aumentaTentativasInvalidasPorEquipa() {
        if (equipaAtual == 20) {
            equipaBranca.aumentarTentativasInvalidas();
        } else {
            equipaPreta.aumentarTentativasInvalidas();
        }
    }

    void aumentaJogadasSemCaptura() {
        nrDeJogadasSemCaptura++;
    }

    void aumentaTurnoClasse() {
        turnoClasse++;
    }

    public int getNrDeJogadasSemCaptura() {
        return nrDeJogadasSemCaptura;
    }

    void resetJogadasSemCaptura() {
        nrDeJogadasSemCaptura = 0;
    }

    boolean homerADormir() {
        if (equipaAtual == 10) {
            return turnoClasse % 3 == 0;
        } else {
            return turnoClasse % 3 == 0;

        }
    }

    public void setEquipaAtual(int equipaAtual) {
        this.equipaAtual = equipaAtual;
    }

    public int getTurnoClasse() {
        return turnoClasse;
    }

   // public void undo (int id) {
       // tabuleiro.retornaPecaPorId(id).diminuiNumeroDeCapturas();
    //}
}
