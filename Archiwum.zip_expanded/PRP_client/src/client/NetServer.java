package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;


public class NetServer implements AutoCloseable {
	
	private final int portNumber;
	private final ServerSocket socketServer;
	
	public NetServer(int portNumber) throws IOException {
		this.portNumber = portNumber;
		socketServer = new ServerSocket(this.portNumber);
	}
	
	public void run() {
		while(true) {
			try (Socket socket = socketServer.accept()) { // instrukcja blokuj�ca
				boolean isAutoFlush = true;
				PrintWriter response = new PrintWriter(socket.getOutputStream(), isAutoFlush);
				response.println(LocalDateTime.now());
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	@Override
	public void close() throws Exception {
	if (socketServer != null) { socketServer.close(); }
	}
}
