package sg.edu.rp.c346.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnWrite, btnRead;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI handlers to be defined
        btnWrite = findViewById(R.id.buttonWrite);
        btnRead = findViewById(R.id.buttonRead);



        File folder = new File(folderLocation);
        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File targetFile = new File(folderLocation, "data.txt");

                try{
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write("Hello world + \n");
                    writer.flush();
                    writer.close();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Failed to write!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File targetFile = new File(folderLocation, "data.txt");
                String data ="";
                try{
                    FileReader reader = new FileReader(targetFile);
                    BufferedReader br = new BufferedReader(reader);
                    String line = br.readLine();
                    while(line != null){
                        data += line + "\n";
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tvContent.setText(data);
            }
        });
    }
}

