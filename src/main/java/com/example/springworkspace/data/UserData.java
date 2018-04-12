package com.example.springworkspace.data;

public class UserData {

    private long id;
    private String apiKey;

    public UserData(long id, String apiKey) {
        this.id = id;
        this.apiKey = apiKey;
    }

    public long getId() {
        return id;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                '}' ;
    }
}
