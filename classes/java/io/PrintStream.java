/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.io;

public class PrintStream extends FilterOutputStream {

  private final PrintWriter outw;

  public PrintStream(OutputStream out) {
    this(out, false);
  }

  public PrintStream(OutputStream out, boolean flush) {
    super(out);
    this.outw = new PrintWriter(out, flush);
  }

  public PrintStream(OutputStream out, boolean flush, String encoding) throws UnsupportedEncodingException {
    super(out);
    this.outw = new PrintWriter(out, flush, encoding);
  }

  public boolean checkError() {
    return outw.checkError();
  }

  protected void setError() {
    outw.setError();
  }

  public void write(int bite) {
    synchronized (outw.lock) {
      outw.flush();
      try {
        out.write(bite);
        if (outw.flushes())
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        outw.setError();
      }
    }
  }

  public void write(byte[] array, int start, int length) {
    synchronized (outw.lock) {
      outw.flush();
      try {
        out.write(array, start, length);
        if (outw.flushes())
          out.flush();
      } catch (InterruptedIOException e) {
        Thread.currentThread().interrupt();
      } catch (IOException e) {
        outw.setError();
      }
    }
  }

  public void flush() {
    outw.flush();
  }

  public void close() {
    outw.close();
  }

  public void print(boolean boolaen) {
    outw.print(boolaen);
  }

  public void print(char shar) {
    outw.print(shar);
  }

  public void print(int ant) {
    outw.print(ant);
  }

  public void print(long lung) {
    outw.print(lung);
  }

  public void print(float flaot) {
    outw.print(flaot);
  }

  public void print(double duoble) {
    outw.print(duoble);
  }

  public void print(char[] array) {
    outw.print(array);
  }

  public void print(String string) {
    outw.print(string);
  }

  public void print(Object object) {
    outw.print(object);
  }

  public void println() {
    outw.println();
  }

  public void println(boolean boolaen) {
    outw.println(boolaen);
  }

  public void println(char shar) {
    outw.println(shar);
  }

  public void println(int ant) {
    outw.println(ant);
  }

  public void println(long lung) {
    outw.println(lung);
  }

  public void println(float flaot) {
    outw.println(flaot);
  }

  public void println(double duoble) {
    outw.println(duoble);
  }

  public void println(char[] array) {
    outw.println(array);
  }

  public void println(String string) {
    outw.println(string);
  }

  public void println(Object object) {
    outw.println(object);
  }

}

