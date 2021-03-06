package com.beijunyi.sa2016.tools.legacy.serializers;

import com.beijunyi.sa2016.tools.legacy.Palet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.ImmutableList;

import java.awt.*;

class PaletSerializer extends Serializer<Palet> {

  @Override
  public void write(Kryo kryo, Output output, Palet object) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Palet read(Kryo kryo, Input input, Class<Palet> type) {
    ImmutableList.Builder<Color> colors = ImmutableList.builder();
    for(int i = 0; i < 224; i++) {
      int blue = input.read();
      int green = input.read();
      int red = input.read();
      colors.add(new Color(red, green, blue));
    }
    return new Palet(colors.build());
  }
}
