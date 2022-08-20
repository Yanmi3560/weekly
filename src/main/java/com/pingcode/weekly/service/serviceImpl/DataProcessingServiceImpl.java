package com.pingcode.weekly.service.serviceImpl;


import com.pingcode.weekly.pojo.origin.*;
import com.pingcode.weekly.pojo.target.Item;

import com.pingcode.weekly.service.DataProcessingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    @Resource
    private MongodbServiceImpl mongodbService;

    private Data data;
    private List<Value> values;
    private References references;
    private Lookups lookups;


    //接受Shipitems---Json对象
    @Override
    public List<Item> receive(Shipitems shipitems) {
        this.data = shipitems.getData();
        this.values = data.getValue();
        this.references = data.getReferences();
        this.lookups = references.getLookups();
        return handle();
    }

    //开始处理
    @Override
    public List<Item> handle() {

        //获取references---字段映射
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {

            Item item = new Item();
            Value value = values.get(i);
            item.set_id(value.get_id());


            //设置标识符
            if (value.getWhole_identifier() != null) {
                item.setWhole_identifier(value.getWhole_identifier());
            } else {
                item.setWhole_identifier("空标识");
            }

            //设置标题
            if (value.getTitle() != null) {
                item.setTitle(value.getTitle());
            } else {
                item.setTitle("标题为空");
            }

            //设置客户
            if (value.getCompany_id() != null) {
                item.setCompany(getCompany(value.getCompany_id()));
            } else {
                item.setCompany("未关联客户");
            }

            //获取客户级别
            if (value.getCompany_category_ids() != null) {
                item.setCompany_groups(getCompanyGroups(value.getCompany_category_ids()));
            }

            //设置标签
            if (value.getTag_ids() != null) {
                item.setTag(getTagName(value.getTag_ids()));
            }

            //获取当前item状态
            if (value.getState_id() != null) {
                item.setState(getStateName(value.getState_id()));
            }

            //获取评论信息
            if (value.getLast_comment() != null) {
                //最后评论时间
                item.setLast_comment_time(String.valueOf(value.getLast_comment().getUpdated_at()));
                //最后评论内容
                item.setLast_comment(value.getLast_comment().getContent());
                //获取最后评论者名字
                item.setLast_commenter(getMemberName(value.getLast_comment().getCommenter().getId()));
            } else {
                item.setLast_comment_time("此工作项目暂无评论时间");
                item.setLast_comment("此工作项目暂无评论内容");
                item.setLast_commenter("此工作项目暂无评论者");
            }

            if (value.getType_id() != null) {
                item.setType(getType(value.getType_id()));
            } else {
                item.setType("未找到对应类型");
            }

            if (value.getUpdated_by() != null) {
                item.setUpdated_at(String.valueOf(value.getUpdated_at()));
                item.setUpdated_by(getMemberName(value.getUpdated_by()));
            }

            item.setCreated_by(getMemberName(value.getCreated_by()));
            item.setCreated_at(String.valueOf(value.getCreated_at()));

            items.add(item);
        }

        for (Item item : items) {

            System.out.println(item);
        }


        return items;
    }

    //匹配类型名称
    @Override
    public String getType(String id) {
        for (Types type : lookups.getTypes()) {
            if (id.equals(type.get_id())) {
                return type.getName();
            }

        }
        return "";
    }

    //匹配状态名称
    @Override
    public String getStateName(String id) {

        for (States state : lookups.getStates()) {
            if (id.equals(state.get_id())) {

                return state.getName();
            }
        }
        return "";
    }

    //获取item标签
    @Override
    public String getTagName(List<String> ids) {

        String tagName = "";
        for (String id : ids) {
            for (Tags tag : lookups.getTags()) {
                if (id.equals(tag.get_id())) {
                    tagName += " " + tag.getName();
                }
            }
        }
        if (tagName.equals("")) {
            return "未设置标签";
        } else {
            return tagName;
        }
    }

    //获取公司级别
    @Override
    public String getCompanyGroups(List<String> ids) {

        String company_level = "";

        for (String id : ids) {
            for (Company_groups company_group : lookups.getCompany_groups()) {
                if (id.equals(company_group.get_id())) {

                    company_level += " " + company_group.getName();
                }
            }
        }

        if (company_level.equals("")) {
            return "未给客户设置级别";
        } else {
            return company_level;
        }
    }


    //获取客户名称
    @Override
    public String getCompany(String id) {
        for (Companies company : lookups.getCompanies()) {
            if (Objects.equals(company.get_id(), id)) {
                return company.getName();
            }
        }


        return "";
    }


    //获取最后评论者名字
    @Override
    public String getMemberName(String id) {

        for (Members member : lookups.getMembers()) {
            if (Objects.equals(member.getUid(), id)) {
                return member.getDisplay_name();
            }
        }
        return "空";
    }


}
