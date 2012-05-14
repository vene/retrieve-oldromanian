import io.Source

import org.apache.lucene.index._
import org.apache.lucene.document._
import org.apache.lucene.analysis.Analyzer
import org.apache.lucene.store.{Directory, RAMDirectory}

object IndexBuilder {
    val prefix = "data/"

    def createDocument(title: String, content: String) : Document = {
        val doc = new Document
        doc.add(new Field("title", title, Field.Store.YES, Field.Index.NO))
        doc.add(new Field("content", content, Field.Store.YES,
                          Field.Index.ANALYZED, Field.TermVector.YES))
        return doc
    }

    def buildIndexDirectory(analyzer: Analyzer) : Directory = {
        val idx = new RAMDirectory
        val writer = new IndexWriter(idx, analyzer, 
                                     IndexWriter.MaxFieldLength.UNLIMITED)

        val titles = io.Source.fromFile(prefix + "titles.txt").getLines.toList
        titles.zipWithIndex.foreach {
            case (title, i) => {
                val file = Source.fromFile(prefix + (i + 1) + ".txt").mkString
                writer.addDocument(createDocument(title, file))
            }
        }

        writer.commit()
        writer.close()
        
        return idx
    }
}
