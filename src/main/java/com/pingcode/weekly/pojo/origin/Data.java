/**
 * Copyright 2022 bejson.com
 */
package com.pingcode.weekly.pojo.origin;

import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2022-08-06 22:47:47
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@lombok.Data
@ToString
public class Data {

    private List<Value> value;
    private References references;
    private int count;
    private int page_index;
    private int page_size;
    private int page_count;
    private Meta meta;

}