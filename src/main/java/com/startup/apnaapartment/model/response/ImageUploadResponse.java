package com.startup.apnaapartment.model.response;

import java.util.List;
import lombok.Data;

@Data
public class ImageUploadResponse {

  private Object customCoordinates;
  private int size;
  private String filePath;
  private boolean isPrivateFile;
  private String name;
  private int width;
  private String url;
  private String fileType;
  private String fileId;
  private String thumbnailUrl;
  private int height;
  private List<String> tags;
}