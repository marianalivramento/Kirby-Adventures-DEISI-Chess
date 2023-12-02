package pt.ulusofona.lp2.deisichess;

public class Equipa {
    int pretoOuBranco;
    boolean turno;
    int nrCapturas = 0;
    int nrJogadasValidas = 0;
    int nrTentativasInvalidas = 0;
    int numeroDoTurno = 1;

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

    public int getNrJogadasValidas() {
        return nrJogadasValidas;
    }

    public int getNrTentativasInvalidas() {
        return nrTentativasInvalidas;
    }

    public void aumentarJogadasValidas() {
        nrJogadasValidas++;
    }

    public void aumentarTenativasInvalidas() {
        nrTentativasInvalidas++;
    }

    public void aumentarPecasCapturadas() {
        nrCapturas++;
    }

}
