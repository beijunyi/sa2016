package com.beijunyi.sa2016.tools.converters;

import com.beijunyi.sa2016.tools.converters.characters.CharacterExtractor;
import com.beijunyi.sa2016.tools.converters.images.ImageExtractor;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ExtractorModule extends AbstractModule {

  private static final ImmutableCollection<Class<? extends AssetExtractor>> EXTRACTORS = ImmutableSet.of(
    CharacterExtractor.class,
    ImageExtractor.class
  );

  @Override
  protected void configure() {
    Multibinder<AssetExtractor> binder = Multibinder.newSetBinder(binder(), AssetExtractor.class);
    for(Class<? extends AssetExtractor> e : EXTRACTORS) binder.addBinding().to(e);
  }

}
