package pt.ulusofona.lp2.deisichess

import java.util.*

enum class StatType{
    TOP_5_CAPTURAS, TOP_5_PONTOS, PECAS_MAIS_5_CAPTURAS, PECAS_MAIS_BARALHADAS, TIPOS_CAPTURADOS
}


fun func1(gameManager: GameManager) : List<String> {

    return gameManager.arr().sortedBy { it.numeroDeCapturas }
            .map { it.alcunha + " (" + it.equipa.pretoOuBrancoString() + ") fez " + it.numeroDeCapturas + " capturas"}
            .reversed()
            .take(5)

}

fun getStatsCalculator(stat : StatType) : Function1<GameManager, List<String>> {
    val game : GameManager
    when (stat) {
        StatType.TOP_5_CAPTURAS -> return ::func1
        StatType.TOP_5_PONTOS -> return ::func1
        StatType.PECAS_MAIS_5_CAPTURAS -> return ::func1
        StatType.PECAS_MAIS_BARALHADAS -> return :: func1
        StatType.TIPOS_CAPTURADOS -> return :: func1
    }
}

fun main() {

}

//getStatsCalculator retorna 1 função (5). recebe um enumerado do tipo StatType e retorna uma função responsavel
//por calcular as estatisticas estas 5 funções são escritas aqui. Recebem um gameManager e retornam
//uma lista de Strings. Os formatos das strings estão no video
//NAO PODEMOS USAR CICLOS NESTE FICHEIRO. É POSSIVEL FAZER AS FUNÇÕES COM APENAS UMA LINHA
//IMPORTANTE: o calculo das estatisticas tem que ser feito nestas funçõe, nao pode ser feito no GameManager