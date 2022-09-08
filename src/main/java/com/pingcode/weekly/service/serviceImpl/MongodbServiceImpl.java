package com.pingcode.weekly.service.serviceImpl;


import com.mongodb.client.FindIterable;
import com.pingcode.weekly.pojo.target.Item;
import com.pingcode.weekly.pojo.target.ThisWeekItem;
import com.pingcode.weekly.service.MongodbService;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

@Service
public class MongodbServiceImpl implements MongodbService {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String findItemAll() {

        return "";

    }

    @Override
    public String insertItems(List<Item> itemList) {

        int i = 1;
        for (Item item : itemList) {
            Item insert = mongoTemplate.insert(item);
            i++;
            System.out.println("insert:  " + insert);
        }

        return "插入完成,成功插入" + i + "数据";
    }


    @Override
    public Boolean reinitialize() {

        mongoTemplate.dropCollection(Item.class);
        mongoTemplate.dropCollection(ThisWeekItem.class);
        return null;
    }

    @Override
    public Item getItemById(String id) {

        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Item.class);
    }

    @Override
    public String insertItem(Item item) {


        return "成功插入数据-----" + mongoTemplate.insert(item);
    }

    @Override
    public String insertItem(Item item, String connection) {

        return "成功插入数据到本周周报表中-----" + mongoTemplate.insert(item, connection);

    }

    @Override
    public String updateItem(Item item) {


        Item save = mongoTemplate.save(item);
        return null;
    }
    @Override
    public String updateItem(Item item,String connection) {

        Item save = mongoTemplate.save(item,connection);
        return null;
    }

    @Override
    public List<Item> getAllThisWeekItems(String connection) {

        return mongoTemplate.findAll(Item.class, connection);

    }


}
