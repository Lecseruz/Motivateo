package com.example.magomed.motivateo.managers.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by magomed on 26.09.17.
 */

public class HeaderManager {
    private String token;
    private String clientId;
    Map<String, String> headers = new HashMap<>();

    public void setToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
