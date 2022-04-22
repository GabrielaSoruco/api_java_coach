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
	("Programacion Funcional", "Es un paradigma declarativo. Nos enfocamos en qué estamos haciendo, enfoque imperativo, y no en el cómo. 
	Expresaremos nuestra lógica sin describir controles de flujo, no usaremos ciclos ni condicionales. En programación funcional usaremos 'expresiones lambdas' para escribir código."),
	("Expresiones Lambda", "Es un bloque de código que se pasa de un lado a otro. Se puede pensar a la expresión lambda como un método anónimo, tiene parámetros y un cuerpo 
	igual que los métodos completos pero no tiene un nombre como un método real. \nLa sintaxis de una expresión Lambda es muy sencilla: tendrá siempre 2 partes separadas por una flecha: \n() -> expresion
    \n(parametros) -> expresion \n(parametros) -> {sentencias;}");

insert into ejemplos (descripcion_ejemplo, contenido_ejemplo, fk_concepto)
values 
("Programación Funcional", 
"La problemática es conocer la cantidad de números de la lista mayores a 10. \nList<Integer> numeros = List.of(18, 5, 9, 55); \nEl enforque imperativo sería el siguiente: 
\nLong result = numeros.stream().filter(num -> num > 10).count();\nPrimero se filtra luego se suma. 
\nPodemos concluir que es funcional ya que delega el control de flujos y condiciones a funciones (filter y count)."
, 1),
("Expresiones lambda", 
"Esta expresión lambda no tiene parámetros, por lo que la lista de parámetros está vacía. Devuelve el valor constante 28.6. El tipo de devolución se infiere que es double. \ndouble miMetodo() { return 28.6;}",
2),
("Expresiones lambda", 
"Por supuesto, el método definido por una expresión lambda no tiene un nombre. Aquí se muestra una expresión lambda ligeramente más interesante: \n() -> Math.random() * 100",
2);

insert into preguntas (contenido_pregunta, fk_concepto)
values 
("Cuál sería el código equivalente para imprimir una lista de numeros completa usando expresiones lambda?", 2),
("La siguiente declaración es correcta? \nEn la programación funcional son fundamentales los ciclos y los condicionales", 1);


insert into respuestas (contenido_respuesta, es_corecta, fk_pregunta)
values 
("lista.forEach((n) -> System.out.println(n));", true, 1),
("lista.forEach() -> System.out.println(''));", false, 1),
("lista.for(() -> System.out.println());", false, 1),
("Absolutamente, son cruciales", false, 2),
("Lo normal sería que un desarrollador tuviera que hacer un bucle, iterando y crear una lógica pero la programación funcional, 
te da funciones que hace que se parezca más a leer y escribir que a programar.", true, 2);



