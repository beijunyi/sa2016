package com.beijunyi.sa2016.assets.serializers;

import com.beijunyi.sa2016.assets.Media;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class MediaSerializer extends Serializer<Media> {

  @Override
  public void write(Kryo kryo, Output output, Media object) {
    output.writeAscii(object.getFormat());
    output.writeInt(object.getData().length);
    output.writeBytes(object.getData());
  }

  @Override
  public Media read(Kryo kryo, Input input, Class<Media> type) {
    String format = input.readString();
    byte[] data = new byte[input.readInt()];
    input.readBytes(data);
    return new Media(format, data);
  }
}
