FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-DRM-condition.patch \
            file://0002-Add-support-for-Vivante-3D-GPU.patch \
            file://weston.sh"

PACKAGECONFIG = "fbdev"

EXTRA_OECONF += "\
                --enable-simple-egl-clients \
                --disable-libunwind \
                --disable-xwayland-test \
                WESTON_NATIVE_BACKEND=fbdev-backend.so"

export COMPOSITOR_LIBS="-lGLESv2 -lEGL -lGAL -lwayland-server -lxkbcommon -lpixman-1"
export COMPOSITOR_CFLAGS="-I ${STAGING_DIR_HOST}/usr/include/pixman-1 -DLINUX=1 -DEGL_API_FB -DEGL_API_WL"
export FB_COMPOSITOR_CFLAGS="-DLINUX=1 -DEGL_API_FB -DEGL_API_WL -I $WLD/include"
export FB_COMPOSITOR_LIBS="-lGLESv2 -lEGL -lwayland-server -lxkbcommon"
export SIMPLE_EGL_CLIENT_CFLAGS="-DLINUX -DEGL_API_FB -DEGL_API_WL"

do_install_append () {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0755 ${WORKDIR}/weston.sh ${D}${sysconfdir}/profile.d/
}

python __anonymous () {
    if d.getVar('SOC_FAMILY', True) == 'mx6':
        extra_oeconf = d.getVar('EXTRA_OECONF', True).split()
        take_out = ['--disable-simple-egl-clients']
        new_extra_oeconf = []
        for i in extra_oeconf:
            if i not in take_out:
                new_extra_oeconf.append(i)
        d.setVar('EXTRA_OECONF', ' '.join(new_extra_oeconf))

        depends = d.getVar('DEPENDS', True).split()
        take_out = ['mesa']
        new_depends = []
        for i in depends:
            if i not in take_out:
                new_depends.append(i)
        d.setVar('DEPENDS', ' '.join(new_depends))
}

FILES_${PN} += "${sysconfdir}/profile.d/weston.sh"

PACKAGE_ARCH_mx6 = "${MACHINE_ARCH}"
