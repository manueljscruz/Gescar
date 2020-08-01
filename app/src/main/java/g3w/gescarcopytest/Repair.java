package g3w.gescarcopytest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manuel Cruz on 09/09/2017.
 */

public class Repair implements Parcelable{

    //int repairEntry;
    String licensePlate;
    String date;
    String typeOfCost;
    String supplier;
    String description;
    Float cost;


    public Repair(String nLicensePlate, String nDate, String nTypeOfCost, String nSupplier, String nDescription, Float nCost) {
        this.licensePlate = nLicensePlate;
        this.date = nDate;
        this.typeOfCost = nTypeOfCost;
        this.supplier = nSupplier;
        this.description = nDescription;
        this.cost = nCost;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfCost() {
        return typeOfCost;
    }

    public void setTypeOfCost(String typeOfCost) {
        this.typeOfCost = typeOfCost;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(licensePlate);
        dest.writeString(date);
        dest.writeString(typeOfCost);
        dest.writeString(supplier);
        dest.writeString(description);
        dest.writeFloat(cost);
    }

    public static final Parcelable.Creator<Repair> CREATOR = new Parcelable.Creator<Repair>()
    {
        public Repair createFromParcel(Parcel input) { return new Repair(input);}
        public Repair[] newArray(int size){return new Repair[size];}
    };

    private Repair (Parcel input)  // THE ERROR IS COMING THROUGH HERE! ( LINE 100 )
    {
        licensePlate = input.readString();
        date = input.readString();
        typeOfCost = input.readString();
        supplier = input.readString();
        description = input.readString();
        cost = input.readFloat();
    }
}
