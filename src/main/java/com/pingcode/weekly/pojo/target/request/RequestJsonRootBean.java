/**
 * Copyright 2022 bejson.com
 */
package com.pingcode.weekly.pojo.target.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Auto-generated: 2022-08-09 0:10:18
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "requestjsonrootbean")
public class RequestJsonRootBean {
    private View view;

}