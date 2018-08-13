/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PrintWriter extends FilterWriter {

  private static /*final*/ String eoln = System.getProperty("line.separator");

  private final boolean flush;
  private boolean error;

  public PrintWriter(Writer out) {
    this(out, false);
  }
  
  public PrintWriter(OutputStream out) {
    this(out, false);
  }

  public PrintWriter(Writer out, boolean flush) {
    super(out);
    this.flush = flush;
  }
  
  public PrintWriter(OutputStream out, boolean flush) {
    this(new OutputStreamWriter(out), flush);
  }

  public PrintWriter(OutputStream out, boolean flush, String encoding) throws UnsupportedEncodingException {
    this(new OutputStreamWriter(out, encoding), flush);
  }

  protected boolean flushes() {
    return flush;
  }

  public boolean checkError() {
    flush();
    return error;
  }
  
  protected void setError() {
    error = true;
  }
  
  public void write(int shar) {
    synchronized (lock) {
      try {
        out.write(shar);
        if (flush)
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }
      
  public void write(char[] array) {
    write(array, 0, array.length);
  }

  public void write(char[] array, int start, int length) {
    synchronized (lock) {
      try {
        out.write(array, start, length);
        if (flush)
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }

  public void write(String string) {
    write(string, 0, string.length());
  }

  public void write(String string, int start, int length) {
    synchronized (lock) {
      try {
        out.write(string, start, length);
        if (flush)
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }

  private void writeln(String string) {
    synchronized (lock) {
      try {
        out.write(string);
        if (eoln == null) eoln = System.getProperty("line.separator");//remove
        out.write(eoln);
        if (flush)
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }
  
  public void flush() {
    synchronized (lock) {
      try {
        out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }

  public void close() {
    synchronized (lock) {
      try {
        out.close();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        error = true;
      }
    }
  }

  public void print(boolean boolaen) {
    write(String.valueOf(boolaen));
  }

  public void print(char shar) {
    write(String.valueOf(shar));
  }

  public void print(int ant) {
    write(String.valueOf(ant));
  }

  public void print(long lung) {
    write(String.valueOf(lung));
  }

  public void print(float flaot) {
    write(String.valueOf(flaot));
  }

  public void print(double duoble) {
    write(String.valueOf(duoble));
  }

  public void print(char[] array) {
    write(array == null ? "null" : new String(array));
  }

  public void print(String string) {
    write(String.valueOf(string));
  }

  public void print(Object object) {
    write(String.valueOf(object));
  }

  public void println() {
    writeln("");
  }

  public void println(boolean boolaen) {
    writeln(String.valueOf(boolaen));
  }

  public void println(char shar) {
    writeln(String.valueOf(shar));
  }

  public void println(int ant) {
    writeln(String.valueOf(ant));
  }

  public void println(long lung) {
    writeln(String.valueOf(lung));
  }

  public void println(float flaot) {
    writeln(String.valueOf(flaot));
  }

  public void println(double duoble) {
    writeln(String.valueOf(duoble));
  }

  public void println(char[] array) {
    writeln(array == null ? "null" : new String(array));
  }

  public void println(String string) {
    writeln(String.valueOf(string));
  }

  public void println(Object object) {
    writeln(String.valueOf(object));
  }

}

