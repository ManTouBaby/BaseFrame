package com.hy.baseframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/08/09 21:14
 * @desc:
 */
public class QWPoliceInfo implements Parcelable {
    public QWPoliceInfo() {
    }

    /**
     * policeEquipment : [{"id":"8333324","equipmentName":"8333324","equipmentNum":"8333324","equipmentType":"st","equipmentTypeName":"数字对讲机","ifuse":"R","createtime":"2018-03-30 04:25:17","updatetime":"2018-03-30 04:25:17","mainUnit":"440103460000","mainUnitName":"东漖派出所","dutyUnit":"440103460000","dutyUnitName":"东漖派出所","type":"equipment","policeId":null,"gpsnum":"8333324"}]
     * police : {"id":"032964","policeName":"甘洁","idNum":"440921197001120018","policeNum":"032964","policeType":"mj","policeTypeName":"民警","policeSort":null,"policeSortName":null,"mainUnit":"440103460000","mainUnitName":"东漖派出所","dutyUnit":"440103460000","dutyUnitName":"东漖派出所","innerUnit":null,"innerUnitName":null,"job":"ZW10004","jobName":"巡视员","isLeader":"0","gender":"1","mobilePhone":"13500031155","birthday":"1970-01-12","createtime":"2018-06-14 14:39:43","updatetime":"2018-08-08 16:50:56","ifuse":"R","nation":null,"degree":null,"political":"TYMH0731","nativePlace":"TYMH0731","status":null,"pinyin":null}
     */

    private PoliceBean police;
    private List<BBEquipment> policeEquipment;

    protected QWPoliceInfo(Parcel in) {
        police = in.readParcelable(PoliceBean.class.getClassLoader());
        policeEquipment = in.createTypedArrayList(BBEquipment.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(police, flags);
        dest.writeTypedList(policeEquipment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QWPoliceInfo> CREATOR = new Creator<QWPoliceInfo>() {
        @Override
        public QWPoliceInfo createFromParcel(Parcel in) {
            return new QWPoliceInfo(in);
        }

        @Override
        public QWPoliceInfo[] newArray(int size) {
            return new QWPoliceInfo[size];
        }
    };

    public PoliceBean getPolice() {
        return police;
    }

    public void setPolice(PoliceBean police) {
        this.police = police;
    }

    public List<BBEquipment> getPoliceEquipment() {
        return policeEquipment;
    }

    public void setPoliceEquipment(List<BBEquipment> policeEquipment) {
        this.policeEquipment = policeEquipment;
    }
}
