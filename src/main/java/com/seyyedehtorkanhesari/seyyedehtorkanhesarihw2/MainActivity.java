package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper dbHelper;
    EditText etId, etName, etnumOfLike, etnumOfComment;
    TextView tvResult;
    Button btnFind, btnAdd, btnDelete, btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnFind = findViewById(R.id.btnFind);
        dbHelper = new DatabaseHelper(this);
        Log.d("DATABASE", "OK");
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etnumOfLike = findViewById(R.id.etLike);
        etnumOfComment = findViewById(R.id.etComment);
        tvResult = findViewById(R.id.tvResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

       // validateCourseTitle();

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean res=false;
        int id= Integer.parseInt(etId.getText().toString());
        if(view.getId() == R.id.btnAdd){
            validateCourseTitle();
            String name = etName.getText().toString();
            int numOfLike = Integer.parseInt(etnumOfLike.getText().toString());
            int numOfComment = Integer.parseInt(etnumOfComment.getText().toString());
            boolean resinser = MediaTable.insertMed(dbHelper,id, name, numOfLike,numOfComment);
            if(resinser){
                tvResult.setText("Course title "+name+" is added");
            }
        }
        else if(view.getId() == R.id.btnUpdate){
            String name = etName.getText().toString();
            int numOfLike = Integer.parseInt(etnumOfLike.getText().toString());
            int numOfComment = Integer.parseInt(etnumOfComment.getText().toString());
            res = MediaTable.updateMed(dbHelper,id, name, numOfLike,numOfComment);
            if(res)
                tvResult.setText("Update is done");
        }
        else {
            res = MediaTable.deleteMed(dbHelper, id);
            if(res)
                tvResult.setText("Delete is done");
        }
    }

    private boolean validateCourseTitle() {
        String CourseInput=etName.getText().toString().trim();
        if(CourseInput.isEmpty()) {
            etName.setError("Field cant be empty");
            Toast.makeText(this, "Enter a value, can't be Empty!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            etName.setError(null);
            return true;
        }

    }

}
