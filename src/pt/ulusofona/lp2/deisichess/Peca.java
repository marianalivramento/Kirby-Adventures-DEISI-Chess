package pt.ulusofona.lp2.deisichess;

public class Peca {

    int id;
    int tipo;
    Equipa equipa;
    String alcunha;
    Square coordenadas;
    boolean estado;

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
