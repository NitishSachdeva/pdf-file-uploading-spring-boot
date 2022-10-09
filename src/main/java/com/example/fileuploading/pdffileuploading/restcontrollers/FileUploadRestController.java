package com.example.fileuploading.pdffileuploading.restcontrollers;

import com.example.fileuploading.pdffileuploading.utility.FileUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadRestController {
    @Autowired
    private FileUploadUtility fileUpload;

    @PostMapping("/upload-pdf-file")
    public ResponseEntity<String> fileUpload(@RequestParam("file")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        //try {
            if (file.isEmpty())
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please select a pdf file");

            if (!file.getContentType().equals("application/pdf"))
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please select only pdf file");

            //pdf-upload-logic
            boolean pdfUploaded = fileUpload.uploadFile(file);
            if (pdfUploaded)
               return ResponseEntity.ok("file uploaded successfully");
                //return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/pdf-files/").path(file.getOriginalFilename()).toUriString());
      /*  }catch (Exception e){
            e.printStackTrace();
        }*/

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!There is some error in upload the pdf file, please try again");
    }
}
