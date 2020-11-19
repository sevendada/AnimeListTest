package com.nss.animelisttest.service.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2/22/2017 AD.
 */

public class BaseService {

    String urlService;


    public String getUrlService() {
        return urlService;
    }

    public void setUrlService(String urlService) {
        this.urlService = urlService;
    }

    public BaseService(String url) {
        this.urlService = url;
    }

    public Map<String,String> initParam() {
        Map<String,String> param = new HashMap<String,String>();
        return  param;
    }

}
