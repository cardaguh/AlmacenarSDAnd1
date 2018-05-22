package com.jikansoftware.almacenarsd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2;
    private Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperar();
            }
        });
    }

    public void guardar(){
        String nombreArchivo = et1.getText().toString();
        String contenido = et2.getText().toString();
        try{
            File tarjetaSD = Environment.getExternalStorageDirectory();
            File file = new File(tarjetaSD.getAbsolutePath(), nombreArchivo);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
            osw.write(contenido);
            osw.flush();
            osw.close();
            Toast.makeText(this, "Se guardaron los datos exitosamente", Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
        }catch (IOException e){
            Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show();
            Log.e("MYAPP", "exception: " + e.getMessage());
            Log.e("MYAPP", "exception: " + e.toString());

        }
    }

    public void recuperar(){
        String nombreArchivo = et1.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nombreArchivo);
        try{
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null){
                todo = todo + linea +" ";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et2.setText(todo);

        }catch (IOException e){
            Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show();
            Log.e("MYAPP2", "exception: " + e.getMessage());
            Log.e("MYAPP2", "exception: " + e.toString());
        }
    }
}
