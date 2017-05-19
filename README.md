# Esercizio 1

In questo progetto abbiamo creato tre Threads chiamati TIC, TAC e TOE.

Questi tre Threads partiranno da 10 e arriveranno a 0 attraverso un ciclo for in un tempo casuale che va da 100 a 300 che verrà prima calcolato e poi trasformato in millisecondi.

Una variabile chiamata punteggio e inizializzata a 0 si incrementerà di 1 ogni volta che il Thread TOE comparirà dopo il Thread TAC.


# Di seguito ci sono i codici per generare un numero casuale tra 100 e 300

* int casuale=100+(int)(Math.random()*300); 

# Nella sezione Home della Wiki verrano visualizzati due screenshot dei due Run del progetto con il relativo commento finale.





# Esercizio 2

Il secondo esercizio è un aggiornamento del primo, dove noi avevamo riscontrato un problema di sincronizzazione nell' aggiornamento della variabile punteggio e nella scrittura a video.
Avendo inserito un monitor, quest' ultimo farà da "semaforo" facendo accedere ad alcuni metodi contenuti al suo interno un solo thread alla volta, questo grazie all' attributo syncronized.
