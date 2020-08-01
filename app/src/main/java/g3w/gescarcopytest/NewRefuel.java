package g3w.gescarcopytest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class NewRefuel extends AppCompatActivity {

    Bundle bdl;
    ArrayList<CarDataset> carros = null;
    ArrayList<String> carPlates = null;
    ArrayList<Refuel> abastecimentos = null;

    private String nRefuelDate;
    private String nLicensePlate;
    private String nModel;
    private String nFuel;
    private Float nQuantity;
    private Float nUnitPrice;
    private Float nTotal;
    private String nDeposit;
    private String nDriver;
    private String nDepartment;
    private int nLastKms;
    private int nKms;
    private int nKmsMade;
    private double nUnitCost;
    private double nAvg;

    private String tempLastKms;
    private String tempNKms;
    private String tempNKmsMade;
    private String tempNUnitCost;
    private String tempQtd;
    private String tempAvg;

    private Spinner spViaturas;
    private Spinner spCondutor;
    private Spinner spDepartamento;
    private Spinner spCombustiveis;
    private Spinner spDeposito;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText etLastKms;
    private EditText etKms;
    private EditText etKmsMade;
    private EditText etUnitCost;
    private EditText etQtd;
    private EditText etAvg;

    private Button insertNewRefuel;
    private Button cancelNewRefuel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_refuel);



        // O QUE FALTA FAZER AQUI:
        //
        // Receber os arraylists da outra atividade
        // Fazer com que o AVG invés de tomar valor de um edittext, seja calculado através dos kms feitos e pelo combustivel
        // Considerar associar um preço unitário para cada tipo de combustivel através de um XML

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

        carPlates = new ArrayList<>();
        getCarsPlates(carros);

        insertNewRefuel = (Button) findViewById(R.id.btn_newrefuel_insert);
        cancelNewRefuel = (Button) findViewById(R.id.btn_newrefuel_cancel);
        mDisplayDate = (TextView) findViewById(R.id.tv_newrefuel_data);
        etLastKms = (EditText) findViewById(R.id.et_newrefuel_lastKms);
        etKms = (EditText) findViewById(R.id.et_newrefuel_Kms);
        etKmsMade = (EditText) findViewById(R.id.et_newrefuel_KmsMade);
        etUnitCost = (EditText) findViewById(R.id.et_newrefuel_unitCost);
        etQtd = (EditText) findViewById(R.id.et_newrefuel_Qtd);
        etAvg = (EditText) findViewById(R.id.et_newrefuel_avg);
        spViaturas = (Spinner) findViewById(R.id.sp_newrefuel_viaturas);
        spCombustiveis = (Spinner) findViewById(R.id.sp_newrefuel_typeOfFuel);
        spCondutor = (Spinner) findViewById(R.id.sp_newrefuel_drivers);
        spDepartamento = (Spinner) findViewById(R.id.sp_newrefuel_department);
        spDeposito = (Spinner) findViewById(R.id.sp_newrefuel_Depot);

        // Adaptador para o Spinner de carros
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carPlates);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de combustiveis
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.combustivel, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de condutores
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.condutor, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de departamentos
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.departamento, android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adaptador para o Spinner de deposito
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.deposito, android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Definir os adaptadores de cada spinner
        spViaturas.setAdapter(adapter1);
        spCombustiveis.setAdapter(adapter2);
        spCondutor.setAdapter(adapter3);
        spDepartamento.setAdapter(adapter4);
        spDeposito.setAdapter(adapter5);

        // Quando é clicado para inserir uma data
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewRefuel.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                nRefuelDate = day + "/" + month + "/" + year;
                mDisplayDate.setText(nRefuelDate);
            }
        };


        // Quando é clicado no botão para inserir um abastecimento novo
        insertNewRefuel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nLastKms = Integer.parseInt(etLastKms.getText().toString());
                nKms = Integer.parseInt(etLastKms.getText().toString());
                nKmsMade = Integer.parseInt(etLastKms.getText().toString());
                nUnitPrice = Float.parseFloat(etUnitCost.getText().toString());
                nQuantity = Float.parseFloat(etLastKms.getText().toString());
                nTotal = nUnitPrice * nQuantity;
                nAvg = Double.parseDouble(etLastKms.getText().toString());

                nLicensePlate = spViaturas.getSelectedItem().toString();
                nFuel = spCombustiveis.getSelectedItem().toString();
                nDriver = spCondutor.getSelectedItem().toString();
                nDepartment = spDepartamento.getSelectedItem().toString();
                nDeposit = spDeposito.getSelectedItem().toString();

                getCarModel(carros, nLicensePlate);

                abastecimentos.add(new Refuel(nRefuelDate, nLicensePlate, nModel, nFuel, nQuantity, nUnitPrice, nTotal, nDeposit));
                Intent insertNewRefuel = new Intent();
                insertNewRefuel.putParcelableArrayListExtra("cars", carros);
                insertNewRefuel.putParcelableArrayListExtra("refuelSupply", abastecimentos);
                insertNewRefuel.setClass(NewRefuel.this, FuelSupplys.class);
                startActivity(insertNewRefuel);
                NewRefuel.this.finish();

            }
        });
        // Quando é clicado no botao para cancelar uma reparacao nova
        cancelNewRefuel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent cancelNewRefuel = new Intent();
                cancelNewRefuel.putParcelableArrayListExtra("cars", carros);
                cancelNewRefuel.putParcelableArrayListExtra("refuelSupply", abastecimentos);
                cancelNewRefuel.setClass(NewRefuel.this, CarRepair.class);
                startActivity(cancelNewRefuel);
                NewRefuel.this.finish();
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

    public void getCarModel ( ArrayList<CarDataset> cars, String nLicensePlate)
    {
        for (int i = 0; i < cars.size(); i++)
        {
            if (cars.get(i).licencePlate == nLicensePlate)
            {
                nModel = cars.get(i).brand;
            }
        }
    }
}
