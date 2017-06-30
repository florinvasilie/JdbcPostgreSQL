insert into parteneri(domeniuActivitate, denumirePartener, tara, oras) 
	values('IT', 'Hotel Viena', 'Austria', 'Viena');

insert into parteneri(domeniuActivitate, denumirePartener, tara, oras) 
	values('IT', 'Hotel Continental', 'Romania', 'Iasi');

insert into specializari(denumireSpecializare, nivelStudii, anulDeStudii)
	values('finante', 'master', 1);

insert into specializari(denumireSpecializare, nivelStudii, anulDeStudii)
	values('info', 'licenta', 2);

insert into activitati(denumire, programul) 
	values('denumire1', 'programul1');

insert into acorduri(dataAcordului, valoareaAcordului, dataInitiala, dataFinala, nrLocuriOcupate, nrLocuriDisponibile, idPartener) 
	values(to_date('2017-06-18', 'yyyy-mm-dd'), 22, to_date('2017-06-18', 'yyyy-mm-dd'), to_date('2017-06-18', 'yyyy-mm-dd'), 0, 40, 1);

insert into comisie(denumireComisie) 
	values('comisia1');

insert into persoane(codPersoana, nume, prenume, email)
	values('codpe', 'Paul', 'Florin', 'email@email.email');

insert into persoane(codPersoana, nume, prenume, email)
	values('codp1', 'Ion', 'Vasile', 'email1@email.email');


insert into studenti(anUniversitar, varstaStudent, codPersoana, idSpecializare)
	values('2016-2017', 21, 'codpe', 1);

insert into studenti(anUniversitar, varstaStudent, codPersoana, idSpecializare)
	values('2016-2017', 23, 'codp1', 1);

insert into studenti(anUniversitar, varstaStudent, codPersoana, idSpecializare)
	values('2016-2017', 21, 'codpe', 2);

insert into dosare(idStudent)
	values(1);

insert into dosare(idStudent)
	values(1);

insert into dosare(idStudent)
	values(3);

insert into sesiuni(dataSesiune, comentarii, codDosar, idComisie)
	values(to_date('2017-06-18', 'yyyy-mm-dd'), 'comentarii', 1, 1);

insert into sesiuni(dataSesiune, comentarii, codDosar, idComisie)
	values(to_date('2017-06-18', 'yyyy-mm-dd'), 'comentarii1', 2, 1);

insert into sesiuni(dataSesiune, comentarii, codDosar, idComisie)
	values(to_date('2017-06-17', 'yyyy-mm-dd'), 'comentarii3', 3, 1);

insert into optiuni_dosare(numarulOptiunii, codDosar, idAcord)
	values(2, 1, 1);

insert into optiuni_dosare(numarulOptiunii, codDosar, idAcord)
	values(4, 2, 1);

insert into optiuni_dosare(numarulOptiunii, codDosar, idAcord) 
	values(5, 3, 1);

insert into optiuni_dosare(numarulOptiunii, codDosar, idAcord)
	values(9, 3, 1);		

insert into selectie_finala(comentarii, punctaj, idOptiuniDosar, idSesiuni)
	values('comentarii', 7, 1, 1);

insert into mobilitati(dataPlecarii, dataSosirii, durataAcordului, idAcord, idActivitate, idSelectieFinala)
	values(to_date('2017-06-18', 'yyyy-mm-dd'), to_date('2017-06-20', 'yyyy-mm-dd'), '2 zile', 1, 1, 1);

insert into probleme_semnalate(detaliiProblema, metodaRezolvareProblema)
	values('problema1', 'rezolvarea1');

insert into persoane_comisie(idComisie, codPersoana)
	values(1, 'codpe');

insert into mentori(codPersoana, codMobilitatea, idProblema, idStudent)
	values('codpe', 1, 1, 1);
	
insert into mentori(codPersoana, codMobilitatea, idProblema, idStudent)
	values('codpe', 1, 1, 3);

insert into mentori(codPersoana, codMobilitatea, idProblema, idStudent)
	values('codp1', 1, 1, 2);

insert into traseu_studenti(anUniversitar, grupa, mediaAnului, numarCredite, idAcord, idStudent)
	values('2015-2016', 'A6', 9, 50, 1,3);