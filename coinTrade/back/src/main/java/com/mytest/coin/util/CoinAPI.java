package com.mytest.coin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Response;


public class CoinAPI {
	private static String serverUrl = "https://api.upbit.com";
	private String accessKey = "";
	private String secretKey = "";
	
	//자산 조회
	public JSONArray CoinMarketAll() throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(serverUrl + "/v1/market/all")
		  .get()
		  .addHeader("accept", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		JSONArray jsonArray = new JSONArray(response.body().string());
		return jsonArray;
	}
	
	//분봉 조회
	public JSONArray CoinCandlesMinutes(int minutes, String market, int count) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url(serverUrl + "/v1/candles/minutes/" + minutes + "?market="+market+ "&count="+count )
		  .get()
		  .addHeader("accept", "application/json")
		  .build();

		Response response = client.newCall(request).execute();
		JSONArray jsonArray = new JSONArray(response.body().string());
		return jsonArray;
	}
	
	//구매 함수
	public JSONObject buyMethod(String market, int price, Map<String, String> paramsIn) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	
	    HashMap<String, String> params = new HashMap<>();
	    params.put("market", market);
	    params.put("side", "bid");
	    params.put("price", Integer.toString(price));
	    params.put("ord_type", "price");
	
	    ArrayList<String> queryElements = new ArrayList<>();
	    for(Map.Entry<String, String> entity : params.entrySet()) {
	        queryElements.add(entity.getKey() + "=" + entity.getValue());
	    }
	    
	    String queryString = String.join("&", queryElements.toArray(new String[0]));
	
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    md.update(queryString.getBytes("UTF-8"));
	
	    String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	
	    Algorithm algorithm = Algorithm.HMAC256(paramsIn.get("secretKey"));
	    String jwtToken = JWT.create()
	            .withClaim("access_key", paramsIn.get("accessKey"))
	            .withClaim("nonce", UUID.randomUUID().toString())
	            .withClaim("query_hash", queryHash)
	            .withClaim("query_hash_alg", "SHA512")
	            .sign(algorithm);
	
	    String authenticationToken = "Bearer " + jwtToken;
	
	    try {
	        HttpClient client = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost("https://api.upbit.com/v1/orders");
	        request.setHeader("Content-Type", "application/json");
	        request.addHeader("Authorization", authenticationToken);
	        request.setEntity(new StringEntity(new Gson().toJson(params)));
	        
	        HttpResponse response = client.execute(request);
	        HttpEntity entity = response.getEntity();
	      
	        String data = EntityUtils.toString(entity, "UTF-8");   
            
	        JSONObject jsonObject = new JSONObject(data);
            
            return jsonObject;
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * 
	 * @param searchParams
	 * @return
	 */
	//구매나 판매정보 조회 함수
	public JSONObject order(String uuid, Map<String, String> paramsIn) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        HashMap<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(paramsIn.get("secretKey"));
        String jwtToken = JWT.create()
                .withClaim("access_key", paramsIn.get("accessKey"))
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("https://api.upbit.com/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            String data = EntityUtils.toString(entity, "UTF-8");   
            
	        JSONObject jsonObject = new JSONObject(data);

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * upbit에서 markets의 현재가격을 가져온다
	 * @param searchParams
	 * @return
	 */
	public JSONArray CurrentCoinPrice(String markets) throws IOException {
		OkHttpClient client = new OkHttpClient();
		
		okhttp3.Request request = new okhttp3.Request.Builder()
		  .url("https://api.upbit.com/v1/ticker?markets=" + markets)
		  .get()
		  .addHeader("accept", "application/json")
		  .build();
		
		Response response = client.newCall(request).execute();
		JSONArray jsonArray = new JSONArray(response.body().string());
		
		return jsonArray;
	}
}
