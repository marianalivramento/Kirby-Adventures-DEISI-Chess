package pt.ulusofona.lp2.deisichess;

public class Kirby extends Peca {

    int valor = 0;
    Peca pecaComida = new Rei();

    @Override
    int getValor() {
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
        return getPecaComida().movesPermitidos(x0, y0, x1, y1, jogo);
    }

    void kirbyTranforma(Peca p) {
        if (Rainha.class.equals(p.getClass())) {
            setPecaComida(new Rainha());
            setTipoDeKirby("Rainha");
            getVersoesKirby().add("Rainha");

        } else if (Rei.class.equals(p.getClass())) {
            setPecaComida(new Rei());
            setTipoDeKirby("Rei");
            getVersoesKirby().add("Rei");

        } else if (PadreDaVila.class.equals(p.getClass())) {
            setPecaComida(new PadreDaVila());
            setTipoDeKirby("Padre");
            getVersoesKirby().add("Padre");

        } else if (PoneiMagico.class.equals(p.getClass())) {
            setPecaComida(new PoneiMagico());
            setTipoDeKirby("Ponei");
            getVersoesKirby().add("Ponei");

        } else if (TorreHorizontal.class.equals(p.getClass())) {
            setPecaComida(new TorreHorizontal());
            setTipoDeKirby("TorreH");
            getVersoesKirby().add("TorreH");

        } else if (TorreVertical.class.equals(p.getClass())) {
            setPecaComida(new TorreVertical());
            setTipoDeKirby("TorreV");
            getVersoesKirby().add("TorreV");


        } else if (Joker.class.equals(p.getClass())) {
            setPecaComida(new Joker());
            setTipoDeKirby("Joker");
            getVersoesKirby().add("Joker");


        } else if (HomerSimpson.class.equals(p.getClass())) {
            setPecaComida(new HomerSimpson());
            setTipoDeKirby("Homer");
            getVersoesKirby().add("Homer");

        }

    }

    boolean move(int x0, int y0, int x1, int y1, Jogo jogo) {
        Peca pecaQueMove = jogo.getTabuleiro().retornoQuadrado(x0, y0).getPeca();
        Square quadradoOrigem = jogo.getTabuleiro().retornoQuadrado(x0, y0);
        Square quadradoDestino = jogo.getTabuleiro().retornoQuadrado(x1, y1);

        if (movesPermitidos(x0, y0, x1, y1, jogo)) {
            if (quadradoDestino.isOcupado()) {

                quadradoDestino.getPeca().setNaoCapturado(false);
                kirbyTranforma(quadradoDestino.getPeca());
                quadradoDestino.getPeca().setCoordenadas(null);
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);

                quadradoDestino.setOcupado(true);
                jogo.getClassEquipaAtual().aumentarPecasCapturadas();

                quadradoOrigem.resetQuadrado();
                return true;

            } else {
                pecaQueMove.setCoordenadas(quadradoDestino);
                quadradoDestino.setPeca(pecaQueMove);
                quadradoDestino.setOcupado(true);
                quadradoOrigem.resetQuadrado();

                return true;
            }
        }
        return false;
    }


    @Override
    String nomeDoTipo(Jogo jogo) {
        return "Kirby/" + getPecaComida().nomeDoTipo(jogo);
    }

    public Peca getPecaComida() {
        return pecaComida;
    }

    public void setPecaComida(Peca pecaComida) {
        this.pecaComida = pecaComida;
    }
}
