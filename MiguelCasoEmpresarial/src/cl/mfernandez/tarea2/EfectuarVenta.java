package cl.mfernandez.tarea2;



import Tablas.Cliente;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EfectuarVenta extends Activity {

	private Cliente manager;
	private Cursor cursor;
	private ListView lista;
	SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_efectuar_venta);

		displayListView();
		

	}
	
	private void displayListView() {
		manager = new Cliente(this);

		lista = (ListView) findViewById(R.id.lv_cliente_para_venta);
		registerForContextMenu(lista);

		cursor = manager.cargarCursorClientes();
		String[] from = new String[] { manager.CLI_NOMBRES,
				manager.CLI_DIRECCION, manager.CLI_FONO };

		int[] to = new int[] { R.id.txtnombre, R.id.txtdireccion, R.id.txtfono };

		adapter = new SimpleCursorAdapter(this, R.layout.filas, cursor, from,
				to);
		lista.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				// saco los datos personalizados de la base de datos por columnas
				String nombre_cliente = cursor.getString(cursor
						.getColumnIndexOrThrow("NOMBRES"));
				
			String codigo_est = cursor.getString(cursor
					.getColumnIndexOrThrow("_id"));
				
				Intent mf =  new Intent (EfectuarVenta.this, DetalleVenta.class);
				mf.putExtra("nombre_cliente", nombre_cliente);
				mf.putExtra("codigo_cliente", codigo_est);
				startActivity(mf);
			

				Toast.makeText(getApplicationContext(), "efectuando venta al cliente " + nombre_cliente,
						Toast.LENGTH_SHORT).show();
				
				Toast.makeText(getApplicationContext(), "codigo del cliente " + codigo_est,
						Toast.LENGTH_SHORT).show();
			}

		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.efectuar_venta, menu);
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
