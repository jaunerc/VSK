package ch.windmill.hslu.vsk.echoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class represents a simple tcp echo server.
 */
public class EchoServer implements Runnable {
	
	private final ServerSocket serverSocket;
	private final ExecutorService pool;
	private boolean isRunning;
	
	public EchoServer(final int port) throws IOException {
		serverSocket = new ServerSocket(port);
		pool = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {
		isRunning = true;
		try {
			while (isRunning) {
				// Starts a new handler for each connection
				// and executes the handler over a thread pool.
				pool.execute(new EchoServerHandler(serverSocket.accept()));
			}
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}

	public void setRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
