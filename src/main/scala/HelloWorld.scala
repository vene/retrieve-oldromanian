import org.apache.lucene.LucenePackage

object HelloWorld {
	def main(args: Array[String]) = println(LucenePackage.get().toString())
}
