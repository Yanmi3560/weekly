package com.pingcode.weekly;

import com.pingcode.weekly.pojo.target.Item;
import com.pingcode.weekly.service.MongodbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeeklyApplicationTests {

    @Autowired
    MongodbService mongodbService;
    @Test
    void contextLoads() {

        for (Item allThisWeekItem : mongodbService.getAllThisWeekItems("ThisWeekItem")) {


            System.out.println(allThisWeekItem.getTitle() + "   " +allThisWeekItem.getType() + " ---- " + allThisWeekItem.getCompany()+" " +allThisWeekItem.getCompany_groups()+ "  "+allThisWeekItem.getState() + "  "+allThisWeekItem.getTag());

        }

    }

}
