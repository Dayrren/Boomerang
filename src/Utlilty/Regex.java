package Utlilty;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    /**
     * @param input The string that the regular expression gets applied upon
     * @param regex regular expression
     * @return All the matches to the regex pattern
     */
    public static List<String> matchPattern(String input, String regex) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            // Add the matched substring to the list of matches
            matches.add(matcher.group());
        }

        return matches;
    }
}