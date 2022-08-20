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
public class Members {

    private String uid;
    private String display_name;
    private String display_name_pinyin;
    private String email;
    private String mobile_area;
    private String mobile;
    private String avatar;
    private String short_code;
    private int status;
    private List<String> role_ids;
    private String team;
    private String name;
    private int type;

}