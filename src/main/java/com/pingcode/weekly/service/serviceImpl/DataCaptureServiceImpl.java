package com.pingcode.weekly.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.pingcode.weekly.pojo.origin.Shipitems;
import com.pingcode.weekly.pojo.origin.Value;
import com.pingcode.weekly.pojo.target.UserCookie;
import com.pingcode.weekly.pojo.target.request.RequestJsonRootBean;
import com.pingcode.weekly.service.DataCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataCaptureServiceImpl implements PageProcessor, DataCaptureService {

    private Shipitems shipItems;

    @Autowired
    private RequestJsonRootBean requestJsonRootBean;

    @Autowired
    private UserCookie userCookie;

    @Override
    public void setSite(Site site) {
        this.site = site;
    }

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来

        // String rawText = page.getRawText();
        Json json = page.getJson();
        //System.out.println("json--------------" + json);

        Shipitems shipitems = json.toObject(Shipitems.class);


        for (Value value : shipitems.getData().getValue()) {
            System.out.println(value.getTitle());
        }

        this.shipItems = shipitems;

    }

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public Request getRequestByPage(int page_index, String productUri) {

        Request request = new Request(productUri + page_index);
        request.setMethod(HttpConstant.Method.POST);
        request.setRequestBody(HttpRequestBody.json(String.valueOf(JSON.toJSON(requestJsonRootBean)), "utf-8"));
        request.addCookie(userCookie.getName(), userCookie.getValue());

        return request;
    }

    @Override
    public List<Shipitems> getCaptureShipItems(String productUri) {
        List<Shipitems> shipItemsList = new ArrayList();
        int page_index = 0;
        int page_count = 0;
        int page_size = 100;
        synchronized (this) {
            do {
                Spider.create(this)
                        .addRequest(getRequestByPage(page_index, productUri))
                        .thread(1)
                        //启动爬虫
                        .run();
                shipItemsList.add(shipItems);
                page_index++;
                page_count = shipItems.getData().getPage_count();
                page_size = shipItems.getData().getPage_size();
            } while (page_index < (page_count % page_size));
            //获取分页数据
        }
        return shipItemsList;
    }
}