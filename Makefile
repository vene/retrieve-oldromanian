SBT=sbt
PYTHON=python

data:
	mkdir data; ${PYTHON} src/fetch/fetch.py

clean-data:
	rm -rf data/

clean: 
	${SBT} clean

clean-all: clean clean-data

jar:
	${SBT} package

run:
	JAVA_OPTS=-Dfile.encoding=utf8 ${SBT} run
