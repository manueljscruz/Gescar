package g3w.gescarcopytest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    Button logindaapp; // Botão de Login
    EditText username; // Secção username
    EditText password; // Secção password

    ArrayList<CarDataset> carsDataset = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Botão Login
        logindaapp = (Button) findViewById(R.id.btn_login); // Associa o id do botão

        logindaapp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { // Quando é clicado o botão
                disableButton(v);
                String funcao = "wslogin";
                username = (EditText)findViewById(R.id.et_username);
                String user = username.getText().toString();
                password = (EditText)findViewById(R.id.et_password);
                String pass = password.getText().toString();
                getData(funcao, user, pass);

            }
        });
    }
    String mLogTag;
    // "wslogin"
     //"mario.joao@curvao.com" +
    //        "123456
    public void getData(String funcao, String username, String password)
    {
        // funcao = "wsfunctions";
        //Cameras Data
        // String camUrl = "http://www.gescar3w.com/index.php/site/wslogin?u=mario.joao@curvao.com&p=123456";
        String camUrl = "http://www.gescar3w.com/index.php/site/" + funcao + "?u="+username+"&p="+ password;
        Log.e(mLogTag, "getting car data\n" + camUrl);
        DownloadJsonFile downloadCameraData = new DownloadJsonFile();
        downloadCameraData.execute(camUrl);
    }

    // Método para prevenir Duplo click
    public static void disableButton(final View v)
    {
        try
        {
            v.setEnabled(false);
            v.setAlpha((float) 0.5);
            v.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        v.setEnabled(true);
                        v.setAlpha((float) 1.0);
                    } catch (Exception e)
                    {
                        Log.d("disableButton","Exception while un hiding the view: " + e.getMessage());
                    }
                }
            }, 1000);
        } catch (Exception e)
        {
            Log.d("disableButton","Exception while hiding the view : " + e.getMessage());
        }
    }

    private void updateCarDataset(String json) throws JSONException
    {
        JSONArray array = new JSONArray(json);

        for (int i = 0; i < array.length(); i++)
        {
            JSONObject object = array.getJSONObject(i);
            Integer carID = object.getInt("viaturaID");
            String lPlate = object.getString("matricula");
            String model = object.getString("modelo");
            String brand = object.getString("marca");
            Double latit = object.getDouble("lat");
            Double longt = object.getDouble("lon");

            carsDataset.add(new CarDataset(carID, model, lPlate, brand, latit, longt));
        }

        Intent login = new Intent();
        login.putParcelableArrayListExtra("cars", carsDataset);
        login.setClass(MainActivity.this, Menu.class);
        startActivity(login); // Começa a nova Actividade
        MainActivity.this.finish(); // Termina esta atividade
    }

    public class DownloadJsonFile extends AsyncTask<String, Void, String> {
        @Override

        protected String doInBackground(String... params)
        {
            try
            {
                URL url = new URL(params[0]);

                // Open a stream from the URL
                InputStream stream = new URL(params[0]).openStream();

                String line;
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                    Log.e(mLogTag, "reading ...");
                }

                // Close the stream
                reader.close();
                stream.close();

                return result.toString();// + params[1];
            }
            catch (IOException e)
            {
                Log.e(mLogTag, "download data could not be read");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jsonObject)
        {
            if (jsonObject != null)
            {
                try
                {
                    Log.e(mLogTag, "data received ->" + jsonObject);

                    if (!jsonObject.contains("noDataAvailable"))
                    {
                        try
                        {
                            updateCarDataset(jsonObject);
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Log.e(mLogTag, "heello");
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

}

