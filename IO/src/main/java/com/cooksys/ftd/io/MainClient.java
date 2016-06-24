package com.cooksys.ftd.io;

import java.util.ArrayList;
import java.util.List;

public class MainClient {
	public static void main(String[] args) throws InterruptedException {

		List<Thread> clientThreads = new ArrayList<Thread>();

		clientThreads.add(new Thread(new Client("File", 667, "Starting Connection", "Connection Created")));

		for (Thread t : clientThreads) {
			t.start();
		}

		for (Thread t : clientThreads) {
			t.join();
		}

	}
}
