package Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper { // класс, отвечающий за работу базы данных
    public static final String DATABASE_NAME = "reserves35.db"; // имя базы данных
    public static final int DATABASE_VERSION = 1; // версия

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + MyDataProvider.TABLE_USERS +
                "(" + MyDataProvider.KEY_USER_ID + " integer primary key, "
                + MyDataProvider.KEY_USER_USERNAME + " text, "
                + MyDataProvider.KEY_USER_PASSWORD + " text, "
                + MyDataProvider.KEY_USER_FIRST_NAME + " text, "
                + MyDataProvider.KEY_USER_LAST_NAME + " text, "
                + MyDataProvider.KEY_USER_PHONENUMB + " text, "
                + MyDataProvider.KEY_USER_MAIL + " text)";
        sqLiteDatabase.execSQL(sql); // создание таблицы пользователей

        sql = "CREATE TABLE " + MyDataProvider.TABLE_NOTIFICATIONS +
                "(" + MyDataProvider.KEY_NOTIFICATION_ID + " integer primary key, "
                + MyDataProvider.KEY_NOTIFICATION_USER + " integer, "
                + MyDataProvider.KEY_NOTIFICATION_RESTAURANT_NAME + " text, "
                + MyDataProvider.KEY_NOTIFICATION_RESTAURANT_LOCATION + " text, "
                + MyDataProvider.KEY_NOTIFICATION_RESTAURANT_KITCHEN + " text, "
                + MyDataProvider.KEY_NOTIFICATION_RESTAURANT_RATING + " text, "
                + MyDataProvider.KEY_NOTIFICATION_RESTAURANT_PHONE_NUMBER + " text, "
                + MyDataProvider.KEY_NOTIFICATION_DATE + " text, "
                + MyDataProvider.KEY_NOTIFICATION_TIME + " text, "
                + MyDataProvider.KEY_NOTIFICATION_NUMBER + " text, "
                + MyDataProvider.KEY_NOTIFICATION_TITLE + " text)";
        sqLiteDatabase.execSQL(sql); // создание таблицы объявлений

        sql = "CREATE TABLE " + MyDataProvider.TABLE_RESTAURANT +
                "(" + MyDataProvider.KEY_RESTAURANT_ID + " integer primary key, "
                + MyDataProvider.KEY_RESTAURANT_NAME + " text, "
                + MyDataProvider.KEY_RESTAURANT_LOCATION + " text, "
                + MyDataProvider.KEY_RESTAURANT_KITCHEN + " text, "
                + MyDataProvider.KEY_RESTAURANT_PHONE + " text, "
                + MyDataProvider.KEY_RESTAURANT_TIMETABLE + " text, "
                + MyDataProvider.KEY_RESTAURANT_RATING + " text)";
        sqLiteDatabase.execSQL(sql); // создание таблицы ресторанов

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", "
                + MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('MANGA SUCHI', 'Гоголя 201', '87774567676', '9:00-00:00', 'японская', '4.8') "; // заполнение таблицы ресторанов
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('PARADISE', 'Рыскулбекова 18a', '87056784646', '9:00-00:00', 'европейская', '4.5') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('MAMAMIA', 'Гоголя 77', '87079563674', '9:00-00:00', 'итальянская', '4.3') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('Papa Johns', 'Тимирязева 38/1', '87775867564', '9:00-00:00', 'итальянская', '4.6') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('DODO Pizza', 'Кунаева 41', '87674543663', '9:00-00:00', 'итальянская', '4.1') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('DEGIRMEN', 'Байтурсынова 63', '87078904567', '9:00-00:00', 'турецкая', '3.8') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('SAMURAI SUSHI', 'пр. Достык 91/1', '84783954785', '9:00-00:00', 'японская', '4.5') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('NEDELKA', 'пр. Абая, 19', '87073732848', '9:00-00:00', 'европейская', '4.3') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('OCEAN BASKET', 'Казыбек Би 50', '97778576757', '9:00-00:00', 'морепродукты', '4.2') ";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO " + MyDataProvider.TABLE_RESTAURANT + "(" +
                MyDataProvider.KEY_RESTAURANT_NAME + ", " + MyDataProvider.KEY_RESTAURANT_LOCATION +
                ", " + MyDataProvider.KEY_RESTAURANT_PHONE + ", " + MyDataProvider.KEY_RESTAURANT_TIMETABLE + ", " +
                MyDataProvider.KEY_RESTAURANT_KITCHEN + ", " + MyDataProvider.KEY_RESTAURANT_RATING +
                ") VALUES ('KOREAN HOUSE', 'Гоголя 2', '87055886776', '9:00-00:00', 'корейская', '4.5') ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
