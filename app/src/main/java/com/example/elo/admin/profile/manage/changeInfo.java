package com.example.elo.admin.profile.manage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.activity.result.PickVisualMediaRequest;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.elo.R;
import com.example.elo.mentor.profile;
import com.example.elo.worker.workerProfile;

import java.util.Objects;

public class changeInfo extends AppCompatActivity {
    final Context context = this;
    TextView email, password, userName, userLevel, userAccessLevel;
    ImageButton back, resetPassword, triangleAccess, triangleLevel, editEmail, editPassword;
    ImageView avatar;
    Button eloCreated, eloPart, tagsChange, enterProfile;
    String role;
    boolean changes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_info);
        changes = false;

        email = findViewById(R.id.email_address);
        password = findViewById(R.id.password_text);
        userName = findViewById(R.id.user_name);
        userLevel = findViewById(R.id.user_level);
        userAccessLevel = findViewById(R.id.user_access_level);

        back = findViewById(R.id.backButton);
        resetPassword = findViewById(R.id.reset_password);
        triangleAccess = findViewById(R.id.triangle1);
        triangleLevel = findViewById(R.id.triangle2);
        editEmail = findViewById(R.id.edit1);
        editPassword = findViewById(R.id.edit2);

        avatar = findViewById(R.id.avatar);

        eloCreated = findViewById(R.id.elo_created);
        eloPart = findViewById(R.id.elo_part);
        tagsChange = findViewById(R.id.tags_change);
        enterProfile = findViewById(R.id.enter_profile);

        email.setText(getIntent().getStringExtra("userEmail"));
        password.setText(getIntent().getStringExtra("userPassword"));
        userName.setText(getIntent().getStringExtra("userName"));
        userLevel.setText(getIntent().getStringExtra("userLevel"));
        userAccessLevel.setText(getIntent().getStringExtra("userAccessLevel"));

        role = (String) userAccessLevel.getText();

        if (userAccessLevel.getText().toString().equals("сотрудник"))
        {
            eloCreated.setEnabled(false);
        }

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
                                email.setText(emailInput.getText().toString());
                                Log.i("AlertDialog",emailInput.getText().toString());
                                changes = true;
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

        editEmail.setOnClickListener(new View.OnClickListener() {
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
                                email.setText(emailInput.getText().toString());
                                Log.i("AlertDialog",emailInput.getText().toString());
                                changes = true;
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
                                password.setText(passwordInput.getText().toString());
                                Log.i("AlertDialog",passwordInput.getText().toString());
                                changes = true;
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

        editPassword.setOnClickListener(new View.OnClickListener() {
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
                                password.setText(passwordInput.getText().toString());
                                Log.i("AlertDialog",passwordInput.getText().toString());
                                changes = true;
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
                                changes = true;
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

        userAccessLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                showPopupMenuAccessLevel(v);
            }
        });

        triangleAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                showPopupMenuAccessLevel(v);
            }
        });

        userLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                showPopupMenuLevel(v);
            }
        });

        triangleLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                showPopupMenuLevel(v);
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                password.setText("");
                Toast.makeText(context, "Пароль удален", Toast.LENGTH_SHORT).show();
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialView = layoutInflater.inflate(R.layout.change_password, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);
                final EditText passwordInput = dialView.findViewById(R.id.input);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                password.setText(passwordInput.getText().toString());
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changes) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View dialView = layoutInflater.inflate(R.layout.change_level_confirm, null);
                    AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                    dialBuilder.setView(dialView);

                    dialBuilder.setCancelable(false)
                            .setPositiveButton("да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "изменения применены", Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                }
                            })
                            .setNeutralButton("отмена", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setNegativeButton("нет", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    email.setText(getIntent().getStringExtra("userEmail"));
                                    password.setText(getIntent().getStringExtra("userPassword"));
                                    userName.setText(getIntent().getStringExtra("userName"));
                                    userLevel.setText(getIntent().getStringExtra("userLevel"));
                                    userAccessLevel.setText(getIntent().getStringExtra("userAccessLevel"));
                                    onBackPressed();
                                }
                            });
                    dialBuilder.create().show();
                }
                else {
                    onBackPressed();
                }
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
                View dialView = layoutInflater.inflate(R.layout.change_level_confirm, null);
                AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                dialBuilder.setView(dialView);

                dialBuilder.setCancelable(false)
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                avatar.setImageResource(R.drawable.avatar1);
                                Toast.makeText(context, "аватар удален", Toast.LENGTH_SHORT).show();
                                changes = true;
                            }
                        })
                        .setNeutralButton("Изменить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(mediaType).build());
                                avatar.setImageResource(R.drawable.avatar);
                                Toast.makeText(context, "аватар изменен", Toast.LENGTH_SHORT).show();
                                changes = true;
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
                if (changes) {
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View dialView = layoutInflater.inflate(R.layout.change_level_confirm, null);
                    AlertDialog.Builder dialBuilder = new AlertDialog.Builder(context);

                    dialBuilder.setView(dialView);

                    dialBuilder.setCancelable(false)
                            .setPositiveButton("да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(context, "изменения применены", Toast.LENGTH_SHORT).show();

                                    if (Objects.equals(role, "сотрудник")) {
                                        Intent intent = new Intent(context, workerProfile.class);
                                        startActivity(intent);
                                    } else if (Objects.equals(role, "наставник")) {
                                        Intent intent = new Intent(context, profile.class);
                                        startActivity(intent);
                                    }
                                }
                            })
                            .setNeutralButton("отмена", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setNegativeButton("нет", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    email.setText(getIntent().getStringExtra("userEmail"));
                                    password.setText(getIntent().getStringExtra("userPassword"));
                                    userName.setText(getIntent().getStringExtra("userName"));
                                    userLevel.setText(getIntent().getStringExtra("userLevel"));
                                    userAccessLevel.setText(getIntent().getStringExtra("userAccessLevel"));
                                    if (Objects.equals(role, "сотрудник")) {
                                        Intent intent = new Intent(context, workerProfile.class);
                                        startActivity(intent);
                                    } else if (Objects.equals(role, "наставник")) {
                                        Intent intent = new Intent(context, profile.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                    dialBuilder.create().show();
                }
                else {
                    if (Objects.equals(role, "сотрудник")) {
                        Intent intent = new Intent(context, workerProfile.class);
                        startActivity(intent);
                    } else if (Objects.equals(role, "наставник")) {
                        Intent intent = new Intent(context, profile.class);
                        startActivity(intent);
                    }
                }
            }
        });

        eloCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAccessLevel.getText().toString().equals("наставник"))
                {
                    changes = true;
                    Intent intent = new Intent(context, com.example.elo.admin.profile.manage.eloCreated.class);
                    startActivity(intent);
                }
            }
        });

        eloPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                Intent intent = new Intent(context, com.example.elo.admin.profile.manage.eloPart.class);
                intent.putExtra("userName", userName.getText().toString());
                startActivity(intent);
            }
        });

        tagsChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                Intent intent = new Intent(context, changeTags.class);
                startActivity(intent);
            }
        });
    }

    private void showPopupMenuLevel(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu_level);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1)
                {
                    userLevel.setText("junior");
                    return true;
                }
                else if (item.getItemId() == R.id.menu2)
                {
                    userLevel.setText("middle");
                    return true;
                }
                else if (item.getItemId() == R.id.menu3)
                {
                    userLevel.setText("senior");
                    return true;
                }
                else
                    return false;
            }
        });
        popupMenu.show();
    }

    private void showPopupMenuAccessLevel(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popupmenu_access_level);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1)
                {
                    userAccessLevel.setText("сотрудник");
                    role = (String) userAccessLevel.getText();
                    eloCreated.setEnabled(false);
                    return true;
                }
                else if (item.getItemId() == R.id.menu2)
                {
                    userAccessLevel.setText("наставник");
                    role = (String) userAccessLevel.getText();
                    eloCreated.setEnabled(true);
                    return true;
                }
                else
                    return false;
            }
        });
        popupMenu.show();
    }
}
