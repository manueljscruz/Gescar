package g3w.gescarcopytest;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import g3w.gescarcopytest.fragments.CarFragment;

/**
 * Created by Manuel Cruz on 18/06/2017.
 */

public class ListCar extends AppCompatActivity{

    private ArrayList<CarDataset> carros = null;
    Bundle bdl;
    TextView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcar);

        // RecyclerView listcar_rv = (RecyclerView)findViewById(R.id.rv_listcars2);

        carros = getIntent().getParcelableArrayListExtra("cars");
        if(carros == null)
        {
            carros = new ArrayList<>();
        }


        CarFragment frag = (CarFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(frag == null)
        {
            frag = new CarFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }

         // lista = (TextView) findViewById(R.id.tv_cars);
         /*
         for (int c = 0; c < carros.size(); c++)
         {
             lista.append("viaturaID:" + carros.get(c).vehicleID + "\r\n"
                     + "matricula:" + carros.get(c).licencePlate +  "\r\n"
                     + "modelo:"+ carros.get(c).model + "\r\n"
                     + "marca:"+ carros.get(c).brand +  "\r\n"
                     + "lat:"+ carros.get(c).latitude + "\r\n"
                     + "lon:"+ carros.get(c).longitude + "\r\n"
                     +
                     "\r\n");
         }
         */

    }

    public ArrayList<CarDataset> getSetCarList()
    {
        int[] fotoCarro = new int[]{R.drawable.car0 , R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4, R.drawable.car5, R.drawable.car6, R.drawable.car7, R.drawable.car8, R.drawable.car9, R.drawable.car10, R.drawable.car11, R.drawable.car12, R.drawable.car13, R.drawable.car14, R.drawable.car15, R.drawable.car16, R.drawable.car17, R.drawable.car18, R.drawable.car19, R.drawable.car20, R.drawable.car21, R.drawable.car22, R.drawable.car23, R.drawable.car24, R.drawable.car25, R.drawable.car26, R.drawable.car27, R.drawable.car28, R.drawable.car29, R.drawable.car30, R.drawable.car31, R.drawable.car32, R.drawable.car33, R.drawable.car34, R.drawable.car35, R.drawable.car36, R.drawable.car37, R.drawable.car38, R.drawable.car39, R.drawable.car40, R.drawable.car41, R.drawable.car42, R.drawable.car43, R.drawable.car44, R.drawable.car45, R.drawable.car46, R.drawable.car47, R.drawable.car48, R.drawable.car49, R.drawable.car50,
                R.drawable.car51, R.drawable.car52, R.drawable.car53, R.drawable.car54, R.drawable.car55, R.drawable.car56, R.drawable.car57, R.drawable.car58, R.drawable.car59, R.drawable.car60, R.drawable.car61, R.drawable.car62, R.drawable.car63, R.drawable.car64, R.drawable.car65, R.drawable.car66, R.drawable.car67, R.drawable.car68, R.drawable.car69, R.drawable.car70, R.drawable.car71, R.drawable.car72, R.drawable.car73, R.drawable.car74, R.drawable.car75, R.drawable.car76, R.drawable.car77, R.drawable.car78, R.drawable.car79, R.drawable.car80, R.drawable.car81, R.drawable.car82, R.drawable.car83, R.drawable.car84, R.drawable.car85, R.drawable.car86, R.drawable.car87, R.drawable.car88, R.drawable.car89, R.drawable.car90, R.drawable.car91, R.drawable.car92, R.drawable.car93, R.drawable.car94, R.drawable.car95, R.drawable.car96, R.drawable.car97, R.drawable.car98, R.drawable.car99, R.drawable.car100, R.drawable.car101, R.drawable.car102, R.drawable.car103, R.drawable.car104, R.drawable.car105, R.drawable.car106, R.drawable.car107, R.drawable.car108, R.drawable.car109, R.drawable.car110};
        ArrayList<CarDataset> carrosAux = new ArrayList<>();

        for (int i = 0; i < fotoCarro.length ; i++)
        {
            for (int j = 0; j < carros.size(); j++)
            {
                if (carros.get(j).getVehicleID() == i)
                {
                    CarDataset c = new CarDataset( fotoCarro [i % fotoCarro.length], carros.get(j).model, carros.get(j).licencePlate, carros.get(j).brand, carros.get(j).latitude, carros.get(j).latitude );
                    carrosAux.add(c);
                }
            }

        }
        return carrosAux;
    }


}
