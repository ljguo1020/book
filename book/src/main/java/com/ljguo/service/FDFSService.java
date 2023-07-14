package com.ljguo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FDFSService {
    String uploadFile(MultipartFile file) throws IOException;

    String uploadFile(File file) throws IOException;

    String uploadFile(String content, String fileExtension);

    void deleteFile(String fileUrl);

    byte[] downloadFile(String fileUrl);


}
