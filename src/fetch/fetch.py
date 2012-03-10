"""Wikisource chapters fetcher

Loads a Wikisource URL and fetches the data into separate files corresponding
to the chapters. The list of chapter titles is printed to `titlex.txt` while
each particular chapter's content is printed in Unicode to `ch-number.txt` .

Not sure how generalizable this is. It works for reading the specific URL I am
targeting. 

"""

import codecs
import urllib2
from bs4 import BeautifulSoup, Comment

MOLD_URL = 'http://ro.wikisource.org/wiki/De_neamul_moldovenilor'
PREFIX = 'data/'
ENC = 'utf-8-sig'

def fetch(url):
	"""Loads a URL into the BeautifulSoup parser, bypassing UA detection"""
	request = urllib2.Request(url, headers={'User-Agent' : 
		"I'm not a bot :) Don't mind me, just getting one single page"})
	document = urllib2.urlopen(request).read()
	return BeautifulSoup(document)

def main(verbose=False):
	if verbose:
		print "Fetching data..."
	doc = fetch(MOLD_URL)
	if verbose:
		print "Done."
		print "Parsing data..."
	headlines = doc.find_all('span', {'class': 'mw-headline'})
	titles = codecs.open(PREFIX + 'titles.txt', 'w+', encoding=ENC)
	for index, headline in enumerate(headlines):
		print >> titles, headline.string
		f = codecs.open(PREFIX + str(index + 1) + '.txt', 'w+', encoding=ENC)
		sibling = headline.parent.next_sibling
		while sibling is not None and \
			  sibling.find('span') in (-1, None) and \
			  type(sibling) is not Comment: #  Break on comments
			if sibling.string is not None:
				print >> f, sibling.string.strip()
			else:
				for string in sibling.stripped_strings:
					print >> f, string
			sibling = sibling.next_sibling
		f.close()
	titles.close()
	if verbose:
		print "Parsing complete"

if __name__=='__main__':
	main(verbose=True)
