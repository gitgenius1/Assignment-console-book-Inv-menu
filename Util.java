import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Util {

  public static HashMap<String, String> requestStringToMap(String request) {
    HashMap<String, String> map = new HashMap<String, String>();
    String[] pairs = request.split("&");
    for (int i = 0; i < pairs.length; i++) {
      String pair = pairs[i];

      try {
        String key = pair.split("=")[0];
        key = URLDecoder.decode(key, "UTF-8");

        String value = pair.split("=")[1];
        value = URLDecoder.decode(value, "UTF-8");

        map.put(key, value);
      } catch (UnsupportedEncodingException e) {
        System.err.println(e.getMessage());
      }

    }
    return map;
  }
}
