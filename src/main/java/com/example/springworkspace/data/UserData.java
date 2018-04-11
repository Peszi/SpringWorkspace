package com.example.springworkspace.data;

public class UserData extends Data {

    private long id;
    private String apiKey;

    public UserData(String message) {
        super(message);
    }

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

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                '}' + message;
    }
}
