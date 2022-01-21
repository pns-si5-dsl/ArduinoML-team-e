grammar ArduinoML;


// RULES
main: definitions states ;


// DEFINITIONS
definitions: definition*;
definition : sensor | actuator;

sensor     : 'Sensor ' id=IDENTIFIER '=' pin=PIN;

actuator   : 'Actuator ' id=IDENTIFIER '=' pin=PIN;


// STATES
states    : state*;
state     : id=IDENTIFIER '{' (action|transition)+ '}';

action    : id=IDENTIFIER 'is' power=STATE;

transition: 'when' condition 'then' to=IDENTIFIER;
condition : id=IDENTIFIER 'is' power=STATE;


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