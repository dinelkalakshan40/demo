package com.example.demo.controller;


import com.example.demo.config.ConfigOkHttp;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class Controller {
    private final ConfigOkHttp weavyService;


    public Controller(ConfigOkHttp weavyService) {
        this.weavyService = weavyService;
    }


    @PostMapping
    public String createUser(@RequestBody Map<String, String> user) throws IOException {
        return weavyService.createUser(user.get("uid"), user.get("name"));
    }


    @GetMapping
    public String listUsers() throws IOException {
        return weavyService.listUsers();
    }


    @GetMapping("/{id}")
    public String getUser(@PathVariable String id) throws IOException {
        return weavyService.getUser(id);
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody Map<String, String> user) throws IOException {
        return weavyService.updateUser(id, user.get("name"));
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) throws IOException {
        return weavyService.deleteUser(id);
    }

}
