package com.pingcode.weekly.controller;
import com.alibaba.fastjson.JSON;
import com.pingcode.weekly.pojo.origin.Shipitems;
import com.pingcode.weekly.pojo.target.Item;
import com.pingcode.weekly.pojo.target.Product;
import com.pingcode.weekly.pojo.target.request.RequestJsonRootBean;
import com.pingcode.weekly.service.DataCaptureService;
import com.pingcode.weekly.service.DataProcessingService;
import com.pingcode.weekly.service.MongodbService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Log4j2
@Controller
@RestController
public class MainController {

    @Autowired
    private RequestJsonRootBean requestJsonRootBean;

    @Autowired
    private Product product;


    private int initFlag = 0;
    @Autowired
    private DataCaptureService dataCaptureService;
    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired
    private MongodbService mongodbService;
    private Object o;

    @RequestMapping("/init")
    public Object init() throws InterruptedException {

        if (initFlag != 1) {
            System.out.println("开始初始化");
            for (int i = 3; i > 0; i--) {
                System.out.println("倒计时:" + i + "s");
                Thread.sleep(1000);
            }
            for (String productUri : product.getProductList()) {

                List<Shipitems> captureShipItems = dataCaptureService.getCaptureShipItems(productUri);

                for (Shipitems captureShipItem : captureShipItems) {
                    List<Item> receive = dataProcessingService.receive(captureShipItem);
                    mongodbService.insertItems(receive);
                }
            }
            initFlag = 1;
        } else {
            return "初始化完成,请勿再次调用";
        }

        System.out.println(requestJsonRootBean);
        System.out.println(this.product);

        return JSON.toJSON(requestJsonRootBean);
    }


    @RequestMapping("getNewitems")
    public Object getNewItems() {
        return new Object();
    }

    @RequestMapping("reinit")
    public String reInit() {

        mongodbService.reinitialize();
        initFlag = 0;

        return "redirect /init";
    }

    @Scheduled(fixedDelay = 60000)
    @RequestMapping("start")
    public String start() {

        if (initFlag == 0) {
            log.log(Level.getLevel("INFO"), "开始定时任务");
            for (String productUri : product.getProductList()) {

                for (Shipitems captureShipItem : dataCaptureService.getCaptureShipItems(productUri)) {

                    for (Item item : dataProcessingService.receive(captureShipItem)) {

                        String id = item.get_id();

                        if (mongodbService.getItemById(id) == null) {
                            mongodbService.insertItem(item);
                            if (item.getState().equals("处理中") && item.getTag().contains("技术支持确认")) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }
                            if (item.getState().equals("已关闭") && item.getTag().contains("非缺陷问题") && (!(mongodbService.getItemById(item.get_id()).getState().equals("已关闭")) || !(mongodbService.getItemById(item.get_id()).getTag().contains("非缺陷问题")))) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }

                        } else {

                            if (item.getState().equals("处理中") && item.getTag().contains("技术支持确认") && (!(mongodbService.getItemById(item.get_id()).getState().equals("处理中")) || !(mongodbService.getItemById(item.get_id()).getTag().contains("技术支持确认")))) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }
                            if (item.getState().equals("已关闭") && item.getTag().contains("非缺陷问题") && (!(mongodbService.getItemById(item.get_id()).getState().equals("已关闭")) || !(mongodbService.getItemById(item.get_id()).getTag().contains("非缺陷问题")))) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }

                            if (item.getType().equals("技术支持") && (!(item.getState().equals(mongodbService.getItemById(item.get_id()).getState())))) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }
                            if ((item.getTag().contains("超时工单") && !(mongodbService.getItemById(item.get_id()).getTag().contains("超时工单")))) {
                                mongodbService.insertItem(item, "ThisWeekItem");
                            }

                            mongodbService.updateItem(item);
                        }
                    }

                }

            }
//        } else {
//            log.log(Level.getLevel("INFO"), "定时任务未介入");
        }
        return "";
    }

    @RequestMapping("weekly")
    public Object weekly() {
        return JSON.toJSON(mongodbService.getAllThisWeekItems("ThisWeekItem"));
//        return JSON.toJSON(mongodbService.getAllThisWeekItems("ThisWeekItem"));

    }


}






