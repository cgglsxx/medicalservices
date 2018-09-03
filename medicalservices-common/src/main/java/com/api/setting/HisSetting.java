package com.api.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by cjh on 2018/5/31.
 */
@ConfigurationProperties(prefix = "his")
public class HisSetting {
    private String url;
    private String key;
    private String redisLockCardPre;
    private String redisLockRegAccount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getRedisLockRegAccount() {
        return redisLockRegAccount;
    }

    public void setRedisLockRegAccount(String redisLockRegAccount) {
        this.redisLockRegAccount = redisLockRegAccount;
    }

    public void setRedisLockCardPre(String redisLockCardPre) {
        this.redisLockCardPre = redisLockCardPre;
    }
}
