/*
 * Copyright (C) 2023 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mobiles.devices.adapter.entity;

public class buildInfo {
    public String  board;
    public String  bootloader;
    public String  brand;
    public String  cpu_abi;
    public String  cpu_abilist;
    public String  device;
    public String  display;
    public String  radioVersion;
    public String  increment;
    public String  fingerPrint;
    public String  hardWare;
    public String  host;
    public String  id;
    public String  manufacture;
    public String  serial;
    public String  product;
    public String  tags;
    public String  time;
    public String  type;
    public String  user;
    public String  sdk;
    public String  sdkInit;
    public String  model;
    public String  osName;
    public String  osArch;
    public String  osVersion;
    public String  androidVersion;
    public String  SECURITY_PATCH;


    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBootloader() {
        return bootloader;
    }

    public void setBootloader(String bootloader) {
        this.bootloader = bootloader;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCpu_abi() {
        return cpu_abi;
    }

    public void setCpu_abi(String cpu_abi) {
        this.cpu_abi = cpu_abi;
    }

    public String getCpu_abilist() {
        return cpu_abilist;
    }

    public void setCpu_abilist(String cpu_abilist) {
        this.cpu_abilist = cpu_abilist;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getRadioVersion() {
        return radioVersion;
    }

    public void setRadioVersion(String radioVersion) {
        this.radioVersion = radioVersion;
    }

    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getHardWare() {
        return hardWare;
    }

    public void setHardWare(String hardWare) {
        this.hardWare = hardWare;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSdk() {
        return sdk;
    }

    public void setSdk(String sdk) {
        this.sdk = sdk;
    }

    public String getSdkInit() {
        return sdkInit;
    }

    public void setSdkInit(String sdkInit) {
        this.sdkInit = sdkInit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getSECURITY_PATCH() {
        return SECURITY_PATCH;
    }

    public void setSECURITY_PATCH(String SECURITY_PATCH) {
        this.SECURITY_PATCH = SECURITY_PATCH;
    }

    @Override
    public String toString() {
        return "buildInfo{" +
                "board='" + board + '\'' +
                ", bootloader='" + bootloader + '\'' +
                ", brand='" + brand + '\'' +
                ", cpu_abi='" + cpu_abi + '\'' +
                ", cpu_abilist='" + cpu_abilist + '\'' +
                ", device='" + device + '\'' +
                ", display='" + display + '\'' +
                ", radioVersion='" + radioVersion + '\'' +
                ", increment='" + increment + '\'' +
                ", fingerPrint='" + fingerPrint + '\'' +
                ", hardWare='" + hardWare + '\'' +
                ", host='" + host + '\'' +
                ", id='" + id + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", serial='" + serial + '\'' +
                ", product='" + product + '\'' +
                ", tags='" + tags + '\'' +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", sdk='" + sdk + '\'' +
                ", sdkInit='" + sdkInit + '\'' +
                ", model='" + model + '\'' +
                ", osName='" + osName + '\'' +
                ", osArch='" + osArch + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", androidVersion='" + androidVersion + '\'' +
                ", SECURITY_PATCH='" + SECURITY_PATCH + '\'' +
                '}';
    }
}
