package com.example.salman.friendsinfo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtId;
    Button btnAdd, btnUpdate, btnView, btnDelete;
    TextView textView;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        edtName = (EditText) findViewById(R.id.edtName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtId = (EditText) findViewById(R.id.edtID);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnView = (Button) findViewById(R.id.btnView);

        textView = (TextView) findViewById(R.id.textView);

        addData();
        veiwAll();
        updtData();
        delData();
    }


    public void addData() {
       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               boolean res = mydb.insertData(edtName.getText().toString(), edtEmail.getText().toString());
               if(res==true)
                   Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(getApplicationContext(), "Failed to Insert", Toast.LENGTH_SHORT).show();

           }
       });
    }

    public void veiwAll() {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.getAllData();
                if(res.getCount()==0)
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                else
                {

                    StringBuffer buffer = new StringBuffer();

                    while(res.moveToNext())
                    {
                        buffer.append("Id: "+res.getString(0)+"\n");
                        buffer.append("Name: "+res.getString(1)+"\n");
                        buffer.append("Email: "+res.getString(2)+"\n");
                    }
                    textView.setText(buffer.toString());
                }

            }
        });
    }

    public void updtData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.updateData(edtId.getText().toString(), edtName.getText().toString(), edtEmail.getText().toString());
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void delData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer res = mydb.deleteData(edtId.getText().toString());
                if(res>0)
                    Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Failed to delete", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
