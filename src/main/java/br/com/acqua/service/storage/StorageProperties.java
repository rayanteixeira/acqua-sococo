package br.com.acqua.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author Jairo Nascimento
 * @Date 14/08/2020
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "arquivos";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
