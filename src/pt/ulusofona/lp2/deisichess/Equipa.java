package pt.ulusofona.lp2.deisichess;

public class Equipa {
    int pretoOuBranco;
    boolean turno;
    int nrCapturas = 0;
    int nrJogadasValidas = 0;
    int nrTentativasInvalidas = 0;
    int numeroDoTurno = 0;

    public Equipa(int pretoOuBranco) {
        this.pretoOuBranco = pretoOuBranco;
    }

    public int getPretoOuBranco() {
        return pretoOuBranco;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getNrCapturas() {
        return nrCapturas;
    }

    public void setNrCapturas(int nrCapturas) {
        this.nrCapturas = nrCapturas;
    }

    public void setNrJogadasValidas(int nrJogadasValidas) {
        this.nrJogadasValidas = nrJogadasValidas;
    }

    public void setNrTentativasInvalidas(int nrTentativasInvalidas) {
        this.nrTentativasInvalidas = nrTentativasInvalidas;
    }

    public int getNrJogadasValidas() {
        return nrJogadasValidas;
    }

    public int getNrTentativasInvalidas() {
        return nrTentativasInvalidas;
    }

    public void aumentarJogadasValidas() {
        nrJogadasValidas++;
    }
    public void aumentarTentativasInvalidas() {
        nrTentativasInvalidas++;
    }
    public void aumentarPecasCapturadas() {
        nrCapturas++;
    }
    public void aumentarNumeroTurno() {
        numeroDoTurno++;
    }
    String pretoOuBrancoString() {
        if (pretoOuBranco == 10) {
            return "PRETA";
        } else if (pretoOuBranco == 20) {
            return "BRANCA";
        } else {
            return "ERRO";
        }
    }
}
