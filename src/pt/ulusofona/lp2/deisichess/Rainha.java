package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca {
    int valor = 8;

    public int getValor() {
        return valor;
    }

    public void aumentaValor(int pontos) {
        this.valor += pontos;
    }

    public void diminuiValor(int pontos) {
        this.valor -= pontos;
    }

    boolean passaPorPeca(int x0, int y0, int x1, int y1, Jogo jogo) {
        int minX = Math.min(x0, x1);
        int maxX = Math.max(x0, x1);
        int minY = Math.min(y0, y1);
        int maxY = Math.max(y0, y1);

        if (minY == maxY) {
            for (int x = minX + 1; x < maxX; x++) {
                Square square = jogo.getTabuleiro().retornoQuadrado(x, minY);
                if (square != null && square.isOcupado()) {
                    return true;
                }
            }
        }

        if (minX == maxX) {
            for (int y = minY + 1; y < maxY; y++) {
                Square square = jogo.getTabuleiro().retornoQuadrado(minX, y);
                if (square != null && square.isOcupado()) {
                    return true;
                }
            }
        }

        for (int i = 1; i <= 5; i++) {
            if (x1 == x0 + i && y1 == y0 + i) {
                return false;
            } else if (x1 == x0 - i && y1 == y0 - i) {
                return false;
            } else if (x1 == x0 + i && y1 == y0 - i) {
                return false;
            } else if (x1 == x0 - i && y1 == y0 + i) {
                return false;
            } else {
                if (x1 > x0 && y1 > y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 + i, y0 + i).isOcupado()) {
                        return true;
                    }
                }
                if (x1 < x0 && y1 < y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 - i, y0 - i).isOcupado()) {
                        return true;
                    }
                }

                if (x1 > x0 && y1 < y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 + i, y0 - i).isOcupado()) {
                        return true;
                    }
                }

                if (x1 < x0 && y1 > y0) {
                    if (jogo.getTabuleiro().retornoQuadrado(x0 - i, y0 + i).isOcupado()) {
                        return true;
                    }
                }
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
        if (!pertenceAequipa(jogo, x1, y1)) {
            if (jogo.getTabuleiro().retornoQuadrado(x1, y1).getPeca() != null && (jogo.getTabuleiro().retornoQuadrado(x1, y1).getPeca().getTipo() == 1 || jogo.getTabuleiro().retornoQuadrado(x1, y1).getPeca().nomeDoTipo(jogo).equals("Joker/Rainha"))) {
                return false;
            }
            for (int i = 1; i <= 5; i++) {
                if ((x1 == x0 + i || x1 == x0 - i) && y1 == y0) { //direita e esquerda
                    return true;
                } else if (x1 == x0 && (y1 == y0 + i || y1 == y0 - i)) { //frente e trás
                    return true;
                } else if ((y1 == y0 + i || y1 == y0 - i) && (x1 == x0 + i || x1 == x0 - i)) {
                    return true;

                }
            }

        }

        return false;
    }


    public String nomeDoTipo(Jogo jogo) {
        return "Rainha";
    }
}


