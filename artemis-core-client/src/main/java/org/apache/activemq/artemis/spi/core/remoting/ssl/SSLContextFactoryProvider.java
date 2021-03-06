/*
 * Copyright 2020 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.artemis.spi.core.remoting.ssl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Provider that loads the SSLContextFactory services and return the one with the highest priority.
 * This is only used to provide SSLContext, so it doesn't support OpenSSL.
 */
public class SSLContextFactoryProvider {
   private static final SSLContextFactory factory;
   static {
      ServiceLoader<SSLContextFactory> loader = ServiceLoader.load(SSLContextFactory.class, Thread.currentThread().getContextClassLoader());
      final List<SSLContextFactory> factories = new ArrayList<>();
      loader.forEach(factories::add);
      Collections.sort(factories);
      factory = factories.get(factories.size() - 1);
   }
   /**
    * @return the SSLContextFactory with the higher priority.
    */
   public static SSLContextFactory getSSLContextFactory() {
      return factory;
   }
}
