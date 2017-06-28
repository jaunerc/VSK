package ch.windmill.hslu.vsk.communication.clock;

/**
 * Nachricht, welche von simuliertem Prozess zu Prozess gesendet wird.
 */
public final class CommunicationMessage implements Message {

    private static final long serialVersionUID = 4748146019417283829L;

    private final int sendingProcessNumber;
    private final int time;
    private final int receivingProcessNumber;

    /**
     * Erzeugt eine Nachricht.
     *
     * @param sendingProcessNumber Prozessnummer des aktuellen Lamport
     * Prozesses.
     * @param time Zeit des aktuellen Lamport Prozesses.
     * @param receivingProcessNumber Prozessnummer des Lamport Prozesses, der
     * die Nachricht gesendet hat.
     */
    public CommunicationMessage(final int sendingProcessNumber, final int time, final int receivingProcessNumber) {
        this.sendingProcessNumber = sendingProcessNumber;
        this.time = time;
        this.receivingProcessNumber = receivingProcessNumber;
    }

    /**
     * Gibt die Prozessnummer des aktuellen Lamport Prozesses zurück.
     *
     * @return Prozessnummer.
     */
    public int getSendingProcessNumber() {
        return sendingProcessNumber;
    }

    /**
     * Gibt die aktuelle Zeit des aktuellen Lamport Prozesses zurück.
     *
     * @return Zeit
     */
    public int getTime() {
        return time;
    }

    /**
     * Gibt die Prozessnummer des Lamport Prozesses zurück, von dem die
     * Nachricht stammt.
     *
     * @return Prozessnummer.
     */
    public int getReceivingProcessNumber() {
        return receivingProcessNumber;
    }

}
