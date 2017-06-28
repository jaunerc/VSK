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

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Der Lamport Prozess empfängt eine Nachricht mit Zeitstempel, wertet diesen
 * aus und setzt entsprechend seine eigene Zeit, wenn nötig. Gemäss Folie 22
 * HSLU Modul VSK - Uhren Synchronisation.
 *
 * @author Fox196 / zadiehl
 */
public final class LamportProcess implements Runnable {

    private Socket socket;
    private int processNumber;
    private int time = 0;
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;
    private JFrame processFrame;
    private JTextField txtSendMessageTo;
    private JPanel clockPanel;
    private JPanel clockRatePanel;
    private JButton btnIncrement;
    private JButton btnDecrement;
    private JLabel clock;
    private JPanel messagePanel;
    private JLabel lblSendMessageTo;
    private JButton btnSend;
    private Timer timer;
    private JTextArea notificationArea;

    private final ActionListener sendMessage = (final ActionEvent e) -> {
        try {
            CommunicationMessage message = new CommunicationMessage(
                    processNumber, time, Integer.parseInt(txtSendMessageTo.getText()));
            toServer.writeObject(message);
        } catch (UnknownHostException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    };

    private final ActionListener incrementClockRate = (final ActionEvent e) -> {
        timer.setDelay(timer.getDelay() / 2);
    };

    private final ActionListener decrementClockRate = (final ActionEvent e) -> {
        timer.setDelay(timer.getDelay() * 2);
    };

    /**
     * Default-Konstruktor.
     */
    public LamportProcess() {
    }

    private int initiateConnection() {
        try {
            socket = new Socket("localhost", 1234);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

            RegisterMessage message = (RegisterMessage) fromServer.readObject();
            processNumber = message.getProcessNumber();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processNumber;
    }

    private void communicate() {
        CommunicationMessage message;
        while (true) {
            try {
                message = (CommunicationMessage) fromServer.readObject();
                notificationArea.append("Got a message from " + message.getSendingProcessNumber() + " with timestamp " + message.getTime() + "\n");
                if (time <= message.getTime()) {
                    time = message.getTime();
                    timer.restart();
                    clockTick.actionPerformed(null);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(LamportProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private final ActionListener clockTick = (final ActionEvent e) -> {
        clock.setText(String.valueOf(time));
        time++;
    };

    @Override
    public void run() {
        processFrame = new JFrame("Lamport Process : " + processNumber);
        processFrame.setLocationByPlatform(true);
        processFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        processFrame.setLayout(new FlowLayout());
        processFrame.setSize(395, 240);
        clockPanel = new JPanel(new FlowLayout());
        clock = new JLabel();
        clock.setFont(new Font("Times New Roman", 0, 50));
        clockTick.actionPerformed(null);
        clockPanel.add(clock);
        clockPanel.setVisible(true);
        clockRatePanel = new JPanel(new GridLayout(2, 1));
        btnIncrement = new JButton("+");
        btnDecrement = new JButton("-");
        btnIncrement.addActionListener(incrementClockRate);
        btnDecrement.addActionListener(decrementClockRate);
        clockRatePanel.add(btnIncrement);
        clockRatePanel.add(btnDecrement);
        clockPanel.add(clockRatePanel);
        processFrame.add(clockPanel);
        messagePanel = new JPanel(new GridLayout());
        lblSendMessageTo = new JLabel("Send a message to ");
        messagePanel.add(lblSendMessageTo);
        txtSendMessageTo = new JTextField();
        txtSendMessageTo.addActionListener(sendMessage);
        messagePanel.add(txtSendMessageTo);
        btnSend = new JButton("Send");
        btnSend.addActionListener(sendMessage);
        messagePanel.add(btnSend);
        processFrame.add(messagePanel);
        notificationArea = new javax.swing.JTextArea(5, 32);
        processFrame.add((new JPanel(new GridLayout(1, 1))).add(new JScrollPane(notificationArea)));
        timer = new Timer(2000, clockTick);
        timer.start();
        processFrame.setVisible(true);
        processNumber = initiateConnection();
        processFrame.setTitle("Lamport Process : " + processNumber);
        communicate();
    }
}
