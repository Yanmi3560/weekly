/**
 * Copyright 2022 bejson.com
 */
package com.pingcode.weekly.pojo.target.request;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2022-08-09 0:10:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
public class View {


    private List<Conditions> conditions;
    private Search search;
    private String sort_by;
    private int sort_type;


}