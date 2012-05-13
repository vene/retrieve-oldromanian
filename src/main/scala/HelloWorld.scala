import io.Source

import org.apache.lucene.index._
import org.apache.lucene.search._
import org.apache.lucene.queryParser.QueryParser
import org.apache.lucene.document._
import org.apache.lucene.store.RAMDirectory
import org.apache.lucene.util.Version
import org.apache.lucene.analysis.standard.StandardAnalyzer

object IndexBuilder {
    val prefix = "data/"

    val analyzer = new RomanianAnalyzer(Version.LUCENE_CURRENT)
    val idx = new RAMDirectory
    val writer = new IndexWriter(idx, analyzer, 
                                 IndexWriter.MaxFieldLength.UNLIMITED)

    def main(args: Array[String]) {
        val titles = io.Source.fromFile(prefix + "titles.txt").getLines.toList
        titles.zipWithIndex.foreach {
            case (title, i) => {
                val file = Source.fromFile(prefix + (i + 1) + ".txt").mkString
                val doc = new Document
                doc add new Field("title", title, Field.Store.YES, 
                                  Field.Index.NO)
                doc add new Field("content", file, Field.Store.YES, 
                                  Field.Index.ANALYZED, Field.TermVector.YES)
                // println(doc.getField("content").stringValue())
                writer.addDocument(doc)
            }
        }
        writer.commit
        writer.close
        //val reader = IndexReader open idx
        //val terms = reader.terms
        //while (terms.next) {
        //  if (terms.docFreq == 8) println(terms.term.text) // print stopwords
        //}  
        val parser = new QueryParser(Version.LUCENE_CURRENT, "content", analyzer)
        println("result: " + parser.parse("unguresc ungurescŭ ungurăsc"))
        //search("Ovidius", false)
        //search("Ovidiu", false)
        //search("Ovidiu", true)
        //search("ovidiu", true)
    }

    def search(queryString: String, fuzzy: Boolean) {
        val searcher = new IndexSearcher(idx)
        var query : Query = null.asInstanceOf[Query]
        if (fuzzy) {
            query = new FuzzyQuery(new Term("content", queryString))
        } 
            else
        {
            val parser = new QueryParser(Version.LUCENE_CURRENT, 
                                     "content", analyzer)
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
