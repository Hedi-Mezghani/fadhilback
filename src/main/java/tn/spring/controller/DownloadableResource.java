package tn.spring.controller;

import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;

public class DownloadableResource extends InputStreamResource {
    private final String downloadUrl;

    public DownloadableResource(InputStream inputStream, String downloadUrl) {
        super(inputStream);
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
}
