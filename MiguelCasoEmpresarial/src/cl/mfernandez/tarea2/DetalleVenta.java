package cl.mfernandez.tarea2;

import Tablas.Login;
import Tablas.Productos;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DetalleVenta extends Activity implements OnClickListener {

	private Productos manager;
	private Cursor cursor;
	private ListView lista, listaDeVenta;
	SimpleCursorAdapter adapter;

	Login loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_detalle_venta);
		loginDataBaseAdapter = new Login(this);
		String nom_cliente = getIntent().getStringExtra("nombre_cliente");

		TextView nombre_clie = (TextView) findViewById(R.id.cliente_nombre);
		Button btn_agregar_prod = (Button) findViewById(R.id.btn_agregar_producto);
		nombre_clie.setText(nom_cliente);
		Toast.makeText(this, "nombre cliente" + nom_cliente, Toast.LENGTH_LONG);

		manager = new Productos(DetalleVenta.this);

	}

	// Methos to handleClick Event of Sign In Button
	public void ingresar(View V) {
		final Dialog dialog = new Dialog(DetalleVenta.this, R.style.NewDialog);
		dialog.setContentView(R.layout.dialogo_agregar_producto);
		dialog.setTitle("Listado de productos");
		//dialog.getWindow().setBackgroundDrawable(null);
		
		
		
		dialog.setCancelable(true);
		dialog.show();

		// la idea es que al presionar sobre el listado de los productos, sale del listado
		// y se debe agregar el listview del detalle de la venta
		// para posteriomrnte guardarla en la base de datos.
		
		cursor = manager.cargarCursorProductosDialogo();
		String[] from = new String[] { manager.PROD_NOMBRE, manager.PROD_MARCA,
				manager.PROD_PRECIO, manager.PROD_STOCK };

		int[] to = new int[] { R.id.dialogotxtNombreProducto,
				R.id.dialogotxtMarca, R.id.dialogotxtPrecio,
				R.id.dialogoTxtStock };
		lista = (ListView) dialog.findViewById(R.id.lv_productos_dialogo);
		adapter = new SimpleCursorAdapter(DetalleVenta.this,
				R.layout.filas_productos_en_dialogo, cursor, from, to);
		lista.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {

				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				// saco los datos personalizados de la base de datos por
				// columnas
				/*
				 * String nombre_cliente = cursor.getString(cursor
				 * .getColumnIndexOrThrow("PROD_NOMBRE"));
				 */
				String codigo_est = cursor.getString(cursor
						.getColumnIndexOrThrow("_id"));
				String nombre_producto = cursor.getString(cursor
						.getColumnIndexOrThrow("NOMBRE"));
				String precio = cursor.getString(cursor
						.getColumnIndexOrThrow("PRECIO"));
				

				/*
				 * Intent mf = new Intent (EfectuarVenta.this,
				 * DetalleVenta.class); mf.putExtra("nombre_cliente",
				 * nombre_cliente); mf.putExtra("codigo_cliente", codigo_est);
				 * startActivity(mf);
				 * 
				 * 
				 * Toast.makeText(getApplicationContext(),
				 * "efectuando venta al cliente " + nombre_cliente,
				 * Toast.LENGTH_SHORT).show();
				 */
				Toast.makeText(getApplicationContext(),
						"codigo del PRODUCTO " + codigo_est, Toast.LENGTH_SHORT)
						.show();
				
				Toast.makeText(getApplicationContext(),
						"Nombre del PRODUCTO " + nombre_producto, Toast.LENGTH_SHORT)
						.show();
				
				Toast.makeText(getApplicationContext(),
						"PRECIO " + precio, Toast.LENGTH_SHORT)
						.show();

				dialog.dismiss();
				llenar_lista();
			}

		});

		/*
		 * dialog.dismiss();
		 */

		// dialog.show();
	}
	
	public void llenar_lista(){
		
				listaDeVenta = (ListView) findViewById(R.id.lv_detalle_venta);
		
				
		
	}

	public void dialogo_agregar(View v) {

		/*
		 * 
		 * final Dialog dialog = new Dialog(DetalleVenta.this);
		 * dialog.setContentView(R.layout.filas_productos);
		 * dialog.setTitle("Lista de productos"); dialog.setCancelable(true); //
		 * aca lo declaras cancelable, es decir si clickeaste el boton y no
		 * queres realizar nada apretando el boton back se cierra.
		 * 
		 * ArrayList<String>tulista = new ArrayList<String>(); //declaras una
		 * arraylist que va a ser el que llena la lista con tus elementos
		 * tulista.add("elemento 1"); //agregale los elementos que necesites
		 * mostrar tulista.add("elemento 2");
		 * 
		 * final ListView lvelementos = (ListView)
		 * dialog.findViewById(R.id.lv_detalle_venta); // declaras la listview
		 * que tenes en el xml creada, acordate que para que no te tire null
		 * pointer en vez de hacer el findviewbyid() como de costumbre, tenes
		 * que poner dialog.findviewbyid() para que sepa que los elementos estan
		 * en el dialog y no en tu activity.
		 * 
		 * lvelementos.setAdapter(new
		 * ArrayAdapter<String>(getApplicationContext(),
		 * android.R.layout.simple_list_item_multiple_choice, tulista));
		 * lvelementos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // declaras
		 * el adapter y ya con esto se te mostraria tu lista. en
		 * android.R.layout.simple_list_item_multiple_choice podes ir
		 * cambiandolo y fijarte cual necesitarias, en este caso yo use una con
		 * checkbox para seleccionar muchos, pero en tu caso podrias poner
		 * simple_list_item1.
		 * 
		 * lvelementos.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { // TODO Auto-generated method stub
		 * dialog.dismiss(); } });
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_venta, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
