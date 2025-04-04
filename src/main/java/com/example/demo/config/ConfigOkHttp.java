package com.example.demo.config;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ConfigOkHttp {
    private static final String URL = "https://www.weavy.com/docs/reference/api/users";

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

                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String listUsers() throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public String getUser(String id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
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
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public String deleteUser(String id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/" + id)
                .delete()

                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}