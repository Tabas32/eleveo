package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static int parseFirstNumber(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            return 0;
        }
    }
}
