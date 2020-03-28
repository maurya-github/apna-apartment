package com.startup.apnaapartment.service;

import com.startup.apnaapartment.helper.RestTemplateHelper;
import com.startup.apnaapartment.model.response.ImageUploadResponse;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

  private String imageKitPrivateKey;
  private String imageKitFileUploadUrl;
  private RestTemplateHelper restTemplateHelper;

  @Autowired
  public UploadService(@Value("${imagekit.io.private.key}") String imageKitPrivateKey,
      @Value("${imagekit.io.file.upload.url}") String uploadUrl,
      RestTemplateHelper restTemplateHelper) {
    this.imageKitPrivateKey = imageKitPrivateKey;
    this.imageKitFileUploadUrl = uploadUrl;
    this.restTemplateHelper = restTemplateHelper;
  }

  public ImageUploadResponse uploadImage(MultipartFile multipartFile) {
    try {
      byte[] bytes = multipartFile.getBytes();
      String encodeImageString = Base64.getEncoder().encodeToString(bytes);

//      ImageUploadRequest imageUplaodrequest = ImageUploadRequest.builder()
//          .file(encodeImageString)
//          .fileName(multipartFile.getName())
//          .folder("/Home/apartments")
//          .useUniqueFileName(true)
//          .responseFields("metadata")
//          .build();

      MultiValueMap<String, Object> body
          = new LinkedMultiValueMap<>();
      body.add("file", encodeImageString);
      body.add("fileName", multipartFile.getOriginalFilename());
      body.add("folder", "/apartments");
      body.add("responseFields", "metadata");

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
      headers.add(HttpHeaders.AUTHORIZATION,
          "Basic " + Base64.getEncoder().encodeToString((imageKitPrivateKey + ":").getBytes()));
      return restTemplateHelper
          .postForEntity(ImageUploadResponse.class, imageKitFileUploadUrl,
              body, headers);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

  }

}
