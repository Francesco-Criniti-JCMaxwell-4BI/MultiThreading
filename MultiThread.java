
package tictactoe;

import java.util.concurrent.TimeUnit;


/**
 *
 * @author Francesco Criniti
 */

public class ES2
{
    // "main" e' il THREAD principale da cui vengono creati e avviati tutti gli altri THREADs
    // i vari THREADs poi evolvono indipendentemente dal "main" che puo' eventualmente terminare prima degli altri
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        
        System.out.println("Main Thread iniziata...");
        Schermi schermo = new Schermi();
        
        // Posso creare un THREAD e avviarlo immediatamente
        Thread tic = new Thread (new TicTacToe("TIC", schermo));
        tic.start();
        
        // Posso creare un 2ndo THREAD e lo faccio avviare immediatamente
        Thread tac = new Thread(new TicTacToe("TAC", schermo));
        tac.start();
        
        // Posso creare un 3zo THREAD e lo faccio avviare immediatamente
        Thread toe = new Thread(new TicTacToe("TOE",schermo));
        toe.start();
        
        
        
        
        try //qui' vengono gestite le eccezioni
        {
            tic.join();
        }     
        catch (InterruptedException e)
        {}
    
        try //qui' vengono gestite le eccezioni
        {
            tac.join();
        }
        catch (InterruptedException e)
        {}
        
        try //qui' vengono gestite le eccezioni
        {
            toe.join();
        }
        catch (InterruptedException e)
        {}
        System.out.println();
        System.out.println("punteggio: " + schermo.punteggio);  //stampo il punteggio
        
        long end = System.currentTimeMillis();
        System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "ms");
    }
}

class Schermi { // classe che gestisce i treads e fa da "semaforo" controllando che il punteggio e lo schermo vengono aggiornati in modo sincronizzato

  String ultimoTHREAD = ""; // ultimo thread che ha scritto sullo schermo
  int punteggio = 0;

  public int punteggio() {  // fornisce il punteggio
    return this.punteggio;
  }

  public synchronized void scrivi(String thread, String msg) { //metodo utilizzato dai threads accedendo al monitor, synchronized serve per far accedere un solo threads alla volta
    int casuale=100+(int)(Math.random()*300); //numero casuale tra 100 e 400
    msg += ": " + casuale + " :";
    if( thread.equals("TOE") && ultimoTHREAD.equals("TAC")) { // se si è avviato il thread toe e precedentemente si è avviato il thread tac
        punteggio++; //aumenta il punteggio di 1
        msg += "  + 1";
    }
    try {
        TimeUnit.MILLISECONDS.sleep(casuale); //casuale ora diventa un numero rappresentante il tempo il MILLISECONDI
    } catch (InterruptedException e) {} //Richiamo eccezione    this.ultimoTHREAD = thread;
    System.out.println(msg);
    ultimoTHREAD = thread;
  }
}
// Ci sono vari (troppi) metodi per creare un THREAD in Java questo e' il mio preferito per i vantaggi che offre
// +1 si puo estendere da un altra classe
// +1 si possono passare parametri (usando il Costruttore)
// +1 si puo' controllare quando un THREAD inizia indipendentemente da quando e' stato creato
class TicTacToe implements Runnable
{   
     // non essendo "static" c'e' una copia delle seguenti variabili per ogni THREAD
    private String t;
    private String msg;
    Schermi schermo;
    
    // Costruttore, possiamo usare il costruttore per passare dei parametri al THREAD
    public TicTacToe (String s, Schermi schermo)
    {
        this.t = s;
        this.schermo = schermo;
    }
    
    @Override // Annotazione per il compilatore
    // se facessimo un overloading invece di un override il copilatore ci segnalerebbe l'errore
    // per approfondimenti http://lancill.blogspot.it/2012/11/annotations-override.html

    public void run()
    {
        for (int i = 10; i > 0; i--) //ciclo del conto alla rovescia
        {           
            msg = "<" + t + "> " + t + ":" + i;
           
            schermo.scrivi(t, msg); //passo all' ogggetto schermo le variabili t e msg
            
        }
    }
}
