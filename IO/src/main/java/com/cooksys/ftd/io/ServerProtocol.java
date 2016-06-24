package com.cooksys.ftd.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerProtocol implements Runnable {

	private Logger log = LoggerFactory.getLogger(ServerProtocol.class);

	private BufferedReader reader;
	private PrintWriter writer;

	public ServerProtocol(BufferedReader reader, PrintWriter writer) throws IOException {
		super();
		this.reader = reader;
		this.writer = writer;
	}

	@Override
	public void run() {
		try {
			String line;
			while ((line = this.reader.readLine()) != null) {
				this.writer.println(line);
			}
		} catch (IOException e) {
			log.error("client fail! oh noes :(", e);
		}
	}

}
