package com.stone.aidldemo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * create by StoneWay on 2020/7/8
 */
public class UserInfoBean implements Parcelable {
    private String userName;
    private int age;
    private boolean genderBoy;

    public UserInfoBean(Parcel in) {
        userName = in.readString();
        age = in.readInt();
        genderBoy = in.readByte() != 0;
    }

    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        @Override
        public UserInfoBean createFromParcel(Parcel in) {
            return new UserInfoBean(in);
        }

        @Override
        public UserInfoBean[] newArray(int size) {
            return new UserInfoBean[size];
        }
    };

    public UserInfoBean() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGenderBoy() {
        return genderBoy;
    }

    public void setGenderBoy(boolean genderBoy) {
        this.genderBoy = genderBoy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(age);
        dest.writeByte((byte) (genderBoy ? 1 : 0));
    }
}
