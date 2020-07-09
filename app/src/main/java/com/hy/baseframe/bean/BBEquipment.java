package com.hy.baseframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author:Administrator
 * @date:2018/03/21 下午 1:42
 * @desc:
 */

public class BBEquipment implements Parcelable {

    /**
     * id : 43486
     * equipmentName : 许俊阳
     * equipmentNum : 9218909
     * equipmentType : st
     * equipmentTypeName : 数字对讲机
     * ifuse : R
     * createtime : 2018-01-04 18:02:48
     * updatetime : 2018-01-04 18:02:48
     * mainUnit : 440311580000
     * mainUnitName : null
     * dutyUnit : 440311580000
     * dutyUnitName : null
     * type : null
     * gpsnum : null
     */

    private String id;
    private String equipmentName;
    private String equipmentNum;
    private String equipmentType;
    private String equipmentTypeName;
    private String ifuse;
    private String createtime;
    private String updatetime;
    private String mainUnit;
    private String mainUnitName;
    private String dutyUnit;
    private String dutyUnitName;
    private String type;
    private String gpsnum;
    private String policeId;
    private String policeName;
    private boolean isSelect;


    public BBEquipment(){}

    protected BBEquipment(Parcel in) {
        id = in.readString();
        equipmentName = in.readString();
        equipmentNum = in.readString();
        equipmentType = in.readString();
        equipmentTypeName = in.readString();
        ifuse = in.readString();
        createtime = in.readString();
        updatetime = in.readString();
        mainUnit = in.readString();
        mainUnitName = in.readString();
        dutyUnit = in.readString();
        dutyUnitName = in.readString();
        type = in.readString();
        gpsnum = in.readString();
        policeId = in.readString();
        policeName = in.readString();
        isSelect = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(equipmentName);
        dest.writeString(equipmentNum);
        dest.writeString(equipmentType);
        dest.writeString(equipmentTypeName);
        dest.writeString(ifuse);
        dest.writeString(createtime);
        dest.writeString(updatetime);
        dest.writeString(mainUnit);
        dest.writeString(mainUnitName);
        dest.writeString(dutyUnit);
        dest.writeString(dutyUnitName);
        dest.writeString(type);
        dest.writeString(gpsnum);
        dest.writeString(policeId);
        dest.writeString(policeName);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BBEquipment> CREATOR = new Creator<BBEquipment>() {
        @Override
        public BBEquipment createFromParcel(Parcel in) {
            return new BBEquipment(in);
        }

        @Override
        public BBEquipment[] newArray(int size) {
            return new BBEquipment[size];
        }
    };

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNum() {
        return equipmentNum;
    }

    public void setEquipmentNum(String equipmentNum) {
        this.equipmentNum = equipmentNum;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public String getIfuse() {
        return ifuse;
    }

    public void setIfuse(String ifuse) {
        this.ifuse = ifuse;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

    public String getMainUnitName() {
        return mainUnitName;
    }

    public void setMainUnitName(String mainUnitName) {
        this.mainUnitName = mainUnitName;
    }

    public String getDutyUnit() {
        return dutyUnit;
    }

    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
    }

    public String getDutyUnitName() {
        return dutyUnitName;
    }

    public void setDutyUnitName(String dutyUnitName) {
        this.dutyUnitName = dutyUnitName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGpsnum() {
        return gpsnum;
    }

    public void setGpsnum(String gpsnum) {
        this.gpsnum = gpsnum;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
}
