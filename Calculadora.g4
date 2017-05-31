grammar Calculadora;

@header {
	package dds;
}

expresion : termino ((SUM | RES) termino)* ;

termino  : factor ((MUL | DIV) factor)* ;

factor : NUMERO | '(' expresion ')';

NUMERO : DIGIT+ ;

SUM : '+' ;

MUL : '*' ;

RES : '-' ;

DIV : '/' ;

DIGIT : ('0' .. '9') ;

WS : [ \r\n\t] + -> channel (HIDDEN) ;
