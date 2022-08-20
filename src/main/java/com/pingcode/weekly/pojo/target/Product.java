package com.pingcode.weekly.pojo.target;

import com.pingcode.weekly.pojo.target.request.RequestJsonRootBean;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "product")
@Data
@ToString
public class Product {


    /**
     * PingCode:
     * *    https://at.pingcode.com/api/ship/products/6214da6ab672088420da0174/ticket/views/626a33d39ada64c8f095861f/content?pi=0&ps=100
     *
     * Worktile:
     * *    https://at.pingcode.com/api/ship/products/6214e16fb672088420da019a/ticket/views/6267c72a9ada64c8f09572ff/content?pi=0&ps=100
     */

//    private String baseUtl = "https://at.pingcode.com/api/ship/products";
//    private String productId = "6214da6ab672088420da0174";
//    private String middlePath = "ticket";
//    private String view = "views";
//    private String viewId = "626a33d39ada64c8f095861f";
//    private String content = "content";
//    private int pi;
//    private int ps;

    private List<String> productList;
    

//    private String method;
//    private String requestBody;
    //private String



//
//    Request request = new Request("https://at.pingcode.com/api/ship/products/6214da6ab672088420da0174/ticket/views/626a33d39ada64c8f095861f/content?pi=0&ps=100");
//        request.setMethod(HttpConstant.Method.POST);
//        request.setRequestBody(HttpRequestBody.json("{\"view\":{\"conditions\":[{\"operation\":6,\"property_key\":\"type_id\",\"value\":[\"6214e9bd3ebe94e35df23e89\",\"6214e9bd3ebe94e35df23e88\"],\"logic\":1}],\"search\":{\"keywords\":\"\",\"scopes\":[\"identifier\",\"title\"]},\"sort_by\":\"identifier\",\"sort_type\":-1}}","utf-8"));
//        request.addHeader("Content-Type","application/json");
//        request.addHeader("Accept","application/json, text/plain, */*");
//        request.addCookie("s-5da4213e32b2020037757d74","ef2e4ec0256d4f60919326a9593671d1");
//        request.addHeader("Origin","https://at.pingcode.com");






}
