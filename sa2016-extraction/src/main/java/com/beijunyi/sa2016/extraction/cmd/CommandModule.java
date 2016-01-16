package com.beijunyi.sa2016.extraction.cmd;

import java.util.Arrays;
import java.util.Collection;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class CommandModule extends AbstractModule {

  private static final Collection<Class<? extends Command>> HANDLERS = Arrays.<Class<? extends Command>>asList(
    CountCommand.class, ExtractCommand.class
  );

  @Override
  protected void configure() {
    bind(EnvironmentContext.class).toInstance(new EnvironmentContext());

    Multibinder<Command> handlerBinder = Multibinder.newSetBinder(binder(), Command.class);
    for(Class<? extends Command> handler : HANDLERS)
      handlerBinder.addBinding().to(handler);
  }

}