/**
 * Copyright 2022 bejson.com
 */
package com.pingcode.weekly.pojo.origin;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2022-08-06 22:47:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

@Data
@ToString
public class Last_comment {

    private String team;
    private String ticket_id;
    private int type;
    private int client;
    private Commenter commenter;
    private String content;
    private List<String> attachments;
    private int attachment_count;
    private List<String> reactions;
    private long created_at;
    private String created_by;
    private long updated_at;
    private String updated_by;
    private String _id;
    private int is_deleted;
    private int is_archived;

}