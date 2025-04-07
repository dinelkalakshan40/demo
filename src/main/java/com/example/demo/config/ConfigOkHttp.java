package com.example.demo.config;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ConfigOkHttp {
    private static final String URL = "https://8015b5dbc0724d38882ac90397c27649.weavy.io/api/users";
    private final String TOKEN = "wys_iIaS4EpdQ5tHqr3oi5wt0FI7ABqKR20IQASx";
    
    private final OkHttpClient client = new OkHttpClient();

    public String createUser(String uid, String name) throws IOException {
        String json = """
                {
                  "uid": "%s",
                  "name": "%s"
                }
                """.formatted(uid, name);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .addHeader("Authorization", "Bearer" + TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String listUsers() throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String getUser(String id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String updateUser(String id, String name) throws IOException {
        String json = """
                {
                  "name": "%s"
                }
                """.formatted(name);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .patch(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String deleteUser(String id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .addHeader("Authorization", "Bearer" + TOKEN)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}