package HelperDb;




import Tablas.Cliente;
import Tablas.Detalle_pedidos;
import Tablas.Login;
import Tablas.Pedidos;
import Tablas.Productos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	public static final int version_db = 1;
	public static final String NOMBRE_BD= "pruebas3";
	private SQLiteDatabase db;
	
	public DbHelper(Context context) {
		super(context, NOMBRE_BD, null, version_db);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Cliente.CREAR_TABLA_CLIENTES);
		db.execSQL(Login.CREAR_TABLA_LOGIN);
		db.execSQL(Productos.CREAR_TABLA_PRODUCTOS);
		db.execSQL(Pedidos.CREAR_TABLA_PEDIDOS);
		db.execSQL(Detalle_pedidos.CREAR_TABLA_DET_PEDIDOS);
		
	//	insertarAlgunosClientes();
				
		// TODO Auto-generated method stub
		
	}
	
	
	public long CrearClientes( String nombres, String direccion, 
			   String fono, int id_usuario, int estado) {
			 
			  ContentValues valores_clientes = new ContentValues();
			  valores_clientes.put(Cliente.CLI_NOMBRES, nombres);
			  valores_clientes.put(Cliente.CLI_DIRECCION, direccion);
			  valores_clientes.put(Cliente.CLI_FONO, fono);
			  valores_clientes.put(Cliente.CLIE_ID_VENDEDOR, id_usuario);
			  valores_clientes.put(Cliente.CLIE_ESTADO, estado);
			 
			  return db.insert(Cliente.TABLA, null, valores_clientes);
			 }
	
	
	public void insertarAlgunosClientes() {
		 
		CrearClientes("MIGUEL FFF ", "LA DASDAS" , "222", 1, 1);
		CrearClientes("ROSA PARRA ", "LA DASDAS" , "532", 1, 1);
		CrearClientes("LUIS LOPEZ ", "LA DASDAS" , "256", 1, 1);
		CrearClientes("JUAN RIVERA ", "LA DASDAS" , "854", 1, 1);

	}
	
	
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS LOGIN");
		db.execSQL("DROP TABLE IF EXISTS CLIENTES");
		// Create a new one.
		onCreate(db);
	}
	
	
	
	

}
