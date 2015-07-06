package cl.mfernandez.tarea2;



import Tablas.Cliente;
import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class CrearEditarCliente extends Activity  implements OnClickListener{

	// Constantes privadas.

	private Cliente manager;
	private Cursor cursor;
	private EditText txtNombre,txtDireccion, txtFono;
	private ListView lista;
	SimpleCursorAdapter adapter;
	private Button CrearCliente, butonLimpiar, btnvolver;
		private Bundle extras;
		int codigo_interno;
		 String int_cliente; 
		
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_crear_editar_cliente);
		
		  int_cliente = getIntent().getStringExtra("cod_cliente");
		 
		 txtNombre = (EditText) findViewById(R.id.editTextNombre);
		 txtDireccion = (EditText) findViewById(R.id.editTextDireccion);
		 txtFono = (EditText) findViewById(R.id.editTextFono);
		 CrearCliente = (Button) findViewById(R.id.botonGuardar);
		 btnvolver =(Button) findViewById(R.id.botonVolver);
		 butonLimpiar = (Button) findViewById(R.id.botonLimpiar);
		 CrearCliente.setOnClickListener(onClickListener);
		 
		// vuelvo a la activity anterior
		 btnvolver.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							 Intent intent2 = new Intent(CrearEditarCliente.this, Clientes.class);	                
			             		startActivity(intent2);    
						}
					});
		
		// Limpia los campos.
			butonLimpiar.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					limpiarCampos();
				}
			});
		 
		 
		 manager = new Cliente(this);
		 
		 
	
		codigo_interno = Integer.parseInt(int_cliente);
		 
		
		
		
		 if (codigo_interno == 0)
		 {
			 Toast.makeText(this, "crear un nuevo cliente " , Toast.LENGTH_LONG).show();
			
			 
		 }
		 else
		 {
			 Toast.makeText(this, "Sacar los datos del cliente " , Toast.LENGTH_LONG).show();
			 
			// manager.getCursorClientes(int_cliente);
			 
			 cursor = manager.getRegistro(codigo_interno);			 
			 txtNombre.setText(cursor.getString(cursor.getColumnIndex(manager.CLI_NOMBRES)));
			 txtDireccion.setText(cursor.getString(cursor.getColumnIndex(manager.CLI_DIRECCION)));
			 txtFono.setText(cursor.getString(cursor.getColumnIndex(manager.CLI_FONO)));
			 CrearCliente.setText("Modificar");

			 
			 
		 }
		 
		 
		 
		 
	}
	
	
	private OnClickListener onClickListener = new OnClickListener() {
	     @Override
	     public void onClick(View v) {
	         switch(v.getId()){
	             case R.id.botonGuardar:
	            	 if (txtNombre.getText().toString().matches("")
	         				|| txtDireccion.getText().toString().matches("")|| txtFono.getText().toString().matches("")) {
	         			Toast.makeText(getApplicationContext(),
	         					"Debes ingresar los datos solicitados",
	         					Toast.LENGTH_SHORT).show();

	         		} else {
	         			if (codigo_interno == 0)
	         			{
	         				 manager.insertar_cliente(txtNombre.getText().toString(), txtDireccion.getText().toString(), txtFono.getText().toString(), 1, 1);
	  	            	   Toast.makeText(CrearEditarCliente.this, "Cliente " + txtNombre+" Registrado satisfactoriamente !!", Toast.LENGTH_LONG);
	  	            	   Intent intent = new Intent( CrearEditarCliente.this, Clientes.class);	                
	  	             		startActivity(intent);
	  	             		
	         				
	         			}
	         			else
	         			{
	         				manager.actualizarDatos(txtNombre.getText().toString(), txtDireccion.getText().toString(), txtFono.getText().toString(),int_cliente);
	         				 Toast.makeText(CrearEditarCliente.this, "Cliente " + txtNombre+" Registrado satisfactoriamente !!"+int_cliente, Toast.LENGTH_LONG);
	         				
	         				Intent intent = new Intent( CrearEditarCliente.this, Clientes.class);	                
		             		startActivity(intent);
	         			//	manager.actualizardireccion(nombres, direccion, fono, id_vendedor, estado);
	            	  
	         			}
	         		}
	             break;
	             case R.id.btn_venta:
	              
	             break;
	             case R.id.btn_producto:
	            	
	             break;
	         }

	   }
	};
	
	/**
	 * Metodo privado que limpia los campos.
	 */
	private void limpiarCampos() {
		txtNombre.setText("");
		txtDireccion.setText("");
		txtFono.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crear_editar_cliente, menu);
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
