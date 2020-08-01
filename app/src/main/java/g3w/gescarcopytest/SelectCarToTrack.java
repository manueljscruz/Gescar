package g3w.gescarcopytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import g3w.gescarcopytest.ExpandableRecyclerListViewPickCar.CarChild;
import g3w.gescarcopytest.ExpandableRecyclerListViewPickCar.CarCreator;
import g3w.gescarcopytest.ExpandableRecyclerListViewPickCar.CarExpandableAdapter;
import g3w.gescarcopytest.ExpandableRecyclerListViewPickCar.CarParent;
import g3w.gescarcopytest.ExpandableRecyclerListViewPickCar.RecyclerItemClickListener;

public class SelectCarToTrack extends AppCompatActivity
{
    RecyclerView recyclerView;
    ArrayList<String> matriculas = new ArrayList<String>();
    ArrayList<CarDataset> carros = null;
    CarDataset selecionado = null;
    Bundle bdl;
    Context context;

    // Necessário para o RecyclerView
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        ((CarExpandableAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car_to_track);

        recyclerView = (RecyclerView)findViewById(R.id.rv_listcars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        bdl = getIntent().getExtras();

        if( bdl != null)
        {
            carros = bdl.getParcelableArrayList("cars");
        }
        else
        {
            Toast.makeText(SelectCarToTrack.this, "O Bundle está vazio", Toast.LENGTH_LONG).show();
        }

        obtainLicensePlates(carros);

        CarExpandableAdapter adapter = new CarExpandableAdapter(this,initData(matriculas));
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SelectCarToTrack.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                selecionado = carros.get(position);
                Intent geolocalizarSpecific = new Intent();
                geolocalizarSpecific.putExtra("select", selecionado);
                geolocalizarSpecific.setClass(SelectCarToTrack.this, Geolocalizcao.class);
                startActivity(geolocalizarSpecific);
            }

            @Override
            public void onItemLongCLick(View view, int position)
            {
            }
        }));
    }

    private void obtainLicensePlates(ArrayList<CarDataset> cars)
    {
        if(cars != null)
        {
            for(int i=0; i < carros.size(); i++)
            {
                String temp = "";
                temp = carros.get(i).licencePlate;
                matriculas.add(i,temp);
            }
        }
        else
        {
            Toast.makeText(SelectCarToTrack.this, "Não foram recebidos dados", Toast.LENGTH_LONG).show();
        }
    }

    private List<ParentObject> initData(ArrayList<String> licensePlates)
    {
        CarCreator carCreator = CarCreator.get(this, licensePlates);
        List<CarParent> cars = carCreator.getAll();
        List<ParentObject> parentObject = new ArrayList<>();
        for(CarParent car:cars)
        {
            List<Object> childList = new ArrayList<>();
            childList.add(new CarChild("Track your vehicle","See Details"));
            car.setChildObjectList(childList);
            parentObject.add(car);
        }
        return parentObject;
    }

}
