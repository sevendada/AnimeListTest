package com.nss.animelisttest.service.model;

/**
 * Created by mac on 2/22/2017 AD.
 */

public class ResponseService {

    boolean isError = false;
    String errorCode = "";
    String messageResponse = "";
    String messageResponseTh = "";
    String errorFocus = "";
    String contentResponse = "";
    String jsonStringResponse = "";
    AnimeListWinterModel getJsonAnime ;

    public AnimeListWinterModel getGetJsonAnime() {
        return getJsonAnime;
    }

    public void setGetJsonAnime(AnimeListWinterModel getJsonAnime) {
        this.getJsonAnime = getJsonAnime;
    }

    public boolean isError() {
        return isError;
    }
    public void setError(boolean isError) {
        this.isError = isError;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessageResponse() {
        return messageResponse;
    }
    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }
    public String getContentResponse() {
        return contentResponse;
    }
    public void setContentResponse(String contentResponse) {
        this.contentResponse = contentResponse;
    }
    public String getJsonStringResponse() {
        return jsonStringResponse;
    }
    public void setJsonStringResponse(String jsonStringResponse) {
        this.jsonStringResponse = jsonStringResponse;
    }

    public String getMessageResponseTh() {
        return messageResponseTh;
    }

    public void setMessageResponseTh(String messageResponseTh) {
        this.messageResponseTh = messageResponseTh;
    }

    public String getErrorFocus() {
        return errorFocus;
    }

    public void setErrorFocus(String errorFocus) {
        this.errorFocus = errorFocus;
    }
}
