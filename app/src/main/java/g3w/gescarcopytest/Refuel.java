package g3w.gescarcopytest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Manuel Cruz on 28/08/2017.
 */

public class Refuel implements Parcelable{

    String refuelDate;
    String licensePlate;
    String model;
    String fuel;
    Float quantity;
    Float unitPrice;
    Float total;
    String deposit;

    public Refuel(String nrefuelDate, String nlicensePlate, String nmodel, String nfuel, Float nquantity, Float nunitPrice, Float ntotal, String nDeposit) {
        this.refuelDate = nrefuelDate;
        this.licensePlate = nlicensePlate;
        this.model = nmodel;
        this.fuel = nfuel;
        this.quantity = nquantity;
        this.unitPrice = nunitPrice;
        this.total = ntotal;
        this.deposit = nDeposit;
    }

    public String getRefuelDate() {
        return refuelDate;
    }

    public void setRefuelDate(String refuelDate) {
        this.refuelDate = refuelDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        deposit = deposit;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(refuelDate);
        dest.writeString(licensePlate);
        dest.writeString(model);
        dest.writeString(fuel);
        dest.writeFloat(quantity);
        dest.writeFloat(unitPrice);
        dest.writeFloat(total);
        dest.writeString(deposit);
    }

    public static final Parcelable.Creator<Refuel> CREATOR = new Parcelable.Creator<Refuel>()
    {
        public Refuel createFromParcel(Parcel input) { return new Refuel(input);}
        public Refuel[] newArray(int size) { return new Refuel[size];}

    };

    private Refuel (Parcel input)
    {
        refuelDate = input.readString();
        licensePlate = input.readString();
        model = input.readString();
        fuel = input.readString();
        quantity = input.readFloat();
        unitPrice = input.readFloat();
        total = input.readFloat();
        deposit = input.readString();
    }
}
