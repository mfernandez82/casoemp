package Tablas;

import HelperDb.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Pedidos {
	public static final String TABLA = "PEDIDOS";
	
	public static final String PED_ID ="_ID";
	public static final String PED_NUMERO ="NUM_PEDIDO";
	public static final String PED_ID_CLIENTE ="ID_CLIENTE";
	public static final String PED_FECHA ="FECHA_PEDIDO";
	
	
	public static final String CREAR_TABLA_PEDIDOS ="CREATE TABLE " + TABLA
            + "( "+ PED_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ PED_NUMERO +"  INTEGER, "
            				+ PED_ID_CLIENTE +" INTEGER, "
            						+ PED_FECHA +" TEXT); ";
            							
	
	
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public Pedidos(Context context){
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
		
	}
	
	public ContentValues generocontentvalores(int num_pedido, int id_cliente, String fecha_pedido)
	{
		ContentValues valores =  new ContentValues();
		valores.put(PED_NUMERO, num_pedido);
		valores.put(PED_ID_CLIENTE, id_cliente);
		valores.put(PED_FECHA, fecha_pedido);

		return valores;
		
	}
	
	public void insertar_producto (int num_pedido, int id_cliente, String fecha_pedido)
	{
		
		db.insert(TABLA, null, generocontentvalores(num_pedido, id_cliente,fecha_pedido));	
		
	}
	
	public void eliminar_pedido(String numero_pedido )
	{
		db.delete(TABLA, PED_NUMERO +"=?", new String[] {numero_pedido});
		
    }
	

	
	public Cursor cargarCursorClientes(){		
		String[] columnas = new String[] {PED_ID+" AS _id ", PED_NUMERO , PED_ID_CLIENTE, PED_FECHA};				
	return	db.query(TABLA, columnas, null, null, null, null, null);			
	}	
	
}
