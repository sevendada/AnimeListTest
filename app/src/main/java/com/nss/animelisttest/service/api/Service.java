package com.nss.animelisttest.service.api;


import com.nss.animelisttest.service.base.BaseService;
import com.nss.animelisttest.service.http.HttpConnector;
import com.nss.animelisttest.service.model.AnimeListWinterModel;
import com.nss.animelisttest.service.model.ResponseService;

import java.util.Map;

public class Service extends BaseService {

    String serviceUrl;

    public Service(String url) {
        super(url);
    }

    public AnimeListWinterModel getAnimeList(){
        serviceUrl = "/season/";
        HttpConnector httpConnector = new HttpConnector(this.getUrlService()+serviceUrl);
        Map<String,String> param = this.initParam();
        param.put("year", String.valueOf(2020));
        param.put("season","winter");
        httpConnector.setMapParam(param);
        AnimeListWinterModel animeListWinterModel = httpConnector.callServiceTranslateToObject(AnimeListWinterModel.class);
        return  animeListWinterModel;
    }

    public ResponseService ResponseAnimeList(){
        serviceUrl = "/season/";
        HttpConnector httpConnector = new HttpConnector(this.getUrlService()+serviceUrl);
        Map<String,String> param = this.initParam();
        param.put("year", String.valueOf(2020));
        param.put("season","winter");
        httpConnector.setMapParam(param);
        ResponseService responseService = httpConnector.callServiceTranslateToResponseService();
        return  responseService;
    }



}
