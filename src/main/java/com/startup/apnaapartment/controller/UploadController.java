package com.startup.apnaapartment.controller;

import com.startup.apnaapartment.model.response.ImageUploadResponse;
import com.startup.apnaapartment.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

  private UploadService uploadService;

  @Autowired
  public UploadController(UploadService uploadService) {
    this.uploadService = uploadService;
  }

  @RequestMapping("/upload")
  public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok(uploadService.uploadImage(file));
  }

}
