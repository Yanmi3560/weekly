package com.pingcode.weekly.pojo.target;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
@ConfigurationProperties(prefix = "usercookie")
public class UserCookie {
    private String name;
    private String value;

}
