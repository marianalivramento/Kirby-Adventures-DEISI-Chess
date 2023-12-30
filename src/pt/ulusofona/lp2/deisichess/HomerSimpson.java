package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca {
    int valor = 2;

    public int getValor() {
        return valor;
    }

    public void aumentaValor(int pontos) {
        this.valor += pontos;
    }
    public void diminuiValor(int pontos) {
        this.valor -= pontos;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        if ((x1 == x0 + 1 && y1 == y0 + 1) || (x1 == x0 - 1 && y1 == y0 - 1) || (x1 == x0 + 1 && y1 == y0 - 1) || (x1 == x0 - 1 && y1 == y0 + 1)) {
            if (!pertenceAequipa(jogo, x1, y1)) {
                return true;
            }
        }

        return false;
    }

    public String nomeDoTipo(Jogo jogo) {
        return "Homer Simpson";
    }
}
