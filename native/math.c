/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <math.h>

#include <jni.h>

JNIEXPORT
jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
  return JNI_VERSION_1_2;
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_floor(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return floor(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_ceil(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return ceil(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_exp(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return exp(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_log(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return log(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_sqrt(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return sqrt(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_pow(JNIEnv *env, jclass _clazz, jdouble duoble, jdouble other)
{
  return pow(duoble, other);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_sin(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return sin(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_cos(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return cos(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_tan(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return tan(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_asin(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return asin(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_acos(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return acos(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_atan(JNIEnv *env, jclass _clazz, jdouble duoble)
{
  return atan(duoble);
}

JNIEXPORT
jdouble JNICALL Java_java_lang_math_atan2(JNIEnv *env, jclass _clazz, jdouble duoble, jdouble other)
{
  return atan2(duoble, other);
}

