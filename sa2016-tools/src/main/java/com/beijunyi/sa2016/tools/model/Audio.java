package com.beijunyi.sa2016.tools.model;

public class Audio {

  private final String id;
  private final String format;
  private final byte[] bytes;

  public Audio(String id, String format, byte[] bytes) {
    this.id = id;
    this.format = format;
    this.bytes = bytes;
  }

  public String getId() {
    return id;
  }

  public String getFormat() {
    return format;
  }

  public byte[] getBytes() {
    return bytes;
  }

}