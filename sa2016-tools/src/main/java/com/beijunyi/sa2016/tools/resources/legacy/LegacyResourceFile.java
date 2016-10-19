package com.beijunyi.sa2016.tools.resources.legacy;

import javax.annotation.Nonnull;

import com.beijunyi.sa2016.tools.resources.ResourceSignature;

import static com.beijunyi.sa2016.tools.resources.ResourceSignature.regexPattern;
import static com.beijunyi.sa2016.tools.resources.ResourceSignature.stringHeader;
import static com.beijunyi.sa2016.tools.resources.legacy.LegacyResourceLocation.*;
import static com.beijunyi.sa2016.tools.legacy.Palet.PALET_PATTEN;

public enum LegacyResourceFile {
  LS2MAP(SERVER_DATA_MAP, stringHeader("LS2MAP")),
  ADRN(CLIENT_DATA, regexPattern("^adrn_(\\d+)\\.bin$")),
  REAL(CLIENT_DATA, regexPattern("^real_(\\d+)\\.bin$")),
  SPR_ADRN(CLIENT_DATA, regexPattern("^spradrn_(\\d+)\\.bin$")),
  SPR(CLIENT_DATA, regexPattern("^spr_(\\d+)\\.bin$")),
  PALET(CLIENT_DATA_PAL, regexPattern(PALET_PATTEN)),
  ;

  private final LegacyResourceLocation location;
  private final ResourceSignature signature;

  LegacyResourceFile(@Nonnull LegacyResourceLocation location, @Nonnull ResourceSignature signature) {
    this.location = location;
    this.signature = signature;
  }

  @Nonnull
  public LegacyResourceLocation getLocation() {
    return location;
  }

  @Nonnull
  public ResourceSignature getSignature() {
    return signature;
  }

}
