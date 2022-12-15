package com.example.elo;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static final String DB_NAME = "eloDB.db";
    private SQLiteDatabase eloDB;
    private final Context context;

    public static final int DB_VERSION = 10;

    public static final String DB_USERS = "users";
    public static final String DB_ELO = "elo";
    public static final String DB_REL_LIST = "relation_list";
    public static final String DB_ELO_TASKS = "elo_tasks";
    public static final String DB_TASK_PROGRESS = "task_progress";

    public static final String USER_ID = "user_id";
    public static final String USER_TYPE = "user_type";
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_LEVEL = "user_level";
    public static final String USER_TAG1 = "user_tag1";
    public static final String USER_TAG2 = "user_tag2";
    public static final String USER_TAG3 = "user_tag3";
    public static final String ELO_ID = "elo_id";
    public static final String ELO_NAME = "elo_name";
    public static final String ELO_INFO = "elo_info";
    public static final String ELO_AVAILABILITY = "elo_availability";
    public static final String ELO_OWNER_ID = "elo_owner_id";
    public static final String ELO_TAG1 = "elo_tag1";
    public static final String ELO_TAG2 = "elo_tag2";
    public static final String ELO_TAG3 = "elo_tag3";
    public static final String TASK_ID = "task_id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_INFO = "task_info";
    public static final String TASK_URL = "task_url";
    public static final String PROGRESS = "progress";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        Log.e("Path", DB_PATH);
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (!dbExist) {
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException ignored) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDatabase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        String outName = DB_PATH + DB_NAME;
        OutputStream output = new FileOutputStream(outName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    public void openDatabase() throws SQLException {
        String path = DB_PATH + DB_NAME;
        eloDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if (eloDB != null)
            eloDB.close();
        super.close();
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context context1) {
        super(context, name, factory, version);
        this.context = context1;
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler, Context context1) {
        super(context, name, factory, version, errorHandler);
        this.context = context1;
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams, Context context1) {
        super(context, name, version, openParams);
        this.context = context1;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + DB_USERS + " (" +
                        USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                USER_TYPE + " varchar(15) DEFAULT 'Employee' NOT NULL, " +
                USER_NAME + " text NOT NULL, " +
                USER_SURNAME + " text NOT NULL, " +
                USER_EMAIL + " text UNIQUE NOT NULL, " +
                USER_PASSWORD + " text NOT NULL, " +
                USER_LEVEL + " text DEFAULT 'junior', " +
                USER_TAG1 + " text, " +
                USER_TAG2 + " text, " +
                USER_TAG3 + " text, " +
                "CHECK ( "+USER_TYPE+" IN ( 'Admin', 'Mentor', 'Employee' )), " +
                "CHECK ( "+USER_LEVEL+" IN ( 'middle', 'junior', 'senior' )), " +
                "CHECK ( "+USER_TAG1+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), " +
                "CHECK ( "+USER_TAG2+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), " +
                "CHECK ( "+USER_TAG3+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), " +
                "CHECK (length("+USER_NAME+") < 25), " +
                "CHECK (length("+USER_SURNAME+") < 25), " +
                "CHECK (length("+USER_PASSWORD+") > 6));"
        );
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_ELO+" ("+
                ELO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                ELO_NAME+" text UNIQUE NOT NULL, "+
                ELO_INFO+" text, "+
                ELO_AVAILABILITY+" INTEGER NOT NULL DEFAULT 1, "+
                ELO_OWNER_ID+" int NOT NULL, "+
                ELO_TAG1+" text, "+
                ELO_TAG2+" text, "+
                ELO_TAG3+" text, "+
                "CHECK ( "+ELO_AVAILABILITY+" IN ( 0, 1 )), "+
                "CHECK ( "+ELO_TAG1+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), "+
                "CHECK ( "+ELO_TAG2+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), "+
                "CHECK ( "+ELO_TAG3+" IN ( 'java', 'sql', 'c#', 'front', 'back', 'qa', 'python', 'react', 'другое' )), "+
                "FOREIGN KEY ("+ELO_OWNER_ID+") REFERENCES "+DB_USERS+"("+USER_ID+"));"
                );
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_REL_LIST+" (" +
                USER_ID+" int NOT NULL, "+
                ELO_ID+" int NOT NULL, "+
                "PRIMARY KEY ("+USER_ID+", "+ELO_ID+"), "+
                "FOREIGN KEY ("+USER_ID+") REFERENCES "+DB_USERS+"("+USER_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE, " +
                        "FOREIGN KEY ("+ELO_ID+") REFERENCES "+DB_ELO+"("+ELO_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE);"
        );
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_ELO_TASKS+" ("+
                TASK_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                ELO_ID+" INTEGER NOT NULL, "+
                TASK_NAME+" text, "+
                TASK_INFO+" text, "+
                TASK_URL+" text, "+
                "CHECK (length("+TASK_NAME+") < 25)," +
                        " FOREIGN KEY ("+ELO_ID+") REFERENCES "+DB_ELO+"("+ELO_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE);"
                );
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TASK_PROGRESS+" ("+
                USER_ID+" int NOT NULL, "+
                ELO_ID+" int NOT NULL, "+
                TASK_ID+" int NOT NULL, "+
                PROGRESS+" INTEGER NOT NULL DEFAULT 0, "+
                "CHECK ("+PROGRESS+" IN ( 0,1 ))," +
                        "FOREIGN KEY ("+ELO_ID+") REFERENCES "+DB_ELO+"("+ELO_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE, " +
                        "FOREIGN KEY ("+USER_ID+") REFERENCES "+DB_USERS+"("+USER_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE, " +
                        "FOREIGN KEY ("+TASK_ID+") REFERENCES "+DB_ELO_TASKS+"("+TASK_ID+") " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE CASCADE);"
        );
        sqLiteDatabase.execSQL(
        "INSERT INTO "+DB_USERS+" ("+USER_TYPE+", "+USER_NAME+", "+USER_SURNAME+", "+USER_EMAIL+", "+USER_PASSWORD+", "+USER_LEVEL+", "+USER_TAG1+", "+USER_TAG2+", "+USER_TAG3+") "+
        "VALUES ('Admin','Admin','Name','admin@gmail.ru','123admin321', 'senior', 'другое'), " +
                        "('Mentor','Mentor','Name','mail1@gmail.ru','mentSj17aks','senior', 'c#', 'java', 'sql'), " +
                        "('Mentor','Рустам','Авангард','mail3@gmail.ru','111111111','senior', 'sql', 'c#', 'другое'), " +
                        "('Mentor','Фёдор','Власов','mail4@gmail.ru','qska1jmsandc_','senior', 'java', 'front', 'c#'), " +
                        "('Mentor','Михаил','Михайлович','mail666@gmail.ru','sadaksnjx1','senior', 'back', 'sql', 'c#'), " +
                        "('Mentor','Виталий','Новый','mail777@gmail.ru','asdasq123rfdc','senior', 'python', 'c#', 'front'), " +
                        "('Mentor','Артём','Коновалов','mail123@mail.ru','gfbruch14b67','middle', 'java', 'python', 'front'), " +
                        "('Employee','Дима','Перевозчиков','asllssll1@mail.ru','s1bmujnbnb','middle', 'python', 'front', 'react'), " +
                        "('Employee','Самвел','Семенов','sumwellandr@mail.ru','!user2112','junior', 'front', 'c#', 'java'), " +
                        "('Employee','Максим','Максим','maxsquare@mail.ru','!user212312sad','junior', 'back', 'java', 'python'), " +
                        "('Employee','Кирилл','Широбоков','shiro@mail.ru','shirobest','junior', 'back', 'java', 'c#'), " +
                        "('Employee','Alex','Kiselev','kiselex@mail.ru','12osoiisnczxc!','junior', 'front', 'react', 'c#'), " +
                        "('Employee','RUSTAM','GPOWER','rustamg23@mail.ru','qwertyuiop','junior', 'back', 'java', 'python'), " +
                        "('Employee','Богдан','Бельский','bogdan@mail.ru','dandan111','junior', 'front', 'java', 'sql'), " +
                        "('Employee','Worker','Name','empl25@mail.ru','emplPj225qlzv','junior', 'java', 'sql', 'python');"
        );
        sqLiteDatabase.execSQL(
        "INSERT INTO "+DB_ELO+" ("+ELO_NAME+", "+ELO_INFO+", "+ELO_AVAILABILITY+", "+ELO_OWNER_ID+", "+ELO_TAG1+", "+ELO_TAG2+", "+ELO_TAG3+") "+
        "VALUES ('Java для Senior', " +
                "'Курс Java для Senior-разработчиков\nСборник секретиков, недоступных и непонятных обычным девелоперам', " +
                "1, 3, 'java', 'back', 'sql'), " +
                "('Java для начинающих', " +
                "'Курс Java для Junior-разработчиков\nОтлично подойдет для развития навыков работы с backendом на Java, в первую очередь для работы с сервером', " +
                "0, 2, 'java', 'back', 'sql'), " +
                "('Нейросети в Python', " +
                "'Основы машинного обучения на Python, создание и обучение нейросетей, алгоритмы работы', " +
                "1, 3, 'python', 'back', 'другое'), " +
                "('Основы Python', " +
                "'Базовые знания Python\nОсновы синтаксиса и другие важные моменты', " +
                "0, 3, 'python', 'back', 'другое'), " +
                "('C# для начинающих', " +
                "'Этот курс поможет освоить C# так, чтобы быть в нём, как рыба в воде, а также подтянуть знания в области ООП', " +
                "1, 2, 'c#', 'back', 'другое'), " +
                "('Front&back', " +
                "'Важные моменты связи фронта с бэком с точки зрения фронтэндера: как избежать конфликтов', " +
                "1, 4, 'front', 'react', 'back'), " +
                "('SQL for juniors', " +
                "'Азы работы с базами данных, все важные аспекты написания и обработки запросов, особенности работы с PostgreSQL', " +
                "0, 2, 'sql', 'другое', 'back'), " +
                "('FRONTEND FOR JUNIORS', " +
                "'лучший курс для укрепления основных навыков работы с фронтендом\nплюс вы научитесь связывать фронт с бэком (а это самое главное)', " +
                "0, 2, 'front', 'java', 'react');"
        );
        sqLiteDatabase.execSQL("INSERT INTO "+DB_REL_LIST+" ("+ELO_ID+", "+USER_ID+") "+
        "VALUES (1,7), " +
                "(1,13), " +
                "(1,15), " +
                "(2,7), " +
                "(2,13), " +
                "(2,15), " +
                "(3,6), " +
                "(3,7), " +
                "(3,8), " +
                "(3,10), " +
                "(3,11), " +
                "(3,13), " +
                "(4,6), " +
                "(4,7), " +
                "(4,8), " +
                "(4,10), " +
                "(4,11), " +
                "(4,13), " +
                "(5,6), " +
                "(5,11), " +
                "(5,15), " +
                "(6,4), " +
                "(7,3), " +
                "(7,15), " +
                "(8,7), " +
                "(8,13), " +
                "(8,15);"
        );
        sqLiteDatabase.execSQL(
        "INSERT INTO "+DB_ELO_TASKS+" ("+ELO_ID+", "+TASK_NAME+", "+TASK_INFO+", "+TASK_URL+") "+
        "VALUES (1,'Задание 1','Просмотр видео:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(1,'Задание 2','Просмотр видео:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(1,'Задание 3','Immutable Collections. Просмотр видео:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(1,'Задание 4','Просмотр видео:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(1,'Задание 5','Просмотр видео:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(1,'Итоговое задание','решить тест:', 'https://youtu.be/jMvF2zs7ApA'), " +
                        "(2,'Задание 1','посмотреть видео:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(2,'Задание 2','посмотреть видео:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(2,'Задание 3','посмотреть видео:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(2,'Задание 4','посмотреть видео:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(2,'Задание 5','посмотреть видео:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(2,'Итоговое задание','решить тест:', 'https://www.youtube.com/watch?v=U2OliQwRb6c&list=PLDyJYA6aTY1lT614ixLYq48har7EnCXpk'), " +
                        "(3,'Задание 1','Изучение 1 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 2','Изучение 2 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 3','Изучение 3 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 4','Изучение 4 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 5','Изучение 5 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 6','Изучение 6 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Задание 7','Изучение 7 главы:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(3,'Итоговое задание','решить тест:', 'https://metanit.com/python/tutorial/2.1.php'), " +
                        "(4,'Задание 1','Изучение 1 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 2','Изучение 2 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 3','Изучение 3 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 4','Изучение 4 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 5','Изучение 5 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 6','Изучение 6 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Задание 7','Изучение 7 главы:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(4,'Итоговое задание','Просмотр курса по машинному обучению:', 'https://youtu.be/GwIo3gDZCVQ'), " +
                        "(5,'Задание 1','Изучение 1 главы:', 'https://metanit.com/sharp/tutorial/3.1.php'), " +
                        "(5,'Задание 2','Изучение 2 главы:', 'https://metanit.com/sharp/tutorial/3.1.php'), " +
                        "(5,'Задание 3','Изучение 3 главы:', 'https://metanit.com/sharp/tutorial/3.1.php'), " +
                        "(5,'Задание 4','Изучение 4 главы:', 'https://metanit.com/sharp/tutorial/3.1.php'), " +
                        "(5,'Итоговое задание','решить тест:', 'https://metanit.com/sharp/tutorial/3.1.php'), " +
                        "(6,'Задание 1','Ознакомиться с материалом:', 'https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend'), " +
                        "(6,'Задание 2','Ознакомиться с материалом:', 'https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend'), " +
                        "(6,'Задание 3','Ознакомиться с материалом:', 'https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend'), " +
                        "(6,'Итоговое задание','решить тест:', 'https://stackoverflow.com/questions/68164444/how-to-connect-backend-and-frontend'), " +
                        "(7,'Задание 1','Посмотреть видео и попрактиковаться:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(7,'Задание 2','Посмотреть видео и попрактиковаться:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(7,'Задание 3','Посмотреть видео и попрактиковаться:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(7,'Задание 4','Посмотреть видео и попрактиковаться:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(7,'Задание 5','Посмотреть видео и попрактиковаться:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(7,'Итоговое задание','решить тест:', 'https://www.youtube.com/watch?v=sLwiFGAOMK4&list=PLqj7-hRTFl_oweCD2cFQYdJDmD5bwEhb9'), " +
                        "(8,'Задание 1','Посмотреть видео:', 'https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5'), " +
                        "(8,'Задание 2','Посмотреть видео:', 'https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5'), " +
                        "(8,'Задание 3','Посмотреть видео:', 'https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5'), " +
                        "(8,'Задание 4','Посмотреть видео:', 'https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5'), " +
                        "(8,'Итоговое задание','решить тест:', 'https://www.youtube.com/watch?v=cBYDQlwGEjQ&list=PLMB6wLyKp7lV9YoWTMCztq-KXYhYPB09K&index=5');"
        );
        sqLiteDatabase.execSQL(
        "INSERT INTO "+DB_TASK_PROGRESS+" ("+USER_ID+", "+ELO_ID+", "+TASK_ID+", "+PROGRESS+") "+
        "VALUES (3,7,6,0), " +
                "(4,6,1,0), " +
                "(6,3,5,0), " +
                "(6,4,5,0), " +
                "(6,5,4,0), " +
                "(7,1,5,0), " +
                "(7,2,5,0), " +
                "(7,3,2,0), " +
                "(7,4,2,0), " +
                "(7,8,4,0), " +
                "(8,3,3,0), " +
                "(8,3,3,0), " +
                "(10,3,1,0), " +
                "(10,4,1,0), " +
                "(11,3,7,0), " +
                "(11,4,7,0), " +
                "(11,5,4,0), " +
                "(13,1,3,0), " +
                "(13,2,3,0), " +
                "(13,3,4,0), " +
                "(13,4,4,0), " +
                "(13,8,4,0), " +
                "(15,1,1,0), " +
                "(15,2,1,0), " +
                "(15,7,1,0), " +
                "(15,8,1,0);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        if (i1 > i)
//            try {
//                copyDatabase();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        sqLiteDatabase.execSQL("drop table if exists " + DB_TASK_PROGRESS);
        sqLiteDatabase.execSQL("drop table if exists " + DB_ELO_TASKS);
        sqLiteDatabase.execSQL("drop table if exists " + DB_REL_LIST);
        sqLiteDatabase.execSQL("drop table if exists " + DB_ELO);
        sqLiteDatabase.execSQL("drop table if exists " + DB_USERS);

        onCreate(sqLiteDatabase);
    }

    public Cursor query_s(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return eloDB.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
