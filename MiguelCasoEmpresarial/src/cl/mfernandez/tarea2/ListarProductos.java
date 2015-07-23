package cl.mfernandez.tarea2;

import Tablas.Cliente;
import Tablas.Productos;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListarProductos extends Activity {
	
	private Productos manager;
	private Cursor cursor;
	private ListView lista;
	SimpleCursorAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_listar_productos);
		
		manager = new Productos(this);
/*	manager.insertar_producto("COCA COLA 1.5LT", "EMBONOR", 980, 5, 1);
	manager.insertar_producto("VINO DON OMAR TETRA 2 LT", "DON OMAR", 2000, 3, 1);
	manager.insertar_producto("COCINA DIVA 850", "DIVA", 229000, 2, 1);	
	manager.insertar_producto("CELULAR SAMSUMG GALAXY S7", "SAMSUMG", 760310, 8, 1);
	*/
	
		lista = (ListView) findViewById(R.id.lv_productos);
		

		cursor = manager.cargarCursorProductos();
		String[] from = new String[] { manager.PROD_NOMBRE,
				manager.PROD_MARCA, manager.PROD_PRECIO, manager.PROD_STOCK, manager.PROD_ESTADO };

		int[] to = new int[] { R.id.txtNombreProducto, R.id.txtMarcaProducto, R.id.txtPrecioProducto, R.id.txtStockProducto, R.id.txtProductoEstado };
		

		adapter = new SimpleCursorAdapter(this, R.layout.filas_productos, cursor, from,
				to);
		lista.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_productos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
