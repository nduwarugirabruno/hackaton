package io.sambell.product.utils;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Component
public class FilesUtils {

    final
    ServletContext context;

    @Autowired
    public FilesUtils(ServletContext context) {
        this.context = context;
    }

    /**
     * @param file: the file that we need to upload
     * @return a response entity
     */
    public ResponseEntity<String> upload(MultipartFile file, String userName, String type) {

        try {

            Path uploadDir;;
            String fileExtension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));

            String productName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
            uploadDir = switch (fileExtension) {
                case "webm", "mp4" -> Path.of("persistent-memory/" + userName + "/" + type + "/" + productName + "/videos/");
                case "jpg", "jpeg", "png", "webp" -> Path.of("persistent-memory/" + userName + "/" + type + "/" + productName + "/images/");
                case "gif" -> Path.of("persistent-memory/" + userName + "/" + type + "/" + productName + "/effects/");
                default -> Path.of("persistent-memory/other/");
            };

            Files.createDirectories(uploadDir);

            Path targetPath = uploadDir.resolve(file.getOriginalFilename());
            long rep = Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("copy rep: " + rep);
            return ResponseEntity.ok("ok");
        } catch(Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return ResponseEntity.internalServerError().body("error"); // renvoie un code de statut 500
        }
    }

    /**
     * @return a response entity
     */
    public ResponseEntity<String> delete(String param) {

        System.out.println("Delete User Image");
        try {

            File file = new File(context.getRealPath("/persistent-memory/images/image-name.png"));
            System.out.println(file.getName());
            if(file.delete()) System.out.println(file.getName() + " is deleted!");

            else System.out.println("Delete operation is failed.");

        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(
                "Response",
                HttpStatus.OK
        );
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        } else {
            return fileName.substring(dotIndex+1);
        }
    }
}
