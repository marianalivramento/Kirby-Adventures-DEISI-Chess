package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class GetHints implements Comparable<GetHints> {
    int x;
    int y;
    int valorPeca = 0;

    public GetHints(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setValorPeca(int valorPeca) {
        this.valorPeca = valorPeca;
    }

    public int getValorPeca() {
        return valorPeca;
    }

    @Override
    public int compareTo(GetHints other) {
        if (valorPeca < other.getValorPeca()) {
            return 1;
        } else if (valorPeca == other.getValorPeca()) {
            return 0;
        }
        return -1;

    }

    List<GetHints> jogadasPermitidas(Jogo jogo, List<Comparable> hints) {
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        Peca piece = tabuleiro.retornoQuadrado(x,y).getPeca();
        List<GetHints> ordenar = new ArrayList<>();
        if (piece != null) {
            for (Square s : tabuleiro.getQuadrados()) {
                if (piece.movesPermitidos(x, y, s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                    if (!piece.passaPorPeca(x, y, s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                        GetHints result = new GetHints(s.getCoordenadaX(), s.getCoordenadaY());
                        if (tabuleiro.retornoQuadrado(s.getCoordenadaX(), s.getCoordenadaY()).getPeca() != null){
                            result.valorPeca = tabuleiro.retornoQuadrado(s.getCoordenadaX(), s.getCoordenadaY()).getPeca().getValor();
                        }
                        ordenar.add(result);
                    }
                }
            }

        }
        return ordenar;
    }


    @Override
    public String toString() {
        return "(" + x + "," + y + ") -> " + valorPeca;
    }

}
