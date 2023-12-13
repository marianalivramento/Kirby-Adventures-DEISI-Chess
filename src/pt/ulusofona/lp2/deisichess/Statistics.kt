package pt.ulusofona.lp2.deisichess

import java.util.*

enum class StatType{
    TOP_5_CAPTURAS, TOP_5_PONTOS, PECAS_MAIS_5_CAPTURAS, PECAS_MAIS_BARALHADAS, TIPOS_CAPTURADOS
}


fun func1(gameManager: GameManager) : List<String> {

    return gameManager.arr().sortedBy { it.getNumeroDeCapturas() }
            .map { it.getAlcunha() + " (" + it.getEquipa().pretoOuBrancoString() + ") fez " + it.getNumeroDeCapturas() + " capturas"}
            .reversed()
            .take(5)

}

fun func2(gameManager: GameManager) : List<String> {

    return gameManager.arr().sortedBy { it.getPontos() }
            .filter { it.getPontos() > 0 }
            .map { it.getAlcunha()+ " (" + it.getEquipa().pretoOuBrancoString() + ") tem " + it.getPontos() + " pontos"}
            .reversed()
            .take(5)

}

fun func5(gameManager: GameManager) : List<String> {

    return gameManager.arr().filter { !it.getNaoCapturado() }
            .distinctBy { it.getTipo() }
            .map { it.nomeDoTipo(gameManager.jogo) }


}

fun func4(gameManager: GameManager) : List<String> {
    return gameManager.arr().filter { it.numeroDeMovimentosInvalidos >= 1 }
            .sortedBy { it.numeroDeMovimentosInvalidos }
            .reversed()
            .map { "" + it.getEquipa().pretoOuBranco + ":" + it.getAlcunha() + ":" + it.numeroDeMovimentosInvalidos + ":" + it.numeroDeMovimentosValidos}
            .take(3)

}

fun func3(gameManager: GameManager) : List<String> {
    return gameManager.arr().filter { it.getNumeroDeCapturas() > 5 }
            .sortedBy { it.getNumeroDeCapturas()}
            .reversed()
            .map { it.getEquipa().pretoOuBrancoString() + ":" + it.getAlcunha() + ":" + it.getNumeroDeCapturas()}
}




fun getStatsCalculator(stat : StatType) : Function1<GameManager, List<String>> {
    val game : GameManager
    when (stat) {
        StatType.TOP_5_CAPTURAS -> return ::func1
        StatType.TOP_5_PONTOS -> return ::func2
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::func3
        StatType.PECAS_MAIS_BARALHADAS -> return :: func4
        StatType.TIPOS_CAPTURADOS -> return :: func5
    }
}

fun main() {

}

//getStatsCalculator retorna 1 função (5). recebe um enumerado do tipo StatType e retorna uma função responsavel
//por calcular as estatisticas estas 5 funções são escritas aqui. Recebem um gameManager e retornam
//uma lista de Strings. Os formatos das strings estão no video
//NAO PODEMOS USAR CICLOS NESTE FICHEIRO. É POSSIVEL FAZER AS FUNÇÕES COM APENAS UMA LINHA
//IMPORTANTE: o calculo das estatisticas tem que ser feito nestas funçõe, nao pode ser feito no GameManager