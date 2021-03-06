package com.beijunyi.sa2016.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

public class KryoFactory {

  private static final Map<Class, Serializer> SERIALIZERS = new HashMap<>();
  private static final ThreadLocal<Kryo> POOL = new ThreadLocal<Kryo>() {
    @Nonnull
    @Override
    protected Kryo initialValue() {
      return createInstance();
    }
  };

  public static void register(Class type, Serializer serializer) {
    SERIALIZERS.put(type, serializer);
  }

  @Nonnull
  private static Kryo createInstance() {
    Kryo kryo = new Kryo();
    kryo.setReferences(false);
    SERIALIZERS.forEach(kryo::register);
    return kryo;
  }

  @Nonnull
  public static Kryo getInstance() {
    return POOL.get();
  }

}
