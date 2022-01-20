grammar arduinoML;


// RULES
main: definitions states ;


// DEFINITIONS
definitions: definition*;
definition : sensor | actuator;

sensor     : 'Sensor ' IDENTIFIER '=' PIN;

actuator   : 'Actuator ' IDENTIFIER '=' PIN;


// STATES
states    : state*;
state     : IDENTIFIER '{' (action|transition)+ '}';

action    : IDENTIFIER 'is' STATE;

transition: 'when' condition 'then' IDENTIFIER;
condition : IDENTIFIER 'is' STATE;


// FINALS
IDENTIFIER: LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+;
PIN       : ('PIN' NUMBER);
STATE     : ('ON'|'OFF');


// LEXEMS
LOWERCASE   : [a-z];
UPPERCASE   : [A-Z];
NUMBER      : [0-9];
NEWLINE     : (('\r'? '\n' | '\r')+)         -> skip;
WHITE_SPACES: ((' ' | '\t')+)                -> skip;
COMMENTS    : (('//'|'#') ~( '\r' | '\n' )*) -> skip;