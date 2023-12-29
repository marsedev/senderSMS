/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hightech.senderSMS.consumer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hightech.senderSMS.exception.ResourceNotFoundException;
import com.hightech.senderSMS.service.messageSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 * @author marse
 */
@Slf4j
@Component
public class Consumer {
    
    @Autowired
    messageSendService msService;
    
    @RabbitListener(queues = {"${mercosal.queue.name}"})
    public void receiver(@Payload String message){
        JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
        JsonObject dataObject = jsonObject.getAsJsonObject("data");
        JsonObject receptor = dataObject.getAsJsonObject("receptor");
        JsonObject identificacion = dataObject.getAsJsonObject("identificacion");
        JsonElement GC = identificacion.get("codigoGeneracion");
        JsonElement fecEmi = identificacion.get("fecEmi");
        JsonElement telefono = receptor.get("telefono");
        msService.enviarSMS(cleanJsonElement(GC),formatFecEmi(fecEmi), formatPhoneNumber(telefono));
    }
    
    private String cleanJsonElement(JsonElement element){
        if(element != null){
            String result = element.toString();
            result = result.substring(1, result.length()-1);
            return result;
        }
        return null;
    }
    
    private String formatPhoneNumber(JsonElement phoneNumber){
        if(phoneNumber != null){
            String result = phoneNumber.toString();
            result = result.substring(1, result.length()-1);
            result = "503" + result;
            return result;
        }
        return null;
    }
    
    private String formatFecEmi(JsonElement phoneNumber){
        if(phoneNumber != null){
            String result = phoneNumber.toString();
            result = result.substring(1, result.length()-1);
            return result.replace('-', '/');
        }
        return null;
    }
}
