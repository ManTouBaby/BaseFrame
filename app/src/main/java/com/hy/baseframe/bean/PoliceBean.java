package com.hy.baseframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2019/07/25 11:25
 * @desc:
 */
public class PoliceBean implements Parcelable{
    public PoliceBean() {
    }

    /**
     * id : 032964
     * policeName : 甘洁
     * idNum : 440921197001120018
     * policeNum : 032964
     * policeType : mj
     * policeTypeName : 民警
     * policeSort : null
     * policeSortName : null
     * mainUnit : 440103460000
     * mainUnitName : 东漖派出所
     * dutyUnit : 440103460000
     * dutyUnitName : 东漖派出所
     * innerUnit : null
     * innerUnitName : null
     * job : ZW10004
     * jobName : 巡视员
     * isLeader : 0:否 1:是
     * gender : 1:男  2:女
     * mobilePhone : 13500031155
     * birthday : 1970-01-12
     * createtime : 2018-06-14 14:39:43
     * updatetime : 2018-08-08 16:50:56
     * ifuse : R
     * nation : null
     * degree : null
     * political : TYMH0731
     * nativePlace : TYMH0731
     * status : null
     * pinyin : null
     */

    private String id;
    private String policeName;
    private String idNum;
    private String policeNum;
    private String policeType;
    private String policeTypeName;
    private Object policeSort;
    private Object policeSortName;
    private String mainUnit;
    private String mainUnitName;
    private String dutyUnit;
    private String dutyUnitName;
    private Object innerUnit;
    private Object innerUnitName;
    private String job;
    private String jobName;
    private String isLeader;
    private int gender;
    private String mobilePhone;
    private String birthday;
    private String createtime;
    private String updatetime;
    private String ifuse;
    private Object nation;
    private Object degree;
    private String political;
    private String nativePlace;
    private Object status;
    private Object pinyin;

    protected PoliceBean(Parcel in) {
        id = in.readString();
        policeName = in.readString();
        idNum = in.readString();
        policeNum = in.readString();
        policeType = in.readString();
        policeTypeName = in.readString();
        mainUnit = in.readString();
        mainUnitName = in.readString();
        dutyUnit = in.readString();
        dutyUnitName = in.readString();
        job = in.readString();
        jobName = in.readString();
        isLeader = in.readString();
        gender = in.readInt();
        mobilePhone = in.readString();
        birthday = in.readString();
        createtime = in.readString();
        updatetime = in.readString();
        ifuse = in.readString();
        political = in.readString();
        nativePlace = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(policeName);
        dest.writeString(idNum);
        dest.writeString(policeNum);
        dest.writeString(policeType);
        dest.writeString(policeTypeName);
        dest.writeString(mainUnit);
        dest.writeString(mainUnitName);
        dest.writeString(dutyUnit);
        dest.writeString(dutyUnitName);
        dest.writeString(job);
        dest.writeString(jobName);
        dest.writeString(isLeader);
        dest.writeInt(gender);
        dest.writeString(mobilePhone);
        dest.writeString(birthday);
        dest.writeString(createtime);
        dest.writeString(updatetime);
        dest.writeString(ifuse);
        dest.writeString(political);
        dest.writeString(nativePlace);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PoliceBean> CREATOR = new Creator<PoliceBean>() {
        @Override
        public PoliceBean createFromParcel(Parcel in) {
            return new PoliceBean(in);
        }

        @Override
        public PoliceBean[] newArray(int size) {
            return new PoliceBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public String getPoliceTypeName() {
        return policeTypeName;
    }

    public void setPoliceTypeName(String policeTypeName) {
        this.policeTypeName = policeTypeName;
    }

    public Object getPoliceSort() {
        return policeSort;
    }

    public void setPoliceSort(Object policeSort) {
        this.policeSort = policeSort;
    }

    public Object getPoliceSortName() {
        return policeSortName;
    }

    public void setPoliceSortName(Object policeSortName) {
        this.policeSortName = policeSortName;
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

    public Object getInnerUnit() {
        return innerUnit;
    }

    public void setInnerUnit(Object innerUnit) {
        this.innerUnit = innerUnit;
    }

    public Object getInnerUnitName() {
        return innerUnitName;
    }

    public void setInnerUnitName(Object innerUnitName) {
        this.innerUnitName = innerUnitName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(String isLeader) {
        this.isLeader = isLeader;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getIfuse() {
        return ifuse;
    }

    public void setIfuse(String ifuse) {
        this.ifuse = ifuse;
    }

    public Object getNation() {
        return nation;
    }

    public void setNation(Object nation) {
        this.nation = nation;
    }

    public Object getDegree() {
        return degree;
    }

    public void setDegree(Object degree) {
        this.degree = degree;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getPinyin() {
        return pinyin;
    }

    public void setPinyin(Object pinyin) {
        this.pinyin = pinyin;
    }
}
