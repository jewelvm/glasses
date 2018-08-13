/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.net;

import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;

public abstract class HttpURLConnection extends URLConnection {

  public static final int HTTP_OK = 200;
  public static final int HTTP_CREATED = 201;
  public static final int HTTP_ACCEPTED = 202;
  public static final int HTTP_NOT_AUTHORITATIVE = 203; 
  public static final int HTTP_NO_CONTENT = 204;
  public static final int HTTP_RESET = 205;
  public static final int HTTP_PARTIAL = 206;

  public static final int HTTP_MULT_CHOICE = 300;
  public static final int HTTP_MOVED_PERM = 301;
  public static final int HTTP_MOVED_TEMP = 302;
  public static final int HTTP_SEE_OTHER = 303;
  public static final int HTTP_NOT_MODIFIED = 304;
  public static final int HTTP_USE_PROXY = 305;

  public static final int HTTP_BAD_REQUEST = 400;
  public static final int HTTP_UNAUTHORIZED = 401;
  public static final int HTTP_PAYMENT_REQUIRED = 402;
  public static final int HTTP_FORBIDDEN = 403;
  public static final int HTTP_NOT_FOUND = 404;
  public static final int HTTP_BAD_METHOD = 405;
  public static final int HTTP_NOT_ACCEPTABLE = 406;
  public static final int HTTP_PROXY_AUTH = 407;
  public static final int HTTP_CLIENT_TIMEOUT = 408;
  public static final int HTTP_CONFLICT = 409;
  public static final int HTTP_GONE = 410;
  public static final int HTTP_LENGTH_REQUIRED = 411;
  public static final int HTTP_PRECON_FAILED = 412;
  public static final int HTTP_ENTITY_TOO_LARGE = 413;
  public static final int HTTP_REQ_TOO_LONG = 414;
  public static final int HTTP_UNSUPPORTED_TYPE = 415;

  public static final int HTTP_SERVER_ERROR = 500;
  public static final int HTTP_INTERNAL_ERROR = 500;
  public static final int HTTP_NOT_IMPLEMENTED = 501;
  public static final int HTTP_BAD_GATEWAY = 502;
  public static final int HTTP_UNAVAILABLE = 503;
  public static final int HTTP_GATEWAY_TIMEOUT = 504;
  public static final int HTTP_VERSION = 505;

  private static boolean defaultFollowRedirects = true;

  public static void setFollowRedirects(boolean defaultFollowRedirects) {
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkSetFactory();
    HttpURLConnection.defaultFollowRedirects = defaultFollowRedirects;
  }

  public static boolean getFollowRedirects() {
    return defaultFollowRedirects;
  }

  protected String method = "GET";
  protected boolean instanceFollowRedirects = defaultFollowRedirects;
  protected int responseCode = -1;
  protected String responseMessage = null;

  protected HttpURLConnection (URL url) {
    super(url);
  }
    
  public Permission getPermission() throws IOException {
    //return new SocketPermission(url.getHost()+":"+url.getPort(), "connect");
    return null;
  }

  public abstract void disconnect();
  public abstract boolean usingProxy();

  public int getResponseCode() throws IOException {
    if (responseCode == -1) {
      String header = getHeaderField(0);
      int begin = header.indexOf(' ');
      int end = header.indexOf(' ', begin+1);
      if (begin != -1 && end != -1)
        try {
          responseCode = Integer.parseInt(header.substring(begin, end));
        } catch (NumberFormatException e) { }
    }
    return responseCode;
  }
  
  public String getResponseMessage() throws IOException {
    if (responseMessage == null) {
      String header = getHeaderField(0);
      int begin = header.indexOf(' ');
      begin = header.indexOf(' ', begin+1);
      if (begin != -1)
        responseMessage = header.substring(begin+1).trim();
    }
    return responseMessage;
  }

  public InputStream getErrorStream() {
    return null;
  }

  public void setRequestMethod(String method) throws ProtocolException {
    if (!method.equals("GET") && !method.equals("POST") && !method.equals("HEAD") && !method.equals("OPTIONS") && !method.equals("PUT") && !method.equals("DELETE") && !method.equals("TRACE"))
      throw new ProtocolException("Invalid method");
    this.method = method;
  }

  public String getRequestMethod() {
    return method;
  }
    
  public void setInstanceFollowRedirects(boolean instanceFollowRedirects) {
    this.instanceFollowRedirects = instanceFollowRedirects;
  }

  public boolean getInstanceFollowRedirects() {
    return instanceFollowRedirects;
  }

}

