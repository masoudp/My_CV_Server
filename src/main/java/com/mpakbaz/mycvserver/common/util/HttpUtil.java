package com.mpakbaz.mycvserver.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

public class HttpUtil {
    public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }


    public static String makeRequest(String myUrl, String httpMethod, Map<String, String> parameters) {

        URL url = null;
        try {
            url = new URL(myUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setReadTimeout(10000);


        int respCode;
        String inputString = null;
        try {
            conn.setRequestMethod(httpMethod);

            if (Objects.nonNull(parameters))
                for (Object key : parameters.keySet())
                    conn.setRequestProperty(key.toString(), parameters.get(key));


            respCode = conn.getResponseCode();
            if (respCode != 200 && respCode != 201) {
                String error = inputStreamToString(conn.getErrorStream());
                return error;
            }
            inputString = inputStreamToString(conn.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("HttpRequestFail");
        }
        return inputString;
    }

    public static String inputStreamToString(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
