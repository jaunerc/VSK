package ch.hslu.vsk.buffer.forum;

/**
 * Sehr einfache Blocking Queue (gefunden im Forum java-samples.com).
 */
public final class SimpleQueue {

    private int value;
    private boolean valueSet = false;

    /**
     * Falls ein Wert gespeichert wurde, diesen auslesen, ansonsten warten.
     * @return Integer Wert
     * @throws InterruptedException, wenn beim Warten unterbrochen
     */
    public synchronized int get() throws InterruptedException {
        if (!valueSet) {
            this.wait();
        }
        valueSet = false;
        this.notify();
        return value;
    }

    /**
     * Ein Wert speichern, falls noch keiner gespeicher ist, ansonsten warten.
     * @param value zu speichernder Integer Wert
     * @throws InterruptedException, wenn beim Warten unterbrochen
     */
    public synchronized void put(final int value) throws InterruptedException {
        if (valueSet) {
            this.wait();
        }
        this.value = value;
        valueSet = true;
        this.notify();
    }
}
