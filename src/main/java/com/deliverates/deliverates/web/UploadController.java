package com.deliverates.deliverates.web;

import com.deliverates.deliverates.services.UploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/")
    public String root() {
        return "upload";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> screenshotUpload(@RequestParam("image") MultipartFile image,
                                                   RedirectAttributes redirectAttributes) {

        if (image.isEmpty()) {
            System.out.println("FILE IS EMPTY");
        }
        String parsedText = uploadService.parseUploadedImage(image);
        return ResponseEntity.created(null).body(parsedText);
    }
}
