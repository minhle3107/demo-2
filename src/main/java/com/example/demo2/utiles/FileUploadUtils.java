package com.example.demo2.utiles;

import jakarta.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtils {

    public static File getFolderUpload(ServletContext context) {
        String uploadPath = context.getRealPath("/static/uploads");
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }

    public static String uploadFile(ServletContext context, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }

        File uploadDir = getFolderUpload(context);
        String fileName = file.getOriginalFilename();
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        File destinationFile = new File(uploadDir, newFileName);
        file.transferTo(destinationFile);

        return "/static/uploads/" + newFileName;
    }
}