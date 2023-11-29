package pt.ulusofona.lp2.deisichess

import java.util.*

enum class StatType{
    TOP_5_CAPTURAS, TOP_5_PONTOS, PECAS_MAIS_5_CAPTURAS, PECAS_MAIS_BARALHADAS, TIPOS_CAPTURADOS
}

fun func1(gameManager: GameManager) : ArrayList<String> {
    val stringList: ArrayList<String> = ArrayList();
    return stringList
}

fun getStatsCalculator(stat : StatType) : Function<ArrayList<String>> {
    when (stat) {
        StatType.TOP_5_CAPTURAS -> return ::func1
        StatType.TOP_5_PONTOS -> return ::func1
        else -> return ::func1
    }
}

fun main() {

}

//getStatsCalculator retorna 1 função (5). recebe um enumerado do tipo StatType e retorna uma função responsavel
//por calcular as estatisticas estas 5 funções são escritas aqui. Recebem um gameManager e retornam
//uma lista de Strings. Os formatos das strings estão no video
//NAO PODEMOS USAR CICLOS NESTE FICHEIRO. É POSSIVEL FAZER AS FUNÇÕES COM APENAS UMA LINHA
//IMPORTANTE: o calculo das estatisticas tem que ser feito nestas funçõe, nao pode ser feito no GameManager