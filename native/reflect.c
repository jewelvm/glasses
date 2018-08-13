/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <stdlib.h>

#include <jni.h>

JNIEXPORT
jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
  return JNI_VERSION_1_2;
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_reflect_reflect_newArray(JNIEnv *env, jclass _clazz, jclass elementType, jint length)
{
  return (*env)->NewObjectArray(env, length, elementType, NULL);
}

static
jvalue *UnwrapArguments(JNIEnv *env, jobjectArray args)
{
  jclass booleanClass;
  jmethodID booleanID;
  jclass byteClass;
  jmethodID byteID;
  jclass charClass;
  jmethodID charID;
  jclass shortClass;
  jmethodID shortID;
  jclass intClass;
  jmethodID intID;
  jclass longClass;
  jmethodID longID;
  jclass floatClass;
  jmethodID floatID;
  jclass doubleClass;
  jmethodID doubleID;
  int length;
  int i;
  jvalue *values;
  jclass omclass;
  jobject arg;

  booleanClass = (*env)->FindClass(env, "java/lang/Boolean");
  if (booleanClass == NULL) return NULL;
  booleanID = (*env)->GetMethodID(env, booleanClass, "booleanValue", "()Z");
  if (booleanID == NULL) return NULL;

  byteClass = (*env)->FindClass(env, "java/lang/Byte");
  if (byteClass == NULL) return NULL;
  byteID = (*env)->GetMethodID(env, byteClass, "byteValue", "()B");
  if (byteID == NULL) return NULL;

  charClass = (*env)->FindClass(env, "java/lang/Character");
  if (charClass == NULL) return NULL;
  charID = (*env)->GetMethodID(env, charClass, "charValue", "()C");
  if (charID == NULL) return NULL;

  shortClass = (*env)->FindClass(env, "java/lang/Short");
  if (shortClass == NULL) return NULL;
  shortID = (*env)->GetMethodID(env, shortClass, "shortValue", "()S");
  if (shortID == NULL) return NULL;

  intClass = (*env)->FindClass(env, "java/lang/Integer");
  if (intClass == NULL) return NULL;
  intID = (*env)->GetMethodID(env, intClass, "intValue", "()I");
  if (intID == NULL) return NULL;

  longClass = (*env)->FindClass(env, "java/lang/Long");
  if (longClass == NULL) return NULL;
  longID = (*env)->GetMethodID(env, longClass, "longValue", "()J");
  if (longID == NULL) return NULL;

  floatClass = (*env)->FindClass(env, "java/lang/Float");
  if (floatClass == NULL) return NULL;
  floatID = (*env)->GetMethodID(env, floatClass, "floatValue", "()F");
  if (floatID == NULL) return NULL;

  doubleClass = (*env)->FindClass(env, "java/lang/Double");
  if (doubleClass == NULL) return NULL;
  doubleID = (*env)->GetMethodID(env, doubleClass, "doubleValue", "()D");
  if (doubleID == NULL) return NULL;

  length = (*env)->GetArrayLength(env, args);
  if ((*env)->ExceptionCheck(env))
    return NULL;

  values = (jvalue*)calloc(length, sizeof(jvalue));
  if (values == NULL) {
    omclass = (*env)->FindClass(env, "java/lang/OutOfMemoryError");
    if (omclass == NULL)
      return NULL;
    (*env)->ThrowNew(env, omclass, NULL);
    return NULL;
  }

  for (i = 0; i < length; i++) {
    arg = (*env)->GetObjectArrayElement(env, args, i);
    if ((*env)->ExceptionCheck(env))
      return NULL;

    if ((*env)->IsInstanceOf(env, arg, booleanClass)) {
      values[i].z = (*env)->CallBooleanMethod(env, arg, booleanID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, byteClass)) {
      values[i].b = (*env)->CallByteMethod(env, arg, byteID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, charClass)) {
      values[i].c = (*env)->CallCharMethod(env, arg, charID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, shortClass)) {
      values[i].s = (*env)->CallShortMethod(env, arg, shortID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, intClass)) {
      values[i].i = (*env)->CallIntMethod(env, arg, intID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, longClass)) {
      values[i].j = (*env)->CallLongMethod(env, arg, longID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, floatClass)) {
      values[i].f = (*env)->CallFloatMethod(env, arg, floatID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    if ((*env)->IsInstanceOf(env, arg, doubleClass)) {
      values[i].d = (*env)->CallDoubleMethod(env, arg, doubleID);
      (*env)->DeleteLocalRef(env, arg);
      if ((*env)->ExceptionCheck(env))
        return NULL;
      continue;
    }

    values[i].l = (*env)->GetObjectArrayElement(env, arg, 0);
    (*env)->DeleteLocalRef(env, arg);
    if ((*env)->ExceptionCheck(env))
      return NULL;
  }

  return values;
}

JNIEXPORT
jobject JNICALL Java_java_lang_reflect_reflect_newInstance(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jobject result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return NULL;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return NULL;

  result = (*env)->NewObjectA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_lang_reflect_reflect_getBoolean(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jboolean result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return JNI_FALSE;

  if (object != NULL)
    result = (*env)->GetBooleanField(env, object, fieldID);
  else
    result = (*env)->GetStaticBooleanField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jchar JNICALL Java_java_lang_reflect_reflect_getChar(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jchar result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetCharField(env, object, fieldID);
  else
    result = (*env)->GetStaticCharField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jbyte JNICALL Java_java_lang_reflect_reflect_getByte(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jbyte result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetByteField(env, object, fieldID);
  else
    result = (*env)->GetStaticByteField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jshort JNICALL Java_java_lang_reflect_reflect_getShort(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jshort result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetShortField(env, object, fieldID);
  else
    result = (*env)->GetStaticShortField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jint JNICALL Java_java_lang_reflect_reflect_getInt(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jint result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetIntField(env, object, fieldID);
  else
    result = (*env)->GetStaticIntField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jlong JNICALL Java_java_lang_reflect_reflect_getLong(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jlong result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetLongField(env, object, fieldID);
  else
    result = (*env)->GetStaticLongField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jfloat JNICALL Java_java_lang_reflect_reflect_getFloat(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jfloat result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetFloatField(env, object, fieldID);
  else
    result = (*env)->GetStaticFloatField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jdouble JNICALL Java_java_lang_reflect_reflect_getDouble(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jdouble result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->GetDoubleField(env, object, fieldID);
  else
    result = (*env)->GetStaticDoubleField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
jobject JNICALL Java_java_lang_reflect_reflect_getObject(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object)
{
  jfieldID fieldID;
  jobject result;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return NULL;

  if (object != NULL)
    result = (*env)->GetObjectField(env, object, fieldID);
  else
    result = (*env)->GetStaticObjectField(env, clazz, fieldID);

  return result;
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setBoolean(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jboolean value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetBooleanField(env, object, fieldID, value);
  else
    (*env)->SetStaticBooleanField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setChar(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jchar value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetCharField(env, object, fieldID, value);
  else
    (*env)->SetStaticCharField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setByte(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jbyte value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetByteField(env, object, fieldID, value);
  else
    (*env)->SetStaticByteField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setShort(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jshort value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetShortField(env, object, fieldID, value);
  else
    (*env)->SetStaticShortField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setInt(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jint value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetIntField(env, object, fieldID, value);
  else
    (*env)->SetStaticIntField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setLong(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jlong value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetLongField(env, object, fieldID, value);
  else
    (*env)->SetStaticLongField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setFloat(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jfloat value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetFloatField(env, object, fieldID, value);
  else
    (*env)->SetStaticFloatField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setDouble(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jdouble value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetDoubleField(env, object, fieldID, value);
  else
    (*env)->SetStaticDoubleField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_setObject(JNIEnv *env, jclass _clazz, jclass clazz, jobject field, jobject object, jobject value)
{
  jfieldID fieldID;

  fieldID = (*env)->FromReflectedField(env, field);
  if (fieldID == NULL)
    return;

  if (object != NULL)
    (*env)->SetObjectField(env, object, fieldID, value);
  else
    (*env)->SetStaticObjectField(env, clazz, fieldID, value);
}

JNIEXPORT
void JNICALL Java_java_lang_reflect_reflect_invokeVoid(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return;

  if (object != NULL)
    (*env)->CallVoidMethodA(env, object, methodID, values);
  else
    (*env)->CallStaticVoidMethodA(env, clazz, methodID, values);
  
  free(values);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_reflect_reflect_invokeBoolean(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jboolean result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return JNI_FALSE;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return JNI_FALSE;

  if (object != NULL)
    result = (*env)->CallBooleanMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticBooleanMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jbyte JNICALL Java_java_lang_reflect_reflect_invokeByte(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jbyte result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallByteMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticByteMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jchar JNICALL Java_java_lang_reflect_reflect_invokeChar(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jchar result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallCharMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticCharMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jshort JNICALL Java_java_lang_reflect_reflect_invokeShort(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jshort result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallShortMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticShortMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jint JNICALL Java_java_lang_reflect_reflect_invokeInt(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jint result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallIntMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticIntMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jlong JNICALL Java_java_lang_reflect_reflect_invokeLong(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jlong result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallLongMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticLongMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jfloat JNICALL Java_java_lang_reflect_reflect_invokeFloat(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jfloat result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallFloatMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticFloatMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jdouble JNICALL Java_java_lang_reflect_reflect_invokeDouble(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jdouble result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallDoubleMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticDoubleMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

JNIEXPORT
jobject JNICALL Java_java_lang_reflect_reflect_invokeObject(JNIEnv *env, jclass _clazz, jclass clazz, jobject method, jobject object, jobjectArray args)
{
  jmethodID methodID;
  jvalue *values;
  jobject result;

  methodID = (*env)->FromReflectedMethod(env, method);
  if (methodID == NULL)
    return 0;

  values = UnwrapArguments(env, args);
  if (values == NULL)
    return 0;

  if (object != NULL)
    result = (*env)->CallObjectMethodA(env, object, methodID, values);
  else
    result = (*env)->CallStaticObjectMethodA(env, clazz, methodID, values);
  
  free(values);
  
  return result;
}

