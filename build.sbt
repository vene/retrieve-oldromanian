name := "Old Romanian IR example"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies += "org.apache.lucene" % "lucene-core" % "3.6.0"

libraryDependencies += "org.apache.lucene" % "lucene-analyzers" % "3.6.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

testOptions in Test += Tests.Argument("-oD")
