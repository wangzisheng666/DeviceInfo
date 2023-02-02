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

import java.util.List;

public class deviceIfo {


    public buildInfo buildInfos;
    public String ua;
    public String javaUa;
    public String imei;
    public String imsi;
    public String mac;
    public String bssid;
    public String ssid;
    public String phone;
    public String phoneStatus;
    public String phoneType;
    public String width;
    public String height;
    public String densityDpi;
    public String xdpi;
    public String ydpi;
    public String carrier;
    public String mcc;
    public String mnc;
    public String carrierCode;
    public String countryCode;
    public String latitude;
    public String longitude;
    public String simSerial;
    public String simStatus;
    public String androidId;
    public String androidSerial;
    public String ip;
    public String diskSize;
    public String networkType;
    public String networkTypeName;
    public String networkSubType;
    public String networkSubTypeName;
    public String blueTooth;
    public String email;

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getJavaUa() {
        return javaUa;
    }

    public void setJavaUa(String javaUa) {
        this.javaUa = javaUa;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneStatus() {
        return phoneStatus;
    }

    public void setPhoneStatus(String phoneStatus) {
        this.phoneStatus = phoneStatus;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(String densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String getXdpi() {
        return xdpi;
    }

    public void setXdpi(String xdpi) {
        this.xdpi = xdpi;
    }

    public String getYdpi() {
        return ydpi;
    }

    public void setYdpi(String ydpi) {
        this.ydpi = ydpi;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }

    public String getSimStatus() {
        return simStatus;
    }

    public void setSimStatus(String simStatus) {
        this.simStatus = simStatus;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidSerial() {
        return androidSerial;
    }

    public void setAndroidSerial(String androidSerial) {
        this.androidSerial = androidSerial;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getNetworkTypeName() {
        return networkTypeName;
    }

    public void setNetworkTypeName(String networkTypeName) {
        this.networkTypeName = networkTypeName;
    }

    public String getNetworkSubType() {
        return networkSubType;
    }

    public void setNetworkSubType(String networkSubType) {
        this.networkSubType = networkSubType;
    }

    public String getNetworkSubTypeName() {
        return networkSubTypeName;
    }

    public void setNetworkSubTypeName(String networkSubTypeName) {
        this.networkSubTypeName = networkSubTypeName;
    }

    public String getBlueTooth() {
        return blueTooth;
    }

    public void setBlueTooth(String blueTooth) {
        this.blueTooth = blueTooth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public buildInfo getBuildInfos() {
        return buildInfos;
    }

    public void setBuildInfos(buildInfo buildInfos) {
        this.buildInfos = buildInfos;
    }

    @Override
    public String toString() {
        return "deviceIfo{" +
                "buildInfos=" + buildInfos +
                ", ua='" + ua + '\'' +
                ", javaUa='" + javaUa + '\'' +
                ", imei='" + imei + '\'' +
                ", imsi='" + imsi + '\'' +
                ", mac='" + mac + '\'' +
                ", bssid='" + bssid + '\'' +
                ", ssid='" + ssid + '\'' +
                ", phone='" + phone + '\'' +
                ", phoneStatus='" + phoneStatus + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", densityDpi='" + densityDpi + '\'' +
                ", xdpi='" + xdpi + '\'' +
                ", ydpi='" + ydpi + '\'' +
                ", carrier='" + carrier + '\'' +
                ", mcc='" + mcc + '\'' +
                ", mnc='" + mnc + '\'' +
                ", carrierCode='" + carrierCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", simSerial='" + simSerial + '\'' +
                ", simStatus='" + simStatus + '\'' +
                ", androidId='" + androidId + '\'' +
                ", androidSerial='" + androidSerial + '\'' +
                ", ip='" + ip + '\'' +
                ", diskSize='" + diskSize + '\'' +
                ", networkType='" + networkType + '\'' +
                ", networkTypeName='" + networkTypeName + '\'' +
                ", networkSubType='" + networkSubType + '\'' +
                ", networkSubTypeName='" + networkSubTypeName + '\'' +
                ", blueTooth='" + blueTooth + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
