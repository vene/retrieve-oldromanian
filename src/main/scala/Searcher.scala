import org.apache.lucene.search.{IndexSearcher, Query, FuzzyQuery}
import org.apache.lucene.index.Term
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.store.Directory
import org.apache.lucene.util.Version

class Searcher(analyzer: Analyzer, directory: Directory) {
	def search(queryString: String, fuzzy: Boolean) {
        val searcher = new IndexSearcher(directory)
        var query : Query = null.asInstanceOf[Query]
        if (fuzzy) {
            query = new FuzzyQuery(new Term("content", queryString))
        } 
            else
        {
            val parser = new QueryParser(Version.LUCENE_36, "content",
            	                         analyzer)
            query = parser parse queryString
        }
        val hits = searcher.search(query, 10)
        println("Found " + hits.totalHits + " results.")
        hits.scoreDocs.foreach {
            case scoreDoc => {
                val doc = searcher.doc(scoreDoc.doc)
                println(doc.get("title"))
            }
        }
        searcher.close()
    }
}
