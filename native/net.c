/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/time.h>

#ifdef WIN32
#include <windows.h>
#include <winsock.h>
#else
#include <netdb.h>
#include <sys/socket.h>
#include <netinet/in.h>
#define BSD_COMP
#include <sys/ioctl.h>
#undef BSD_COMP
#endif /* WIN32 */

#include <jni.h>

JNIEXPORT
jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
#ifdef WIN32
  WSADATA wsadata;
  int result;

  result = WSAStartup(0x0101, &wsadata);
  if (result != 0) {
    printf("Could not initialize winsocket\n");
    exit(-1);
  }
#endif /* WIN32 */
  return JNI_VERSION_1_2;
}

JNIEXPORT
jstring JNICALL Java_java_net_net_getLocalHostName(JNIEnv *env, jclass _class)
{
  jint rst;
  char hostname[256];

  rst = gethostname(hostname, sizeof(hostname));
  if (rst == -1)
    strcpy(hostname, "localhost");

  return (*env)->NewStringUTF(env, hostname);
}

JNIEXPORT
jobjectArray JNICALL Java_java_net_net_getHostAddressesByName(JNIEnv *env, jclass _class, jstring host)
{
  const char *hostname;
  jintArray array;
  struct in_addr **addrp;
  int i;
  jclass clazz;
  struct hostent *hp;
  jint *elems;

  if (host == NULL) {
    clazz = (*env)->FindClass(env, "java/lang/NullPointerException");
    if (clazz == NULL)
      return NULL;
    (*env)->ThrowNew(env, clazz, "Host argument");
    return NULL;
  }

  hostname = (*env)->GetStringUTFChars(env, host, NULL);
  if (hostname == NULL)
    return NULL;

#ifdef WIN32
  hp = gethostbyname(hostname);
#else
  {
    struct hostent res;
    int h_error;
    char buf[10240];

#ifdef __GLIBC__
    gethostbyname_r(hostname, &res, buf, sizeof(buf), &hp, &h_error);
#else
    hp = gethostbyname_r(hostname, &res, buf, sizeof(buf), &h_error);
#endif
  }
#endif /* WIN32 */

  if (hp == NULL) {
    clazz = (*env)->FindClass(env, "java/net/UnknownHostException");
    if (clazz == NULL) {
      (*env)->ReleaseStringUTFChars(env, host, hostname);
      return NULL;
    }
    (*env)->ThrowNew(env, clazz, (char*)hostname);
    (*env)->ReleaseStringUTFChars(env, host, hostname);
    return NULL;
  }

  addrp = (struct in_addr**)hp->h_addr_list;
  for (i = 0; addrp[i] != NULL; i++)
    ;

  clazz = (*env)->FindClass(env, "[B");
  if (clazz == NULL) {
    (*env)->ReleaseStringUTFChars(env, host, hostname);
    return NULL;
  }
  
  array = (*env)->NewIntArray(env, i);
  if (array == NULL) {
    (*env)->ReleaseStringUTFChars(env, host, hostname);
    return NULL;
  }

  elems = (*env)->GetIntArrayElements(env, array, NULL);

  for (i = 0; addrp[i] != NULL; i++)
    elems[i] = ntohl(*(jint*)addrp[i]);

  (*env)->ReleaseIntArrayElements(env, array, elems, JNI_ABORT);

  (*env)->ReleaseStringUTFChars(env, host, hostname);
  return array;
}

JNIEXPORT
jstring JNICALL Java_java_net_net_getHostNameByAddress(JNIEnv *env, jclass _class, jint address)
{
  struct hostent *hp;

  address = htonl(address);

#ifdef WIN32
  hp = gethostbyaddr((char*)address, sizeof(address), AF_INET);
#else
  {
    struct hostent hent;
    int h_error;
    char buf[10240];

#ifdef __GLIBC__
    gethostbyaddr_r((char*)&address, sizeof(address), AF_INET, &hent, buf, sizeof(buf), &hp, &h_error);
#else
    hp = gethostbyaddr_r((char*)&address, sizeof(address), AF_INET, &hent, buf, sizeof(buf), &h_error);
#endif
  }
#endif /* WIN32 */

  if (hp == NULL)
    return NULL;

  return (*env)->NewStringUTF(env, hp->h_name);
}

JNIEXPORT
jint JNICALL Java_java_net_net_create(JNIEnv *env, jclass _clazz, jboolean stream)
{
  jint fd;
  jclass clazz;

  fd = socket(AF_INET, (stream ? SOCK_STREAM: SOCK_DGRAM), 0);
  if (fd == -1) {
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

#ifndef WIN32
  {
    jint arg = -1;
    setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, (char*)&arg, sizeof(arg));
  }
#endif /* WIN32 */

  return fd;
}

JNIEXPORT
jint JNICALL Java_java_net_net_connect(JNIEnv *env, jclass _clazz, jint fd, jint address, jint port)
{
  struct sockaddr_in him;
  jclass clazz;
  jint result;
  int len;

  memset((char*)&him, 0, sizeof(him));
  him.sin_port = htons((short)port);
  him.sin_addr.s_addr = (unsigned long)htonl(address);
  him.sin_family = AF_INET;

  result = connect(fd, (struct sockaddr*)&him, sizeof(him));
  if (result == -1) {
    clazz = (*env)->FindClass(env, "java/net/ConnectException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  len = sizeof(him);
  if (getsockname(fd, (struct sockaddr*)&him, &len) == -1) {
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }
  return ntohs(him.sin_port);
}

JNIEXPORT
jint JNICALL Java_java_net_net_bind(JNIEnv *env, jclass _clazz, jint fd, jint address, jint port)
{
  jclass clazz;
  struct sockaddr_in him;
  int len;

  memset((char*)&him, 0, sizeof(him));
  him.sin_port = htons((short)port);
  him.sin_addr.s_addr = (unsigned long)htonl(address);
  him.sin_family = AF_INET;

#ifndef WIN32
  if ((address & 0x7F0000FF) == 0x7F0000FF) {
    clazz = (*env)->FindClass(env, "java/net/BindException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }
#endif /* WIN32 */

  if (bind(fd, (struct sockaddr*)&him, sizeof(him)) == -1) {
#ifndef WIN32
    if (errno == EADDRINUSE || errno == EADDRNOTAVAIL || errno == EPERM || errno == EACCES) {
      clazz = (*env)->FindClass(env, "java/net/BindException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
#endif /* WIN32 */
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  if (port == 0) {
    len = sizeof(him);
    if (getsockname(fd, (struct sockaddr*)&him, &len) == -1) {
      clazz = (*env)->FindClass(env, "java/net/SocketException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
    port = ntohs(him.sin_port);
  }

  return port;
}

JNIEXPORT
void JNICALL Java_java_net_net_listen(JNIEnv *env, jclass _clazz, jint fd, jint backlog)
{
  jclass clazz;

#ifndef WIN32
  if (backlog == 0x7FFFFFFF)
    backlog -= 1;
#endif /* WIN32 */

  if (listen(fd, backlog) == -1) {
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, "listen");
    return;
  }
}

JNIEXPORT
jint JNICALL Java_java_net_net_accept(JNIEnv *env, jclass _clazz, jint fd, jint timeout, jintArray paddress, jintArray pport)
{
  jclass clazz;
#ifdef WIN32
  int len;
#else
  socklen_t len;
#endif /* WIN32 */
  struct sockaddr_in him;
  jint port;
  jint address;
  struct timeval time;
  fd_set set;
  int result;

  if (timeout > 0) {
    time.tv_sec = timeout / 1000;
    time.tv_usec = (timeout % 1000) * 1000;
    FD_ZERO(&set);
    FD_SET(fd, &set);
    result = select(fd+1, &set, 0, 0, &time);
    if (result == -1) {
      clazz = (*env)->FindClass(env, "java/net/SocketException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
    if (result == 0) {
      clazz = (*env)->FindClass(env, "java/io/InterrruptedIOException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
  }

  len = sizeof(him);
  fd = accept(fd, (struct sockaddr*)&him, &len);
  if (fd == -1) {
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }
  if (him.sin_family != AF_INET) {
    clazz = (*env)->FindClass(env, "java/net/ProtocolException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  port = ntohs(him.sin_port);
  address = ntohl(him.sin_addr.s_addr);

  (*env)->SetIntArrayRegion(env, paddress, 0, 1, &address);
  if ((*env)->ExceptionOccurred(env))
    return 0;
  (*env)->SetIntArrayRegion(env, pport, 0, 1, &port);
  if ((*env)->ExceptionOccurred(env))
    return 0;

  return fd;
}

JNIEXPORT
jint JNICALL Java_java_net_net_recv(JNIEnv *env, jclass _clazz, jint fd, jbyteArray array, jint start, jint length, jint timeout)
{
  jclass clazz;
  jint array_length;
  jint end;
  jbyte buffer[1024];
  jint received;
  struct timeval time;
  fd_set set;
  int result;

  if (array == NULL) {
    clazz = (*env)->FindClass(env, "java/lang/NullPointerException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  array_length = (*env)->GetArrayLength(env, array);
  if (start < 0 || start > array_length) {
    clazz = (*env)->FindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }
  end = start+length;
  if (end < start || end > array_length) {
    clazz = (*env)->FindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  }

  if (length == 0)
    return 0;

  if (length > sizeof(buffer))
    length = sizeof(buffer);


  if (timeout > 0) {
    time.tv_sec = timeout / 1000;
    time.tv_usec = (timeout % 1000) * 1000;
    FD_ZERO(&set);
    FD_SET(fd, &set);
    result = select(fd+1, &set, NULL, NULL, &time);
    if (result == -1) {
      clazz = (*env)->FindClass(env, "java/net/SocketException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    }
    if (result == 0) {
      clazz = (*env)->FindClass(env, "java/io/InterruptedIOException");
      if (clazz == NULL)
        return 0;
      (*env)->ThrowNew(env, clazz, NULL);
      return 0;
    } 
  }

  received = recv(fd, buffer, length, 0);
  if (received < 0) {
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, "socket read error");
    return 0;
  }

  if (received == 0)
    return -1;

  (*env)->SetByteArrayRegion(env, array, start, received, buffer);
  if ((*env)->ExceptionOccurred(env))
    return 0;

  return received;
}

JNIEXPORT
void JNICALL Java_java_net_net_send(JNIEnv *env, jclass _clazz, jint fd, jbyteArray array, jint start, jint length)
{
  jint array_length;
  jint end;
  jclass clazz;
  jint bstart;
  jint blength;
  jbyte buffer[1024];
  jint sent;

  if (array == NULL) {
    clazz = (*env)->FindClass(env, "java/lang/NullPointerException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }

  array_length = (*env)->GetArrayLength(env, array);
  if (start < 0 || start > array_length) {
    clazz = (*env)->FindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }
  end = start+length;
  if (end < start || end > array_length) {
    clazz = (*env)->FindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }

  while (length > 0) {
    bstart = 0;
    blength = sizeof(buffer) < length ? sizeof(buffer) : length;

    (*env)->GetByteArrayRegion(env, array, start, blength, buffer);
    if ((*env)->ExceptionOccurred(env))
      return;
  
    start += blength;
    length -= blength;

    while (blength > 0) {

      sent = send(fd, &buffer[bstart], blength, 0);
      if (sent == -1) {
        clazz = (*env)->FindClass(env, "java/io/IOException");
        if (clazz == NULL)
          return;
        (*env)->ThrowNew(env, clazz, "Socket write error");
        return;
      }

      bstart += sent;
      blength -= sent;
    }

  }
}

JNIEXPORT
jint JNICALL Java_java_net_net_available(JNIEnv *env, jclass _clazz, jint fd)
{
  jclass clazz;
  jint result;

  result = -1;
#ifdef WIN32
  if (ioctlsocket(fd, FIONREAD, (u_long*)&result) != 0) {
#else
  if (ioctl(fd, FIONREAD, &result) == -1) {
#endif /* WIN32 */
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return 0;
    (*env)->ThrowNew(env, clazz, NULL);
    return 0;
  } 
  return result;
}

JNIEXPORT
void JNICALL Java_java_net_net_shutdownInput(JNIEnv *env, jclass _clazz, jint fd)
{
  jclass clazz;

#ifdef WIN32
  if (shutdown(fd, 0) != 0) {
#else
  if (shutdown(fd, SHUT_RD) == -1) {
#endif /* WIN32 */
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }
}

JNIEXPORT
void JNICALL Java_java_net_net_shutdownOutput(JNIEnv *env, jclass _clazz, jint fd)
{
  jclass clazz;

#ifdef WIN32
  if (shutdown(fd, 1) != 0) {
#else
  if (shutdown(fd, SHUT_WR) == -1) {
#endif /* WIN32 */
    clazz = (*env)->FindClass(env, "java/net/SocketException");
    if (clazz == NULL)
      return;
    (*env)->ThrowNew(env, clazz, NULL);
    return;
  }
}

JNIEXPORT
void JNICALL Java_java_net_net_closesocket(JNIEnv *env, jclass _clazz, jint fd)
{
#ifdef WIN32
  closesocket(fd);
#else
  close(fd);
#endif /* WIN32 */
}

