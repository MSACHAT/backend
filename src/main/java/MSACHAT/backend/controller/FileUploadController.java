package MSACHAT.backend.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private static final String UPLOAD_DIR = "./uploads"; // 上传文件保存的目录

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 确保文件不为空
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try {
            // 获取文件的原始名字
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

            // 根据配置的目录创建文件保存路径
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 将文件保存到服务器指定目录
            Path filePath = uploadPath.resolve(originalFileName);
            Files.copy(file.getInputStream(), filePath);

            return "File uploaded successfully!";
        } catch (IOException e) {
            return "Failed to upload file.";
        }
    }
}
