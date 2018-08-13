/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#include <jni.h>

JNIEXPORT
jclass JNICALL JNU_ClassObject(JNIEnv *env)
{
  return (*env)->FindClass(env, "java/lang/Object");
}

JNIEXPORT
jclass JNICALL JNU_ClassClass(JNIEnv *env)
{
  return (*env)->FindClass(env, "java/lang/Class");
}

JNIEXPORT
jclass JNICALL JNU_ClassString(JNIEnv *env)
{
  return (*env)->FindClass(env, "java/lang/String");
}

JNIEXPORT
jclass JNICALL JNU_ClassThrowable(JNIEnv *env)
{
  return (*env)->FindClass(env, "java/lang/Throwable");
}

JNIEXPORT
void JNICALL JNU_ThrowByName(JNIEnv *env, const char *name, const char *message)
{
  jclass clazz;

  clazz = (*env)->FindClass(env, name);
  if (clazz == NULL)
    return;

  (*env)->ThrowNew(env, clazz, message);
  (*env)->DeleteLocalRef(env, clazz);
}

JNIEXPORT
void JNICALL JNU_ThrowByNameWithLastError(JNIEnv *env, const char *name, const char *message)
{
  jboolean erroneous;

  erroneous = errno != 0;

  if (erroneous) {
    message = strdup(strerror(errno));
    if (message == NULL)
      (*env)->FatalError(env, "memory exausted");
  }
  
  JNU_ThrowByName(env, name, message);
  
  if (erroneous)
    free((char*)message);
}

JNIEXPORT
void JNICALL JNU_ThrowIOException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/io/IOException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowIOExceptionWithLastError(JNIEnv *env, const char *message)
{
  JNU_ThrowByNameWithLastError(env, "java/io/IOException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNullPointerException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NullPointerException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowArrayIndexOutOfBoundsException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/ArrayIndexOutOfBoundsException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowStringIndexOutOfBoundsException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/StringIndexOutOfBoundsException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowIllegalArgumentException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/IllegalArgumentException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNumberFormatException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NumberFormatException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowClassNotFoundException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/ClassNotFoundException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNoSuchFieldException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NoSuchFieldException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNoSuchMethodException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NoSuchMethodException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowIllegalAccessException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/IllegalAccessException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowInstantiationException(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/InstantiationException", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNoSuchFieldError(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NoSuchFieldError", message);
}

JNIEXPORT
void JNICALL JNU_ThrowNoSuchMethodError(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/NoSuchMethodError", message);
}

JNIEXPORT
void JNU_ThrowIllegalAccessError(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/IllegalAccessError", message);
}

JNIEXPORT
void JNICALL JNU_ThrowOutOfMemoryError(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/OutOfMemoryError", message);
}

JNIEXPORT
void JNICALL JNU_ThrowInternalError(JNIEnv *env, const char *message)
{
  JNU_ThrowByName(env, "java/lang/InternalError", message);
}

JNIEXPORT
JNIEnv *JNICALL JNU_GetEnv(JavaVM *jvm, jint version)
{
  JNIEnv *env;
  jint error;

  error = (*jvm)->GetEnv(jvm, (void*)&env, version);
  if (error != JNI_OK)
    return NULL;
  return env;
}

JNIEXPORT
void JNICALL JNU_MonitorWait(JNIEnv *env, jobject object, jlong millis) 
{
  jclass clazz;
  jmethodID methodID;
    
  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return;

  methodID = (*env)->GetMethodID(env, clazz, "wait", "(J)V");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return;

  (*env)->CallVoidMethod(env, object, methodID, millis);
}

JNIEXPORT
void JNICALL JNU_Notify(JNIEnv *env, jobject object) 
{
  jclass clazz;
  jmethodID methodID;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return;

  methodID = (*env)->GetMethodID(env, clazz, "notify", "()V");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return;
  
  (*env)->CallVoidMethod(env, object, methodID);
}

JNIEXPORT
void JNICALL JNU_NotifyAll(JNIEnv *env, jobject object) 
{
  jclass clazz;
  jmethodID methodID;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return;

  methodID = (*env)->GetMethodID(env, clazz, "notifyAll", "()V");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return;
  
  (*env)->CallVoidMethod(env, object, methodID);
}

JNIEXPORT
jboolean JNICALL JNU_Equals(JNIEnv *env, jobject object, jobject another)
{
  jclass clazz;
  jmethodID methodID;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return JNI_FALSE;

  methodID = (*env)->GetMethodID(env, clazz, "equals", "(Ljava/lang/Object;)Z");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return JNI_FALSE;
  
  return (*env)->CallBooleanMethod(env, object, methodID, another);
}

JNIEXPORT
jstring JNICALL JNU_ToString(JNIEnv *env, jobject object)
{
  jclass clazz;
  jmethodID methodID;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return NULL;

  methodID = (*env)->GetMethodID(env, clazz, "toString", "()Ljava/lang/String;");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return NULL;
  
  return (*env)->CallObjectMethod(env, object, methodID);
}

JNIEXPORT
jboolean JNICALL JNU_IsInstanceOfByName(JNIEnv *env, jobject object, char *cname)
{
  jclass clazz;
  jboolean result;

  clazz = (*env)->FindClass(env, cname);
  if (clazz != NULL)
    return JNI_FALSE;

  result = (*env)->IsInstanceOf(env, object, clazz);
  (*env)->DeleteLocalRef(env, clazz);
  return result;
}

JNIEXPORT
jobject JNICALL JNU_NewObjectByName(JNIEnv *env, const char *cname, const char *descriptor, ...) 
{
  jclass clazz;
  jmethodID methodID;
  va_list list;
  jobject result;

  clazz = (*env)->FindClass(env, cname);
  if (clazz == NULL)
    return NULL;

  methodID = (*env)->GetMethodID(env, clazz, "<init>", descriptor);
  if (methodID == NULL) {
    (*env)->DeleteLocalRef(env, clazz);
    return NULL;
  }

  va_start(list, descriptor);
  result = (*env)->NewObjectV(env, clazz, methodID, list);
  va_end(list);
  
  (*env)->DeleteLocalRef(env, clazz);
  return result;
}

JNIEXPORT
jvalue JNICALL JNU_GetStaticFieldByName(JNIEnv *env, jboolean *exCheck, const char *cname, const char *name, const char *descriptor)
{
  jclass clazz;
  jfieldID fieldID;
  jvalue result;

  clazz = (*env)->FindClass(env, cname);
  if (clazz == NULL)
    return result;

  fieldID = (*env)->GetStaticFieldID(env, clazz, name, descriptor);
  if (fieldID == NULL) {
    (*env)->DeleteLocalRef(env, clazz);
    return result;
  }

  switch (descriptor[0]) {
  case '[':
  case 'L': result.l = (*env)->GetStaticObjectField(env, clazz, fieldID); break;
  case 'Z': result.z = (*env)->GetStaticBooleanField(env, clazz, fieldID); break;
  case 'B': result.b = (*env)->GetStaticByteField(env, clazz, fieldID); break;
  case 'C': result.c = (*env)->GetStaticCharField(env, clazz, fieldID); break;
  case 'S': result.s = (*env)->GetStaticShortField(env, clazz, fieldID); break;
  case 'I': result.i = (*env)->GetStaticIntField(env, clazz, fieldID); break;
  case 'J': result.j = (*env)->GetStaticLongField(env, clazz, fieldID); break;
  case 'F': result.f = (*env)->GetStaticFloatField(env, clazz, fieldID); break;
  case 'D': result.d = (*env)->GetStaticDoubleField(env, clazz, fieldID); break;
  }

  (*env)->DeleteLocalRef(env, clazz);

  if (exCheck != NULL)
    *exCheck = (*env)->ExceptionCheck(env);

  return result;
}

JNIEXPORT
void JNICALL JNU_SetStaticFieldByName(JNIEnv *env, jboolean *exCheck, const char *cname, const char *name, const char *descriptor, ...)
{
  jclass clazz;
  jfieldID fieldID;
  va_list list;

  clazz = (*env)->FindClass(env, cname);
  if (clazz == NULL)
    return;

  fieldID = (*env)->GetStaticFieldID(env, clazz, name, descriptor);
  if (fieldID == NULL) {
    (*env)->DeleteLocalRef(env, clazz);
    return;
  }

  va_start(list, descriptor);
  switch (descriptor[0]) {
  case '[':
  case 'L': (*env)->SetStaticObjectField(env, clazz, fieldID, va_arg(list, jobject)); break;      
  case 'Z': /* (*env)->SetStaticBooleanField(env, clazz, fieldID, va_arg(list, jboolean)); break; */
  case 'B': /* (*env)->SetStaticByteField(env, clazz, fieldID, va_arg(list, jbyte)); break; */
  case 'C': /* (*env)->SetStaticCharField(env, clazz, fieldID, va_arg(list, jchar)); break; */
  case 'S': /* (*env)->SetStaticShortField(env, clazz, fieldID, va_arg(list, jshort)); break; */
  case 'I': (*env)->SetStaticIntField(env, clazz, fieldID, va_arg(list, jint)); break;
  case 'J': (*env)->SetStaticLongField(env, clazz, fieldID, va_arg(list, jlong)); break;
  case 'F': /* (*env)->SetStaticFloatField(env, clazz, fieldID, va_arg(list, jfloat)); break; */
  case 'D': (*env)->SetStaticDoubleField(env, clazz, fieldID, va_arg(list, jdouble)); break;
  }
  va_end(list);

  (*env)->DeleteLocalRef(env, clazz);

  if (exCheck != NULL)
    *exCheck = (*env)->ExceptionCheck(env);
}

JNIEXPORT
jvalue JNICALL JNU_GetFieldByName(JNIEnv *env, jboolean *exCheck, jobject object, const char *name, const char *descriptor) 
{
  jclass clazz;
  jfieldID fieldID;
  jvalue result;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return result;

  fieldID = (*env)->GetFieldID(env, clazz, name, descriptor);
  (*env)->DeleteLocalRef(env, clazz);
  if (fieldID == NULL)
    return result;

  switch (descriptor[0]) {
  case '[':
  case 'L': result.l = (*env)->GetObjectField(env, object, fieldID); break;      
  case 'Z': result.z = (*env)->GetBooleanField(env, object, fieldID); break;
  case 'B': result.b = (*env)->GetByteField(env, object, fieldID); break;
  case 'C': result.c = (*env)->GetCharField(env, object, fieldID); break;
  case 'S': result.s = (*env)->GetShortField(env, object, fieldID); break;
  case 'I': result.i = (*env)->GetIntField(env, object, fieldID); break;
  case 'J': result.j = (*env)->GetLongField(env, object, fieldID); break;
  case 'F': result.f = (*env)->GetFloatField(env, object, fieldID); break;
  case 'D': result.d = (*env)->GetDoubleField(env, object, fieldID); break;
  }

  if (exCheck)
    *exCheck = (*env)->ExceptionCheck(env);

  return result;
}

JNIEXPORT
void JNICALL JNU_SetFieldByName(JNIEnv *env, jboolean *exCheck, jobject object, const char *name, const char *descriptor, ...)
{
  jclass clazz;
  jfieldID fieldID;
  va_list list;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return;

  fieldID = (*env)->GetFieldID(env, clazz, name, descriptor);
  (*env)->DeleteLocalRef(env, clazz);
  if (fieldID == NULL)
    return;

  va_start(list, descriptor);
  switch (descriptor[0]) {
  case '[':
  case 'L': (*env)->SetObjectField(env, object, fieldID, va_arg(list, jobject)); break;
  case 'Z': /* (*env)->SetBooleanField(env, object, fieldID, va_arg(list, jboolean)); break; */
  case 'B': /* (*env)->SetByteField(env, object, fieldID, va_arg(list, jbyte)); break; */
  case 'C': /* (*env)->SetCharField(env, object, fieldID, va_arg(list, jchar)); break; */
  case 'S': /* (*env)->SetShortField(env, object, fieldID, va_arg(list, jshort)); break; */
  case 'I': (*env)->SetIntField(env, object, fieldID, va_arg(list, jint)); break;
  case 'J': (*env)->SetLongField(env, object, fieldID, va_arg(list, jlong)); break;
  case 'F': /* (*env)->SetFloatField(env, object, fieldID, va_arg(list, jfloat)); break; */
  case 'D': (*env)->SetDoubleField(env, object, fieldID, va_arg(list, jdouble)); break;
  }
  va_end(list);

  if (exCheck != NULL)
    *exCheck = (*env)->ExceptionCheck(env);
}

JNIEXPORT
jvalue JNICALL JNU_CallStaticMethodByName(JNIEnv *env, jboolean *exCheck, const char *cname, const char *name, const char *descriptor, ...)
{
  jclass clazz;
  jmethodID methodID;
  va_list list;
  jvalue result;

  clazz = (*env)->FindClass(env, cname);
  if (clazz == NULL)
    return result;

  methodID = (*env)->GetStaticMethodID(env, clazz, name, descriptor);
  if (methodID == NULL) {
    (*env)->DeleteLocalRef(env, clazz);
    return result;
  }

  va_start(list, descriptor);
  switch (strchr(descriptor, ')')[1]) {
  case '[':
  case 'L': result.l = (*env)->CallStaticObjectMethodV(env, clazz, methodID, list); break;
  case 'Z': result.z = (*env)->CallStaticBooleanMethodV(env, clazz, methodID, list); break;
  case 'B': result.b = (*env)->CallStaticByteMethodV(env, clazz, methodID, list); break;
  case 'C': result.c = (*env)->CallStaticCharMethodV(env, clazz, methodID, list); break;
  case 'S': result.s = (*env)->CallStaticShortMethodV(env, clazz, methodID, list); break;
  case 'I': result.i = (*env)->CallStaticIntMethodV(env, clazz, methodID, list); break;
  case 'J': result.j = (*env)->CallStaticLongMethodV(env, clazz, methodID, list); break;
  case 'F': result.f = (*env)->CallStaticFloatMethodV(env, clazz, methodID, list); break;
  case 'D': result.d = (*env)->CallStaticDoubleMethodV(env, clazz, methodID, list); break;
  case 'V': (*env)->CallStaticVoidMethodV(env, clazz, methodID, list); break;
  }
  va_end(list);

  (*env)->DeleteLocalRef(env, clazz);

  if (exCheck != NULL)
    *exCheck = (*env)->ExceptionCheck(env);

  return result;    
}

JNIEXPORT
jvalue JNICALL JNU_CallMethodByNameV(JNIEnv *env, jboolean *exCheck, jobject object, const char *name, const char *descriptor, va_list list)
{
  jclass clazz;
  jmethodID methodID;
  jvalue result;

  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return result;

  methodID = (*env)->GetMethodID(env, clazz, name, descriptor);
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return result;

  switch (strchr(descriptor, ')')[1]) {
  case '[':
  case 'L': result.l = (*env)->CallObjectMethodV(env, object, methodID, list); break;
  case 'Z': result.z = (*env)->CallBooleanMethodV(env, object, methodID, list); break;
  case 'B': result.b = (*env)->CallByteMethodV(env, object, methodID, list); break;
  case 'C': result.c = (*env)->CallCharMethodV(env, object, methodID, list); break;
  case 'S': result.s = (*env)->CallShortMethodV(env, object, methodID, list); break;
  case 'I': result.i = (*env)->CallIntMethodV(env, object, methodID, list); break;
  case 'J': result.j = (*env)->CallLongMethodV(env, object, methodID, list); break;
  case 'F': result.f = (*env)->CallFloatMethodV(env, object, methodID, list); break;
  case 'D': result.d = (*env)->CallDoubleMethodV(env, object, methodID, list); break;
  case 'V': (*env)->CallVoidMethodV(env, object, methodID, list); break;
  }

  if (exCheck != NULL)
    *exCheck = (*env)->ExceptionCheck(env);

  return result;    
}

JNIEXPORT
jvalue JNICALL JNU_CallMethodByName(JNIEnv *env, jboolean *exCheck, jobject object, const char *name, const char *descriptor, ...)
{
  va_list list;
  jvalue result;

  va_start(list, descriptor);
  result = JNU_CallMethodByNameV(env, exCheck, object, name, descriptor, list);
  va_end(list);
  return result;    
}

JNIEXPORT
jstring JNICALL JNU_NewStringPlatform(JNIEnv *env, const char *chars)
{
  jint length;
  jbyteArray bytes;
  jclass clazz;
  jmethodID methodID;
  jstring result;

  length = strlen(chars);

  bytes = (*env)->NewByteArray(env, length);
  if (bytes == NULL)
    return NULL;

  (*env)->SetByteArrayRegion(env, bytes, 0, length, (jbyte*)chars);
  if ((*env)->ExceptionCheck(env)) {
    (*env)->DeleteLocalRef(env, bytes);
    return NULL;
  }

  clazz = JNU_ClassString(env);
  if (clazz == NULL) {
    (*env)->DeleteLocalRef(env, bytes);
    return NULL;
  }

  methodID = (*env)->GetMethodID(env, clazz, "<init>", "([B)V");
  if (methodID == NULL) {
    (*env)->DeleteLocalRef(env, clazz);
    (*env)->DeleteLocalRef(env, bytes);
    return NULL;
  }

  result = (*env)->NewObject(env, clazz, methodID, bytes);
  (*env)->DeleteLocalRef(env, clazz);
  (*env)->DeleteLocalRef(env, bytes);
  return result;
}

JNIEXPORT
const char *JNICALL JNU_GetStringPlatformChars(JNIEnv *env, jstring string, jboolean *isCopy)
{
  jclass clazz;
  jmethodID methodID;
  jbyteArray bytes;
  jint length;
  char *chars;

  clazz = (*env)->GetObjectClass(env, string);
  if (clazz == NULL)
    return NULL;

  methodID = (*env)->GetMethodID(env, clazz, "getBytes", "()[B");
  (*env)->DeleteLocalRef(env, clazz);
  if (methodID == NULL)
    return NULL;

  bytes = (*env)->CallObjectMethod(env, string, methodID);
  if ((*env)->ExceptionCheck(env))
    return NULL;

  if (bytes == NULL) {
    JNU_ThrowNullPointerException(env, NULL);
    return NULL;
  }

  length = (*env)->GetArrayLength(env, bytes);

  chars = (char*)malloc(length+1);
  if (chars == NULL)
    (*env)->FatalError(env, "memory exausted");

  (*env)->GetByteArrayRegion(env, bytes, 0, length, (jbyte*)chars);
  (*env)->DeleteLocalRef(env, bytes);
  if ((*env)->ExceptionCheck(env))
    return NULL;

  chars[length] = '\0';

  if (isCopy != NULL)
    *isCopy = JNI_TRUE;

  return chars;
}

JNIEXPORT
void JNICALL JNU_ReleaseStringPlatformChars(JNIEnv *env, jstring string, const char *chars)
{
  free((char*)chars);
}

JNIEXPORT
jint JNICALL JNU_CopyObjectArray(JNIEnv *env, jobjectArray target, jobjectArray source, jint length)
{
  jint i;
  jobject object;

  for (i = 0; i < length; i++) {
    object = (*env)->GetObjectArrayElement(env, source, i);
    (*env)->SetObjectArrayElement(env, target, i, object);
    (*env)->DeleteLocalRef(env, object);
  }
  return JNI_OK;
}

JNIEXPORT
void JNICALL JNU_PrintString(JNIEnv *env, const char *message, jstring string)
{
  const char *chars;
   
  chars = JNU_GetStringPlatformChars(env, string, NULL);
  if (chars == NULL)
    return;

  fprintf(stderr, "%s: %s\n", message, chars);

  JNU_ReleaseStringPlatformChars(env, string, chars);
}

JNIEXPORT
void JNICALL JNU_PrintClass(JNIEnv *env, const char *message, jobject object)
{
  jclass clazz;
  jstring string;
    
  clazz = (*env)->GetObjectClass(env, object);
  if (clazz == NULL)
    return;

  string = JNU_ToString(env, clazz);
  (*env)->DeleteLocalRef(env, clazz);
  if ((*env)->ExceptionCheck(env))
    return;

  JNU_PrintString(env, message, string);
  (*env)->DeleteLocalRef(env, string);
}

JNIEXPORT
int jio_fprintf(FILE *stream, const char *format, ...)
{
  va_list list;
  int result;

  va_start(list, format);
  result = fprintf(stream, format, list);
  va_end(list);
  return result;
}

JNIEXPORT
int jio_snprintf(char *str, size_t size, const char *format, ...)
{
  va_list list;
  int result;

  va_start(list, format);
#ifdef WIN32
  result = _vsnprintf(str, size, format, list);
#else
  result = vsnprintf(str, size, format, list);
#endif /* WIN32 */
  va_end(list);
  return result;
}

