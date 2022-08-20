package com.pingcode.weekly.service;

import com.pingcode.weekly.pojo.origin.*;
import com.pingcode.weekly.pojo.target.Item;
import java.util.List;

public interface DataProcessingService {

    //接受ShipItems---Json对象
    List<Item> receive(Shipitems shipitems);

    //开始处理
    List<Item> handle();

    //匹配类型名称
    String getType(String id);

    //匹配状态名称
    String getStateName(String id);

    //获取item标签
    String getTagName(List<String> ids);

    //获取公司级别
    String getCompanyGroups(List<String> ids);

    //获取客户名称
    String getCompany(String id);

    //获取最后评论者名字
    String getMemberName(String id);

}
