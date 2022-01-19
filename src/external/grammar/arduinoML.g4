grammar arduinoML;

// RULES
main: 'Hello ' name '!';
name: ANY+;

// LEXEMS
ANY: .;