package app;

import unitunes.UniTunes;

public class Main {
	public static void main(String[] args) {
		UniTunes unitunes = new UniTunes();
		unitunes.start();	
		unitunes.close();
	}
}
