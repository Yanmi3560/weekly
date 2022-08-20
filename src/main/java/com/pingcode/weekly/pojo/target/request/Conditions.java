/**
 * Copyright 2022 bejson.com
 */
package com.pingcode.weekly.pojo.target.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Auto-generated: 2022-08-09 0:10:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
@Component
public class Conditions {

    private int operation;
    private String property_key;
    private List<String> value;
    private int logic;


}