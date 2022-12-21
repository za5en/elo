package com.example.elo.mentor.eloPage.pass;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import com.example.elo.DatabaseHelper;
import com.example.elo.MakeLinksClickable;
import com.example.elo.R;

public class eloPass extends AppCompatActivity {
    final Context context = this;
    ImageButton back, confirm, link;
    String urlText;
    boolean firstClick;
    int userId, eloId;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String[] columns = {null};
    String selection = null;
    String[] selectionArgs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elo_pass);

        back = findViewById(R.id.backButton);
        confirm = findViewById(R.id.confirm);
        confirm.setEnabled(false);
        link = findViewById(R.id.url);
        firstClick = true;

        userId = getIntent().getIntExtra("userId", 2);
        eloId = getIntent().getIntExtra("eloId", 1);
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String taskId = "SELECT task_id FROM task_progress where user_id = '"+userId+"' and elo_id = '"+eloId+"';";
        Cursor cursor = db.rawQuery(taskId, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                taskId = cursor.getString(0);
            }
            cursor.close();
        }

        TextView taskName = findViewById(R.id.taskName);
        TextView taskDescText = findViewById(R.id.task_desc_text);
        //TextView url = findViewById(R.id.url);

        String tmp = "Задание " + taskId;
        taskName.setText(tmp);
        taskDescText.setText(getIntent().getStringExtra("taskDesc"));
        urlText = getIntent().getStringExtra("url");

//        String textWithLink = String.format("<a href=\"%s\">Ссылка</a>", urlText);
//        link.setText(HtmlCompat.fromHtml(textWithLink, HtmlCompat.FROM_HTML_MODE_LEGACY));
//        link.setLinksClickable(true);
//        link.setMovementMethod(LinkMovementMethod.getInstance());

//        CharSequence text = url.getText();
//        if (text instanceof Spannable)
//        {
//            url.setText(MakeLinksClickable.reformatText(text));
//        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlText);
                Intent WebView = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(WebView);
                if (firstClick) {
                    confirm.setEnabled(true);
                    confirm.setImageResource(R.drawable.task_completed);
                    firstClick = false;
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.setEnabled(false);
                confirm.setImageResource(R.drawable.wait_for_confirm);
                Toast.makeText(context, "Запрос о выполнении задания отправлен наставнику, ожидайте", Toast.LENGTH_SHORT).show();
                contentValues.put(DatabaseHelper.PROGRESS, 1);
                db.update(DatabaseHelper.DB_TASK_PROGRESS, contentValues, "user_id = ? AND elo_id = ?", new String[] {String.valueOf(userId), String.valueOf(eloId)});
            }
        });
    }
}
