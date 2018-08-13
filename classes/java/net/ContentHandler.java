/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;

public abstract class ContentHandler {

  protected ContentHandler() { }

  public abstract Object getContent(URLConnection connection) throws IOException;

  public Object getContent(URLConnection connection, Class[] classes) throws IOException {
    Object object = getContent(connection);
    for (int i = 0; i < classes.length; i++)
      if (classes[i].isInstance(object))
        return object;
    return null;
  }

}

