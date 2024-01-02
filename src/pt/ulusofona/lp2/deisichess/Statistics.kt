package pt.ulusofona.lp2.deisichess

enum class StatType{
    TOP_5_CAPTURAS, TOP_5_PONTOS, PECAS_MAIS_5_CAPTURAS, PECAS_MAIS_BARALHADAS, TIPOS_CAPTURADOS
}

fun topCincoCapturas(gameManager: GameManager) : List<String> {

    return gameManager.arr()
            .sortedWith(compareBy(
                    { -it.getNumeroDeCapturas() },
                    { it.getEquipa().getPretoOuBranco() } ))
            .map { it.getAlcunha() + " (" + it.getEquipa().pretoOuBrancoString() + ") fez " + it.getNumeroDeCapturas() + " capturas"}
            .take(5)
}

fun topCincoPontos(gameManager: GameManager) : List<String> {

    return gameManager.arr().sortedBy { it.getPontos() }
            .filter { it.getPontos() > 0 }
            .map { it.getAlcunha()+ " (" + it.getEquipa().pretoOuBrancoString() + ") tem " + it.getPontos() + " pontos"}
            .reversed()
            .take(5)
}

fun tiposCapturados(gameManager: GameManager) : List<String> {

    return gameManager.arr().filter { !it.getNaoCapturado() }
            .distinctBy { it.getTipo() }
            .map { it.nomeDoTipo(gameManager.jogo) }
            .sorted()
}

fun maisBaralhadas(gameManager: GameManager) : List<String> {

    return gameManager.arr().filter { it.getNumeroDeMovimentosInvalidos() >= 1 }
            .sortedBy { it.getNumeroDeMovimentosInvalidos() }
            .reversed()
            .map { "" + it.getEquipa().getPretoOuBranco() + ":" + it.getAlcunha() + ":" + it.getNumeroDeMovimentosInvalidos() + ":" + it.getNumeroDeMovimentosValidos()}
            .take(3)
}

fun maisCincoCapturas(gameManager: GameManager) : List<String> {

    return gameManager.arr().filter { it.getNumeroDeCapturas() > 5 }
            .sortedWith(compareBy({ -it.getNumeroDeCapturas() }, { it.getEquipa().getPretoOuBranco() } ))
            .map { it.getEquipa().pretoOuBrancoString() + ":" + it.getAlcunha() + ":" + it.getNumeroDeCapturas()}
}




fun getStatsCalculator(stat : StatType) : Function1<GameManager, List<String>> {
    when (stat) {
        StatType.TOP_5_CAPTURAS -> return ::topCincoCapturas
        StatType.TOP_5_PONTOS -> return ::topCincoPontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::maisCincoCapturas
        StatType.PECAS_MAIS_BARALHADAS -> return :: maisBaralhadas
        StatType.TIPOS_CAPTURADOS -> return :: tiposCapturados
    }
}
