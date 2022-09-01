package com.pingcode.weekly;

import com.pingcode.weekly.pojo.target.Product;
import com.pingcode.weekly.pojo.origin.Shipitems;
import com.pingcode.weekly.pojo.target.Item;
import com.pingcode.weekly.service.DataCaptureService;
import com.pingcode.weekly.service.DataProcessingService;
import com.pingcode.weekly.service.MongodbService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

@SpringBootTest
class WeeklyApplicationTests {

    @Autowired
    MongodbService mongodbService;
    @Autowired
    DataCaptureService dataCaptureService;
    @Autowired
    DataProcessingService dataProcessingService;
    @Autowired
    Product product;

    @Test
    void contextLoads() {

        for (Item allThisWeekItem : mongodbService.getAllThisWeekItems("ThisWeekItem")) {


            System.out.println(allThisWeekItem.getTitle() + "   " + allThisWeekItem.getType() + " ---- " + allThisWeekItem.getCompany() + " " + allThisWeekItem.getCompany_groups() + "  " + allThisWeekItem.getState() + "  " + allThisWeekItem.getTag());

        }


    }

    @Test
    void init() {

        mongodbService.reinitialize();

        for (String productUri : product.getProductList()) {

            List<Shipitems> captureShipItems = dataCaptureService.getCaptureShipItems(productUri);

            for (Shipitems captureShipItem : captureShipItems) {
                List<Item> receive = dataProcessingService.receive(captureShipItem);
                mongodbService.insertItems(receive);
            }
        }
    }

    @Test
    void printItems() {
//        List<Item> items = mongodbService.getAllThisWeekItems("item");
        List<Item> items = mongodbService.getAllThisWeekItems("ThisWeekItem");
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getCompany_groups().equals(o1.getCompany_groups())){
                    return -1;
                }else {
                    return 1;
                }
            }
        });

        for (Item item : items) {
            System.out.println(item.getCompany().trim());
            System.out.println("\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t" + StringUtils.substringBeforeLast(item.getTitle().trim(), "@"));
        }
    }
}
