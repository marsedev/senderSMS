/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hightech.senderSMS.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marse
 */
public class GenericDto extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
	
	public boolean containsProperty(String name) {
		return containsKey(name);
	}
	
	public void setProperty(String name, Object value) {
		put(name, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String name, Class<T> type) {
		return (T) get(name);
	}
	
	public String getStringProperty(String name) {
		return getProperty(name, String.class);
	}
	
	public Boolean getBooleanProperty(String name) {
		return getProperty(name, Boolean.class);
	}
	
	public Integer getIntegerProperty(String name) {
		return getProperty(name, Integer.class);
	}
	
	public Long getLongProperty(String name) {
		return getProperty(name, Long.class);
	}
	
	public Double getDoubleProperty(String name) {
		return getProperty(name, Double.class);
	}
		
	@SuppressWarnings("unchecked")
	public <T> List<T> getListProperty(String name, Class<T> type) {
		return (List<T>) get(name);
	}
	
	public List<Object> getListProperty(String name) {
		return getListProperty(name, Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getMapProperty(String name, Class<T> type) {
		return (Map<String, T>) get(name);
	}
	
	public Map<String, Object> getMapProperty(String name) {
		return getMapProperty(name, Object.class);
	}
}
