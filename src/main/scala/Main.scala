import org.apache.lucene.util.Version

object RetrieveOldRomanian extends Application {
        val analyzer = new RomanianAnalyzer(Version.LUCENE_CURRENT)
        val directory = IndexBuilder.buildIndexDirectory(analyzer)

        new Searcher(analyzer, directory).search("Unguresc", false)
}
