package com.example.yander.contactos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.net.Uri;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Button llamadas;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        textView = (TextView) findViewById(R.id.texto);


        button.setOnClickListener(this);
        llamadas.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                ObtenerDatos();


                break;

            default:
                break;
        }
    }




    public void ObtenerDatos(){

        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);

        textView.setText("");


        while(c.moveToNext()){
            textView.append("Identificador: " + c.getString(0) + " Nombre: " + c.getString(1) + " Número: " + c.getString(2)+  " Tipo: " + c.getString(3)+"\n");
        }
        c.close();

        /*contactsCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,   // URI de contenido para los contactos
                projection,                        // Columnas a seleccionar
                selectionClause                    // Condición del WHERE
                selectionArgs,                     // Valores de la condición
                sortOrder);                        // ORDER BY columna [ASC|DESC]*/

    }

}