package g3w.gescarcopytest;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static g3w.gescarcopytest.R.drawable.listcars;

/**
 * Created by Manuel Cruz on 18/06/2017.
 */

public class Menu extends AppCompatActivity{

    // IMAGE BUTTONS DE MENU
    ImageButton geolocate;
    ImageButton listcar;
    ImageButton refuel;
    ImageButton carRepair;

    // END OF IMAGE BUTTONS

    ArrayList<CarDataset> carros = null;
    Bundle bdl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bdl = getIntent().getExtras();

        if( bdl != null)
        {
            Log.e("teste1", "TESTE DEU");
            carros = bdl.getParcelableArrayList("cars");
            Log.e("teste3", "Veículo ID MENU:"+carros.get(0).vehicleID);
        }
        else
        {
            Toast.makeText(Menu.this, "O Bundle está vazio", Toast.LENGTH_LONG).show();
            Log.e("teste", "TESTE NAO DEU");
        }

        listcar = (ImageButton)findViewById(R.id.imgbtn_listcar); // Definir o ImageButton para o ID específico
        geolocate = (ImageButton)findViewById(R.id.imgbtn_geolocalizacao); // Definir o ImageButton para o ID específico
        carRepair = (ImageButton)findViewById(R.id.imgbtn_despesas); // Definir o Imagebutton para o ID específico
        refuel = (ImageButton)findViewById(R.id.imgbtn_abastecimentos); // Defininr o Imagebutton para o ID específico


        listcar.setOnClickListener(new View.OnClickListener() // Actividade para Listar Carros
        {
            @Override
            public void onClick(View v)
            {
                
                Intent listcars = new Intent();
                listcars.putParcelableArrayListExtra("cars", carros);
                listcars.setClass(Menu.this, ListCar.class);
                startActivity(listcars);
            }
        });

        geolocate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent geolocalizar = new Intent();
                geolocalizar.putParcelableArrayListExtra("cars", carros);
                geolocalizar.setClass(Menu.this,SelectCarToTrack.class);
                startActivity(geolocalizar);
            }
        });

        carRepair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent carRepair = new Intent();
                carRepair.putParcelableArrayListExtra("cars", carros);
                carRepair.setClass(Menu.this, CarRepair.class);
                startActivity(carRepair);
            }
        });

        refuel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent carRefuel = new Intent();
                carRefuel.putParcelableArrayListExtra("cars", carros);
                carRefuel.setClass(Menu.this, FuelSupplys.class);
                startActivity(carRefuel);
            }
        });

    }

}
