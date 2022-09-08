package com.pingcode.weekly.service;

import com.pingcode.weekly.pojo.target.Item;

import java.util.List;

public interface MongodbService {

    String findItemAll();

    String insertItems(List<Item> itemList);

    Boolean reinitialize();

    Item getItemById(String id);

    String insertItem(Item item);

    String insertItem(Item item,String connection);

    String updateItem(Item item);

    String updateItem(Item item,String connection);


    List<Item> getAllThisWeekItems(String connection);


}
