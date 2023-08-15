package org.example.StreamLine.Advice;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExceptionHandlerObject {
    private Map<String, String> map;

    public ExceptionHandlerObject() {
        this.map = new HashMap<>();
    }

    public Map<String, String> createErrorMap(List<FieldError> errorList) {
        errorList.forEach(error -> {
            map.put(error.getField(), error.getDefaultMessage());
        });

        return map;
    }

    public Map<String, String> setKeyValuePair(String key, String value) {
        map.put(key, value);
        return map;
    }
}
