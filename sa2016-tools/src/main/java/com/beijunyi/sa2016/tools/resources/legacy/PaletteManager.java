package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResource.*;

public class PaletteManager {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Palet> paletMap;

  @Inject
  public PaletteManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  public int count() throws IOException {
    indexResources();
    return paletMap.size();
  }

  private void indexResources() throws IOException {
    if(paletMap == null) {
      paletMap = readResources();
    }
  }

  @Nonnull
  private Map<Integer, Palet> readResources() throws IOException {
    Map<Integer, Palet> ret = new HashMap<>();
    Set<Path> files = finder.find(PALET);
    for(Path file : files) {
      String filename = file.getFileName().toString();
      Matcher matcher = Palet.PALET_PATTEN.matcher(filename);
      if(!matcher.matches())
        throw new IllegalStateException();
      int id = Integer.valueOf(matcher.group(1));
      try(InputStream stream = Files.newInputStream(file)) {
        ret.put(id, kryo.readObject(new Input(stream), Palet.class));
      }
    }
    return ret;
  }

}
