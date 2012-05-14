import org.scalatest.FunSuite
import org.tartarus.snowball.SnowballProgram

class TestStemmer extends FunSuite {
    val stemmer = new OldRomanianStemmer
    
    def checkWordStem(word: String, stem: String) {
        stemmer.setCurrent(word)
        stemmer.stem
        assert(stemmer.getCurrent === stem, "Stem of " + word)
    }

    test("ungur{a+}sc, unguresc and unguresc{u+} should have ungur as stem") {
        val test_words = Array("ungurăsc", "unguresc", "ungurescŭ")
        for (word <- test_words) checkWordStem(word, "ungur")
    }

    test("Romanian stems from the example page") {
        val words = Array("abruptă", "absent", "absentă", "absente", "absenţa",
                      "absenţă", "absenţi", "absolut", "absoluta", "absolută",
                      "absolute", "absolutul", "absolutului", "absoluţi",
                      "absolve", "absolvenţi", "absolvenţii", "absolvi",
                      "absolvire", "absolvit", "absolvită", "absolviţi",
                      "absorbant", "absorbantă", "absorbi", "absorbit", 
                      "absorbite", "absorbiţi", "absorbţia", "abstinent",
                      "abstract", "abstractă", "abstracte", "abstractiza",
                      "abstractizare", "abstractizat", "abstractizăm",
                      "abstracto", "abstracţia", "abstracţii", "ocol",
                      "ocolea", "ocolesc", "ocoleşte", "ocoleşti", "ocoli",
                      "ocolim", "ocolind", "ocolire", "ocolişuri", "ocolit",
                      "ocolită", "ocoliţi", "ocolul", "ocoluri", "ocolurile",
                      "ocrotit", "ocrotitoare", "ocrotitor", "ocrotiţi",
                      "octavă", "octavian", "octet", "octeţi", "octogenarul",
                      "octombrie", "ocular", "ocult", "ocultarea", "ocultat",
                      "ocultă", "ocultării", "oculţi", "ocup", "ocupa",
                      "ocupai", "ocupanţi", "ocupanţii", "ocupase", "ocupat")

        val stems = Array("abrupt", "absent", "absent", "absent", "absenţ",
                      "absenţ", "absenţ", "absol", "absol", "absol", "absol",
                      "absol", "absol", "absoluţ", "absolv", "absolvenţ",
                      "absolvenţ", "absolv", "absolv", "absolv", "absolv",
                      "absolv", "absorb", "absorb", "absorb", "absorb",
                      "absorb", "absorb", "absorbţ", "abstinent", "abstract",
                      "abstract", "abstract", "abstractiz", "abstractiz",
                      "abstractiz", "abstractiz", "abstracto", "abstracţ",
                      "abstracţ", "ocol", "ocol", "ocol", "ocol", "ocol",
                      "ocol", "ocol", "ocol", "ocol", "ocolişur", "ocol",
                      "ocol", "ocol", "ocol", "ocolur", "ocolur", "ocrot",
                      "ocrot", "ocrot", "ocrot", "octav", "octavian", "octet",
                      "octeţ", "octogenar", "octombr", "ocular", "ocult",
                      "ocult", "ocult", "ocult", "ocultăr", "oculţ", "ocup",
                      "ocup", "ocup", "ocupanţ", "ocupanţ", "ocup", "ocup")

        for ((word, stem) <- words zip stems) checkWordStem(word, stem)
    }
}
