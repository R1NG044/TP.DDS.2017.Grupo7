grammar Calculadora;
@header{ 
	package ar.edu.utn.frba.dds.tp.antlr;
}

expresion : termino ((SUM | RES) termino)* ;

termino  : factor ((MUL | DIV) factor)* ;

factor : NUMERO | '(' expresion ')' | indicador | cuenta ;

NUMERO: ('0' .. '9')+ ;

SUM : '+' ;

MUL : '*' ;

RES : '-' ;

DIV : '/' ;

indicador: 'IND('NOMBRE')';

cuenta: 'CUENTA('NOMBRE')';

NOMBRE: (('a'..'z'|'A'..'Z')+) ;

WS : [ \r\n\t] + -> channel (HIDDEN) ;