DROP TABLE IF EXISTS parteneri CASCADE;
DROP TABLE IF EXISTS activitati CASCADE;
DROP TABLE IF EXISTS acorduri CASCADE;
DROP  TABLE IF EXISTS comisie CASCADE;
DROP  TABLE IF EXISTS persoane CASCADE;
DROP  TABLE IF EXISTS specializari CASCADE;
DROP  TABLE IF EXISTS studenti CASCADE;
DROP  TABLE IF EXISTS traseu_studenti CASCADE;
DROP  TABLE IF EXISTS dosare CASCADE;
DROP  TABLE IF EXISTS sesiuni CASCADE;
DROP  TABLE IF EXISTS optiuni_dosare CASCADE;
DROP  TABLE IF EXISTS selectie_finala CASCADE;
DROP  TABLE IF EXISTS mobilitati CASCADE;
DROP  TABLE IF EXISTS probleme_semnalate CASCADE;
DROP  TABLE IF EXISTS persoane_comisie CASCADE;
DROP  TABLE IF EXISTS mentori CASCADE;


CREATE TABLE parteneri (
	idPartener serial primary key,
	domeniuActivitate VARCHAR (60) NOT NULL,
	denumirePartener VARCHAR (60) NOT NULL, ---ex nume hotel
	tara VARCHAR (40) NOT NULL,
	oras VARCHAR (40) NOT NULL
); --done



CREATE TABLE activitati(
	idActivitate serial primary key,
	denumire varchar (40), -- ce face la mobilitate
	programul varchar(40)
); --done



CREATE TABLE acorduri (
	idAcord serial primary key, 
	dataAcordului date, --cand a fost semnat
	valoareaAcordului integer,
	dataInitiala date,  -- partenerul are un nr de locuri pt studenti 
	dataFinala date,
	nrLocuriOcupate integer,
	nrLocuriDisponibile integer,
	idPartener integer NOT NULL CONSTRAINT fk_idPartener REFERENCES Parteneri(idPartener)
); --done



CREATE TABLE comisie (
	idComisie serial primary key,
	denumireComisie varchar (40)
); --done;



CREATE TABLE persoane (
	codPersoana varchar (15) NOT NULL CONSTRAINT pk_persoane PRIMARY KEY,
	nume varchar (40),
	prenume varchar (40),
	email varchar (40)
); --done



CREATE TABLE specializari (
	idSpecializare serial primary key,
	denumireSpecializare varchar (40),
	nivelStudii varchar(40), --licenta/master/etc
	anulDeStudii integer
); --done



CREATE TABLE studenti (
	idStudent serial primary key,
	anUniversitar varchar (40),
	varstaStudent integer,
	codPersoana varchar(15) CONSTRAINT fk_codPersiana REFERENCES persoane(codPersoana),
	idSpecializare integer CONSTRAINT fk_idSpecializare REFERENCES specializari(idSpecializare)
);



CREATE TABLE traseu_studenti (
	idTraseuStudent serial primary key,
	anUniversitar varchar (40),
	grupa char (40),
	mediaAnului integer,
	numarCredite integer,
    idAcord integer NOT NULL CONSTRAINT fk_idAcord REFERENCES acorduri(idAcord),
	idStudent integer NOT NULL CONSTRAINT fk_idStudent REFERENCES studenti(idStudent)
);



CREATE TABLE dosare (
	codDosar serial primary key,
	idStudent integer NOT NULL CONSTRAINT fk_idStudent REFERENCES studenti(idStudent)
); -- done


CREATE TABLE sesiuni (
	idSesiuni serial primary key,
	dataSesiune date,
	comentarii varchar (80),
	codDosar integer NOT NULL CONSTRAINT fk_codDosar REFERENCES dosare(codDosar),
	idComisie integer NOT NULL CONSTRAINT fk_idComisie REFERENCES comisie(idComisie)
); --done



CREATE TABLE optiuni_dosare (
	idOptiuniDosar serial primary key,
	numarulOptiunii integer,
	codDosar integer NOT NULL CONSTRAINT fk_codDosar REFERENCES dosare(codDosar),
	idAcord integer NOT NULL CONSTRAINT fk_idAcord REFERENCES acorduri(idAcord)
); --done



CREATE TABLE selectie_finala (
	idSelectieFinala serial primary key,
	comentarii varchar(50),
	punctaj integer,
	idOptiuniDosar integer NOT NULL CONSTRAINT fk_idOptiuniDosar REFERENCES optiuni_dosare(idOptiuniDosar),
	idSesiuni integer NOT NULL CONSTRAINT fk_idSesiuni REFERENCES sesiuni(idSesiuni)
); --done



CREATE TABLE mobilitati (
	codMobilitatea serial primary key,
	dataPlecarii date,  --date despre plecare
	dataSosirii date,
	durataAcordului varchar (40),
	idAcord integer CONSTRAINT fk_idAcord REFERENCES acorduri(idAcord),
	idActivitate integer CONSTRAINT fk_idActivitate REFERENCES activitati(idActivitate),
	idSelectieFinala integer NOT NULL CONSTRAINT fk_idSelectieFinala REFERENCES selectie_finala(idSelectieFinala)
); --done


CREATE TABLE probleme_semnalate (
	idProblema serial primary key,
	detaliiProblema varchar(80),
	metodaRezolvareProblema varchar(80)
); --done


CREATE TABLE persoane_comisie (
	idComisie integer NOT NULL CONSTRAINT fk_idComisie REFERENCES comisie(idComisie),
	codPersoana varchar(15) NOT NULL CONSTRAINT fk_codPersoana REFERENCES persoane(codPersoana)
);


CREATE TABLE mentori (
	idMentor serial primary key,
	codPersoana varchar(15) NOT NULL CONSTRAINT fk_codPersoana REFERENCES persoane(codPersoana),
	codMobilitatea integer NOT NULL CONSTRAINT fk_codMobilitate REFERENCES mobilitati(codMobilitatea),
	idProblema integer NOT NULL CONSTRAINT fk_idProblema REFERENCES probleme_semnalate(idProblema),
	idStudent integer NOT NULL CONSTRAINT fk_idStudent REFERENCES studenti(idStudent)
); --done