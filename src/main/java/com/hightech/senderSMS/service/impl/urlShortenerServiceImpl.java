/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hightech.senderSMS.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hightech.senderSMS.service.urlShortenerService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marse
 */
@Slf4j
@Service
public class urlShortenerServiceImpl implements urlShortenerService{

    @Override
    public String acortarUrl(String url){
        String urlCorta = "";
        Map<String, String> properties = new HashMap<>();
        properties.put("url", url);
        String uri = "https://api.encurtador.dev/encurtamentos";
        HttpEntity<Map<String, String>> request = new HttpEntity<>(properties);
        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.postForObject(uri, request, String.class);
        JsonObject jsonObject = new Gson().fromJson(Response, JsonObject.class);
        JsonElement urlElement = jsonObject.get("urlEncurtada");
        urlCorta = urlElement.toString();
        urlCorta = urlCorta.substring(1, urlCorta.length()-1);
        return urlCorta;
    }
    
}
