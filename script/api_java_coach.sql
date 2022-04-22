create database api_java_coach;
use api_java_coach;

    create table conceptos (
       id_concepto integer not null auto_increment,
        contenido_concepto longtext not null,
        nombre_concepto varchar(255) not null,
        primary key (id_concepto)
    ) engine=InnoDB

    create table ejemplos (
       id_ejemplo integer not null auto_increment,
        contenido_ejemplo longtext not null,
        descripcion_ejemplo varchar(255) not null,
        fk_concepto integer not null,
        primary key (id_ejemplo)
    ) engine=InnoDB
    
    create table preguntas (
       id_pregunta integer not null auto_increment,
        contenido_pregunta varchar(255) not null,
        fk_concepto integer not null,
        primary key (id_pregunta)
    ) engine=InnoDB
    
    create table respuestas (
       id_respuesta integer not null auto_increment,
        contenido_respuesta varchar(255) not null,
        es_correcta bit not null,
        fk_pregunta integer not null ,
        primary key (id_respuesta)
    ) engine=InnoDB
    

    alter table ejemplos 
       add constraint FKht382pypremyn8kwf211xv3qv 
       foreign key (fk_concepto) 
       references conceptos (id_concepto)

    alter table preguntas 
       add constraint FKldhcabdp5japbckwe17byss4u 
       foreign key (fk_concepto) 
       references conceptos (id_concepto)
    
    alter table respuestas 
       add constraint FK92v7b0oi46qb7w6q8m4i2u3gm 
       foreign key (fk_pregunta) 
       references preguntas (id_pregunta)

   -- ------------------------------------------------

insert into conceptos (
    nombre_concepto, contenido_concepto)
	values 
	("Programacion Funcional", "Es un paradigma declarativo. Nos enfocamos en qu� estamos haciendo, enfoque imperativo, y no en el c�mo. 
	Expresaremos nuestra l�gica sin describir controles de flujo, no usaremos ciclos ni condicionales. En programaci�n funcional usaremos 'expresiones lambdas' para escribir c�digo."),
	("Expresiones Lambda", "Es un bloque de c�digo que se pasa de un lado a otro. Se puede pensar a la expresi�n lambda como un m�todo an�nimo, tiene par�metros y un cuerpo 
	igual que los m�todos completos pero no tiene un nombre como un m�todo real. \nLa sintaxis de una expresi�n Lambda es muy sencilla: tendr� siempre 2 partes separadas por una flecha: \n() -> expresion
    \n(parametros) -> expresion \n(parametros) -> {sentencias;}");

insert into ejemplos (descripcion_ejemplo, contenido_ejemplo, fk_concepto)
values 
("Programaci�n Funcional", 
"La problem�tica es conocer la cantidad de n�meros de la lista mayores a 10. \nList<Integer> numeros = List.of(18, 5, 9, 55); \nEl enforque imperativo ser�a el siguiente: 
\nLong result = numeros.stream().filter(num -> num > 10).count();\nPrimero se filtra luego se suma. 
\nPodemos concluir que es funcional ya que delega el control de flujos y condiciones a funciones (filter y count)."
, 1),
("Expresiones lambda", 
"Esta expresi�n lambda no tiene par�metros, por lo que la lista de par�metros est� vac�a. Devuelve el valor constante 28.6. El tipo de devoluci�n se infiere que es double. \ndouble miMetodo() { return 28.6;}",
2),
("Expresiones lambda", 
"Por supuesto, el m�todo definido por una expresi�n lambda no tiene un nombre. Aqu� se muestra una expresi�n lambda ligeramente m�s interesante: \n() -> Math.random() * 100",
2);

insert into preguntas (contenido_pregunta, fk_concepto)
values 
("Cu�l ser�a el c�digo equivalente para imprimir una lista de numeros completa usando expresiones lambda?", 2),
("La siguiente declaraci�n es correcta? \nEn la programaci�n funcional son fundamentales los ciclos y los condicionales", 1);


insert into respuestas (contenido_respuesta, es_corecta, fk_pregunta)
values 
("lista.forEach((n) -> System.out.println(n));", true, 1),
("lista.forEach() -> System.out.println(''));", false, 1),
("lista.for(() -> System.out.println());", false, 1),
("Absolutamente, son cruciales", false, 2),
("Lo normal ser�a que un desarrollador tuviera que hacer un bucle, iterando y crear una l�gica pero la programaci�n funcional, 
te da funciones que hace que se parezca m�s a leer y escribir que a programar.", true, 2);



