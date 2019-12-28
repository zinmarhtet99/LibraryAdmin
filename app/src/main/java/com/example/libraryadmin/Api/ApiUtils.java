package com.example.libraryadmin.Api;

public class ApiUtils {

    public static final String BASE_URL="http://192.168.43.99/CULibrary/api/";

    public static ApiInterface getApi(){
        return ApiClient.getRetrofit(BASE_URL).create(ApiInterface.class);

    }
}
