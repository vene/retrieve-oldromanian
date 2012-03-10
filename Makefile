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
