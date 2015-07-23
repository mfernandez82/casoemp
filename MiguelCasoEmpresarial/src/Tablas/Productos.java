package Tablas;

import HelperDb.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Productos {
	public static final String TABLA = "PRODUCTOS";
	
	public static final String PROD_ID ="_ID";
	public static final String PROD_NOMBRE ="NOMBRE";
	public static final String PROD_MARCA ="MARCA";
	public static final String PROD_PRECIO ="PRECIO";
	public static final String PROD_STOCK ="STOCK";
	public static final String PROD_ESTADO ="ESTADO";
	
	public static final String CREAR_TABLA_PRODUCTOS ="CREATE TABLE " + TABLA
            + "( "+ PROD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ PROD_NOMBRE +"  TEXT, "
            				+ PROD_MARCA +" TEXT, "
            						+ PROD_PRECIO +" INTEGER,"
            								+ PROD_STOCK +" INTEGER,"
            										+ PROD_ESTADO + " integer); ";
	
	
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public Productos(Context context){
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
		
	}
	
	public ContentValues generocontentvalores(String nombre, String marca, int precio, int stock, int estado)
	{
		ContentValues valores =  new ContentValues();
		valores.put(PROD_NOMBRE, nombre);
		valores.put(PROD_MARCA, marca);
		valores.put(PROD_PRECIO, precio);
		valores.put(PROD_STOCK, stock);
		valores.put(PROD_ESTADO, estado);
		
		return valores;
		
	}
	
	public void insertar_producto (String nombre, String marca, int precio, int stock, int estado)
	{
		
		db.insert(TABLA, null, generocontentvalores(nombre, marca,precio,stock, estado));	
		
	}
	
	public void eliminar_cliente(String nombre)
	{
		db.delete(TABLA, PROD_NOMBRE +"=?", new String[] {nombre});
		
    }
	
public void actualizardireccion(String nombre, String marca, int precio, int stock, int estado)
{
		
		db.update(TABLA, generocontentvalores(nombre, marca, precio,stock, estado), PROD_NOMBRE +"=?", new String []{nombre});
		
	}
	
	public Cursor cargarCursorProductos(){		
		String[] columnas = new String[] {PROD_ID+" AS _id ", PROD_NOMBRE  , PROD_MARCA, PROD_PRECIO, PROD_STOCK, PROD_ESTADO};				
	return	db.query(TABLA, columnas, null, null, null, null, null);			
	}	
	
	public Cursor cargarCursorProductoSegunId(String id){		
		String[] columnas = new String[] {PROD_ID+" AS _id ", PROD_NOMBRE  , PROD_MARCA, PROD_PRECIO, PROD_STOCK, PROD_ESTADO};
		String[] args = new String[] {id};
		
	return	db.query(TABLA, columnas, "_id = ?", args, null, null, null);			
	}	
	
	public Cursor cargarCursorProductosDialogo(){		
		String[] columnas = new String[] {PROD_ID+" AS _id ", PROD_NOMBRE  , PROD_MARCA, PROD_PRECIO, PROD_STOCK};				
	return	db.query(TABLA, columnas, null, null, null, null, null);			
	}
	
}
