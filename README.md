# Mobile_info
检测手机环境
#### 重构
#### 基础检测
    1.基础标识检测 机型、devices id、imei、mac等
    2.frida、xposed等hook检测
    3.root检测（magisk）
    4.adb检测
    5.调试检测

#### 加强检测
    1.boot_id和uuid 检测
    2.GPU信息检测OpenGL
    3.文件系统 id检测
    4.检测是否为出厂rom




public static final String ID = getString("ro.build.id");
public static final String DISPLAY = getString("ro.build.display.id");
public static final String PRODUCT = getString("ro.product.name");
public static final String DEVICE = getString("ro.product.device");
public static final String BOARD = getString("ro.product.board");
public static final String MANUFACTURER = getString("ro.product.manufacturer");
public static final String BRAND = getString("ro.product.brand");
public static final String MODEL = getString("ro.product.model");
public static final String BOOTLOADER = getString("ro.bootloader");
public static final String SKU = getString("ro.boot.hardware.sku");
public static final String ODM_SKU = getString("ro.boot.product.hardware.sku");
public static final boolean IS_EMULATOR = getString("ro.boot.qemu").equals("1");
public static final String SERIAL = getString("no.such.thing");
public static final String[] SUPPORTED_ABIS = getStringList("ro.product.cpu.abilist", ",");
public static final String[] SUPPORTED_32_BIT_ABIS = getStringList("ro.product.cpu.abilist32", ",");
public static final String INCREMENTAL = getString("ro.build.version.incremental");
public static final String RELEASE = getString("ro.build.version.release");
public static final String RELEASE_OR_PREVIEW_DISPLAY = getString("ro.build.version.release_or_preview_display");
public static final String BASE_OS = SystemProperties.get("ro.build.version.base_os", "");
public static final String SECURITY_PATCH = SystemProperties.get("ro.build.version.security_patch", "");
public static final String SDK = getString("ro.build.version.sdk");
public static final int SDK_INT = SystemProperties.getInt("ro.build.version.sdk", 0);


<androidx.core.widget.NestedScrollView style="@style/ScrollViewStyle">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="800dp"
                android:orientation="vertical"
                android:gravity="center"
                >


                <TextView
                    android:id="@+id/page1_button_text"
                    android:layout_width="match_parent"
                    android:layout_height="460dp"
                    android:scrollbars="vertical"
                    android:singleLine="false"
                    android:text="content"
                    android:padding="10dp"
                    android:fadeScrollbars="false"
                    android:scrollbarThumbVertical="@drawable/text_scroll"
                    android:textSize="14sp" />



                <com.xuexiang.xui.widget.shadow.ShadowTextView
                    android:id="@+id/page1_button_bianji"
                    android:layout_width="200dp"
                    android:layout_height="60dp"
                    android:layout_marginTop="10dp"
                    android:gravity="center"
                    android:paddingEnd="5dp"
                    android:paddingBottom="5dp"
                    android:text="变机"
                    android:textColor="@color/xui_config_color_white"
                    app:sd_bgColor="?attr/colorAccent"
                    app:sd_offsetX="5dp"
                    app:sd_offsetY="5dp"
                    app:sd_shadowRadius="5dp"
                    app:sd_shapeRadius="5dp" />

            </LinearLayout>
        </androidx.core.widget.NestedScrollView>

