package Tablas;

import HelperDb.DbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Detalle_pedidos {
	public static final String TABLA = "DET_PEDIDOS";
	
	public static final String DET_ID ="_ID";
	public static final String DET_NUMERO_PEDIDO ="NUM_PEDIDO";
	public static final String DET_COD_PRODUCTO ="COD_PRODUCTO";
	public static final String DET_CANTIDAD ="CANTIDAD";
	public static final String DET_PRECIO ="PRECIO_UNIT";
	public static final String DET_SUB_TOTAL ="SUB_TOTAL";
	
	
	// SENTICACIA PARA CREAR TABLA
	public static final String CREAR_TABLA_DET_PEDIDOS ="CREATE TABLE " + TABLA
            + "( "+ DET_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ DET_NUMERO_PEDIDO +"  INTEGER, "
            				+ DET_COD_PRODUCTO +" INTEGER, "
            				+ DET_CANTIDAD +"  INTEGER, "
            				+ DET_PRECIO +" INTEGER, "
            						+ DET_SUB_TOTAL +" INTEGER); ";
            				
	private DbHelper helper;
	private SQLiteDatabase db;
	
	public Detalle_pedidos(Context context){
		helper = new DbHelper(context);
		db = helper.getWritableDatabase();
		
	}
	
	public ContentValues generocontentvalores(int num_pedido, int cod_producto, int cantidad, int precio_unit, int sub_total)
	{
		ContentValues valores =  new ContentValues();
		valores.put(DET_NUMERO_PEDIDO, num_pedido);
		valores.put(DET_COD_PRODUCTO, cod_producto);
		valores.put(DET_CANTIDAD, cantidad);
		valores.put(DET_PRECIO, precio_unit);
		valores.put(DET_SUB_TOTAL, sub_total);

		return valores;
		
	}
	
	public void insertar_detalle_pedido (int num_pedido, int cod_producto, int cantidad, int precio_unit, int sub_total)
	{
		
		db.insert(TABLA, null, generocontentvalores(num_pedido, cod_producto,cantidad,precio_unit, sub_total));	
		
	}
	
	public void eliminar_prod_del_detalle(String numero_pedido, String cod_producto )
	{
		db.delete(TABLA, DET_NUMERO_PEDIDO +"=?" + DET_COD_PRODUCTO +"=?", new String[] {numero_pedido, cod_producto});
		
    }
	

	
	public Cursor cargarCursorDetallePedidos(){		
		String[] columnas = new String[] {DET_ID+" AS _id ", DET_NUMERO_PEDIDO , DET_COD_PRODUCTO, DET_CANTIDAD,DET_PRECIO, DET_SUB_TOTAL};				
	return	db.query(TABLA, columnas, null, null, null, null, null);			
	}	
	
}
