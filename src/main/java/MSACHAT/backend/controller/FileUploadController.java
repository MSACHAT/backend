package MSACHAT.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
public class FileUploadController {

    // 设置上传文件保存的本地路径
    String uploadDir = "C:\\Users\\zhang\\Downloads\\testImage";

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();

            // 构建本地文件路径
            Path filePath = Path.of(uploadDir, fileName);

            // 复制文件到本地
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 返回存储的本地地址
            String localFilePath = filePath.toAbsolutePath().toString();
            return ResponseEntity.ok(localFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }
    }
}
