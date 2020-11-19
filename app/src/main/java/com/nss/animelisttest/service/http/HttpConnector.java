package com.nss.animelisttest.service.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nss.animelisttest.service.model.AnimeListWinterModel;
import com.nss.animelisttest.service.model.ResponseService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mac on 2/27/2017 AD.
 */

public class HttpConnector {

    OkHttpClient client = null;
    Response response = null;
    String url  = null;
    Map<String,String> mapParam = null;

    public HttpConnector(String url) {
        client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
        this.url = url;
        mapParam = new HashMap<String,String>();
    }

    public void setMapParam(Map<String,String> mapParam) {
        this.mapParam = mapParam;
    }

    public <T> List<T> callServiceTranslateToListObject(Class<T> classz) throws RuntimeException {
        Response response = excutePost();
        if(response == null ) throw new RuntimeException("E91 ERROR_CONECTION_SERVICE!");
        else{
            try {
                Type type = new TypeToken<T>(){}.getType();
                return new Gson().fromJson(response.body().string(), new ListReflection<T>(classz));
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("E93 ERROR_CONVERT_JSON!");
            }
        }
    }

    public <T> T callServiceTranslateToObject(Class<T> classz) throws RuntimeException {
        Response response = excutePost();
        if(response == null ) throw new RuntimeException("E91 ERROR_CONECTION_SERVICE!");
        else{
            try {
                Type type = new TypeToken<T>(){}.getType();
                return new Gson().fromJson(response.body().string(), type);
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("E93 ERROR_CONVERT_JSON!");
            }
        }
    }

   /* public ResponseService callOldServiceTranslateToResponseService() {
        ResponseService responseService = new ResponseService();
        Response response = excutePost();
        if(response == null) {
            responseService.setError(true);
            responseService.setErrorCode("E91");
            responseService.setMessageResponse("ERROR CONECTION SERVICE!");
        }else{
            if(response.isSuccessful()) {
                try {
                    String messageResponse = response.body().string();
                    if(StringUtils.isBlank(messageResponse)) {
                        responseService.setError(true);
                        responseService.setErrorCode("E92");
                        responseService.setMessageResponse("ERROR SERVICE!");
                    }else{
                        if(messageResponse.equals("SUCCESS")) {
                            responseService.setError(false);
                            responseService.setMessageResponse(messageResponse);
                        }else{
                            responseService.setError(true);
                            responseService.setErrorCode("E94");
                            responseService.setMessageResponse(messageResponse);
                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    responseService.setError(true);
                    responseService.setErrorCode("E93");
                    responseService.setMessageResponse("ERROR CHECK MESSAGE RESPONSE!");
                }
            }else{
                responseService.setError(true);
                responseService.setErrorCode("E92");
                responseService.setMessageResponse("ERROR SERVICE!");
            }
        }
        return  responseService;
    }*/

    public ResponseService callServiceTranslateToResponseService() {
        ResponseService responseService = new ResponseService();
        Response response = excutePost();
        if(response == null) {
            responseService.setError(true);
            responseService.setErrorCode("E91");
            responseService.setMessageResponse("ERROR CONECTION SERVICE!");
        }else{
            if(response.isSuccessful()) {
                try {
                    //responseService = new Gson().fromJson(response.body().string(),ResponseService.class);
                    responseService.setGetJsonAnime(new Gson().fromJson(response.body().string(), AnimeListWinterModel.class));
                }catch (Exception e) {
                    e.printStackTrace();
                    responseService.setError(true);
                    responseService.setErrorCode(""+response.code());
                    responseService.setMessageResponse(response.message());
                }
            }else{
                responseService.setError(true);
                responseService.setErrorCode(""+response.code());
                responseService.setMessageResponse(response.message());
            }
        }
        return  responseService;
    }

    public <T> T callServiceTranslateToModelOject(Class<T> classz) throws RuntimeException {
        Response response = excutePost();
        if(response == null) {
            throw new RuntimeException("ERROR CONECTION SERVICE!");
        }else{
            if(response.isSuccessful()) {
                try {
                    return new Gson().fromJson(response.body().string(),classz);
                }catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("ERROR_CONVERT_JSON!");
                }
            }else{
                throw new RuntimeException("ERROR SERVICE!");
            }
        }
    }

    public Response excuteGET() {

        HttpUrl.Builder httpBuider = HttpUrl.parse(this.url).newBuilder();
        if (mapParam != null) {
            for (Map.Entry<String, String> entry : mapParam.entrySet()) {
                httpBuider.addQueryParameter(entry.getKey(),entry.getValue());
            }
        }

        Request request = new Request.Builder().url(httpBuider.build()).build();

        try {

            response = client.newCall(request).execute();

        }catch (Exception e) {
            e.printStackTrace();
            response = null;
        }

        return  response;

    }

    private Response excutePost() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : mapParam.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            formBuilder.add(key,value);
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder().url(this.url).post(requestBody).build();
        try {
            response = client.newCall(request).execute();
        }catch (Exception e) {
            e.printStackTrace();
            response = null;
        }
        return  response;
    }

}
