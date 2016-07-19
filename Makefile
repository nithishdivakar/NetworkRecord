all:
	latexmk -pdf Network-Lab-Manual.tex

clean:
	latexmk -C
	latexmk -c
