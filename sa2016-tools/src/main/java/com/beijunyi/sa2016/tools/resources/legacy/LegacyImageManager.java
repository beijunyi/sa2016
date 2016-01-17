package com.beijunyi.sa2016.tools.resources.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.beijunyi.sa2016.tools.resources.legacy.structs.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResource.*;
import static java.nio.file.StandardOpenOption.READ;

public class LegacyImageManager {

  private final Kryo kryo;
  private final LegacyResourceFinder finder;

  private Map<Integer, Adrn> adrnMap;
  private Map<Integer, Set<Integer>> floorElementsMap;
  private FileChannel realChannel;

  @Inject
  public LegacyImageManager(@Nonnull Kryo kryo, @Nonnull LegacyResourceFinder finder) {
    this.kryo = kryo;
    this.finder = finder;
  }

  public int count() throws IOException {
    indexResources();
    return adrnMap.size();
  }

  @Nonnull
  public LegacyImageObject getImage(int id) throws IOException {
    indexResources();
    Adrn adrn = adrnMap.get(id);
    Real real = readRealData(adrn);
    return new LegacyImageObject(adrn , real);
  }

  private void indexResources() throws IOException {
    if(adrnMap == null || floorElementsMap == null) {
      AdrnSet resources = openAdrn();
      realChannel = openReal();
      adrnMap = new HashMap<>();
      floorElementsMap = new HashMap<>();
      for(Adrn adrn : resources.getAdrns()) {
        int uid = adrn.getUid();
        int mapId = adrn.getMapId();
        adrnMap.put(uid, adrn);
        if(mapId != 0) {
          Set<Integer> uids = floorElementsMap.get(mapId);
          if(uids == null) {
            uids = new HashSet<>();
            floorElementsMap.put(mapId, uids);
          }
          uids.add(uid);
        }
      }
    }
  }

  @Nonnull
  private AdrnSet openAdrn() throws IOException {
    Path adrn = finder.findUnique(ADRN);
    try(Input input = new Input(Files.newInputStream(adrn))) {
      return kryo.readObject(input, AdrnSet.class);
    }
  }

  @Nonnull
  private FileChannel openReal() throws IOException {
    Path real = finder.findUnique(REAL);
    return FileChannel.open(real, READ);
  }

  @Nonnull
  private Real readRealData(@Nonnull Adrn adrn) throws IOException {
    realChannel.position(adrn.getAddress());
    try(Input input = new Input(Channels.newInputStream(realChannel))) {
      return kryo.readObject(input, Real.class);
    }
  }



}