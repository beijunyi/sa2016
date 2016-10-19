package com.beijunyi.sa2016.tools.legacy.serializers;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.legacy.Spr;
import com.beijunyi.sa2016.tools.legacy.SprFrame;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

import static com.beijunyi.sa2016.tools.utils.IntegerReader.LE;

public class SprSerializer extends Serializer<Spr> {

  @Override
  public void write(Kryo kryo, Output output, Spr object) {
    throw new UnsupportedOperationException();
  }

  @Nonnull
  @Override
  public Spr read(Kryo kryo, Input input, Class<Spr> type) {
    int direction = LE.uint16(input);
    int action = LE.uint16(input);
    int duration = (int) LE.uint32(input);
    int length = (int) LE.uint32(input);
    ImmutableList.Builder<SprFrame> frames = ImmutableList.builder();
    for(int i = 0; i < length; i++)
      frames.add(kryo.readObject(input, SprFrame.class));
    return new Spr(direction, action, duration, length, frames.build());
  }
}
