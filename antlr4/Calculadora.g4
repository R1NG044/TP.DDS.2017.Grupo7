grammar Calculadora;

@header {
	package dds;
}

//expresion : termino ((SUM | RES) termino)* | termino ((MUL | DIV) termino)* ;
expresion : termino ((SUM | RES) termino)* ;

//El uso de parentesis no está andando bien y habria que usarlo para modificar la precedencia. x ej: 3*(4+5)

termino  : factor ((MUL | DIV) factor)* ;

factor : NUMERO | '(' expresion ')' | indicador | cuenta ;

NUMERO: ('0' .. '9')+ ;

//NUMERO : DIGIT+ ;

SUM : '+' ;

MUL : '*' ;

RES : '-' ;

DIV : '/' ;

//DIGIT : ('0' .. '9') ;

//TEXTO :  ('a'..'z'|'A'..'Z')+ ;

//INDICADOR: 'IND('(('a'..'z'|'A'..'Z')+)')';

indicador: 'IND('NOMBRE')';

cuenta: 'CUENTA('NOMBRE')';

NOMBRE: (('a'..'z'|'A'..'Z')+) ;

WS : [ \r\n\t] + -> channel (HIDDEN) ;