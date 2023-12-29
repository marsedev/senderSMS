/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hightech.senderSMS.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author marse
 */
public interface messageSendService {
    
    public String enviarSMS(String GC, String fecEmi,String phoneNumber);
}
