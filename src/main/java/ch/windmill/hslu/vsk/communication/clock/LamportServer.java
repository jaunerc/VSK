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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Der Lamport Server ist die zentrale Instanz, welche die erzeugten Lamport
 * Prozesse beinhaltet. Er erzeugt die Lamport Prozesse und verteilt die
 * eingehenden Nachrichten an die adressierten Lamport Prozesse.
 */
public final class LamportServer implements Runnable {

    private int numOfClients = 0;
    private final List<ObjectOutputStream> outputConnections = new ArrayList<>();
    private ServerSocket socket;
    private Socket tempSocket;
    private final JFrame serverFrame;
    private final JFrame addProcFrame;
    private final JPanel serverPanel;
    private final JLabel lblClients;
    private final JTextArea txtNotifications;
    private final JScrollPane scrollPane;

    /**
     * Default-Konstruktor, der zwei Fenster der Lamport Clock Simulation
     * öffnet. Das Mediator Fenster enthält die erzeugten und registrierten
     * Lamport Prozesse. Das Lamport Fenster ermöglicht die Erzeugung eines
     * neuen Lamport Prozesses mit Hilfe eines Buttons.
     */
    public LamportServer() {
        serverFrame = new JFrame("Mediator");
        serverFrame.setLocationByPlatform(true);
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setSize(225, 200);

        serverPanel = new javax.swing.JPanel();
        serverPanel.setLayout(new java.awt.GridLayout(0, 1));

        lblClients = new javax.swing.JLabel();
        lblClients.setFont(new Font("Times New Roman", 0, 21));
        updateClientCount();

        txtNotifications = new JTextArea(4, 16);
        txtNotifications.setEditable(false);
        scrollPane = new JScrollPane(txtNotifications);
        scrollPane.setSize(180, 150);

        serverPanel.add(lblClients);
        serverPanel.add(scrollPane);
        serverFrame.add(serverPanel);
        serverFrame.setVisible(true);

        addProcFrame = new JFrame("Lamport");
        addProcFrame.setSize(125, 75);
        addProcFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton btnAddProcess = new JButton("Add Process");
        btnAddProcess.addActionListener(new ActionListenerImpl());
        addProcFrame.add(btnAddProcess);
        addProcFrame.setVisible(true);
    }

    @Override
    public void run() {
        ObjectOutputStream tempObjectOutputStream;
        try {
            socket = new ServerSocket(1234);
            while (true) {
                try {
                    tempSocket = socket.accept();
                    tempObjectOutputStream = new ObjectOutputStream(tempSocket.getOutputStream());
                    tempObjectOutputStream.writeObject(new RegisterMessage(++numOfClients));
                    txtNotifications.append("Registered process number " + numOfClients + "\n");
                    updateClientCount();
                    this.outputConnections.add(tempObjectOutputStream);

                    (new Thread(new ServerThread(this, tempSocket))).start();
                } catch (IOException ex) {
                    Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LamportServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateClientCount() {
        this.lblClients.setText("Number of Processes: " + numOfClients);
    }

    /**
     * Gibt die Liste der OutputStreams zu den einzelnen Lamport Prozessen
     * zurück.
     *
     * @return ArrayList von OutputStreams.
     */
    public List<ObjectOutputStream> getOutputConnections() {
        return outputConnections;
    }

    private static class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Thread(new LamportProcess()).start();
        }
    }
}
