package com.github.perryvaldez.sebooks.utilities;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.perryvaldez.seebooks.exceptions.DeserializeOperationException;
import com.github.perryvaldez.seebooks.exceptions.InvokeReadMethodException;
import com.github.perryvaldez.seebooks.exceptions.SerializeOperationException;
import com.github.perryvaldez.seebooks.forms.FormPersistable;

public final class FormUtils {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(FormUtils.class);
	
	private static boolean isRegularMethodName(String methodName) {
		return !(methodName.equals("getSerializedOrigValues") || methodName.equals("getClass"));
	}
	
	private static String derivePropertyName(String methodName) {
		String propName = null;
		int propNameOffset = 0;
		
		if(methodName.startsWith("get")) {
			propNameOffset = 3;
		} else if (methodName.startsWith("is")) {
			propNameOffset = 2;
		}
		
		propName = Introspector.decapitalize(methodName.substring(propNameOffset, methodName.length()));
		
		return propName;
	}
	
	private static NameValuePair getPropertyNameAndValue(FormPersistable form, PropertyDescriptor pd) {
		String methodName = pd.getReadMethod().getName();
		NameValuePair pair = null;
		
		try {
			String value = "" + pd.getReadMethod().invoke(form);
            String propName = derivePropertyName(methodName);
		
			pair = new NameValuePair();
			pair.setName(propName);
			pair.setValue(value);
		
		} catch (InvocationTargetException ex) {
			throw new InvokeReadMethodException("An error occurred while saving form field values", ex);
		} catch (IllegalAccessException ex) {
			throw new InvokeReadMethodException("An error occurred while saving form field values", ex);
		}
		
		return pair;
	}
	
	private static Map<String, String> collectFormPropertiesAndValues(FormPersistable form) {
		PropertyDescriptor[] pdList = BeanUtils.getPropertyDescriptors(form.getClass());
		Map<String, String> propMap = new HashMap<String, String>();
		
		Arrays.stream(pdList).filter(pd -> isRegularMethodName(pd.getReadMethod().getName())).forEach(pd -> {
            NameValuePair pair = getPropertyNameAndValue(form, pd);

            if (pair != null) {
            	propMap.put(pair.getName(), pair.getValue());	
            }
		});
		
		return propMap;
	}
	
	private static boolean isEqualValues(String a, String b) {
		return ((a == null && b == null) || a.equals(b));
	}
	
	private static String toBase64(String plain) {
		return Base64.getEncoder().encodeToString(plain.getBytes());
	}
	
	private static String fromBase64(String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
		String decodedString = new String(decodedBytes);
		
		return decodedString;
	}
	
	private static String serializeMap(Map<String, String> map) {
		var mapper = new ObjectMapper();
		String value = "";
		
		try {
			value = toBase64(mapper.writeValueAsString(map));
		} catch (JsonProcessingException ex) {
			throw new SerializeOperationException("An error occurred while serializing map: ", ex);
		}
		
		return value;
	}
	
	private static Map<String, String> deserializeMap(String serialized) {
		var mapper = new ObjectMapper();
		var typeRef = new TypeReference<Map<String, String>>() {};
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			map = mapper.readValue(fromBase64(serialized), typeRef);
		} catch (JsonParseException ex) {
			throw new DeserializeOperationException("An error occurred while serializing map: ", ex);
		} catch (JsonMappingException ex) {
			throw new DeserializeOperationException("An error occurred while serializing map: ", ex);
		} catch (IOException ex) {
			throw new DeserializeOperationException("An error occurred while serializing map: ", ex);
		}
		
		return map;
	}
	
	public static void saveFormOrigValues(FormPersistable form) {
		Map<String, String> propMap = collectFormPropertiesAndValues(form);	
        form.setSerializedOrigValues(serializeMap(propMap));        
	}
	
	public static List<String> getDirtyProperties(FormPersistable form) {
		List<String> dirtyProps = new ArrayList<String>();
		
		Map<String, String> propMap = deserializeMap(form.getSerializedOrigValues());
		Map<String, String> newPropMap = collectFormPropertiesAndValues(form);
		
		propMap.keySet().stream().forEach(key -> {
		    String a = propMap.get(key);
		    String b = newPropMap.get(key);
		    
		    if (!isEqualValues(a, b)) {
		    	dirtyProps.add(key);
		    }
		}); 
		
		return dirtyProps;
	}
}

class NameValuePair {
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
}
