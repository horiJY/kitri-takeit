package service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;

public class JwtService {
  public String decode(String token) {

    String[] splitToken = token.split("\\.");
    Decoder decoder = Base64.getDecoder();
    byte[] decodedBytes = decoder.decode(splitToken[1]);

    String decodedString = null;
    try {
      decodedString = new String(decodedBytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return decodedString;

  }
}
