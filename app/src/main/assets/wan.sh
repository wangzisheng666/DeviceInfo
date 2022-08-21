#!/system/bin/sh
injectprop  ro.debuggable 1
mysu
stop;start
