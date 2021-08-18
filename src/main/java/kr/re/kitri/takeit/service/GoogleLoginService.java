package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GoogleLoginService {

    // 토큰요청
    // https://developers.google.com/identity/protocols/oauth2/openid-connect#exchangecode
    public String getAccessToken(String permisiveCode) {
	String googleGetTokenUrl = "https://oauth2.googleapis.com/token";
	String id_Token = "";
	String access_Token = "";
	// String refresh_Token = ""; // 이 필드는 인증 요청 에서 access_type 매개 변수가 offline 으로
	// 설정된 경우에만 존재

	try {
	    URL url = new URL(googleGetTokenUrl);
	    String bodyData = "grant_type=authorization_code";
	    bodyData += "&client_id=134711181820-tjdlp7ug9hegle7hmhnir9qkdrad5c0p.apps.googleusercontent.com";
	    bodyData += "&client_secret=tZfpHrgKkH6JrL9DsZYrIiKK";
	    bodyData += "&redirect_uri=http://localhost:8080/takeit_prj/login/google";
	    // bodyData += "&state=takeit_googlelogin";
	    bodyData += "&code=" + permisiveCode;

	    // Stream 연결
	    // Post요청, x-www-form-urlencoded
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	    conn.setRequestMethod("POST");
	    // http header 값 넣기
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setDoOutput(true);

	    // request 하기
	    BufferedWriter bw;
	    bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
	    bw.write(bodyData);
	    bw.flush();
	    bw.close();

	    // 결과코드 확인
	    int responseCode = conn.getResponseCode();
	    System.out.println("responseCode: " + responseCode);

	    // response JSON타입 메세지 가져오기
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    String input = "";
	    StringBuilder sb = new StringBuilder();
	    while ((input = br.readLine()) != null) {
		sb.append(input);
	    }
	    br.close();

	    // 특정 토큰만 return, 전체 return은 마지막에 참고
	    JsonParser parser = new JsonParser();
	    JsonElement element = parser.parse(sb.toString());
	    id_Token = access_Token = element.getAsJsonObject().get("id_token").getAsString();

	    // access_Token = element.getAsJsonObject().get("access_token").getAsString();
	    // refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	    // System.out.println("access_token : " + access_Token);
	    // System.out.println("refresh_token : " + refresh_Token);

	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// 전체 데이터 Gson으로 파싱
	// Gson gson = new Gson();
	// return gson.toJson(sb.toString());
	// accessToken만 리턴
	return access_Token;
    } // getAccessToken end

    public HashMap<String, String> getUserInfo(String idToken) {// 유저 프로필url,닉네임,id를 가져온다

	// return값
	// google은 accessToken과 id_token에 값을 같이 담아서 준다
	// id_token에 값을 decode해 정보를 얻는다.

	HashMap<String, String> resultUserInfo = new HashMap<>();

	try {
	    JwtService jwtdecoder = new JwtService();
	    String userInfoJwt = jwtdecoder.decode(idToken);

	    JsonParser parser = new JsonParser();
	    JsonElement element = parser.parse(userInfoJwt);

	    String id = element.getAsJsonObject().get("email").getAsString();
	    String nickname = element.getAsJsonObject().get("name").getAsString();
	    String thumnailURL = element.getAsJsonObject().get("picture").getAsString();

	    resultUserInfo.put("id", id);
	    resultUserInfo.put("nickname", nickname);
	    resultUserInfo.put("thumnailURL", thumnailURL);

	} catch (NullPointerException e) {
	    e.printStackTrace();
	}

	return resultUserInfo;
    } // getUserInfo end
}
