package com.example.elo;

import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    final Context context = this;
    ImageButton register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register = findViewById(R.id.register);

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
    }
}
