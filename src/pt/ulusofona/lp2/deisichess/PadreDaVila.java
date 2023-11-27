package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PadreDaVila extends Peca{
    int valor = 3;

    public int getValor() {
        return valor;
    }

    //O PADRE NÃO ESTÁ A ANDAR NA DIAGONAL DIREITA
    boolean move(int x0, int y0, int x1, int y1){
        if (x0 == x1 && y0 == y1) {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
                if ((x1 == x0 + i && y1 == y0 + i) || (x1 == x0 - i && y1 == y0 - i) || (x1 == x0 + i && y1 == y0 - i) || (x1 == x0 - i && y1 == y0 + i)) {
                    numeroJogadas++;
                    return true;
                }

        }
        return false;
    }
    List<Comparable> jogadasPermitidas(Tabuleiro tabuleiro) {
        List<Comparable> permittedMoves = new ArrayList<>();
        for (Square s : tabuleiro.getQuadrados()) {
            if (move(coordenadas.getCoordenadaX(), coordenadas.getCoordenadaY(), s.getCoordenadaX(), s.getCoordenadaY())) {
                if (s.getPeca() == null){
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->0" );
                }else {
                    permittedMoves.add("(" + s.getCoordenadaX() + ", " + s.getCoordenadaY() + ")->" + s.getPeca().getValor());
                }
            }
        }
        Collections.sort(permittedMoves, (move1, move2) -> {
            int valor1 = Integer.parseInt(move1.toString().split("->")[1]);
            int valor2 = Integer.parseInt(move2.toString().split("->")[1]);
            return Integer.compare(valor2, valor1);
        });
        return permittedMoves;
    }
}
