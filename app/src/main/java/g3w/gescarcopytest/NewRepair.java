package g3w.gescarcopytest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class NewRepair extends AppCompatActivity {

    ArrayList<CarDataset> carros = null;
    ArrayList<String> carPlates = null;
    ArrayList<Repair> repairs = null;
    Bundle bdl;

    private Spinner spViaturas;
    private Spinner spTipoDespesa;
    private Spinner spFonecedor;
    private String date;
    private String description;
    private String licensePlate;
    private String typeOfCost;
    private String supplier;
    private String tempCost;
    private Float cost;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText etDescription;
    private EditText etCost;
    private Button insertNewRepair;
    private Button cancelNewRepair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_repair);

        bdl = getIntent().getExtras();

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

        cost = (float) 0.00;

        carPlates = new ArrayList<>();
        getCarsPlates(carros);

        insertNewRepair = (Button) findViewById(R.id.btn_newrepair_insert);
        cancelNewRepair = (Button) findViewById(R.id.btn_newrepair_cancel);
        mDisplayDate = (TextView) findViewById(R.id.tv_newrepair_data);
        etDescription = (EditText) findViewById(R.id.et_newrepair_descricao);
        etCost = (EditText) findViewById(R.id.et_newrepair_custo);


        // Adaptador para o Spinner de carros
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carPlates);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de Tipo de Despesa
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.tipoDespesa, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de Fornecedor
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.fornecedor, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Definir localização dos spinners
        spViaturas = (Spinner) findViewById(R.id.sp_newrepair_viaturas);
        spTipoDespesa = (Spinner) findViewById(R.id.sp_newrepair_tipoDespesa);
        spFonecedor = (Spinner) findViewById(R.id.sp_newrepair_fornecedor);

        // Definir os adaptadores respetivos de cada spinner
        spViaturas.setAdapter(adapter1);
        spTipoDespesa.setAdapter(adapter2);
        spFonecedor.setAdapter(adapter3);


        // O que fazer quando o item for selecionado ou não
        spViaturas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Quando é clicado para inserir uma data
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewRepair.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        // Quando é clicado no botão para inserir uma reparaçao nova
        insertNewRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cost = Float.parseFloat(etCost.getText().toString());
                description = etDescription.getText().toString();
                licensePlate = spViaturas.getSelectedItem().toString();
                typeOfCost = spTipoDespesa.getSelectedItem().toString();
                supplier = spFonecedor.getSelectedItem().toString();

                repairs.add(new Repair(licensePlate, date, typeOfCost, supplier, description, cost));
                Intent insertNewRepair = new Intent();
                insertNewRepair.putParcelableArrayListExtra("cars", carros);
                insertNewRepair.putParcelableArrayListExtra("repairs", repairs);
                insertNewRepair.setClass(NewRepair.this, CarRepair.class);
                startActivity(insertNewRepair);
                NewRepair.this.finish();

            }
        });
        // Quando é clicado no botao para cancelar uma reparacao nova
        cancelNewRepair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent insertNewRepair = new Intent();
                insertNewRepair.putParcelableArrayListExtra("cars", carros);
                insertNewRepair.putParcelableArrayListExtra("repairs", repairs);
                insertNewRepair.setClass(NewRepair.this, CarRepair.class);
                startActivity(insertNewRepair);
                NewRepair.this.finish();
            }
        });

    }

    public void getCarsPlates ( ArrayList<CarDataset> cars)
    {
        for(int i = 0; i < cars.size(); i++)
        {
            carPlates.add(carros.get(i).licencePlate);
        }
    }
}
