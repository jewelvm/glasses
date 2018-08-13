/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#ifndef _GNI_H
#define _GNI_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

#define GNI_VERSION_1   0x0FA10000

typedef
const struct GNI_Interface GNI_Interface;

struct GNI_Interface {
  jobject (JNICALL *Clone)(JNIEnv*,jobject);
  jobjectArray (JNICALL *GetStackTrace)(JNIEnv*);
  void (JNICALL *Wait)(JNIEnv*,jobject,jlong);
  void (JNICALL *Notify)(JNIEnv*,jobject);
  void (JNICALL *NotifyAll)(JNIEnv*,jobject);
  void (JNICALL *CompilerEnable)(JNIEnv*);
  void (JNICALL *CompilerDisable)(JNIEnv*);
  jobject (JNICALL *CompilerCommand)(JNIEnv*,jobject);
  jboolean (JNICALL *CompileClass)(JNIEnv*,jclass);
  jboolean (JNICALL *CompileClasses)(JNIEnv*,jstring);
  void (JNICALL *GC)(JNIEnv*);
  jlong (JNICALL *TotalMemory)(JNIEnv*);
  jlong (JNICALL *FreeMemory)(JNIEnv*);
  jlong (JNICALL *MaxMemory)(JNIEnv*);
  void (JNICALL *TraceMethodCalls)(JNIEnv*,jboolean);
  void (JNICALL *TraceInstructions)(JNIEnv*,jboolean);
  jint (JNICALL *IdentityHashCode)(JNIEnv*,jobject);
  jobject (JNICALL *CurrentThread)(JNIEnv*);
  void (JNICALL *ThreadYield)(JNIEnv*);
  jobject (JNICALL *CallerClass)(JNIEnv*);
  jobjectArray (JNICALL *CallStack)(JNIEnv*);
  jint (JNICALL *GetClassModifiers)(JNIEnv*,jclass);
  jobject (JNICALL *GetClassLoader)(JNIEnv*,jclass);
  jstring (JNICALL *GetClassName)(JNIEnv*,jclass);
  void (JNICALL *InitializeClass)(JNIEnv*,jclass);
  void (JNICALL *LinkClass)(JNIEnv*,jclass);
  jobjectArray (JNICALL *GetClassInterfaces)(JNIEnv*,jclass);
  jint (JNICALL *GetClassDimensions)(JNIEnv*,jclass);
  jobject (JNICALL *GetClassElement)(JNIEnv*,jclass);
  jboolean (JNICALL *HasStaticInitializer)(JNIEnv*,jclass);
  jclass (JNICALL *GetDeclaringClass)(JNIEnv*,jclass);
  jobjectArray (JNICALL *GetDeclaredClasses)(JNIEnv*,jclass);
  jobjectArray (JNICALL *GetDeclaredFields)(JNIEnv*,jclass);
  jobjectArray (JNICALL *GetDeclaredMethods)(JNIEnv*,jclass);
  jobjectArray (JNICALL *GetDeclaredConstructors)(JNIEnv*,jclass);
  jclass (JNICALL *DeriveClass)(JNIEnv*,jobject,jstring,jbyteArray,jint,jint);
  void (JNICALL *ThreadStart)(JNIEnv*,jobject,jint,jboolean,jlong);
  void (JNICALL *Exit)(JNIEnv*,jint);
  void (JNICALL *ChangeThreadPriority)(JNIEnv*,jobject,jint);
  jboolean (JNICALL *HoldsLock)(JNIEnv*,jobject);
};

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* _GNI_H */

