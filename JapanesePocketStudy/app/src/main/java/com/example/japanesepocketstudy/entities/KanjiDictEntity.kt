data class KanjiDictEntity (
    val kanji: String,
    val frequency: Int?,
    val grade: Int?,
    val stroke_count: Int?,
    val meanings: List<String>,
    val onyomi: List<String>,
    val kunyomi: List<String>,
)