@echo off
echo L�schen aller Hilfsdateien f�r TeX...
pause
del /P *.aux
del /P *.lo*
del /P *.toc
del /P *.out
del /P *.bbl
del /P *.blg
del /P *.pdf
del /P *.dvi

cd sections
del /P *.aux
cd ..
