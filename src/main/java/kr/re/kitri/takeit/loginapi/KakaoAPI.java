package loginapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class KakaoAPI {
    public String getAccessToken(String authorize_code) {
	String access_Token = "";
	String refresh_Token = "";
	String reqURL = "kauth.kakao.com/oauth/token";
//	String reqURL = "https://kauth.kakao.com/oauth/token";
	System.out.println("KAKAOAPI 받은 코드 : " + authorize_code);

	try {
	    // URL 설정 접속
	    URL url = new URL(reqURL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	    // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	    conn.setRequestMethod("POST");
	    conn.setDoOutput(true);

	    // 헤더세팅
//	    conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");

	    // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	    StringBuilder sb = new StringBuilder();
	    sb.append("grant_type=authorization_code");
	    sb.append("&client_id=ba37492513672ce5ce23c00ff86bd01d");
	    sb.append("&redirect_uri=http://localhost:8080/login/kakao");
	    sb.append("&code=" + authorize_code);
//	    System.out.println("Token 요청 주소" + reqURL + sb.toString());
	    bw.write(sb.toString());
	    bw.flush();

	    // 결과 코드가 200이라면 성공
	    int responseCode = conn.getResponseCode();
	    String responseType = conn.getContentType();
	    String bodyMessage = conn.getResponseMessage();
	    System.out.println("responseCode : " + responseCode);
	    System.out.println("responseType : " + responseType);
	    System.out.println("responseMessage : " + bodyMessage);

	    // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	    BufferedReader br = new BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
	    String line = "";
	    String result = "";

	    while ((line = br.readLine()) != null) {
		result += line;
	    }
	    System.out.println("response body : " + result);

//	    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	    JsonParser parser = new JsonParser();
	    JsonElement element = parser.parse(result);

	    access_Token = element.getAsJsonObject().get("access_token").getAsString();
	    refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

	    System.out.println("access_token : " + access_Token);
	    System.out.println("refresh_token : " + refresh_Token);

	    br.close();
	    bw.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return access_Token;
    }
}
