package com.example.elo.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.DatabaseHelper;
import com.example.elo.R;
import com.example.elo.worker.workerMain;

public class register extends AppCompatActivity {
    final Context context = this;
    ImageButton register;
    Button tags, level;
    String user_level;
    EditText name,surname,email,password;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register = findViewById(R.id.register);
        tags = findViewById(R.id.buttonTags);
        level = findViewById(R.id.buttonLevel);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

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
                Cursor cursor = db.query(DatabaseHelper.DB_USERS, null, null, null, null, null, null);
                String name_text = name.getText().toString();
                String surname_text = surname.getText().toString();
                String email_text = email.getText().toString();
                String password_text = password.getText().toString();
                String user_type = "Employee";
                String user_level = "junior";
                String tag1 = "java";
                String tag2 = "c#";
                String tag3 = "sql";
                if (name_text.isEmpty() || surname_text.isEmpty() || email_text.isEmpty() || password_text.isEmpty()) {
                    Toast.makeText(context, "Вы заполнили не все поля", Toast.LENGTH_SHORT).show();
                }
                else {
                    contentValues.put(DatabaseHelper.USER_TYPE, user_type);
                    contentValues.put(DatabaseHelper.USER_NAME, name_text);
                    contentValues.put(DatabaseHelper.USER_SURNAME, surname_text);
                    contentValues.put(DatabaseHelper.USER_EMAIL, email_text);
                    contentValues.put(DatabaseHelper.USER_PASSWORD, password_text);
                    contentValues.put(DatabaseHelper.USER_LEVEL, user_level);
                    contentValues.put(DatabaseHelper.USER_TAG1, tag1);
                    contentValues.put(DatabaseHelper.USER_TAG2, tag2);
                    contentValues.put(DatabaseHelper.USER_TAG3, tag3);
                    db.insert(DatabaseHelper.DB_USERS, null, contentValues);
                    Intent intent = new Intent(context, workerMain.class);
                    startActivity(intent);
                }
                cursor.close();
            }
        });

        tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.elo.mentor.constructorElo.tags.class);
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
