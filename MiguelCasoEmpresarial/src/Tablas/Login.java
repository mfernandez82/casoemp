package Tablas;


import HelperDb.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Login
{
	
public static final String MI_TABLA = "LOGIN";
	
	public static final String CLI_ID ="_ID";
	public static final String CLI_USERNAME ="USERNAME";
	public static final String CLI_PASSWORD ="PASSWORD";
	
	public static final String CREAR_TABLA_LOGIN ="CREATE TABLE " + MI_TABLA
            + "("+ CLI_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ CLI_USERNAME +"  TEXT, "
            				+ CLI_PASSWORD +" TEXT); ";
            						
	
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public Login(Context context){
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
		
	}
	
	public String getSinlgeEntry(String userName)
	{
		Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
        	cursor.close();
        	return "NOT EXIST";
        }
	    cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
		cursor.close();
		return password;				
	}
	
	public ContentValues generocontentvalores(String login, String password)
	{
		ContentValues valores =  new ContentValues();
		valores.put(CLI_USERNAME, login);
		valores.put(CLI_PASSWORD, password);
			
		return valores;
		
	}
	
	public void insertar_login(String login, String password)
	{
		ContentValues valores =  new ContentValues();
		valores.put(CLI_USERNAME, login);
		valores.put(CLI_PASSWORD, password);

		
		db.insert(MI_TABLA, null, valores);	
		
	}
	
	public void eliminar_cliente(String nombre)
	{
		db.delete(MI_TABLA, CLI_USERNAME +"=?", new String[] {nombre});
		}
	
	public void actualizarClave(String login, String password){
		
		db.update(MI_TABLA, generocontentvalores(login, password), CLI_USERNAME +"=?", new String []{login});
		
	}
	
	public Cursor cargarCursorLogin(){		
		String[] columnas = new String[] {CLI_ID + " AS _id ", CLI_USERNAME , CLI_PASSWORD};				
	return	db.query(MI_TABLA, columnas, null, null, null, null, null);			
	}
	
	
	

}
