package com.api.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by cjh on 2018/5/31.
 */
@ConfigurationProperties(prefix = "his",locations = "classpath:his.properties")
public class HisSetting {
    private String wsdl;
    private String namespace;
    private String key;
    private String redisLockCardPre;

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRedisLockCardPre() {
        return redisLockCardPre;
    }

    public void setRedisLockCardPre(String redisLockCardPre) {
        this.redisLockCardPre = redisLockCardPre;
    }
}
