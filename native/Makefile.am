# GLASSES, Generic cLASSES
# Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira

AM_CFLAGS=-I$(jni_includedir) -I$(jni_md_includedir) -I$(top_srcdir)/include
AM_LDFLAGS=-avoid-version -module -no-undefined -rpath $(libdir) -Wl,-s

lib_LTLIBRARIES=@native_LTLIBRARIES_md@
EXTRA_LTLIBRARIES=\
	libio.la\
	liblang.la\
	libreflect.la\
	libmath.la\
	libnet.la\
	libjava.la\
	io.la\
	lang.la\
	reflect.la\
	math.la\
	net.la\
	java.la

libio_la_SOURCES=io.c

liblang_la_SOURCES=lang.c

libreflect_la_SOURCES=reflect.c

libmath_la_SOURCES=math.c

libnet_la_SOURCES=net.c

libjava_la_SOURCES=java.c

io_la_SOURCES=io.c

lang_la_SOURCES=lang.c

reflect_la_SOURCES=reflect.c

math_la_SOURCES=math.c

net_la_SOURCES=net.c
net_la_LDFLAGS=-Wl,-lwsock32

java_la_SOURCES=java.c
