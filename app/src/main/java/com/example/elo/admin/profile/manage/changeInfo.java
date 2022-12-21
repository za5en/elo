package com.example.elo.admin.profile.manage;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.elo.DatabaseHelper;
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
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_info);
        changes = false;

        userId = getIntent().getIntExtra("changeInfoId", 2);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

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

        String type = null;
        String name = null;
        String surname = null;
        String userEmail = null;
        String userPassword = null;
        String level = null;
        String tag1 = null;
        String tag2 = null;
        String tag3 = null;
        int k = 0;
        columns = new String[] { "user_type", "user_name", "user_surname", "user_email", "user_password", "user_level", "user_tag1", "user_tag2", "user_tag3" };
        selection = "user_id = ?";
        selectionArgs = new String[] { String.valueOf(userId) };
        Cursor cursor = db.query(DatabaseHelper.DB_USERS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                type = cursor.getString(0);
                name = cursor.getString(1);
                surname = cursor.getString(2);
                userEmail = cursor.getString(3);
                userPassword = cursor.getString(4);
                level = cursor.getString(5);
                tag1 = cursor.getString(6);
                tag2 = cursor.getString(7);
                tag3 = cursor.getString(8);
                name += ' ' + surname;
            }
            cursor.close();
        }

        email.setText(userEmail);
        password.setText(userPassword);
        userName.setText(name);
        userLevel.setText(level);
        userAccessLevel.setText(type);

        role = type;

        if (role.equals("Employee"))
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
                                String new_email = emailInput.getText().toString();
                                email.setText(new_email);
                                Log.i("AlertDialog",new_email);
                                contentValues.put(DatabaseHelper.USER_EMAIL, new_email);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
                                String new_email = emailInput.getText().toString();
                                email.setText(new_email);
                                Log.i("AlertDialog",new_email);
                                contentValues.put(DatabaseHelper.USER_EMAIL, new_email);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
                                String new_pass = passwordInput.getText().toString();
                                password.setText(new_pass);
                                Log.i("AlertDialog",new_pass);
                                contentValues.put(DatabaseHelper.USER_PASSWORD, new_pass);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
                                String new_pass = passwordInput.getText().toString();
                                password.setText(new_pass);
                                Log.i("AlertDialog",new_pass);
                                contentValues.put(DatabaseHelper.USER_PASSWORD, new_pass);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
                                String new_name = nameInput.getText().toString();
                                password.setText(new_name);
                                Log.i("AlertDialog",new_name);
                                contentValues.put(DatabaseHelper.USER_NAME, new_name);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
                                String new_pass = passwordInput.getText().toString();
                                password.setText(new_pass);
                                Log.i("AlertDialog",new_pass);
                                contentValues.put(DatabaseHelper.USER_PASSWORD, new_pass);
                                db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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

                                    if (Objects.equals(role, "Employee")) {
                                        Intent intent = new Intent(context, workerProfile.class);
                                        intent.putExtra("userId", userId);
                                        startActivity(intent);
                                    } else if (Objects.equals(role, "Mentor")) {
                                        Intent intent = new Intent(context, profile.class);
                                        intent.putExtra("userId", userId);
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
                                    contentValues.put(DatabaseHelper.USER_EMAIL, getIntent().getStringExtra("userEmail"));
                                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });

                                    password.setText(getIntent().getStringExtra("userPassword"));
                                    contentValues.put(DatabaseHelper.USER_PASSWORD, getIntent().getStringExtra("userPassword"));
                                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });

                                    userName.setText(getIntent().getStringExtra("userName"));
                                    contentValues.put(DatabaseHelper.USER_NAME, getIntent().getStringExtra("userName"));
                                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });

                                    userLevel.setText(getIntent().getStringExtra("userLevel"));
                                    contentValues.put(DatabaseHelper.USER_LEVEL, getIntent().getStringExtra("userLevel"));
                                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });

                                    userAccessLevel.setText(getIntent().getStringExtra("userAccessLevel"));
                                    contentValues.put(DatabaseHelper.USER_TYPE, getIntent().getStringExtra("userAccessLevel"));
                                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });

                                    if (Objects.equals(role, "Employee")) {
                                        Intent intent = new Intent(context, workerProfile.class);
                                        intent.putExtra("userId", userId);
                                        startActivity(intent);
                                    } else if (Objects.equals(role, "Mentor")) {
                                        Intent intent = new Intent(context, profile.class);
                                        intent.putExtra("userId", userId);
                                        startActivity(intent);
                                    }
                                }
                            });
                    dialBuilder.create().show();
                }
                else {
                    if (Objects.equals(role, "Employee")) {
                        Intent intent = new Intent(context, workerProfile.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    } else if (Objects.equals(role, "Mentor")) {
                        Intent intent = new Intent(context, profile.class);
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                    }
                }
            }
        });

        eloCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userAccessLevel.getText().toString().equals("Mentor"))
                {
                    changes = true;
                    Intent intent = new Intent(context, com.example.elo.admin.profile.manage.eloCreated.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
            }
        });

        eloPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changes = true;
                Intent intent = new Intent(context, com.example.elo.admin.profile.manage.eloPart.class);
                intent.putExtra("userId", userId);
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
        ContentValues contentValues = new ContentValues();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1)
                {
                    userLevel.setText("junior");
                    contentValues.put(DatabaseHelper.USER_LEVEL, "junior");
                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
                    return true;
                }
                else if (item.getItemId() == R.id.menu2)
                {
                    userLevel.setText("middle");
                    contentValues.put(DatabaseHelper.USER_LEVEL, "middle");
                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
                    return true;
                }
                else if (item.getItemId() == R.id.menu3)
                {
                    userLevel.setText("senior");
                    contentValues.put(DatabaseHelper.USER_LEVEL, "senior");
                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
        ContentValues contentValues = new ContentValues();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu1)
                {
                    userAccessLevel.setText("Employee");
                    role = (String) userAccessLevel.getText();
                    contentValues.put(DatabaseHelper.USER_TYPE, "Employee");
                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
                    eloCreated.setEnabled(false);
                    return true;
                }
                else if (item.getItemId() == R.id.menu2)
                {
                    userAccessLevel.setText("Mentor");
                    role = (String) userAccessLevel.getText();
                    contentValues.put(DatabaseHelper.USER_TYPE, "Mentor");
                    db.update(DatabaseHelper.DB_USERS, contentValues, "user_id = ?", new String[] { String.valueOf(userId) });
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
