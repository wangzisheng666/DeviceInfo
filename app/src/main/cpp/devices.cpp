#include <jni.h>
#include <stdlib.h>
#include <pty.h>
#include <sys/un.h>
#include <sys/socket.h>
#include <sys/system_properties.h>


// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("devices")
//      }
//    }
JNIEXPORT jboolean JNICALL Java_com_mobiles_devices0_fuction_check_1frida_mCFPort(JNIEnv *env, jclass jc){
//    struct sockaddr_in sa;
//    memset(&sa, 0, sizeof(sa));
//    sa.sin_family = AF_INET;
//    sa.sin_port = htons(27042);
//    sa.sin_addr.s_addr = inet_addr("127.0.0.1");
//    int sock = socket(AF_INET , SOCK_STREAM , 0);
//    int tt = connect(sock , (struct sockaddr*)&sa , sizeof(sa));
//    if ( tt != -1) {
//        return 1;
//    }else{
//        return 0;
//    }

};



