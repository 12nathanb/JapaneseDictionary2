data class JMDictSimple (
    var id: String,
    var kanji: List<SimpleKanji>,
    var kana: List<SimpleKana>,
    var meaning: List<SimpleMeaning>
)

data class SimpleKanji(
    val kanji: String,
    val common: Boolean
)

data class SimpleKana(
    val kanji: String,
    val common: Boolean
)

data class SimpleMeaning(
    val meaning: String,
    val workType: List<String>
)