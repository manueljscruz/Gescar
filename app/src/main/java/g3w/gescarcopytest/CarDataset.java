package g3w.gescarcopytest;

import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Manuel Cruz on 01/06/2017.
 */

public class CarDataset implements Parcelable
{

    int vehicleID;
    String model;
    String licencePlate;
    String brand;
    Double latitude;
    Double longitude;

    public CarDataset(Integer nVehicleID, String nModel, String nLicencePlate, String nBrand, Double nLatitude, Double nLongitude)
    {
        this.vehicleID = nVehicleID;
        this.model = nModel;
        this.licencePlate = nLicencePlate;
        this.brand = nBrand;
        this.latitude = nLatitude;
        this.longitude = nLongitude;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(vehicleID);
        dest.writeString(model);
        dest.writeString(licencePlate);
        dest.writeString(brand);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Parcelable.Creator<CarDataset> CREATOR
            = new Parcelable.Creator<CarDataset>()
    {
        public CarDataset createFromParcel(Parcel input)
        {
            return new CarDataset(input);
        }

        public CarDataset[] newArray(int size)
        {
            return new CarDataset[size];
        }
    };

    private CarDataset(Parcel input)
    {
        vehicleID = input.readInt();
        model = input.readString();
        licencePlate = input.readString();
        brand = input.readString();
        latitude = input.readDouble();
        longitude = input.readDouble();
    }

}
