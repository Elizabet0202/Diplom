
package ru.iteco.fmhandroid.dto

data class NewsTestData(
    val category: String,
    val description: String
)

object NewsData {

    val createNews = NewsTestData(
        category = "Объявление",
        description = "News created by automated UI test"
    )

    val deleteNews = NewsTestData(
        category = "Объявление",
        description = "News created for deletion test"
    )

    fun uniqueCreateTitle(): String =
        "Auto test news ${System.currentTimeMillis()}"

    fun uniqueDeleteTitle(): String =
        "News for deletion ${System.currentTimeMillis()}"
}