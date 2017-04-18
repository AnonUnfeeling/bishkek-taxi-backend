package ua.jdroidcoder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by jdroidcoder on 18.04.17.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
