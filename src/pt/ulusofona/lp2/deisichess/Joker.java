package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca {
    int valor = 4;


    public int getValor() {
        return valor;
    }


    @Override
    boolean movesPermitidos(int x0, int y0, int x1, int y1, Jogo jogo) {
        if (!pertenceAequipa(jogo, x1, y1)) {
            switch (jogo.getTurnoClasse() % 6) {
                case 0:
                    Rainha rainha = new Rainha();
                    return rainha.movesPermitidos(x0, y0, x1, y1, jogo);
                case 1:
                    PoneiMagico poneiMagico = new PoneiMagico();
                    return poneiMagico.movesPermitidos(x0, y0, x1, y1, jogo);
                case 2:
                    PadreDaVila padreDaVila = new PadreDaVila();
                    return padreDaVila.movesPermitidos(x0, y0, x1, y1, jogo);
                case 3:
                    TorreHorizontal torreHorizontal = new TorreHorizontal();
                    return torreHorizontal.movesPermitidos(x0, y0, x1, y1, jogo);
                case 4:
                    TorreVertical torreVertical = new TorreVertical();
                    return torreVertical.movesPermitidos(x0, y0, x1, y1, jogo);
                case 5:
                    HomerSimpson homer = new HomerSimpson();
                    return homer.movesPermitidos(x0, y0, x1, y1, jogo);
            }
        }
        return false;
    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {

        switch (jogo.getTurnoClasse() % 6) {
            case 0:
                Rainha rainha = new Rainha();
                return rainha.move(x0, y0, x1, y1, jogo);

            case 1:
                PoneiMagico poneiMagico = new PoneiMagico();
                return poneiMagico.move(x0, y0, x1, y1, jogo);
            case 2:
                PadreDaVila padreDaVila = new PadreDaVila();
                return padreDaVila.move(x0, y0, x1, y1, jogo);
            case 3:
                TorreHorizontal torreHorizontal = new TorreHorizontal();
                return torreHorizontal.move(x0, y0, x1, y1, jogo);
            case 4:
                TorreVertical torreVertical = new TorreVertical();
                return torreVertical.move(x0, y0, x1, y1, jogo);
            case 5:
                HomerSimpson homer = new HomerSimpson();
                return homer.move(x0, y0, x1, y1, jogo);
        }

        return false;
    }

    public String nomeDoTipo(Jogo jogo) {
        switch (jogo.getTurnoClasse() % 6) {
            case 0:
                return "Joker/Rainha";
            case 1:
                return "Joker/Ponei Mágico";
            case 2:
                return "Joker/Padre da Vila";
            case 3:
                return "Joker/TorreHor";
            case 4:
                return "Joker/TorreVert";
            case 5:
                return "Joker/Homer Simpson";
            default:
                return "Desconhecido";
        }
    }
}
