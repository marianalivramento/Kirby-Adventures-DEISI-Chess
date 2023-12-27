package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca {
    int valor = 3;

    public int getValor() {
        return valor;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        int minY = Math.min(y0, y1);
        int maxY = Math.max(y0, y1);
        // determina os valores minimos e maximos entre y0 e y1 e dps em baixo conta do menor ao maior e ve se tem algum quadrado ocupado

        for (int y = minY + 1; y < maxY; y++) {
            Square square = jogo.getTabuleiro().retornoQuadrado(x0, y);
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
        if (x0 == x1) {
            if (!pertenceAequipa(jogo, x1, y1)) {
                return true;
            }
        }
        return false;
    }
    public String nomeDoTipo(Jogo jogo) {
        return "TorreVert";
    }
}
