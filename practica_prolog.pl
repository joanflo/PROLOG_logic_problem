/*Autors:
  - Juan Gabriel Florit Gomila
  - Bernardo Miquel Riera
*/

misteri_misterios:-

	permutacio([lady_q,lady_r,lady_s],Dames),
	permutacio([abreu,bulto,coromines],Vestits),

	/*Lady S duia el vestit d'Abreu*/
	posicio(lady_s,Dames,P1),
	posicio(abreu,Vestits,P1),

	permutacio([tulipa,clavell,rosa],Flors),

	/*Lady S no duia la rosa*/
	posicio(rosa,Flors,P2),
	P1\=P2,

	/*La rosa estava al vestit de Bultó*/
	posicio(bulto,Vestits,P2),

	permutacio([organdi,seda,franella],Materials),

	/*El vestit de Bultó és de seda*/
	posicio(seda,Materials,P2),

	/*El vestit d'Abreu no és de franel·la*/
	posicio(franella,Materials,P3),
	P1\=P3,

	/*Lady R no du la tulipa*/
	posicio(lady_r,Dames,P4),
	posicio(tulipa,Flors,P5),
	P4\=P5,

	/*La dama del vestit fet d'organdí no porta la tulipa*/
	posicio(organdi,Materials,P6),
	posicio(_,Dames,P6),
	P5\=P6,
	!,/*evitam la permutació de la solució*/

	escriure([Dames,Vestits,Flors,Materials]).



/*FUNCIONS AUXILIARS: */

/*Escrivim el resultat del problema*/
escriure([[]|_]).
escriure(L1):-
	car(L1,LAtoms),
	LAtoms=[Dama,Vestit,Flor,Material],
	write(Dama),write(' va dur el vestit de '),write(Vestit),write(' amb la '),
	write(Flor),write(' i el material era '),write(Material),write('.'),nl,
	cdr(L1,L2),
	escriure(L2).

/*Extreu el primer element de cada llista*/
car([],[]).
car([[X|L1]|L2],[X|L3]):-car(L2,L3).

/*Torna les llistes sense el seu primer element*/
cdr([],[]).
cdr([[X|L1]|L2],[L1|L3]):-cdr(L2,L3).

/*Inserta un element dins la llista donada*/
insertar(E,L,[E|L]).
insertar(E,[X|Y],[X|Z]):-insertar(E,Y,Z).

/*Permuta una llista donada per paràmetre*/
permutacio([],[]).
permutacio([X|Y],Z):-permutacio(Y,L), insertar(X,L,Z).

/*Diu la posició on es troba un element*/
posicio(X,[X|L],1):-!.
posicio(X,[Y|L],P):-posicio(X,L,P1), P is P1+1.
