package com.example.elo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.model.Tasks_elo;

public class register extends AppCompatActivity {
    final Context context = this;
    ImageButton register;
    Button tags, level;
    String user_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register = findViewById(R.id.register);
        tags = findViewById(R.id.buttonTags);
        level = findViewById(R.id.buttonLevel);

        TextView textView = findViewById(R.id.regText);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("registration");
        Shader textShader=new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, workerMain.class);
                startActivity(intent);
            }
        });

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, tags.class);
                startActivity(intent);
            }
        });

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.level_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                dialBuilder.create().show();
            }
        });
    }
}
