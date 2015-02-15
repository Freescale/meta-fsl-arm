# Copyright (C) 2013, 2014 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by Freescale"
DESCRIPTION = "Linux Kernel provided and supported by Freescale with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

# back to -1.0.0_ga branch for all patch release
SRCBRANCH = "imx_3.10.17_1.0.0_ga"
SRCREV = "33597e348b2d60dd5c71890ef7b7d3d3fd6e4e97"
LOCALVERSION = "-1.0.2_ga"

SRC_URI += "file://0001-ARM-clk-imx6q-fix-video-divider-for-revision-1.0-of-.patch \
            file://0002-ARM-imx6sl-Disable-imx6sl-specific-code-when-imx6sl-.patch \
"

COMPATIBLE_MACHINE = "(mx6)"
