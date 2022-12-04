package com.example.elo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elo.model.Tasks_elo;

public class changeInfo extends AppCompatActivity {
    final Context context = this;
    TextView email, password, userName, userLevel;
    ImageButton back;
    ImageView avatar;
    Button eloChange, tagsChange, enterProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_info);

        email = findViewById(R.id.email_address);
        password = findViewById(R.id.password_text);
        userName = findViewById(R.id.user_name);
        userLevel = findViewById(R.id.user_level);

        back = findViewById(R.id.backButton);

        avatar = findViewById(R.id.avatar);

        eloChange = findViewById(R.id.elo_change);
        tagsChange = findViewById(R.id.tags_change);
        enterProfile = findViewById(R.id.enter_profile);

        email.setText(getIntent().getStringExtra("userEmail"));
        password.setText(getIntent().getStringExtra("userPassword"));
        userName.setText(getIntent().getStringExtra("userName"));
        userLevel.setText(getIntent().getStringExtra("userLevel"));

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.change_email, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText emailInput = dialView.findViewById(R.id.input);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                userName.setText(emailInput.getText().toString());
                                Log.i("AlertDialog",emailInput.getText().toString());
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

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.change_password, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText passwordInput = dialView.findViewById(R.id.input);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                userName.setText(passwordInput.getText().toString());
                                Log.i("AlertDialog",passwordInput.getText().toString());
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

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.change_name, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText nameInput = dialView.findViewById(R.id.input);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                userName.setText(nameInput.getText().toString());
                                Log.i("AlertDialog",nameInput.getText().toString());
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

        userLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.access_level_dial, null);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        ActivityResultContracts.PickVisualMedia.VisualMediaType mediaType = (ActivityResultContracts.PickVisualMedia.VisualMediaType) ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE;
//
//        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
//                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
//                    // Callback is invoked after the user selects a media item or closes the
//                    // photo picker.
//                    if (uri != null) {
//                        Log.d("PhotoPicker", "Selected URI: " + uri);
//                    } else {
//                        Log.d("PhotoPicker", "No media selected");
//                    }
//                });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.accept_task_dial, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                avatar.setImageResource(R.drawable.avatar1);
                                Toast.makeText(context, "аватар удален", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("Изменить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(mediaType).build());
                                avatar.setImageResource(R.drawable.avatar);
                                Toast.makeText(context, "аватар изменен", Toast.LENGTH_SHORT).show();
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

        enterProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, profile.class); //depends on user
                startActivity(intent);
            }
        });

        eloChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, manageElo.class); //list of elo + adapter + page from backup
                startActivity(intent);
            }
        });

        tagsChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, tags.class); //change tags page
                startActivity(intent);
            }
        });
    }
}
