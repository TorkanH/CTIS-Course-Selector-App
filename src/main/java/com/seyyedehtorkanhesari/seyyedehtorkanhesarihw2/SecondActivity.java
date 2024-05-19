package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    String imgName = "";
    int[] imgIds = {R.drawable.ctis, R.drawable.resel, R.drawable.artel};


    private GestureDetectorCompat gestureDetector;
    private CustomGestureListener customGestureListener;

    RecyclerView recylerMed;
    DatabaseHelper dbHelper;
    EditText etKey;
    Dialog customDialog;
    ImageButton btnImgFind;
    CustomRecyclerViewAdapter adapter;
    Spinner spImage;
    ImageView imageSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        spImage = findViewById(R.id.spImage);
        imageSP = findViewById(R.id.imageSP);
        dbHelper = new DatabaseHelper(this);
        customDialog = new Dialog(this );
        btnImgFind = findViewById(R.id.btnImgFind);
        etKey = findViewById(R.id.etKey);
        Commons.data = (ArrayList<SocialMedia>) MediaTable.getAllMedia(dbHelper);
        adapter = new CustomRecyclerViewAdapter(this, Commons.data);
        recylerMed = findViewById(R.id.recylerMed);
        recylerMed.setAdapter(adapter);
        btnImgFind.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recylerMed.setLayoutManager(layoutManager);


        customGestureListener = new CustomGestureListener();
        gestureDetector = new GestureDetectorCompat(this, customGestureListener);

        spImage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                imageSP.setImageResource(imgIds[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageSP = (ImageView) findViewById(R.id.imageSP);

        imageSP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //return MainActivity.this.mDetector.onTouchEvent(motionEvent);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

    }
    @Override
    public void onClick(View v){
        String key = etKey.getText().toString();
        Commons.data = (ArrayList<SocialMedia>) MediaTable.findMed(dbHelper,key );
        adapter.notifyDataSetChanged();
    }
    public void displayDialog(final String msg){
        final TextView tv;
        Button btnClose;
        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.dialog);
        tv = customDialog.findViewById(R.id.tvDialogName);
        btnClose = customDialog.findViewById(R.id.btnClose);
        tv.setText(msg+"");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }


    class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            Toast.makeText(getBaseContext(), "CTIS course images!",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            Toast.makeText(getBaseContext(), "you can view other categories!", Toast.LENGTH_SHORT).show();
        }
    }
}
