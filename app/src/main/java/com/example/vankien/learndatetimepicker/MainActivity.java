package com.example.vankien.learndatetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText txtName,txtDetail;
    TextView txtDate,txtTime;
    Button btnDate,btnTime,btnAdd;

    ListView lvCongViec;
    ArrayList<WorkModel> arrJobs = new ArrayList<>();
    ArrayAdapter<WorkModel> arrayAdapter = null;

    Calendar calendar;
    Date dateFinish,hourFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtName.getText().toString().trim().equals("") && !txtDetail.getText().toString().trim().equals("")){
                    if(dateFinish == null || hourFinish == null){
                        Toast.makeText(MainActivity.this,"Please choose Date and Time",Toast.LENGTH_LONG).show();
                    }else{
                        WorkModel workModel = new WorkModel(txtName.getText().toString(),txtDetail.getText().toString(),dateFinish,hourFinish);
                        txtName.setText("");
                        txtDetail.setText("");
                        arrJobs.add(workModel);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Please fill all data first",Toast.LENGTH_LONG).show();
                }
            }
        });

        lvCongViec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrJobs.remove(position);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"You deleted object at index "+position,Toast.LENGTH_LONG).show();
                return false;
            }
        });
        lvCongViec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WorkModel data = arrJobs.get(position);
                Toast.makeText(MainActivity.this,"Bạn đã chọn : "+data.getDesciption(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        txtName = findViewById(R.id.txtName);
        txtDetail = findViewById(R.id.txtDetail);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);

        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnAdd = findViewById(R.id.btnAdd);

        lvCongViec = findViewById(R.id.lvCongViec);
        arrayAdapter = new ArrayAdapter<WorkModel>(this,android.R.layout.simple_list_item_1,arrJobs);
        lvCongViec.setAdapter(arrayAdapter);
        txtName.requestFocus();
        calendar = Calendar.getInstance();
    }
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFinish = calendar.getTime();
            }
        };
        String s=txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                MainActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Choose Date");
        pic.show();
    }

    private void showTimePicker(){
        final TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtTime.setText(""+hourOfDay+":"+minute);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                hourFinish = calendar.getTime();
            }
        };
        String s=txtTime.getText()+"";
        String strArrtmp[]=s.split(":");
        int hour = Integer.parseInt(strArrtmp[0]);
        int minute = Integer.parseInt(strArrtmp[1]);
        TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,callback,hour,minute,true);
        dialog.setTitle("ChooseTime");
        dialog.show();

    }
}
