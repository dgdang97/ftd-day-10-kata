package com.cooksys.ftd.io;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		String output = "C:\\Code\\Day 10\\IO\\output.txt";
		Server server = new Server(667, output);

		new Thread(server).start();

		List<Thread> clientThreads = new ArrayList<Thread>();

		clientThreads.add(new Thread(new Client("localhost", 667, "Starting Connection", "Connection Created")));

		for (Thread t : clientThreads) {
			t.start();
		}

		for (Thread t : clientThreads) {
			t.join();
		}

	}

}
