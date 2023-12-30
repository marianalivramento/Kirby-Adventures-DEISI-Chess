package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }

    public void aumentaValor(int pontos) {
        this.valor += pontos;
    }
    public void diminuiValor(int pontos) {
        this.valor -= pontos;
    }


    //verifico se quando faz o movimento se passa por cima de uma peca
    // as condicoes sao para n declara q passa por cima de outra peca quando o move é possivel
    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {

        int minX = Math.min(x0, x1);
        int maxX = Math.max(x0, x1);

        for (int x = minX + 1; x < maxX; x++) {
            Square square = jogo.getTabuleiro().retornoQuadrado(x, y0);
            if (square != null && square.isOcupado()) {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {

        if (passaPorPeca(x0, y0, x1, y1, jogo)) {
            return false;
        }

        if (x0 == x1 && y0 == y1) {
            return false;
        }
        if (y0 == y1) {
            if (!pertenceAequipa(jogo, x1, y1)) {
                return true;
            }
        }

        return false;
    }


    public String nomeDoTipo(Jogo jogo) {
        return "TorreHor";
    }
}
