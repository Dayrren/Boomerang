package Comunication;
import Utlilty.Regex;

import java.util.ArrayList;
import java.util.List;

public class IOBot implements IO {


    @Override
    public void sendMessage(Object message) {
        //dummy method, does nothing
    }

    @Override
    public String requestResponse(Object message) {
        List<String> options = new ArrayList<>();
        if (message.toString().contains("card"))
            options = Regex.matchPattern((String) message, "(?<=\\[).?(?=\\])");
        if (message.toString().contains("Activity"))
            options = Regex.matchPattern((String) message, "\\b(\\w*[A-Za-z]\\w*)\\b");

        if (!options.isEmpty()){
            return options.get(0);
        }
        System.out.println("no valid choice");
        return "NO";
    }
}

