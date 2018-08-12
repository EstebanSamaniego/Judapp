package com.example.a0esifsam.drawerlayout.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DbHandler extends SQLiteOpenHelper {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "judappdb";

    private final static String TBL_CANCIONES = "TABLA_CANCIONES";
    private final static String KEY_ID = "id";
    private final static String KEY_NOM = "nombre";
    private final static String KEY_AUT = "autor";
    private final static String KEY_DESC = "descripcion";

    //Constructor
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Inicializacion
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CANC = "CREATE TABLE " + TBL_CANCIONES + "( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NOM + " TEXT, "
                + KEY_AUT + " TEXT, "
                + KEY_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_CANC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_CANCIONES);
        onCreate(db);
    }

    //CRUD METHODS
    void insertCancion(String nombre, String autor, String descripcion){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NOM, nombre);
        cv.put(KEY_AUT, autor);
        cv.put(KEY_DESC, descripcion);

        long newRowId = db.insert(TBL_CANCIONES, null, cv);
        db.close();
    }

    public ArrayList<String> getCanciones(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> listaCanciones = new ArrayList<>();

        String query = "SELECT nombre, autor, descripcion FROM " + TBL_CANCIONES;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            String cancion;

            cancion = cursor.getString(cursor.getColumnIndex(KEY_NOM));
            //cancion.put("autor", cursor.getString(cursor.getColumnIndex(KEY_AUT)));
            //cancion.put("descripcion", cursor.getString(cursor.getColumnIndex(KEY_DESC)));

            listaCanciones.add(cancion);
        }

        return listaCanciones;
    }
}
