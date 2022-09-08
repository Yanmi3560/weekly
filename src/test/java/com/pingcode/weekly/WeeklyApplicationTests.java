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
        List<Item> thisWeekItems = mongodbService.getAllThisWeekItems("ThisWeekItem");
        List<Item> items = mongodbService.getAllThisWeekItems("item");

        int i = 0;
        int j = 0;
        int k = 0;
        System.out.println("===========================================================================");
        System.out.println("=============================技术支持类型工单处理=============================");
        for (Item item : thisWeekItems) {
            if (item.getType().contains("技术支持")) {
                i++;
                //Item newItem = mongodbService.getItemById(item.get_id());
                //item = newItem;
                //mongodbService.updateItem(item, "ThisWeekItem");
                // System.out.println();
                // System.out.println("\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t" + StringUtils.substringBeforeLast(item.getTitle().trim(), "@"));
                //System.out.println(item.getCompany().trim() + "\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t状态:" + item.getState() + "\t" + item.getTitle());
                System.out.println(outputItem(item));
                System.out.println();
            }
        }
        System.out.println("=================================确认缺陷工单处理=============================");
        for (Item item : thisWeekItems) {
            if (item.getType().contains("缺陷")&& !item.getTag().contains("超时工单")) {
                j++;
                //Item newItem = mongodbService.getItemById(item.get_id());
                //item = newItem;
                //mongodbService.updateItem(item, "ThisWeekItem");
                // System.out.println();
                // System.out.println("\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t" + StringUtils.substringBeforeLast(item.getTitle().trim(), "@"));
                //System.out.println(item.getCompany().trim() + "\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t状态:" + item.getState() + "\t" + item.getTitle());
                System.out.println(outputItem(item));
                System.out.println();
            }
        }

        System.out.println("=================================超时工单处理=============================");
        for (Item item : thisWeekItems) {
            if (item.getTag().contains("超时工单")) {
                k++;
                //Item newItem = mongodbService.getItemById(item.get_id());
                //item = newItem;
                //mongodbService.updateItem(item, "ThisWeekItem");
                // System.out.println();
                // System.out.println("\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t" + StringUtils.substringBeforeLast(item.getTitle().trim(), "@"));
                //System.out.println(item.getCompany().trim() + "\t【" + item.getTag().trim() + "】\t[" + item.getProducts_name() + "] \t" + " 工单类型:" + item.getType() + "\t状态:" + item.getState() + "\t" + item.getTitle());
                System.out.println(outputItem(item));
                System.out.println();
                //System.out.println("最后评论人:"+item.getLast_commenter()+":"+item.getLast_comment());
                System.out.println();
            }
        }
        System.out.println("===========================================================================");
        System.out.println("本周共处理技术支持类型工单:  " + i + "个工作项");
        System.out.println("本周共处理缺陷类型工单:  " + j + "个工作项");
        System.out.println("本周超时类型关闭工单:  " + k + "个工作项");




    }
    String outputItem (Item item){
        return "【"+item.getCompany().trim() +"  "+item.getCompany_groups().trim()+"】"+"【" + item.getProducts_name() + "】"+ "\t" + item.getTitle() + "\t" + "\t【" + item.getState()+"】" ;

    }

}
