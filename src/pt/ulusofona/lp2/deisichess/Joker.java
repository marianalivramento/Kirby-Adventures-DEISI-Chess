package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.List;

public class Joker extends Peca {
    int valor = 4;



    public int getValor() {
        return valor;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        switch (equipa.numeroDoTurno % 6) {
            case 1:
                Rainha rainha = new Rainha();
                return rainha.move(x0, y0, x1, y1, jogo);
            case 2:
                PoneiMagico poneiMagico = new PoneiMagico();
                return poneiMagico.move(x0, y0, x1, y1, jogo);
            case 3:
                PadreDaVila padreDaVila = new PadreDaVila();
                return padreDaVila.move(x0, y0, x1, y1, jogo);
            case 4:
                TorreHorizontal torreHorizontal = new TorreHorizontal();
                return torreHorizontal.move(x0, y0, x1, y1, jogo);
            case 5:
                TorreVertical torreVertical = new TorreVertical();
                return torreVertical.move(x0, y0, x1, y1, jogo);
            case 0:
               HomerSimpson homer = new HomerSimpson();
               return homer.move(x0, y0, x1, y1, jogo);
        }

        numeroJogadas++;
        return true;
    }

    List<Comparable> jogadasPermitidas(Jogo jogo) {
        List<Comparable> permittedMoves = new ArrayList<>();
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY(), jogo)) {
                if (s.getPeca() == null) {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0");
                } else {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->" + s.getPeca().getValor());
                }
            }
        }
        return permittedMoves;
    }
}
