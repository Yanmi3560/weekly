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
public class Value {

    private String _id;
    private String team;
    private int identifier;
    private String product_id;
    private String title;
    private Submitter submitter;
    private String type_id;
    private String state_id;
    private long created_at;
    private String created_by;
    private long updated_at;
    private String updated_by;
    private int attachment_count;
    private List<Participants> participants;
    private String permissions;
    private int is_deleted;
    private List<String> tag_ids;
    private int vote_count;
    private int inner_comment_count;
    private int public_comment_count;
    private List<Share_scopes> share_scopes;
    private List<Votes> votes;
    private Last_comment last_comment;
    private int channel;
    private String channel_id;
    private String company_id;
    private List<String> company_category_ids;
    private String whole_identifier;
    private int have_vote;
}