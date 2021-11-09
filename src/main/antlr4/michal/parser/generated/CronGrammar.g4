grammar CronGrammar;

line : minute_sequence ' ' hour_sequence ' ' day_of_month_sequence ' ' month_sequence ' ' day_of_week_sequence ' ' command;

minute_sequence : arithmetical | explicit | all | range;
hour_sequence : arithmetical | explicit | all | range;
day_of_month_sequence : arithmetical | explicit | all | range;
month_sequence : arithmetical | explicit | all | range;
day_of_week_sequence : arithmetical | explicit | all | range;

all : '*';
explicit : INT (',' INT)*;
range : INT '-' INT;
arithmetical: '*/' arithmetical_step;
arithmetical_step : INT;
command : .+;

INT : '0'|[1-9]+[0-9]*;
SPACE: ' ';
ANY : .;