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

public class NaverLoginService {
    public String getAccessToken(String permisiveCode) {
	String naverGetTokenUrl = "https://nid.naver.com/oauth2.0/token";
	String access_Token = "";
//	String refresh_Token = "";

	try {
	    URL url = new URL(naverGetTokenUrl);
	    String bodyData = "grant_type=authorization_code";
	    bodyData += "&client_id=_DR4yv1dxw5JqPZv2e8g";
	    bodyData += "&client_secret=WT3qe4X8vh";
	    bodyData += "&redirect_uri=http://localhost:8080/takeit_prj/login/naver";
	    bodyData += "&state=takeit_naverlogin";
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

//	     response JSON타입 메세지 가져오기
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
	    access_Token = element.getAsJsonObject().get("access_token").getAsString();
//	    refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//	    System.out.println("access_token : " + access_Token);
//	    System.out.println("refresh_token : " + refresh_Token);

	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// 전체 데이터 Gson으로 파싱
//	Gson gson = new Gson();
//	return gson.toJson(sb.toString());
	// accessToken만 리턴
	return access_Token;
    } // getAccessToken end

    public HashMap<String, String> getUserInfo(String accessToken) {// 유저 프로필url,닉네임,id를 가져온다

//	네이버는 accessToken만 넘겨주면 전체 데이터를 준다.

	String naverGetUserInfoUrl = "https://openapi.naver.com/v1/nid/me";
	HashMap<String, String> resultUserInfo = new HashMap<>();

	try {
	    URL url = new URL(naverGetUserInfoUrl);

	    // Stream 연결
	    // Post요청, x-www-form-urlencoded
	    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	    conn.setRequestMethod("POST");
	    // 요청에 필요한 Header에 포함될 내용
	    conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	    // http header 값 넣기
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setDoOutput(true);

	    // request 하기
	    BufferedWriter bw;
	    bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//	    bw.write(bodyData);
	    bw.flush();
	    bw.close();

	    // 결과코드 확인
	    int responseCode = conn.getResponseCode();
	    System.out.println("responseCode: " + responseCode);

////	     response JSON타입 메세지 가져오기
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    String input = "";
	    StringBuilder sb = new StringBuilder();
	    while ((input = br.readLine()) != null) {
		sb.append(input);
	    }
	    br.close();
//	    response 확인
//	    System.out.println(sb.toString());

	    JsonParser parser = new JsonParser();
	    JsonElement element = parser.parse(sb.toString());

	    String id = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
	    String nickname = element.getAsJsonObject().get("response").getAsJsonObject().get("name").getAsString();
	    String thumnailURL = element.getAsJsonObject().get("response").getAsJsonObject().get("profile_image")
		    .getAsString();

	    resultUserInfo.put("id", id);
	    resultUserInfo.put("nickname", nickname);
	    resultUserInfo.put("thumnailURL", thumnailURL);

	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (NullPointerException e) {
	    e.printStackTrace();
	}

	return resultUserInfo;
    } // getUserInfo end

}
