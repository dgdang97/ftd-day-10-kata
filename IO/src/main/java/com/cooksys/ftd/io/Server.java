package com.cooksys.ftd.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server implements Runnable {

	private Logger log = LoggerFactory.getLogger(Server.class);
	
	private Set<Socket> clients;
	private int port;
	private static String output;
	
	public Server(int port, String output) {
		super();
		this.port = port;
		this.clients = new HashSet<>();
		Server.output = output;
	}
	
	public static String writeOutput(String input) throws Exception {
		try (FileInputStream in = new FileInputStream(input); FileOutputStream out = new FileOutputStream(Server.output);) {
		int c;
		while((c = in.read()) != -1) {
			out.write(c);
			}
		return "Success!";
		}
	}



	@Override
	public void run() {
		try (ServerSocket ss = new ServerSocket(this.port);) {
			while (true) {
				Socket client = ss.accept();
				clients.add(client);
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
				ServerProtocol protocol = new ServerProtocol(reader, writer);
				new Thread(protocol).start();
			}
		} catch (IOException e) {
			log.error("server fail! oh noes :(", e);
		} finally {
			for (Socket client : this.clients) {
				try {
					client.close();
				} catch (IOException e) {
					log.warn("error while closing client socket", e);
				}
			}
		}
	}

}
