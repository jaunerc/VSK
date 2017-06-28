/* 
 * Copyright 2017 Hochschule Luzern - Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.windmill.hslu.vsk.communication.clock;

/**
 * Nachricht für die Anzeige der Prozessnummer beim Lamport Prozess.
 */
public final class RegisterMessage implements Message {

    private static final long serialVersionUID = 3496568363825201666L;
    private int processNumber = 0;

    /**
     * Erzeugt eine Nachrichten Registrierung.
     *
     * @param processNumber Prozessnummer des Lamport Prozesses.
     */
    public RegisterMessage(final int processNumber) {
        this.processNumber = processNumber;
    }

    /**
     * Default-Konstruktor.
     */
    public RegisterMessage() {
    }

    /**
     * Gibt die Prozessnummer des Lamport Prozesses zurück.
     *
     * @return Prozessnummer.
     */
    public int getProcessNumber() {
        return processNumber;
    }
}
