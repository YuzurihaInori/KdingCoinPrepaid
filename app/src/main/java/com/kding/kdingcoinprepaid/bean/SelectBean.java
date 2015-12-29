package com.kding.kdingcoinprepaid.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Toast-pc on 2015/12/21.
 */
public class SelectBean implements Parcelable ,Comparable<SelectBean>{

    public String id = "";
    public String name = "";
    private boolean isChecked;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeByte(isChecked ? (byte) 1 : (byte) 0);
    }

    public SelectBean() {
    }

    private SelectBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<SelectBean> CREATOR = new Parcelable.Creator<SelectBean>() {
        public SelectBean createFromParcel(Parcel source) {
            return new SelectBean(source);
        }

        public SelectBean[] newArray(int size) {
            return new SelectBean[size];
        }
    };

    @Override
    public String toString() {
        return id +" ";
    }


    @Override
    public int compareTo(@NonNull SelectBean another) {
        return this.id.compareTo(another.id);
    }

    public boolean isSelected() {
        return isChecked;
    }

    public void setSelected(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
