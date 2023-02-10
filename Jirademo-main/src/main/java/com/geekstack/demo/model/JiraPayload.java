
package com.geekstack.demo.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class JiraPayload {

    private Fields fields;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

}
