package com.cooksys.ftd.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements Runnable {

	private Logger log = LoggerFactory.getLogger(Client.class);

	private String hostname;
	private int port;
	private String[] messages;

	public Client(String hostname, int port, String... messages) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.messages = messages;
	}

	public void fileTransfer() throws Exception {
		try {
			String file = "C:\\Code\\Day 10\\IO\\input.txt";
			log.info("Sending file");
			try (FileInputStream in = new FileInputStream(file)) {
				log.info("File Stream Started");
				int c;
				while((c = in.read()) != -1) {
					Server.writeOutput(c);
					}
				log.info("Success!");
			}
		}  catch (Exception e) {
			log.error("ERROR: File Transfer Failed", e);
		}
	}
	
	@Override
	public void run(){
		try (Socket client = new Socket(this.hostname, this.port);
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter writer = new PrintWriter(client.getOutputStream(), true);) {


			// send a bunch of messages and read the responses
			for (String message : this.messages) {
				log.info("Sending server message: {}", message);
				writer.println(message);
				log.info("Server responded with message: {}", reader.readLine());
			}
			this.fileTransfer();
		} catch (IOException e) {
			log.error("client fail! oh noes :(", e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

