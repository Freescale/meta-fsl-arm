# Enable EGL backend for Wayland
PACKAGECONFIG_mx6 = "${@base_contains('DISTRO_FEATURES', 'wayland', 'wayland egl', '', d)} \
                     ${@base_contains('DISTRO_FEATURES', 'x11', 'glx x11', '', d)}"

CFLAGS_append_mx6 += "${@base_contains('DISTRO_FEATURES', 'wayland', '-DLINUX -DEGL_API_FB -DEGL_API_WL', '', d)}"
