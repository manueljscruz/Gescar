package g3w.gescarcopytest;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Manuel Cruz on 08/09/2017.
 */

public class CarRepair extends AppCompatActivity
{
    TableLayout tl;
    TableRow tr;
    TableRow tr_head;

    ArrayList<Repair> repairs = null;
    ArrayList<CarDataset> carros = null;
    Bundle bdl;
    ImageButton newRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrepair);

        Log.e("teste1", "TESTE DEU");
        carros = getIntent().getParcelableArrayListExtra("cars");
        if(carros == null)
        {
            carros = new ArrayList<>();
        }
        repairs = getIntent().getParcelableArrayListExtra("repairs");
        if(repairs == null)
        {
            repairs = new ArrayList<>();
        }

        repairs.add(new Repair("82-AB-42", "08/03/1993", "Pneus", "Curvão", "1 Pneu", (float) 50.00));

        tl = (TableLayout) findViewById(R.id.tl_carrepair_2ndrow);
        createRepairTable(repairs);

        newRepair = (ImageButton)findViewById(R.id.imgbtn_carrepair_insert);

        newRepair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insertRepair = new Intent();
                insertRepair.putParcelableArrayListExtra("cars", carros);
                insertRepair.putParcelableArrayListExtra("repairs", repairs);
                insertRepair.setClass(CarRepair.this, NewRepair.class);
                startActivity(insertRepair);
            }
        });
    }

    public void createRepairTable(ArrayList<Repair> repairs)
    {
        tr_head = new TableRow(this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setId(View.generateViewId());
        tr_head.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView lable_licensePlate = new TextView(this);
        lable_licensePlate.setId(View.generateViewId());
        lable_licensePlate.setText("Matricula");
        lable_licensePlate.setTextColor(Color.BLACK);
        lable_licensePlate.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_licensePlate);

        TextView lable_date = new TextView(this);
        lable_date.setId(View.generateViewId());
        lable_date.setText("Data");
        lable_date.setTextColor(Color.BLACK);
        lable_date.setPadding(5,5,5,5);
        tr_head.addView(lable_date);

        TextView lable_typeOfCost = new TextView(this);
        lable_typeOfCost.setId(View.generateViewId());
        lable_typeOfCost.setText("Tipo de Custo");
        lable_typeOfCost.setTextColor(Color.BLACK);
        lable_typeOfCost.setPadding(5,5,5,5);
        tr_head.addView(lable_typeOfCost);

        TextView lable_supplier = new TextView(this);
        lable_supplier.setId(View.generateViewId());
        lable_supplier.setText("Fornecedor");
        lable_supplier.setTextColor(Color.BLACK);
        lable_supplier.setPadding(5,5,5,5);
        tr_head.addView(lable_supplier);

        TextView lable_description = new TextView(this);
        lable_description.setId(View.generateViewId());
        lable_description.setText("Descrição");
        lable_description.setTextColor(Color.BLACK);
        lable_description.setPadding(5,5,5,5);
        tr_head.addView(lable_description);

        TextView lable_cost = new TextView(this);
        lable_cost.setId(View.generateViewId());
        lable_cost.setText("Custo");
        lable_cost.setTextColor(Color.BLACK);
        lable_cost.setPadding(5, 5, 5, 5);
        tr_head.addView(lable_cost);

        tl.addView(tr_head, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT));


        for (int i = 0; i < repairs.size(); i++)
        {
            tr = new TableRow(this);
            tr.setId(View.generateViewId());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView lable_licensePlateCar = new TextView(this);
            lable_licensePlateCar.setId(View.generateViewId());
            lable_licensePlateCar.setText(repairs.get(i).licensePlate);
            lable_licensePlateCar.setTextColor(Color.BLACK);
            lable_licensePlateCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_licensePlateCar);

            TextView lable_dateCar = new TextView(this);
            lable_dateCar.setId(View.generateViewId());
            lable_dateCar.setText(repairs.get(i).date);
            lable_dateCar.setTextColor(Color.BLACK);
            lable_dateCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_dateCar);

            TextView lable_typeOfCostCar = new TextView(this);
            lable_typeOfCostCar.setId(View.generateViewId());
            lable_typeOfCostCar.setText(repairs.get(i).typeOfCost);
            lable_typeOfCostCar.setTextColor(Color.BLACK);
            lable_typeOfCostCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_typeOfCostCar);

            TextView lable_supplierCar = new TextView(this);
            lable_supplierCar.setId(View.generateViewId());
            lable_supplierCar.setText(repairs.get(i).supplier);
            lable_supplierCar.setTextColor(Color.BLACK);
            lable_supplierCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_supplierCar);

            TextView lable_descriptionCar = new TextView(this);
            lable_descriptionCar.setId(View.generateViewId());
            lable_descriptionCar.setText(repairs.get(i).description);
            lable_descriptionCar.setTextColor(Color.BLACK);
            lable_descriptionCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_descriptionCar);

            TextView lable_costCar = new TextView(this);
            lable_costCar.setId(View.generateViewId());
            lable_costCar.setText(repairs.get(i).cost.toString());
            lable_costCar.setTextColor(Color.BLACK);
            lable_costCar.setPadding(2, 0, 5, 0);
            tr.addView(lable_costCar);

            tl.addView(tr, new TableLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT));
        }

    }
}

// Quando quiser inserir uma fila nova na tabela
        /*
        tr = new TableRow(this);
        nMatricula = new TextView(this);
        nData = new TextView(this);
        nTipoDespesa = new TextView(this);
        nFornecedor = new TextView(this);
        nDescricao = new TextView(this);
        nCusto = new TextView(this);

        nMatricula.setText(repairs.get(0).licensePlate);
        nMatricula.setTextSize(15);
        nMatricula.setGravity(Gravity.CENTER);

        nData.setText(repairs.get(0).date);
        nData.setTextSize(15);
        nData.setGravity(Gravity.CENTER);

        nTipoDespesa.setText(repairs.get(0).typeOfCost);
        nTipoDespesa.setTextSize(15);
        nTipoDespesa.setGravity(Gravity.CENTER);

        nFornecedor.setText(repairs.get(0).supplier);
        nFornecedor.setTextSize(15);
        nFornecedor.setGravity(Gravity.CENTER);

        nDescricao.setText(repairs.get(0).description);
        nDescricao.setTextSize(15);
        nDescricao.setGravity(Gravity.CENTER);

        nCusto.setText(repairs.get(0).cost.toString());
        nCusto.setTextSize(15);
        nCusto.setGravity(Gravity.CENTER);

        tr.addView(nMatricula);
        tr.addView(nData);
        tr.addView(nTipoDespesa);
        tr.addView(nFornecedor);
        tr.addView(nDescricao);
        tr.addView(nCusto);

        */
        /*
        input1.setText(value);
        input1.setTextSize(15); // por exemplo
        input1.setGravity(Gravity.CENTER);

        tr.addView(inputn); // Fazer isto para cada valor.
         */

