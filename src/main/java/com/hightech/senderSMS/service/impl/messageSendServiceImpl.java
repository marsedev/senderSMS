/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hightech.senderSMS.service.impl;


import com.hightech.senderSMS.service.messageSendService;
import com.hightech.senderSMS.service.urlShortenerService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author marse
 */
@Slf4j
@Service
public class messageSendServiceImpl implements messageSendService {

    @Autowired
    urlShortenerService urlShortener;
    
    @Override
    public String enviarSMS(String GC, String fecEmi, String phoneNumber) {
        String message = "Mercosal le envía su documento tributario electrónico.\n";
        String jsonUrl = "https://sefuatpbs.hightech-corp.com/Storage/"+ fecEmi +"/"+ GC +".json";
        String pdfUrl = "https://sefuatpbs.hightech-corp.com/Storage/"+ fecEmi +"/"+ GC +".pdf";
        String jsonCorto = urlShortener.acortarUrl(jsonUrl);
        String pdfCorto = urlShortener.acortarUrl(pdfUrl);
        message = message + "\nJson: " + jsonCorto + "\nPdf: " + pdfCorto;
        Map<String, String> properties = new HashMap<>();
        properties.put("usuario", "Mercosal");
        properties.put("contrasena", "Ms@l56");
        properties.put("numero", phoneNumber);
        properties.put("mensaje", message);
        properties.put("remitente", "Mercosal");
        String uri = "https://smscorp.tigo.com.sv/wsAPISmsCorp.asmx/enviarSMS";
        HttpEntity<Map<String, String>> request = new HttpEntity<>(properties);
        RestTemplate restTemplate = new RestTemplate();
        String Response = restTemplate.postForObject(uri, request, String.class);
        log.info(Response);
        return Response;
    }

}
