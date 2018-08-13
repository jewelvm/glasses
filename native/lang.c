/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <stdlib.h>
#include <limits.h>
#include <unistd.h>
#include <sys/time.h>

#ifdef WIN32
#include <windows.h>
#include <shlobj.h>
#else
#include <dlfcn.h>
#include <sys/utsname.h>
#include <pwd.h>
#endif /* WIN32 */

#include <jni.h>
#include <gni.h>

static
GNI_Interface *gni;

JNIEXPORT
jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
  jint error;

  error = (*jvm)->GetEnv(jvm, (void*)&gni, GNI_VERSION_1);
  if (error != JNI_OK)
    return error;
  return JNI_VERSION_1_2;
}

JNIEXPORT
jclass JNICALL Java_java_lang_lang_getClass(JNIEnv *env, jclass _clazz, jobject object)
{
  return (*env)->GetObjectClass(env, object);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_clone(JNIEnv *env, jclass _clazz, jobject object)
{
  return gni->Clone(env, object);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_wait(JNIEnv *env, jclass _clazz, jobject object, jlong millis)
{
  gni->Wait(env, object, millis);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_notify(JNIEnv *env, jclass _clazz, jobject object)
{
  gni->Notify(env, object);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_notifyAll(JNIEnv *env, jclass _clazz, jobject object)
{
  gni->NotifyAll(env, object);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_getDefiningLoader(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassLoader(env, clazz);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_getName(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassName(env, clazz);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_lang_isAssignableFrom(JNIEnv *env, jclass _clazz, jclass sup, jclass sub)
{
  return (*env)->IsAssignableFrom(env, sub, sup);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_getSuperclass(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return (*env)->GetSuperclass(env, clazz);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getInterfaces(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassInterfaces(env, clazz);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_newInstance(JNIEnv *env, jclass _clazz, jobject clazz)
{
  jmethodID init;

  init = (*env)->GetMethodID(env, clazz, "<init>", "()V");
  if (init == NULL)
    return NULL;

  return (*env)->NewObject(env, clazz, init);
}

JNIEXPORT
jint JNICALL Java_java_lang_lang_getDimensions(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassDimensions(env, clazz);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_getElementType(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassElement(env, clazz);
}

JNIEXPORT
jint JNICALL Java_java_lang_lang_getModifiers(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetClassModifiers(env, clazz);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_initialize(JNIEnv *env, jclass _clazz, jobject clazz)
{
  gni->InitializeClass(env, clazz);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_link(JNIEnv *env, jclass _clazz, jobject clazz)
{
  gni->LinkClass(env, clazz);
}

JNIEXPORT
jclass JNICALL Java_java_lang_lang_derive(JNIEnv *env, jclass _clazz, jobject loader, jstring name, jbyteArray array, jint start, jint length)
{
  return gni->DeriveClass(env, loader, name, array, start, length);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_lang_hasStaticInitializer(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->HasStaticInitializer(env, clazz);
}

JNIEXPORT
jclass JNICALL Java_java_lang_lang_getDeclaringClass(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetDeclaringClass(env, clazz);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getDeclaredClasses(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetDeclaredClasses(env, clazz);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getDeclaredFields(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetDeclaredFields(env, clazz);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getDeclaredMethods(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetDeclaredMethods(env, clazz);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getDeclaredConstructors(JNIEnv *env, jclass _clazz, jobject clazz)
{
  return gni->GetDeclaredConstructors(env, clazz);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_load(JNIEnv *env, jclass _clazz, jstring name)
{
  const char *chars;
  void *handle;
  jint (JNICALL *onLoad)(JavaVM*,void*);
  jint result;
  JavaVM *jvm;

  chars = (*env)->GetStringUTFChars(env, name, NULL);
  if (chars == NULL)
    return 0;

#ifndef WIN32
  handle = dlopen(chars, RTLD_NOW);
#else
  handle = LoadLibrary(chars);
#endif /*WIN32*/
  
  (*env)->ReleaseStringUTFChars(env, name, chars);
  if (handle == NULL)
    return 0;

#ifndef WIN32
  onLoad = dlsym(handle, "JNI_OnLoad");
#else
  onLoad = (void*)GetProcAddress(handle, "JNI_OnLoad");
  if (onLoad == NULL)
    onLoad = (void*)GetProcAddress(handle, "_JNI_OnLoad@8");
#endif /*WIN32*/

  if (onLoad != NULL) {
    result = (*env)->GetJavaVM(env, &jvm);
    if (result != JNI_OK) {
#ifndef WIN32
      dlclose(handle);
#else
      FreeLibrary(handle);
#endif /*WIN32*/
      return 0;
    }

    result = onLoad(jvm, NULL);
    if ((*env)->ExceptionOccurred(env)) {
#ifndef WIN32
      dlclose(handle);
#else
      FreeLibrary(handle);
#endif /*WIN32*/
      return 0;
    }

    if (result != JNI_VERSION_1_2) {
#ifndef WIN32
      dlclose(handle);
#else
      FreeLibrary(handle);
#endif /*WIN32*/
      return 0;
    }
  }

  return (jlong)(jint)handle;
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_findSymbol(JNIEnv *env, jclass _clazz, jlong lhandle, jstring symbol, jint words)
{
  const char *chars;
  void *handle;
  void *fnPtr;

  handle = (void*)(jint)lhandle;

  chars = (*env)->GetStringUTFChars(env, symbol, NULL);
  if (chars == NULL)
    return 0;

#ifdef WIN32
  fnPtr = GetProcAddress(handle, chars);
  if (fnPtr == NULL) {
    char *temp = malloc(1+strlen(chars)+1+6+1);
    if (temp == NULL) {
      jclass clazz = (*env)->FindClass(env, "java/lang/OutOfMemoryError");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
    sprintf(temp, "_%s@%d", chars, (int)(8+4*words));
    fnPtr = GetProcAddress(handle, temp);
    free(temp);
  }
#else
  fnPtr = dlsym(handle, chars);
#endif /* WIN32 */

  (*env)->ReleaseStringUTFChars(env, symbol, chars);

  return (jlong)(jint)fnPtr;
}

JNIEXPORT
void JNICALL Java_java_lang_lang_compilerEnable(JNIEnv *env, jclass _clazz)
{
  return gni->CompilerEnable(env);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_compilerDisable(JNIEnv *env, jclass _clazz)
{
  return gni->CompilerDisable(env);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_compilerCommand(JNIEnv *env, jclass _clazz, jobject command)
{
  return gni->CompilerCommand(env, command);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_lang_compileClass(JNIEnv *env, jclass _clazz, jclass clazz)
{
  return gni->CompileClass(env, clazz);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_lang_compileClasses(JNIEnv *env, jclass _clazz, jstring classes)
{
  return gni->CompileClasses(env, classes);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_doubleToRawLongBits(JNIEnv *env, jclass _clazz, jdouble value)
{
  return *(jlong*)&value;
}

JNIEXPORT
jdouble JNICALL Java_java_lang_lang_longBitsToDouble(JNIEnv *env, jclass _clazz, jlong value)
{
  return *(jdouble*)&value;
}

JNIEXPORT
jint JNICALL Java_java_lang_lang_floatToRawIntBits(JNIEnv *env, jclass _clazz, jfloat value)
{
  return *(jint*)&value;
}

JNIEXPORT
jfloat JNICALL Java_java_lang_lang_intBitsToFloat(JNIEnv *env, jclass _clazz, jint value)
{
  return *(jfloat*)&value;
}

JNIEXPORT
void JNICALL Java_java_lang_lang_gc(JNIEnv *env, jclass _clazz)
{
  gni->GC(env);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_totalMemory(JNIEnv *env, jclass _clazz)
{
  return gni->TotalMemory(env);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_freeMemory(JNIEnv *env, jclass _clazz)
{
  return gni->FreeMemory(env);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_maxMemory(JNIEnv *env, jclass _clazz)
{
  return gni->MaxMemory(env);
}

JNIEXPORT
jint JNICALL Java_java_lang_lang_availableProcessors(JNIEnv *env, jclass _clazz)
{
  return 1;
}

JNIEXPORT
void JNICALL Java_java_lang_lang_traceMethodCalls(JNIEnv *env, jclass _clazz, jboolean on)
{
  gni->TraceMethodCalls(env, on);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_traceInstructions(JNIEnv *env, jclass _clazz, jboolean on)
{
  gni->TraceInstructions(env, on);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_exit(JNIEnv *env, jclass _clazz, jint status)
{
  gni->Exit(env, status);
}

static
void SetSystemProperty(JNIEnv *env, jclass systemClass, const char *name, const char *value)
{
  jmethodID methodID;
  jstring nameString;
  jstring valueString;
  jstring oldString;

  methodID = (*env)->GetStaticMethodID(env, systemClass, "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
  if ((*env)->ExceptionCheck(env))
    return;

  nameString = (*env)->NewStringUTF(env, name);
  if ((*env)->ExceptionCheck(env))
    return;

  valueString = (*env)->NewStringUTF(env, value);
  if ((*env)->ExceptionCheck(env))
    return;

  oldString = (*env)->CallStaticObjectMethod(env, systemClass, methodID, nameString, valueString);
  if ((*env)->ExceptionCheck(env))
    return;

  (*env)->DeleteLocalRef(env, oldString);
}

static
char *userTmpdir()
{
#ifndef WIN32
  return P_tmpdir;
#else
  static char buffer[MAX_PATH];
  
  GetTempPath(sizeof(buffer), buffer);
  return buffer;
#endif /*WIN32*/
}

static
char *userOSName()
{
#ifndef WIN32
  static struct utsname userOSInfo;

  uname(&userOSInfo);
  return userOSInfo.sysname;
#else
  OSVERSIONINFO ver;

  ver.dwOSVersionInfoSize = sizeof(ver);
  GetVersionEx(&ver);
  switch (ver.dwPlatformId) {
  case VER_PLATFORM_WIN32s:
    return "Windows 3.1";
  case VER_PLATFORM_WIN32_WINDOWS:
    return "Windows 95";
  case VER_PLATFORM_WIN32_NT:
    return "Windows NT";
  default:
    return "unknown";
  }
#endif /*WIN32*/
}

static
char *userOSArch()
{
#ifndef WIN32
  static struct utsname userOSInfo;

  uname(&userOSInfo);
  return userOSInfo.machine;
#else
  return "x86";
#endif /*WIN32*/
}

static
char *userOSVersion()
{
#ifndef WIN32
  static struct utsname userOSInfo;

  uname(&userOSInfo);
  return userOSInfo.release;
#else
  static char userOSInfo[22];
  OSVERSIONINFO ver;

  ver.dwOSVersionInfoSize = sizeof(ver);
  GetVersionEx(&ver);
  sprintf(userOSInfo, "%u.%u", (int)ver.dwMajorVersion, (int)ver.dwMinorVersion);
  return userOSInfo;
#endif /*WIN32*/
}

static
char *userName()
{
#ifndef WIN32
  struct passwd *pwd;

  pwd = getpwuid(getuid());
  return pwd->pw_name;
#else
  DWORD length;
  int result;
  static char userInfo[100];
  
  length = sizeof(userInfo);
  result = GetUserName(userInfo, &length);
  if (result == 0)
    return "unknown";
  return userInfo;
#endif /*WIN32*/
}

static
char *userHomedir()
{
#ifndef WIN32
  struct passwd *pwd;

  pwd = getpwuid(getuid());
  return pwd->pw_dir;
#else
  char *l_file;
  LPITEMIDLIST itemList;
  IMalloc *pMalloc;
  static char userHome[MAX_PATH];

  if (SUCCEEDED(SHGetSpecialFolderLocation(NULL, CSIDL_DESKTOPDIRECTORY, &itemList))) {
    SHGetPathFromIDList(itemList, userHome);
    if (SUCCEEDED(SHGetMalloc(&pMalloc))) {
      pMalloc->lpVtbl->Free(pMalloc, itemList);
      pMalloc->lpVtbl->Release(pMalloc);
    }
    l_file = strrchr(userHome, '\\');
    if (l_file != NULL)
      l_file[0] = '\0';
    return userHome;
  }
  return "C:\\";
#endif /*WIN32*/
}

#ifdef WIN32
#define PATH_SEPARATOR                ";"
#define FILE_SEPARATOR                "\\"
#define LINE_SEPARATOR                "\r\n"
#define JAVA_LIBRARY_PREFIX           ""     /* non-standard */
#define JAVA_LIBRARY_SUFFIX           ".dll" /* non-standard */
#else
#define PATH_SEPARATOR                ":"
#define FILE_SEPARATOR                "/"
#define LINE_SEPARATOR                "\n"
#define JAVA_LIBRARY_PREFIX           "lib"  /* non-standard */
#define JAVA_LIBRARY_SUFFIX           ".so"  /* non-standard */
#endif /* WIN32 */

JNIEXPORT
void JNICALL Java_java_lang_System_registerSystemProperties(JNIEnv *env, jclass clazz)
{
  char cdir[PATH_MAX+1];
  jint i;
  static
  struct {
    const char *key;
    const char *value;
  } defaultProperties[] = {
    { "path.separator", PATH_SEPARATOR },
    { "file.separator", FILE_SEPARATOR },
    { "line.separator", LINE_SEPARATOR },
    { "java.library.prefix", JAVA_LIBRARY_PREFIX }, /* non-standard */
    { "java.library.suffix", JAVA_LIBRARY_SUFFIX }, /* non-standard */
    { NULL, NULL },
  };

  for (i = 0; defaultProperties[i].key != NULL; i++) {
    SetSystemProperty(env, clazz, defaultProperties[i].key, defaultProperties[i].value);
    if ((*env)->ExceptionCheck(env))
      return;
  }
  SetSystemProperty(env, clazz, "java.io.tmpdir", userTmpdir());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "os.name", userOSName());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "os.arch", userOSArch());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "os.version", userOSVersion());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "user.name", userName());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "user.home", userHomedir());
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "user.language", "en");
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "user.region", "US");
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "user.timezone", "GMT");
  if ((*env)->ExceptionCheck(env))
    return;
  getcwd(cdir, sizeof(cdir));
  SetSystemProperty(env, clazz, "user.dir", cdir);
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "file.encoding", "Default");
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "file.encoding.pkg", "java.io");
  if ((*env)->ExceptionCheck(env))
    return;
  SetSystemProperty(env, clazz, "java.ext.dirs", "");
  if ((*env)->ExceptionCheck(env))
    return;
}

JNIEXPORT
jstring JNICALL Java_java_lang_lang_getenv(JNIEnv *env, jclass _clazz, jstring name)
{
  const char *chars;
  char *value;

  chars = (*env)->GetStringUTFChars(env, name, NULL);
  if (chars == NULL)
    return NULL;

  value = getenv(chars);

  (*env)->ReleaseStringUTFChars(env, name, chars);

  if (value == NULL)
    return NULL;

  return (*env)->NewStringUTF(env, value);
}

JNIEXPORT
jlong JNICALL Java_java_lang_lang_currentTimeMillis(JNIEnv *env, jclass _clazz)
{
#ifndef WIN32
  struct timeval tv;

  gettimeofday(&tv, NULL);
  return 1000*(jlong)tv.tv_sec + (jlong)(tv.tv_usec/1000);
#else
  SYSTEMTIME st;
  FILETIME ft;
  ULARGE_INTEGER ul;
  ULARGE_INTEGER ul_1970;

  GetSystemTime(&st);
  SystemTimeToFileTime(&st, &ft);
  ul.u.LowPart = ft.dwLowDateTime;
  ul.u.HighPart = ft.dwHighDateTime;
  ul_1970.u.LowPart = 0xD53E8000;
  ul_1970.u.HighPart = 0x019DB1DE;
  return ((jlong)ul.QuadPart - (jlong)ul_1970.QuadPart)/10000;
#endif /*WIN32*/
}

JNIEXPORT
jint JNICALL Java_java_lang_lang_identityHashCode(JNIEnv *env, jclass _clazz, jobject object)
{
  return gni->IdentityHashCode(env, object);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_getStackTrace(JNIEnv *env, jclass _clazz)
{
  return gni->GetStackTrace(env);
}

JNIEXPORT
jobject JNICALL Java_java_lang_Thread_currentThread(JNIEnv *env, jclass _clazz)
{
  JavaVM *jvm;
  jint error;
  jclass clazz;

  if (gni == NULL) {
    error = (*env)->GetJavaVM(env, &jvm);
    if (error == JNI_OK)
      error = (*jvm)->GetEnv(jvm, (void*)&gni, GNI_VERSION_1);
    if (error != JNI_OK) {
      clazz = (*env)->FindClass(env, "java/lang/InternalError");
      if (clazz == NULL)
        return NULL;
      (*env)->ThrowNew(env, clazz, "Fail to get GNI");
      return NULL;
    }
  }
  
  return gni->CurrentThread(env);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_yield(JNIEnv *env, jclass _clazz)
{
  return gni->ThreadYield(env);
}

JNIEXPORT
jobject JNICALL Java_java_lang_lang_callerClass(JNIEnv *env, jclass _clazz)
{
  return gni->CallerClass(env);
}

JNIEXPORT
jobjectArray JNICALL Java_java_lang_lang_callStack(JNIEnv *env, jclass _clazz)
{
  return gni->CallStack(env);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_start(JNIEnv *env, jclass _clazz, jobject object, jint priority, jboolean daemon, jlong stackSize)
{
  gni->ThreadStart(env, object, priority, daemon, stackSize);
}

JNIEXPORT
void JNICALL Java_java_lang_lang_changePriority(JNIEnv *env, jclass _clazz, jobject thread, jint priority)
{
  gni->ChangeThreadPriority(env, thread, priority);
}

JNIEXPORT
jboolean JNICALL Java_java_lang_lang_holdsLock(JNIEnv *env, jclass _clazz, jobject object)
{
  return gni->HoldsLock(env, object);
}

