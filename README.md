Old Romanian IR example
=======================

This is a small project based on Lucene, using Scala and SBT. The purpose is
to set up a IR system that allows for user-friendly queries on old Romanian
language texts. The corpus is from [Miron Costin](http://en.wikipedia.org/wiki/
Miron_Costin)'s "[De Neamul Moldovenilor](http://ro.wikisource.org/wiki/
De_neamul_moldovenilor)" (On The Moldavian People).

Working on such data poses several challenges. Lemmatizing is more difficult
than in modern Romanian, and note that Romanian is much more morphology-heavy
than English. There are also more non-ASCII characters than in modern Romanian.

The goal is to build a system flexible enough to allow slightly modernized
queries to satisfy information needs of present-day people, which will express
them in modern language.  
