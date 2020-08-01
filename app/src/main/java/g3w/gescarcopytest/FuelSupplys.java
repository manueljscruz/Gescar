package g3w.gescarcopytest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FuelSupplys extends AppCompatActivity {

    TableLayout tl;
    TableRow tr;
    TableRow tr_head;

    ArrayList<Refuel> abastecimentos = null;
    ArrayList<CarDataset> carros = null;

    ImageButton newRefuel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_supplys);


        carros = getIntent().getParcelableArrayListExtra("cars");
        if(carros == null)
        {
            carros = new ArrayList<>();
        }

        abastecimentos = getIntent().getParcelableArrayListExtra("refuelSupply");
        if(abastecimentos == null)
        {
            abastecimentos = new ArrayList<>();
        }

        abastecimentos.add(new Refuel("08/03/93", "82-AB-42", "Ibiza", "Gasolina 95", (float) 20.40, (float) 1.523, (float) 31.06, "Perelhal[PER]"));

        for (int i = 0; i < abastecimentos.size() ; i++)
        {
            Log.e("Refuel", "Carro:"+abastecimentos.get(i).getLicensePlate());
        }


        tl = (TableLayout) findViewById(R.id.tl_fuelsupplys_2ndrow);
        createRefuelTable(abastecimentos);

        newRefuel = (ImageButton)findViewById(R.id.imgbtn_fuelsupplys_insert);

        newRefuel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insertRepair = new Intent();
                insertRepair.putParcelableArrayListExtra("cars", carros);
                insertRepair.putParcelableArrayListExtra("refuelSupply", abastecimentos);
                insertRepair.setClass(FuelSupplys.this, NewRefuel.class);
                startActivity(insertRepair);
            }
        });
    }

    public void createRefuelTable(ArrayList<Refuel> abastecimentos)
    {
        tr_head = new TableRow(this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setId(View.generateViewId());
        tr_head.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView lable_dateRefuel = new TextView(this);
        lable_dateRefuel.setId(View.generateViewId());
        lable_dateRefuel.setText("Data_Abast.");
        lable_dateRefuel.setTextColor(Color.BLACK);
        lable_dateRefuel.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_dateRefuel);

        TextView lable_licensePlate = new TextView(this);
        lable_licensePlate.setId(View.generateViewId());
        lable_licensePlate.setText("Matricula.");
        lable_licensePlate.setTextColor(Color.BLACK);
        lable_licensePlate.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_licensePlate);

        TextView lable_model = new TextView(this);
        lable_model.setId(View.generateViewId());
        lable_model.setText("Modelo.");
        lable_model.setTextColor(Color.BLACK);
        lable_model.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_model);

        TextView lable_fuelType = new TextView(this);
        lable_fuelType.setId(View.generateViewId());
        lable_fuelType.setText("Combustivel.");
        lable_fuelType.setTextColor(Color.BLACK);
        lable_fuelType.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_fuelType);

        TextView lable_quantity = new TextView(this);
        lable_quantity.setId(View.generateViewId());
        lable_quantity.setText("Quantidade.");
        lable_quantity.setTextColor(Color.BLACK);
        lable_quantity.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_quantity);

        TextView lable_unitPrice = new TextView(this);
        lable_unitPrice.setId(View.generateViewId());
        lable_unitPrice.setText("Preço Unit.");
        lable_unitPrice.setTextColor(Color.BLACK);
        lable_unitPrice.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_unitPrice);

        TextView lable_total = new TextView(this);
        lable_total.setId(View.generateViewId());
        lable_total.setText("Total.");
        lable_total.setTextColor(Color.BLACK);
        lable_total.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_total);

        TextView lable_fuelDepot = new TextView(this);
        lable_fuelDepot.setId(View.generateViewId());
        lable_fuelDepot.setText("Deposito.");
        lable_fuelDepot.setTextColor(Color.BLACK);
        lable_fuelDepot.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_fuelDepot);

        tl.addView(tr_head, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < abastecimentos.size(); i++)
        {
            tr = new TableRow(this);
            tr.setId(View.generateViewId());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView lable_dateRefuelFuel = new TextView(this);
            lable_dateRefuelFuel.setId(View.generateViewId());
            lable_dateRefuelFuel.setText(abastecimentos.get(i).refuelDate);
            lable_dateRefuelFuel.setTextColor(Color.BLACK);
            lable_dateRefuelFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_dateRefuelFuel);

            TextView lable_licensePlateFuel = new TextView(this);
            lable_licensePlateFuel.setId(View.generateViewId());
            lable_licensePlateFuel.setText(abastecimentos.get(i).licensePlate);
            lable_licensePlateFuel.setTextColor(Color.BLACK);
            lable_licensePlateFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_licensePlateFuel);

            TextView lable_modelFuel = new TextView(this);
            lable_modelFuel.setId(View.generateViewId());
            lable_modelFuel.setText(abastecimentos.get(i).model);
            lable_modelFuel.setTextColor(Color.BLACK);
            lable_modelFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_modelFuel);

            TextView lable_fuelTypeFuel = new TextView(this);
            lable_fuelTypeFuel.setId(View.generateViewId());
            lable_fuelTypeFuel.setText(abastecimentos.get(i).fuel);
            lable_fuelTypeFuel.setTextColor(Color.BLACK);
            lable_fuelTypeFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_fuelTypeFuel);

            TextView lable_quantityFuel = new TextView(this);
            lable_quantityFuel.setId(View.generateViewId());
            lable_quantityFuel.setText(abastecimentos.get(i).quantity.toString());
            lable_quantityFuel.setTextColor(Color.BLACK);
            lable_quantityFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_quantityFuel);

            TextView lable_unitPriceFuel = new TextView(this);
            lable_unitPriceFuel.setId(View.generateViewId());
            lable_unitPriceFuel.setText(abastecimentos.get(i).unitPrice.toString());
            lable_unitPriceFuel.setTextColor(Color.BLACK);
            lable_unitPriceFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_unitPriceFuel);

            TextView lable_totalFuel = new TextView(this);
            lable_totalFuel.setId(View.generateViewId());
            lable_totalFuel.setText(abastecimentos.get(i).total.toString());
            lable_totalFuel.setTextColor(Color.BLACK);
            lable_totalFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_totalFuel);

            TextView lable_fuelDepotFuel = new TextView(this);
            lable_fuelDepotFuel.setId(View.generateViewId());
            lable_fuelDepotFuel.setText(abastecimentos.get(i).deposit);
            lable_fuelDepotFuel.setTextColor(Color.BLACK);
            lable_fuelDepotFuel.setPadding(5, 5, 5, 5);
            tr.addView(lable_fuelDepotFuel);

            tl.addView(tr, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT));
        }
    }
}
