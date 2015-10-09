LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel fsl-kernel-localversion
LOCALVERSION ?= "+ls1"

SRC_URI = "git://git.freescale.com/ppc/sdk/linux.git;branch=${SRCBRANCH} \
    file://defconfig \
"

SRC_URI += " \
    file://0001-ARM-8158-LLVMLinux-use-static-inline-in-ARM-ftrace.patch \
    file://0001-ARM-LLVMLinux-Change-extern-inline-to-static-inline.patch \
    file://0003-use-static-inline-in-ARM-lifeboot.h.patch \
"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"
ZIMAGE_BASE_NAME = "zImage-${PKGE}-${PKGV}-${PKGR}-${MACHINE}-${DATETIME}"

S = "${WORKDIR}/git"

do_install_append() {
    install -m 0644 arch/${ARCH}/boot/zImage ${D}/boot/zImage-${KERNEL_VERSION}
}

do_deploy_append() {
    install -m 0644 arch/${ARCH}/boot/zImage ${DEPLOYDIR}/${ZIMAGE_BASE_NAME}.bin
    ln -sf ${ZIMAGE_BASE_NAME}.bin ${DEPLOYDIR}/zImage-${MACHINE}.bin
    ln -sf ${ZIMAGE_BASE_NAME}.bin ${DEPLOYDIR}/zImage
}

FILES_kernel-image += "/boot/zImage*"
