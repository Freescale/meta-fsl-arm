# Enable framebuffer  backend for Wayland

CFLAGS_append_mx6 += "${@base_contains('DISTRO_FEATURES', 'wayland', '-DLINUX -DEGL_API_FB -DEGL_API_WL', '', d)}"
