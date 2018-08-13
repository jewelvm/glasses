# GLASSES, Generic cLASSES
# Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira

AC_INIT(glasses,0.5a)
AC_CONFIG_SRCDIR([classes/java/lang/Object.java])
AC_CANONICAL_TARGET
AM_INIT_AUTOMAKE
AM_CONFIG_HEADER([config.h])
AC_PREFIX_DEFAULT([/usr/java/glasses])
AC_DISABLE_STATIC
AC_LIBTOOL_WIN32_DLL
AC_PROG_CC
AC_PROG_LIBTOOL
AC_PROG_JAVAC
AC_PROG_JAR
AC_JNI_INCLUDEDIRS

native_LTLIBRARIES_md=
case "$host" in
*-*-mingw*)
  native_LTLIBRARIES_md='io.la lang.la reflect.la math.la net.la java.la'
  ;;
*)
  native_LTLIBRARIES_md='libio.la liblang.la libreflect.la libmath.la libnet.la libjava.la'
  AC_CHECK_LIB(dl,dlopen)
  AC_CHECK_LIB(m,atan2)
  ;;
esac
AC_SUBST(native_LTLIBRARIES_md)

AC_CONFIG_FILES([Makefile
include/Makefile
classes/MANIFEST.MF
classes/Makefile
classes/java/Makefile
classes/java/io/Makefile
classes/java/lang/Makefile
classes/java/lang/ref/Makefile
classes/java/lang/reflect/Makefile
classes/java/net/Makefile
classes/java/security/Makefile
classes/java/security/cert/Makefile
classes/java/util/Makefile
classes/java/util/jar/Makefile
classes/java/util/zip/Makefile
native/Makefile])
AC_OUTPUT
