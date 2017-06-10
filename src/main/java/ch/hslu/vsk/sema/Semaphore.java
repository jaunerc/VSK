package ch.hslu.vsk.sema;

/**
 * Ein nach oben nicht begrenztes Semaphor, 
 * d.h. der Semaphorenzähler kann unendlich wachsen.
 */
public final class Semaphore {

    private int sema;	// Semaphorenzähler
    private int count;	// Anzahl der wartenden Threads. 

    /**
     * Erzeugt ein Semaphore mit dem Anfangswert 0 des Semaphorenzählers.
     */
    public Semaphore() {
        this(0);
    }

    /**
     * Erzeugt ein Semaphore mit einem Initalwert für den Semaphorenzähler.
     * @param init ein Initialwert, der init Passiersignale im Semaphor initialisiert.
     * @throws IllegalArgumentException, wenn der Initalwert negativ ist.
     */
    public Semaphore(final int init) throws IllegalArgumentException {
        if (init < 0) {
            throw new IllegalArgumentException(init + " < 0");
        }
        sema = init;
        count = 0;
    }

    /**
     * Entspricht dem P() Eintritt (Passieren) in einen synchronisierten
     * Bereich, wobei mitgezählt wird, der wievielte Eintritt es ist. Falls der
     * Zähler null ist wird der Aufrufer blockiert.
     * @throws java.lang.InterruptedException, wenn das Warten unterbrochen wird.
     */
    public synchronized void acquire() throws InterruptedException {
        while (sema == 0) {
            count++;
            this.wait();
            count--;
        }
        sema--;
    }

    /**
     * Entspricht dem V() Verlassen (Freigeben) eines synchronisierten
     * Bereiches, wobei ebenfalls mitgezählt wird, wie oft der Bereich verlassen
     * wird.
     */
    public synchronized void release() {
        if (count > 0) {
            this.notify();
        }
        sema++;
    }

    /**
     * Lesen der Anzahl wartenden Threads.
     * @return Anzahl wartende Threads
     */
    public synchronized int pending() {
        return count;
    }
}
