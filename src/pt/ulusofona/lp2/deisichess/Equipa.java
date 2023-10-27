package pt.ulusofona.lp2.deisichess;

public class Equipa {
    int pretoOuBranco;
    boolean turno;
    int nrCapturas;
    int nrJogadasValidas;
    int nrTentativasInvalidas;

    public Equipa(int pretoOuBranco) {
        this.pretoOuBranco = pretoOuBranco;
    }

    public int getPretoOuBranco() {
        return pretoOuBranco;
    }

    public void setPretoOuBranco(int pretoOuBranco) {
        this.pretoOuBranco = pretoOuBranco;
    }

    public boolean isTurno() {
        return turno;
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

    public int getNrJogadasValidas() {
        return nrJogadasValidas;
    }

    public void setNrJogadasValidas(int nrJogadasValidas) {
        this.nrJogadasValidas = nrJogadasValidas;
    }

    public int getNrTentativasInvalidas() {
        return nrTentativasInvalidas;
    }

    public void setNrTentativasInvalidas(int nrTentativasInvalidas) {
        this.nrTentativasInvalidas = nrTentativasInvalidas;
    }


}
