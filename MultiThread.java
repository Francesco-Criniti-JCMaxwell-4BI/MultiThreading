package multithread;
import java.util.concurrent.TimeUnit;
import static multithread.TicTacToe.cont;

/**
 *
 * @author Francesco Criniti
 */

public class ES1 
{
    // "main" e' il THREAD principale da cui vengono creati e avviati tutti gli altri THREADs
    // i vari THREADs poi evolvono indipendentemente dal "main" che puo' eventualmente terminare prima degli altri
    public static void main(String[] args) 
    {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        // Posso creare un THREAD e avviarlo immediatamente
        Thread tic = new Thread (new TicTacToe("TIC"));
        tic.start();
        
        // Posso creare un 2ndo THREAD e lo faccio avviare immediatamente
        Thread tac = new Thread(new TicTacToe("TAC"));
        tac.start();
        
        // Posso creare un 3zo THREAD e lo faccio avviare immediatamente
        Thread toe = new Thread(new TicTacToe("TOE"));
        toe.start();
        
        long end = System.currentTimeMillis();
        System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "ms");
        
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
        System.out.println("punteggio: " + cont);  //stampo il punteggio
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
    //essendo una variabile static ne basta una per tutti i THREAD
    public static int cont = 0;
    public static boolean c = false;
    // Costruttore, possiamo usare il costruttore per passare dei parametri al THREAD
    public TicTacToe (String s) 
    {
        this.t = s;
    }
    
    @Override // Annotazione per il compilatore
    // se facessimo un overloading invece di un override il copilatore ci segnalerebbe l'errore
    // per approfondimenti http://lancill.blogspot.it/2012/11/annotations-override.html

    public void run() 
    {
        for (int i = 10; i > 0; i--) //ciclo del conto alla rovescia
        {           
            if("TAC".equals(t))
                c = true;
                
            msg = "<" + t + "> ";
            //System.out.print(msg);
            int casuale=100+(int)(Math.random()*300); //genera un numero casuale da 100 a 300
             try {
                TimeUnit.MILLISECONDS.sleep(casuale);  //il numero casuale diventa il tempo con cui viene effettuato il conto alla rovescia
            } catch (InterruptedException e) {} //eccezione
            if("TOE".equals(t) && c == true) 
                cont++; //incremento il contatore
            else
                c = false;
            msg += t + ": " + i;
            
            System.out.println(msg); //stampo a video il messaggio del punteggio
        } 
    }
}
