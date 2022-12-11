package com.example.elo.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.R;
import com.example.elo.admin.adminMain;
import com.example.elo.mentor.MainActivity;
import com.example.elo.worker.workerMain;

import java.util.Objects;

public class auth extends AppCompatActivity {

    final Context context = this;
    ImageButton loginButton, register, resetPassword;
    EditText login, password;

    final String mentor_login = "mentor1@mail.ru";
    final String mentor_password = "mentSj17aks";
    final String worker_login = "employee25@mail.ru";
    final String worker_password = "emplPj225qlzv";
    final String admin_login = "admin777@mail.ru";
    final String admin_password = "777adminmskinno111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        TextView textView = findViewById(R.id.authText);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText("authorization");
        Shader textShader=new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        getColor(R.color.dark),
                        getColor(R.color.middle),
                        getColor(R.color.light),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        loginButton = findViewById(R.id.log_in);
        register = findViewById(R.id.register);
        resetPassword = findViewById(R.id.password_reset);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login_text = login.getText().toString();
                String password_text = password.getText().toString();
                if (Objects.equals(login_text, mentor_login) && Objects.equals(password_text, mentor_password))
                {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
                else if (Objects.equals(login_text, worker_login) && Objects.equals(password_text, worker_password))
                {
                    Intent intent = new Intent(context, workerMain.class);
                    startActivity(intent);
                }
                else if (Objects.equals(login_text, admin_login) && Objects.equals(password_text, admin_password))
                {
                    Intent intent = new Intent(context, adminMain.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    login.getText().clear();
                    password.getText().clear();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.login.register.class);
                startActivity(intent);
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(context, password_reset.class);
                    startActivity(intent);
            }
        });
    }
}
