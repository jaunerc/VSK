package ch.windmill.hslu.vsk.echoserver;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides a demo execution of the EchoServer.
 */
public class DemoEchoServer {

	private static final int DEFAULT_SERVER_PORT = 7777;

	/**
	 * Private constructor.
	 */
	private DemoEchoServer() {

	}

	public static void main(String[] args) throws IOException {
		int serverPort = parsePort(args);
		final ExecutorService executorService = Executors.newSingleThreadExecutor();
		Logger.getLogger(DemoEchoServer.class.getName()).log(Level.INFO, "Start new Server on Port " + serverPort);
		executorService.execute(new EchoServer(serverPort));
	}

	private static int parsePort(final String[] args) {
		int serverPort = DEFAULT_SERVER_PORT;
		if (args.length > 0) {
			try {
				serverPort = Integer.parseInt(args[0]);
				if (serverPort < 1 || serverPort > 65535) {
					throw new NumberFormatException("Portnumber is out of range.");
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument " + args[0] + " must be a valid port number.");
			}
		}
		return serverPort;
	}
}