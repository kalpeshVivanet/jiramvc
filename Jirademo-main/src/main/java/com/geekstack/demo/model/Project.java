
package com.geekstack.demo.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Project {

    private String key;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

}
