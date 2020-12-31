grammar PL;

prog: stat+; 

stat: stat ';' stat                 # TwoStat
    | ID ':=' exp                   # Assign
    | 'print' '(' exp_list ')'      # PrintStat
    ;

exp: ID                             # IdExpr
    | INT                           # IntExpr
    | exp MULOP exp                 # MulOP
    | exp ADDOP exp                 # AddOP
    | '(' exp ')'                   # parenExpr
    | '(' stat ',' exp ')'          # StatExprTuple
    ;

exp_list: exp                       # SingExpr
    | exp_list ',' exp              # ExprList
    ;

ID: [a-zA-Z]+ [0-9]*;
STR: [a-zA-Z];
INT: [0-9]+;
ADDOP: [-+];
MULOP: [*/%];
WS : [ \t\r\n]+ -> skip ;    //skip whitespace