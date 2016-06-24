package com.cooksys.ftd.io;

public class MainServer {

	public static void main(String[] args) throws InterruptedException {
		String output = "C:\\Code\\Day 10\\IO\\output.txt";
		Server server = new Server(667, output);

		new Thread(server).start();

	}

}
