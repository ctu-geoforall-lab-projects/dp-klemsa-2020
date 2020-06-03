FILE=tomas-klemsa-dp-2020

all:
	pdflatex -shell-escape $(FILE)
	bibtex $(FILE)
	pdflatex -shell-escape $(FILE)
	pdflatex -shell-escape $(FILE) 

clean:
	rm -f *~ *.aux *.log *.out *.bbl *.blg *.loa *.lof \
	*.toc *.lot *.lol

distclean:
	make clean
	rm -f $(FILE).pdf

