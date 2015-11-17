require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot provided by Freescale with focus on QorIQ Layerscape1 boards"
LICENSE = "GPLv2 & BSD-3-Clause & BSD-2-Clause & LGPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = " \
    file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://Licenses/bsd-2-clause.txt;md5=6a31f076f5773aabd8ff86191ad6fdd5 \
    file://Licenses/bsd-3-clause.txt;md5=4a1190eac56a9db675d58ebe86eaf50c \
    file://Licenses/lgpl-2.0.txt;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
    file://Licenses/lgpl-2.1.txt;md5=4fbd65380cdd255951079008b364516c \
"

SRCBRANCH = "master"
SRC_URI = "git://git.freescale.com/ppc/sdk/u-boot.git;branch=${SRCBRANCH}"
SRC_URI += "file://0001-uboot-support-gcc5.patch "
SRCREV = "6ba8eedbcdc4b063f59a63e6288b938af739e8ad"

LOCALVERSION ?= "+ls1"

S = "${WORKDIR}/git"

inherit fsl-u-boot-localversion

DEPENDS += "change-file-endianess-native dtc-native"
PROVIDES += "u-boot"

do_compile_append () {
 unset i j
    if [ "x${UBOOT_CONFIG}" != "x" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ]; then
                    case "${config}" in
                        *nand* | *sdcard*)
                            cp ${config}/u-boot-with-spl-pbl.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                        *spi*) 
                            tclsh ${STAGING_BINDIR_NATIVE}/byte_swap.tcl ${config}/u-boot-dtb.bin ${config}/u-boot.swap.bin 8
                            cp ${config}/u-boot.swap.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                    esac
                fi
            done
            unset j
        done
        unset i
    fi
}

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"

ALLOW_EMPTY_${PN} = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(ls102xa)"

