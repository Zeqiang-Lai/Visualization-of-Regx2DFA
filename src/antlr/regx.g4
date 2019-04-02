grammar regx;

primaryExp: 
    Identifier |
    LeftParen expression RightParen |
    ;

closureExp:
    primaryExp |
    closureExp Closure;

concatExp:
    closureExp |
    concatExp Concat closureExp;

orExp:
    concatExp |
    orExp Or concatExp;

expression:
    orExp;

Or: '|';
Closure: '*';
LeftParen : '(';
RightParen : ')';
Concat : '&';

Identifier: Nondigit+;
fragment
Nondigit : [a-zA-Z_];

Whitespace
    :   [ \t]+
        -> skip
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> skip
    ;
