package pt.ulusofona.lp2.deisichess;

abstract class Peca implements Comparable<Peca> {

    int id;
    int tipo;
    int valor;
    Equipa equipa;
    String alcunha;
    Square coordenadas;
    boolean naoCapturado = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public void setAlcunha(String alcunha) {
        this.alcunha = alcunha;
    }

    public Square getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Square coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean getNaoCapturado() {
        return naoCapturado;
    }

    public void setNaoCapturado(boolean naoCapturado) {
        this.naoCapturado = naoCapturado;
    }

    @Override
    public int compareTo(Peca peca) {
        if (peca.getValor() < valor) {
            return 1;
        } else if (peca.getValor() == valor) {
            return 0;
        }
        return -1;
    }


}
