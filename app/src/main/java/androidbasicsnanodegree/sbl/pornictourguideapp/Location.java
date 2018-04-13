/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.os.Parcel;
import android.os.Parcelable;

// Custom object used to store details about a specific location

public class Location implements Parcelable {

    // Following methods are implemented in order to pass data between activities using Parcelable interface

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeString(this.OpeningDays);
        dest.writeInt(this.Category);
        dest.writeInt(this.ImageId);
        dest.writeString(this.Description);
        dest.writeString(this.Informations);
        dest.writeInt(this.AudioId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Declaring the attributes

    private String Name;

    // Opening days is intended to be used as a subtitle in the RecyclerView so it needs to be short (i.e : Open daily, or Open tue-fri)
    private String OpeningDays;

    private int Category;
    private int ImageId;
    private String Description;

    // Information variable should contains practical infos like opening hours and prices if applicable
    private String Informations;

    private int AudioId;

    // Following method is the general constructor

    public Location(String name, String openingDays, int category, int imageId, String description, String informations, int audioId) {

        this.Name = name;
        this.OpeningDays = openingDays;
        this.Category = category;
        this.ImageId = imageId;
        this.Description = description;
        this.Informations = informations;
        this.AudioId = audioId;
    }

    //Following constructor is used to create an object with Parcelable (useful to pass data between activities)

    private Location(Parcel in) {
        this.Name = in.readString();
        this.OpeningDays = in.readString();
        this.Category = in.readInt();
        this.ImageId = in.readInt();
        this.Description = in.readString();
        this.Informations = in.readString();
        this.AudioId = in.readInt();
    }

    //Following methods are used to access the date from outside the class

    public String getName() {
        return this.Name;
    }

    public String getOpeningDays() {
        return this.OpeningDays;
    }

    public int getCategory() {
        return this.Category;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getDescription() {
        return Description;
    }

    public String getInformations() {
        return Informations;
    }

    public int getAudioId() {
        return AudioId;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Informations='" + Informations + '\'' +
                '}';
    }
}
