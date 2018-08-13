# GLASSES, Generic cLASSES
# Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira

SUBDIRS=java
CLEANFILES=glasses.jar
EXTRA_DIST=MANIFEST.MF.in
PKGDIRS=\
	java/io\
	java/lang\
	java/lang/ref\
	java/lang/reflect\
	java/net\
	java/security\
	java/security/cert\
	java/util\
	java/util/jar\
	java/util/zip

data_DATA=glasses.jar

glasses.jar: MANIFEST.MF $(addsuffix /classnoinst.stamp,$(PKGDIRS))
	$(JAR) cvfm $@ $< $(addsuffix /*.class,$(PKGDIRS))
