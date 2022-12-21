package com.example.elo.login;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.admin.adminMain;
import com.example.elo.mentor.MainActivity;
import com.example.elo.worker.workerMain;

import java.util.Objects;

public class auth extends AppCompatActivity {

    final Context context = this;
    ImageButton loginButton, register;//resetPassword;
    EditText login, password;

    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    final String mentor_login = "mail1@gmail.ru";
    final String mentor_password = "mentSj17aks";
    final String worker_login = "empl25@mail.ru";
    final String worker_password = "emplPj225qlzv";
    final String admin_login = "admin@gmail.ru";
    final String admin_password = "123admin321";

    final String worker = "Employee";
    final String mentor = "Mentor";
    final String admin = "Admin";

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
        //resetPassword = findViewById(R.id.password_reset);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String[] columns = {null};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String[] columns = {null};
                String selection = null;
                String[] selectionArgs = null;
                String groupBy = null;
                String having = null;
                String orderBy = null;

                int userId = 0;
                String userName = null;
                String userSurname = null;
                String userPassword = null;
                String userType = null;
                String userLevel = null;
                String userTag1 = null;
                String userTag2 = null;
                String userTag3 = null;

                String login_text = login.getText().toString();
                String password_text = password.getText().toString();
                columns = new String[] { "user_id", "user_name", "user_surname", "user_password", "user_type",
                        "user_level", "user_tag1", "user_tag2", "user_tag3"};
                selection = "user_email = ?";
                selectionArgs = new String[] { login_text };

                Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        int idColIndex = cursor.getColumnIndex("user_id");
                        int nameColIndex = cursor.getColumnIndex("user_name");
                        int surnameColIndex = cursor.getColumnIndex("user_surname");
                        int passwordColIndex = cursor.getColumnIndex("user_password");
                        int typeColIndex = cursor.getColumnIndex("user_type");
                        int levelColIndex = cursor.getColumnIndex("user_level");
                        int tag1ColIndex = cursor.getColumnIndex("user_tag1");
                        int tag2ColIndex = cursor.getColumnIndex("user_tag2");
                        int tag3ColIndex = cursor.getColumnIndex("user_tag3");

                        userId = cursor.getInt(idColIndex);
                        userName = cursor.getString(nameColIndex);
                        userSurname = cursor.getString(surnameColIndex);
                        userPassword = cursor.getString(passwordColIndex);
                        userType = cursor.getString(typeColIndex);
                        userLevel = cursor.getString(levelColIndex);
                        userTag1 = cursor.getString(tag1ColIndex);
                        userTag2 = cursor.getString(tag2ColIndex);
                        userTag3 = cursor.getString(tag3ColIndex);
                    }
                }
                cursor.close();
                if (Objects.equals(password_text, userPassword))
                {
                    if (Objects.equals(userType, worker)) {
                        Intent intent = new Intent(context, workerMain.class);
                        Toast.makeText(context, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        intent.putExtra("userId", userId);

                        startActivity(intent);
                    }
                    else if (Objects.equals(userType, mentor)) {
                        Intent intent = new Intent(context, MainActivity.class); //прописать интенты на все строки из таблы
                        Toast.makeText(context, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        intent.putExtra("userId", userId);

                        startActivity(intent);                                   //либо только на ид
                    }
                    else if (Objects.equals(userType, admin)) {
                        Intent intent = new Intent(context, adminMain.class);
                        Toast.makeText(context, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show();
                        intent.putExtra("userId", userId);

                        startActivity(intent);
                    }
                }
//                if (Objects.equals(login_text, mentor_login) && Objects.equals(password_text, mentor_password))
//                {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    startActivity(intent);
//                }
//                else if (Objects.equals(login_text, worker_login) && Objects.equals(password_text, worker_password))
//                {
//                    Intent intent = new Intent(context, workerMain.class);
//                    startActivity(intent);
//                }
//                else if (Objects.equals(login_text, admin_login) && Objects.equals(password_text, admin_password))
//                {
//                    Intent intent = new Intent(context, adminMain.class);
//                    startActivity(intent);
//                }
                else
                {
                    Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
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

//        resetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    Intent intent = new Intent(context, password_reset.class);
//                    startActivity(intent);
//            }
//        });
    }
}
