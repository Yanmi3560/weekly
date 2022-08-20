package com.pingcode.weekly.pojo.target;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document("ThisWeekItem")
@Data
@ToString
public class ThisWeekItem {
    @Id
    //工作项id
    private String _id;
    //工作项标识
    private String whole_identifier;
    //工作项标题
    private String title;
    //工作项状态
    private String state;
    //工作项更新时间
    private String updated_at;
    //工作项更新人
    private String updated_by;
    //工作项标签
    private String tag;
    //工作项评论
    private String last_comment;
    //工作项最后评论者
    private String last_commenter;
    //工作项最后评论时间
    private String last_comment_time;
    //工作项所属客户
    private String company;
    //工作项类型
    private String type;
    //工作项所属客户登记
    private String company_groups;
    //工作项创建人
    private String created_by;
    //工作项创建时间
    private String created_at;


}
