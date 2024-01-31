package Comunication;

import java.io.IOException;

public interface IO {
    /**
     * Sends a message to the connected client
     * @param message String to send
     * @throws IOException
     */
    void sendMessage(Object message) throws IOException;

    /**
     * Reads the response from the human player or the bot.
     * In the bot case it responds with Yes or the first Character
     */
    String requestResponse(Object message) throws ClassNotFoundException, IOException;

}
