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
public class Search {

    private String keywords;
    private List<String> scopes;

}