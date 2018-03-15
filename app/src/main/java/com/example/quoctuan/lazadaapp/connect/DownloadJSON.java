package com.example.quoctuan.lazadaapp.connect;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by quoctuan on 09/03/2018.
 */

public class DownloadJSON extends AsyncTask<String,Void,String>{

    private String str_url = "";
    private List<HashMap<String, String>> attrs = new ArrayList<>();
    private boolean method = true;

    public DownloadJSON(String url) {
        this.str_url = url;
        method = true;
    }

    public DownloadJSON(String url, List<HashMap<String, String>> attrs) {
        this.str_url = url;
        this.attrs = attrs;
        method = false;
    }

    @Override
    protected String doInBackground(String... strings) {
        String str_data = "";
        try {
            URL url = new URL(str_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (!method) {
                str_data = methodPost(conn);
            }else {
                str_data = methodGet(conn);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("data", str_data);
        return str_data;
    }

    private String methodGet(HttpURLConnection conn) {
        String str_data ="";
        InputStream input;
        try {
            conn.connect();
            input = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer bufferData = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                bufferData.append(line);
            }
            str_data = bufferData.toString();
            bufferedReader.close();
            reader.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return str_data;
    }

    private String methodPost(HttpURLConnection conn) {

        String str_data = "";
        String key = "";
        String value = "";

        try {
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            Uri.Builder builder = new Uri.Builder();
            int count = attrs.size();
            for (int i = 0; i < count; i++) {

                for (Map.Entry<String, String> values : attrs.get(i).entrySet()) {
                    key = values.getKey();
                    value = values.getValue();
                }
                builder.appendQueryParameter(key, value);
            }

            String query = builder.build().getEncodedQuery();
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            writer.write(query);
            writer.flush();

            writer.close();
            streamWriter.close();
            outputStream.close();

            str_data = methodGet(conn);

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str_data;
    }
}
