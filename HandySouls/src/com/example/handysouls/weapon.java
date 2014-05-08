package com.example.handysouls;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class weapon implements Parcelable{
	public String name = "";
	public String imgLoc = "";
	public Bitmap img;
	public String damage = "";
	public String durability = "";
	public String weight = "";
	public String statsNeeded = "";
	public String statBonuses = "";
	public String availability = "";
	public String specialNote = "";
	
	public weapon() {}
	
	public weapon(Parcel in) {
		this.name = in.readString();
		this.imgLoc = in.readString();
		this.damage = in.readString();
		this.durability = in.readString();
		this.weight = in.readString();
		this.statsNeeded = in.readString();
		this.statBonuses = in.readString();
		this.availability = in.readString();
		this.specialNote = in.readString();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeString(imgLoc);
		dest.writeString(damage);
		dest.writeString(durability);
		dest.writeString(weight);
		dest.writeString(statsNeeded);
		dest.writeString(statBonuses);
		dest.writeString(availability);
		dest.writeString(specialNote);
		
	}
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public weapon createFromParcel(Parcel in) {
			return new weapon(in);
		}
		
		public weapon[] newArray(int size) {
			return new weapon[size];
		}
	};
	
}
