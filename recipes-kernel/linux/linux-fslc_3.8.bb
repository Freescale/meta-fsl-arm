# Copyright (C) 2012-2013 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)

include linux-fslc.inc

PV = "3.8+git${SRCPV}"
PR = "r5"

# patches-3.8
SRCREV = "72053409242244eaa59497e665945ddeea69c397"

COMPATIBLE_MACHINE = "(mxs|mx3|mx5|mx6)"
