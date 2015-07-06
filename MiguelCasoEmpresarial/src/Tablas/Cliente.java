package Tablas;

import HelperDb.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Cliente {
	public static final String TABLA = "CLIENTES";
	
	public static final String CLI_ID ="_ID";
	public static final String CLI_NOMBRES ="NOMBRES";
	public static final String CLI_DIRECCION ="DIRECCION";
	public static final String CLI_FONO ="FONO";
	public static final String CLIE_ID_VENDEDOR ="ID_LOGIN";
	public static final String CLIE_ESTADO ="ESTADO";
	private String[] columnas = new String[]{ CLI_ID, CLI_NOMBRES, CLI_DIRECCION, CLI_FONO, CLIE_ID_VENDEDOR, CLIE_ESTADO} ;
	
	
	public static final String CREAR_TABLA_CLIENTES ="CREATE TABLE " + TABLA
            + "( "+ CLI_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ CLI_NOMBRES +"  TEXT, "
            				+ CLI_DIRECCION +" TEXT, "
            						+ CLI_FONO +" TEXT,"
            								+ CLIE_ID_VENDEDOR +" INTEGER,"
            										+ CLIE_ESTADO + " integer); ";
	
	
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public Cliente(Context context){
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
		
	}
	

	
	public ContentValues generocontentvalores(String nombres, String Direccion, String fono, int id_vendedor, int estado)
	{
		ContentValues valores =  new ContentValues();
		valores.put(CLI_NOMBRES, nombres);
		valores.put(CLI_DIRECCION, Direccion);
		valores.put(CLI_FONO, fono);
		valores.put(CLIE_ID_VENDEDOR, id_vendedor);
		valores.put(CLIE_ESTADO, estado);	
		return valores;
		
	}
	
	public void insertar_cliente (String nombres, String direccion, String fono, int id_vendedor, int estado)
	{
		
		db.insert(TABLA, null, generocontentvalores(nombres, direccion,fono,id_vendedor, estado));	
		
	}
	
	public void eliminar_cliente(String nombre)
	{
		db.delete(TABLA, CLI_NOMBRES +"=?", new String[] {nombre});
		
    }
	
	public void eliminar_cliente_por_id(String id)
	{
		//db.execSQL("delete clientes where _id =" +id);
		db.delete(TABLA, CLI_ID +"=?", new String[] {id});
		
    }
	
	public void actualizardireccion(String nombres, String direccion, String fono, int id_vendedor, int estado){
		
		db.update(TABLA, generocontentvalores(nombres, direccion, fono,id_vendedor, estado), CLI_NOMBRES +"=?", new String []{nombres});
		
	}
	
	public Cursor cargarCursorClientes(){		
		String[] columnas = new String[] {CLI_ID+" AS _id ", CLI_NOMBRES +" AS NOMBRES ", CLI_DIRECCION, CLI_FONO};				
	return	db.query(TABLA, columnas, null, null, null, null, null);			
	}	
	
	public Cursor getCursorClientes(String id){		
		String[] columnas = new String[] {CLI_ID+" AS _id ", CLI_NOMBRES , CLI_DIRECCION, CLI_FONO};				
	return	db.query(TABLA, columnas, " _id=?"+new String[]{id}, null, null, null, null);			
	}	
	
	
	public Cursor getRegistro(long id) throws SQLException
	   {
	      Cursor c = db.query( true, TABLA, columnas, CLI_ID + "=" + id, null, null, null, null, null);
	 
	      //Nos movemos al primer registro de la consulta
	      if (c != null) {
	         c.moveToFirst();
	      }
	      return c;
	   }
	
	public ContentValues generocontentvaloresActualizar(String nombres, String Direccion, String fono)
	{
		ContentValues valores =  new ContentValues();
		valores.put(CLI_NOMBRES, nombres);
		valores.put(CLI_DIRECCION, Direccion);
		valores.put(CLI_FONO, fono);
		
		return valores;
		
	}
	
public void actualizarDatos(String nombres, String direccion, String fono, String id_cliente){
		
		db.update(TABLA, generocontentvaloresActualizar(nombres, direccion, fono), CLI_ID +"=?", new String []{id_cliente});
		
	}
	
	

	
}
