package com.pingcode.weekly.service;

import com.pingcode.weekly.pojo.origin.Shipitems;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

import java.util.List;

public interface DataCaptureService {

    List<Shipitems> getCaptureShipItems(String productUri);

    void setSite(Site site);

    Request getRequestByPage(int page,String productUri);
}
