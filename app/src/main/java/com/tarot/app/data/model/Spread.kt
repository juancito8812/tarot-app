package com.tarot.app.data.model

data class Spread(val name: String, val cardCount: Int, val positions: List<SpreadPosition>)
data class SpreadPosition(val index: Int, val label: String, val description: String)

object Spreads {
    val oneCard = Spread("Una Carta", 1, listOf(SpreadPosition(0, "La Carta", "Tu guía para hoy")))
    val threeCard = Spread("Tres Cartas", 3, listOf(
        SpreadPosition(0, "Pasado", "Lo que ha moldeado esta situación"),
        SpreadPosition(1, "Presente", "Lo que está sucediendo ahora"),
        SpreadPosition(2, "Futuro", "Lo que está por venir")
    ))
    val celticCross = Spread("Cruz Celta", 10, listOf(
        SpreadPosition(0, "Presente", "Situación actual"),
        SpreadPosition(1, "Desafío", "Lo que te cruza"),
        SpreadPosition(2, "Pasado", "Lo que está debajo de ti"),
        SpreadPosition(3, "Futuro", "Lo que está frente a ti"),
        SpreadPosition(4, "Arriba", "Metas conscientes"),
        SpreadPosition(5, "Abajo", "Influencias subconscientes"),
        SpreadPosition(6, "Consejo", "Tu enfoque"),
        SpreadPosition(7, "Externo", "Entorno y otros"),
        SpreadPosition(8, "Esperanzas", "Esperanzas y miedos"),
        SpreadPosition(9, "Resultado", "Resultado final")
    ))
    val all = listOf(oneCard, threeCard, celticCross)
}
