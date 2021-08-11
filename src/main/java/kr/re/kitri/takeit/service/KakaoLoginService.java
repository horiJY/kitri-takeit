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

public class KakaoLoginService {

    public String getAccessToken(String permisiveCode) {
	String kakaoGetTokenUrl = "https://kauth.kakao.com/oauth/token";
	String access_Token = "";
//	String refresh_Token = "";

	try {
	    URL url = new URL(kakaoGetTokenUrl);
	    String bodyData = "grant_type=authorization_code&";
	    bodyData += "client_id=ba37492513672ce5ce23c00ff86bd01d&";
	    bodyData += "redirect_uri=http://localhost:8080/takeit_prj/login/kakao&";
	    bodyData += "code=" + permisiveCode;
	    // 보안강화를 위한 client_secret
//	    bodyData += "client_secret=GZbFxa9pD2g6cmMa0bfRHLuOPIJrfrCY";

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

//	https://kapi.kakao.com/v2/user/me
//	    -H "Authorization: Bearer {ACCESS_TOKEN}"
//	    secure_resource=true&
//	    property_keys=["properties.nickname","properties.thumbnail_image","kakao_account.email"]
//

	String kakaoGetUserInfoUrl = "https://kapi.kakao.com/v2/user/me";
	HashMap<String, String> resultUserInfo = new HashMap<>();

	try {
	    URL url = new URL(kakaoGetUserInfoUrl);
	    String bodyData = "secure_resource=true";
	    bodyData += "&property_keys=[\"properties.nickname\",\"properties.thumbnail_image\",\"kakao_account.email\"]";

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
	    bw.write(bodyData);
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

	    String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname")
		    .getAsString();
	    String thumnailURL = element.getAsJsonObject().get("properties").getAsJsonObject().get("thumbnail_image")
		    .getAsString();
	    // email은 비즈앱 동의 필요, 대안으로 id를 가져와 앞에 k붙여 식별
	    String id = "k" + element.getAsJsonObject().get("id").getAsString();

	    resultUserInfo.put("id", id);
	    resultUserInfo.put("nickname", nickname);
	    resultUserInfo.put("thumnailURL", thumnailURL);

	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return resultUserInfo;
    } // getUserInfo end

}
