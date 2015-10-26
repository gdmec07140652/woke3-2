package com.example.administrator.woke3_2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.FileNameMap;
import java.nio.Buffer;

public class work3_2 extends Activity implements View.OnClickListener {
    private EditText et1,et2,et3,et4;
    private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work3_2);
        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);
        et3= (EditText) findViewById(R.id.et3);
        et4= (EditText) findViewById(R.id.et4);
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(this);
        SharedPreferences user = getSharedPreferences("user",0);
        et1.setText(user.getString("acc",""));
        et2.setText(user.getString("pass",""));

        et3.setText(readFromRom("aaa.txt"));
    }



    private void saveRomFile(String str,String fileName){
        try {
            FileOutputStream fos = openFileOutput(fileName,0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(str);
            bw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String readFromRom (String fileName){
        StringBuffer sb;
        sb = new StringBuffer();
        String s;
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ((s = br.readLine())!=null){
                sb.append(s);
                et4.setText(sb.toString());
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_work3_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

            SharedPreferences user = getSharedPreferences("user",0);
            SharedPreferences.Editor editor = user.edit();
            editor.putString("acc",et1.getText().toString());
            editor.putString("pass", et2.getText().toString());
            editor.commit();
            saveRomFile(et3.getText().toString(), "aaa.txt");
            readFromRom("aaa.txt");

    }
}
