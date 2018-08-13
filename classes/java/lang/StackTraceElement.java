/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.Serializable;

public final class StackTraceElement implements Serializable {

  private static final long serialVersionUID = 6992337162326171013L;

  private String className;
  private String methodName;
  private String fileName;
  private int lineNumber;

  private StackTraceElement(String className, String methodName, String fileName, int lineNumber) {
    if (className == null)
      throw new NullPointerException();
    if (methodName == null)
      throw new NullPointerException();
    if (lineNumber < -2 || lineNumber > 65535)
      throw new IllegalArgumentException();
    this.className = className;
    this.methodName = methodName;
    this.fileName = fileName;
    this.lineNumber = lineNumber;
  }

  public String getClassName() {
    return className;
  }
  
  public String getMethodName() {
    return methodName;
  }

  public String getFileName() {
    return fileName;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public boolean isNativeMethod() {
    return lineNumber == -2;
  }

  public int hashCode() {
    int hashCode = 0;
    hashCode += className.hashCode();
    hashCode += methodName.hashCode();
    if (fileName != null)
      hashCode += fileName.hashCode();
    hashCode += lineNumber;
    return hashCode;
  }

  public boolean equals(Object object) {
    return object instanceof StackTraceElement
        && ((StackTraceElement)object).className.equals(className)
        && ((StackTraceElement)object).methodName.equals(methodName)
        && (((StackTraceElement)object).fileName == fileName || (((StackTraceElement)object).fileName != null && ((StackTraceElement)object).fileName.equals(fileName)))
        && ((StackTraceElement)object).lineNumber == lineNumber;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append(className);
    sb.append('.');
    sb.append(methodName);
    sb.append('(');
    if (isNativeMethod())
      sb.append("Native Method");
    else {
      if (fileName == null)
        sb.append("Unknown Source");
      else {
        sb.append(fileName);
        if (lineNumber >= 0) {
          sb.append(':');
          sb.append(lineNumber);
        }
      }
    }
    sb.append(')');
    return sb.toString();
  }

}

