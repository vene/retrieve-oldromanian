import org.apache.lucene.util.Version

object RetrieveOldRomanian extends Application {
        val analyzer = new RomanianAnalyzer(Version.LUCENE_CURRENT)
        val directory = IndexBuilder.buildIndexDirectory(analyzer)
        var loop = true
        while (loop)
        {
        	printf("Interogare> ")
            var query = readLine()
            if (query != "")
                new Searcher(analyzer, directory).search(query, false)
            else
                loop = false
        }
}
