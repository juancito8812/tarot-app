package com.tarot.app.data.model
import org.json.JSONObject

data class TarotCard(
    val id: Int,
    val name: String,
    val arcana: String,
    val suit: String?,
    val number: Int,
    val upright: String,
    val reversed: String,
    val keywords: List<String>,
    val element: String?,
    val image: String
) {
    companion object {
        fun parseList(rawJson: String): List<TarotCard> {
            val root = JSONObject(rawJson)
            val arr = root.getJSONArray("cards")
            return (0 until arr.length()).map { i ->
                val o = arr.getJSONObject(i)
                TarotCard(
                    id = o.getInt("id"),
                    name = o.getString("name"),
                    arcana = o.getString("arcana"),
                    suit = if (o.has("suit") && !o.isNull("suit")) o.getString("suit") else null,
                    number = o.getInt("number"),
                    upright = o.getString("upright"),
                    reversed = o.getString("reversed"),
                    keywords = o.getJSONArray("keywords").let { a ->
                        (0 until a.length()).map { a.getString(it) }
                    },
                    element = if (o.has("element") && !o.isNull("element")) o.getString("element") else null,
                    image = o.getString("image")
                )
            }
        }
    }
}
