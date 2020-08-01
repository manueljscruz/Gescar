package g3w.gescarcopytest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import g3w.gescarcopytest.CarDataset;
import g3w.gescarcopytest.R;

/**
 * Created by Manuel Cruz on 29/10/2017.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private ArrayList<CarDataset> mList;
    private LayoutInflater mLayoutInflater;

    public CarAdapter(Context c, ArrayList<CarDataset> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.item_car, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        myViewHolder.ivCar.setImageResource(mList.get(position).getVehicleID());
        myViewHolder.tvBrand.setText(mList.get(position).getBrand());
        myViewHolder.tvLicensePlate.setText(mList.get(position).getLicencePlate());

    }

    public void addListItem(CarDataset c, int position)
    {
        mList.add(c);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivCar;
        public TextView tvBrand;
        public TextView tvLicensePlate;

        public MyViewHolder(View itemView)
        {
            super(itemView);

            ivCar = (ImageView) itemView.findViewById(R.id.iv_car);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_listBrand);
            tvLicensePlate = (TextView) itemView.findViewById(R.id.tv_listLicensePlate);
        }
    }
}

