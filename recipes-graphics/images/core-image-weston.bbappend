# FIXME: Vivante GPU driver cannot operate in X11 and Wayland in same
# distribution as it needs to have different libraries installed. So
# in case 'x11' is in DISTRO_FEATURES, Wayland is disabled.
CONFLICT_DISTRO_FEATURES_append_mx6 = " x11"

# FIXME: i.MX6SL cannot use mesa for Graphics and it lacks GL support,
#        so for now we skip it.
CORE_IMAGE_BASE_INSTALL_remove_mx6sl = "clutter-1.0-examples"
