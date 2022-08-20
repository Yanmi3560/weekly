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
public class View {

    private List<Conditions> conditions;
    private Search search;
    private String sort_by;
    private int sort_type;
    private int condition_logic;
}