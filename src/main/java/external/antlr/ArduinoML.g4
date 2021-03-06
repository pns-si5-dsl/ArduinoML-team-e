grammar ArduinoML;


program: definition* state* EOF;

// DEFINITIONS
definition      : 'Sensor '   id=ID 'on' pin=PIN                  #sensor
                | 'Actuator ' id=ID 'on' pin=PIN                  #actuator
                ;

// STATES
state           : 'initial' id=ID '{' declarations '}'            #initialState
                |           id=ID '{' declarations '}'            #pendingState
                ;

declarations    : declaration+;
declaration     : id=ID 'is' signal=SIGNAL                        #action
                | conditions 'then' to=ID                         #transition
                ;

conditions      : condition ('and' conditions)?;
condition       : 'when'  id=ID  'is' signal=SIGNAL               #sensorCondition
                | 'after' d=LONG 'ms'                             #timedCondition
                ;

// TOKENS
ID              : LC (LC|UC|NUM)*;
PIN             : 'PIN' PIN_NUM;
SIGNAL          : ('HIGH'|'LOW');
LONG            : NUM+;
PIN_NUM         : NUM|'10'|'11'|'12';

LC              : [a-z];
UC              : [A-Z];
NUM             : [0-9];
NEWLINE         : '\r'? [\n\r]+                 -> skip;
WHITE_SPACES    : [ \t\n]+                      -> skip;
COMMENTS        : '//' ~[\r\n]*                 -> skip;