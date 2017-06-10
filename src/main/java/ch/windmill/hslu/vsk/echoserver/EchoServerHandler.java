package ch.windmill.hslu.vsk.echoserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a simple echo server handler.
 */
public class EchoServerHandler implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(EchoServerHandler.class.getName());

	private final Socket clientSocket;

	public EchoServerHandler(final Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

			LOGGER.log(Level.INFO, "New connection");
			processInputMessages(reader, writer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processInputMessages(final BufferedReader reader, final BufferedWriter writer) throws IOException {
		String input = null;
		while ((input = reader.readLine()) != null) {
			LOGGER.log(Level.INFO, "Message received: " + input);
			writeStringMessage("ECHO: " + input, writer);
		}
		LOGGER.log(Level.INFO, "Connection terminated...");
	}

	private void writeStringMessage(final String message, final BufferedWriter writer) throws IOException {
		writer.write(message);
		writer.flush();
	}
}
