package Main;

import Content.Australia.Australia;
import Game.Client;
import Game.Server;

import static Content.ContentManager.setContentPack;

public class BoomerangAustralia {

	private static boolean isValidPlayerCount(int numPlayers, int numBots) {
		int totalPlayers = numPlayers + numBots;
		return totalPlayers >= 2 && totalPlayers <= 4;
	}

	private static void startServer(int numPlayers, int numBots) {
		new Server(numPlayers, numBots);
	}

	private static void startClient(String ipAddress) {
		Client.client(ipAddress);
	}

	private static void displayInvalidInputMessage() {
		System.out.println("Invalid input. This game is for 2-4 players/bots.");
		System.out.println("Server syntax: java Main.BoomerangAustralia numPlayers numBots ContentPack(optional)");
		System.out.println("Client syntax: IP");

	}


	public static void main(String[] params) {

		if (params.length >= 3) {
			if (params[2].equalsIgnoreCase("australia"))
				setContentPack(new Australia());
			else {
				displayInvalidInputMessage();
				throw new Error("invalid ContentPack");
			}
		}


		if (params.length >= 2) {
			int numPlayers = Integer.parseInt(params[0]);
			int numBots = Integer.parseInt(params[1]);

			if (isValidPlayerCount(numPlayers, numBots)) {
				startServer(numPlayers, numBots);
			} else {
				displayInvalidInputMessage();
				throw new Error("invalid player range");
			}
		} else if (params.length == 1) {
			startClient(params[0]);
		} else {
			displayInvalidInputMessage();
			throw new Error("invalid params");
		}
	}
}