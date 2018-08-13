/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <limits.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <dirent.h>

#ifdef WIN32
#include <windows.h>
#endif /* WIN32 */

#include <jni.h>

JNIEXPORT
jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
  return JNI_VERSION_1_2;
}

JNIEXPORT
void JNICALL Java_java_io_io_write(JNIEnv *env, jclass _clazz, jint id, jbyteArray array, jint start, jint length)
{
  jbyte *elements;
  jint result;
  jclass clazz;

  elements = (*env)->GetByteArrayElements(env, array, NULL);
  if (elements == NULL)
    return;

  result = write(id, (char*)&elements[start], length);

  (*env)->ReleaseByteArrayElements(env, array, elements, JNI_ABORT);

  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }
}

JNIEXPORT
jint JNICALL Java_java_io_io_read(JNIEnv *env, jclass _clazz, jint id, jbyteArray array, jint start, jint length)
{
  jbyte *elements;
  jint result;
  jclass clazz;

  elements = (*env)->GetByteArrayElements(env, array, NULL);
  if (elements == NULL)
    return 0;

  result = read(id, (char*)&elements[start], length);
  
  (*env)->ReleaseByteArrayElements(env, array, elements, JNI_COMMIT);
  
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  if (result == 0)
    result = -1;

  return result;
}

JNIEXPORT
jint JNICALL Java_java_io_io_create(JNIEnv *env, jclass _clazz, jstring string, jboolean append)
{
  const char *path;
  struct stat sb;
  jint id;
  jclass clazz;
  int flags;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return 0;

#ifdef WIN32
  flags = O_BINARY|O_NOINHERIT;
#else
  flags = 0;
#endif /* WIN32 */

  id = open(path, O_WRONLY|O_CREAT|(append ? O_APPEND : O_TRUNC)|flags, 0666);
  if (id != -1) {
    if (fstat(id, &sb) != -1) {
      if ((sb.st_mode & S_IFMT) == S_IFDIR) {
        close(id);
        id = -1;
      }
    } else {
      close(id);
      id = -1;
    }
  }

  if (id == -1) {
    clazz = (*env)->FindClass(env, "java/io/FileNotFoundException");
    if (clazz == NULL) {
      (*env)->ReleaseStringUTFChars(env, string, path);
      return 0;
    }
    (*env)->ThrowNew(env, clazz, path);
  }

  (*env)->ReleaseStringUTFChars(env, string, path);

  return id;
}

JNIEXPORT
jint JNICALL Java_java_io_io_open(JNIEnv *env, jclass _clazz, jstring string, jboolean write)
{
  const char *path;
  struct stat sb;
  jint id;
  jclass clazz;
  int flags;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return 0;

#ifdef WIN32
  flags = O_BINARY|O_NOINHERIT;
#else
  flags = 0;
#endif /* WIN32 */

  id = open(path, (write ? O_RDWR|O_CREAT : O_RDONLY)|flags, 0666);
  if (id != -1) {
    if (fstat(id, &sb) != -1) {
      if ((sb.st_mode & S_IFMT) == S_IFDIR) {
        close(id);
        id = -1;
      }
    } else {
      close(id);
      id = -1;
    }
  }

  if (id == -1) {
    clazz = (*env)->FindClass(env, "java/io/FileNotFoundException");
    if (clazz == NULL) {
      (*env)->ReleaseStringUTFChars(env, string, path);
      return 0;
    }
    (*env)->ThrowNew(env, clazz, path);
  }

  (*env)->ReleaseStringUTFChars(env, string, path);

  return id;
}

JNIEXPORT
void JNICALL Java_java_io_io_seek(JNIEnv *env, jclass _clazz, jint id, jlong offset)
{
  jlong result;
  jclass clazz;

  result = lseek(id, offset, SEEK_SET);
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, "Seek error");
  }
}

JNIEXPORT
jlong JNICALL Java_java_io_io_tell(JNIEnv *env, jclass _clazz, jint id)
{
  jlong result;
  jclass clazz;

  result = lseek(id, 0, SEEK_CUR);
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, "Seek error");
    return 0;
  }
  return result;
}

JNIEXPORT
jlong JNICALL Java_java_io_io_length__I(JNIEnv *env, jclass _clazz, jint id)
{
  jlong offset;
  jlong result;
  jclass clazz;

  offset = lseek(id, 0, SEEK_CUR);
  if (offset == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, "Seek error");
    return 0;
  }
  result = lseek(id, 0, SEEK_END);
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, "Seek error");
    return 0;
  }
  offset = lseek(id, offset, SEEK_SET);
  if (offset == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, "Seek error");
    return 0;
  }
  return result;
}

JNIEXPORT
void JNICALL Java_java_io_io_close(JNIEnv *env, jclass _clazz, jint id)
{
  jint result;
  jclass clazz;

  result = close(id);
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, "Close error");
  }
}

JNIEXPORT
jint JNICALL Java_java_io_io_plaf(JNIEnv *env, jclass _clazz)
{
#ifdef WIN32
  return 2;
#else
  return 1;
#endif /* WIN32 */
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_isDirectory(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  attr = GetFileAttributes(path);
  if (attr != -1)
    result = (attr & FILE_ATTRIBUTE_DIRECTORY) != 0;
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = (sb.st_mode & S_IFMT) == S_IFDIR;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_isFile(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  attr = GetFileAttributes(path);
  if (attr != -1)
    result = (attr & FILE_ATTRIBUTE_DIRECTORY) == 0;
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = (sb.st_mode & S_IFMT) == S_IFREG;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_isHidden(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  attr = GetFileAttributes(path);
  if (attr != -1)
    result = (attr & FILE_ATTRIBUTE_HIDDEN) != 0;
}
#else
  if (path[0] == '.')
    result = JNI_TRUE;
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_exists(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  attr = GetFileAttributes(path);
  if (attr != -1)
      result = JNI_TRUE;
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = JNI_TRUE;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_canRead(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = access(path, R_OK) == 0;

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_canWrite(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = access(path, W_OK) == 0;

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jlong JNICALL Java_java_io_io_length__Ljava_lang_String_2(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jlong result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return 0;

  result = 0;

#ifdef WIN32
{
//  struct stat sb;
  int id;

// bug in mingw32 stat implementation?
//  if (stat(path, &sb) == 0)
//    result = sb.st_size;

  id = open(path, O_RDONLY|O_BINARY|O_NOINHERIT, 0666);
  if (id != -1) {
    result = lseek(id, 0, SEEK_END);
    close(id);
  }
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = sb.st_size;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_createNewFile(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  int id;
  jclass clazz;
  int flags;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

#ifdef WIN32
  flags = O_BINARY|O_NOINHERIT;
#else
  flags = 0;
#endif /* WIN32 */
  
  id = open(path, O_RDWR|O_CREAT|O_EXCL|flags, 0666);

  (*env)->ReleaseStringUTFChars(env, string, path);

  if (id != -1) {
    close(id);
    return JNI_TRUE;
  }

  if (errno != EEXIST) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return JNI_FALSE;
    (*env)->ThrowNew(env, clazz, "Create file exclusively error");
    return JNI_FALSE;
  }

  return JNI_FALSE;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_renameTo(JNIEnv *env, jclass _clazz, jstring string1, jstring string2)
{
  const char *path1;
  const char *path2;
  jboolean result;

  path1 = (*env)->GetStringUTFChars(env, string1, NULL);
  if (path1 == NULL)
    return JNI_FALSE;

  path2 = (*env)->GetStringUTFChars(env, string2, NULL);
  if (path2 == NULL) {
    (*env)->ReleaseStringUTFChars(env, string1, path1);
    return JNI_FALSE;
  }

  result = rename(path1, path2) == 0;

  (*env)->ReleaseStringUTFChars(env, string1, path1);
  (*env)->ReleaseStringUTFChars(env, string2, path2);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_setReadOnly(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  attr = GetFileAttributes(path);
  if (attr != -1)
    result = SetFileAttributes(path, attr|FILE_ATTRIBUTE_READONLY) == 0;
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = chmod(path, sb.st_mode & ~(S_IWUSR|S_IWGRP|S_IWOTH)) == 0;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_delete(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

#ifdef WIN32
{
  DWORD attr;

  SetFileAttributes(path, 0);
  attr = GetFileAttributes(path);
  if (attr == 0xffffffff)
    result = JNI_FALSE;
  else {
    if (attr & FILE_ATTRIBUTE_DIRECTORY)
      result = RemoveDirectory(path) == 0;
    else
      result = DeleteFile(path) == 0;
  }
}
#else
  result = remove(path) == 0;
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jstring JNICALL Java_java_io_io_canonicalize(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  const char *canpath;
  char buffer[PATH_MAX];
  jstring canonical;
  jclass clazz;
  
  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return NULL;

#ifdef WIN32
  canpath = _fullpath(buffer, path, sizeof(buffer));
  // needs to improve
#else
  canpath = realpath(path, buffer);
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  if (canpath == NULL) {
    clazz = (*env)->FindClass(env, "java/io/IOException");
    if (clazz == NULL)
      return NULL;
    (*env)->ThrowNew(env, clazz, "Canonicalize error");
    return NULL;
  }

  canonical = (*env)->NewStringUTF(env, canpath);
  if (canonical== NULL)
    return NULL;

  return canonical;
}

JNIEXPORT
jboolean JNICALL Java_java_io_io_mkdir(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jboolean result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

#ifdef WIN32
  result = mkdir(path) == 0;
#else
  result = mkdir(path, 0777) == 0;
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

JNIEXPORT
jobjectArray JNICALL Java_java_io_io_roots(JNIEnv *env, jclass _clazz)
{
  jclass clazz;
  jobjectArray array;
  jstring root;

  clazz = (*env)->FindClass(env, "java/lang/String");
  if (clazz == NULL)
    return NULL;

#ifdef WIN32
{
  DWORD drives;
  jint length;
  jint i;
  char buffer[4];

  drives = GetLogicalDrives();

  length = 0;
  for (i = 'A'; i <= 'Z'; i++)
    if ((drives & (1 << (i-'A'))) != 0)
      length++;

  array = (*env)->NewObjectArray(env, length, clazz, NULL);
  if (array == NULL)
    return NULL;

  length = 0;
  for (i = 'A'; i <= 'Z'; i++)
    if ((drives & (1 << (i-'A'))) != 0) {
      sprintf(buffer, "%c:\\", (char)i);
      root = (*env)->NewStringUTF(env, buffer);
      if (root == NULL)
        return NULL;
      (*env)->SetObjectArrayElement(env, array, length, root);
      (*env)->DeleteLocalRef(env, root);
      length++;
    }
}
#else
  root = (*env)->NewStringUTF(env, "/");
  if (root == NULL)
    return NULL;

  array = (*env)->NewObjectArray(env, 1, clazz, root);
  if (array == NULL)
    return NULL;
#endif /* WIN32 */

  return array;
}

JNIEXPORT
jobjectArray JNICALL Java_java_io_io_list(JNIEnv *env, jclass cl, jstring string)
{
  const char *path;
  jclass clazz;
  jint length;
  jobjectArray array;
  jint i;
  jstring name;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  clazz = (*env)->FindClass(env, "java/lang/String");
  if (clazz == NULL)
    return NULL;

#ifdef WIN32
{
  HANDLE file;
  WIN32_FIND_DATA data;
  char buffer[PATH_MAX];

  strcpy(buffer, path);
  strcat(buffer, "\\*.*");

  file = FindFirstFile(buffer, &data);
  if (file == INVALID_HANDLE_VALUE)
    return NULL;

  length = 0;
  do {
    if (strcmp(data.cFileName, ".") == 0 || strcmp(data.cFileName, "..") == 0)
      continue;
    length++;
  } while (FindNextFile(file, &data));

  FindClose(file);

  array = (*env)->NewObjectArray(env, length, clazz, NULL);
  if (array == NULL)
    return NULL;

  file = FindFirstFile(buffer, &data);
  if (file == INVALID_HANDLE_VALUE)
    return NULL;
  
  i = 0;
  do {
    if (strcmp(data.cFileName, ".") == 0 || strcmp(data.cFileName, "..") == 0)
      continue;
    name = (*env)->NewStringUTF(env, data.cFileName);
    if (name == NULL) {
      FindClose(file);
      return NULL;
    }
    (*env)->SetObjectArrayElement(env, array, i, name);
    (*env)->DeleteLocalRef(env, name);
    i++;
  } while (FindNextFile(file, &data));
  
  FindClose(file);
}
#else
{
  DIR *dir;
  struct dirent *den;

  dir = opendir(path);
  if (dir == NULL)
    return NULL;

  length = 0;
  while ((den = readdir(dir)) != NULL) {
    if (strcmp(den->d_name, ".") == 0 || strcmp(den->d_name, "..") == 0)
      continue;
    length++;
  }

  array = (*env)->NewObjectArray(env, length, clazz, NULL);
  if (array == NULL)
    return NULL;

  rewinddir(dir);

  i = 0;
  while ((den = readdir(dir)) != NULL) {
    if (strcmp(den->d_name, ".") == 0 || strcmp(den->d_name, "..") == 0)
      continue;
    name = (*env)->NewStringUTF(env, den->d_name);
    if (name == NULL) {
      closedir(dir);
      return NULL;
    }
    (*env)->SetObjectArrayElement(env, array, i, name);
    (*env)->DeleteLocalRef(env, name);
    i++;
  }
  
  closedir(dir);
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return array;
}

JNIEXPORT
jlong JNICALL Java_java_io_io_lastModified(JNIEnv *env, jclass _clazz, jstring string)
{
  const char *path;
  jlong result;

  path = (*env)->GetStringUTFChars(env, string, NULL);
  if (path == NULL)
    return JNI_FALSE;

  result = 0;

#ifdef WIN32
{
  HANDLE file;
  WIN32_FIND_DATA data;

  file = FindFirstFile(path, &data);
  if (file != INVALID_HANDLE_VALUE) {
    FindClose(file);
    result = (*(jlong*)&data.ftLastWriteTime)/10000 - 11644473600000;
  }
}
#else
{
  struct stat sb;

  if (stat(path, &sb) == 0)
    result = 1000*(jlong)sb.st_mtime;
}
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, string, path);

  return result;
}

