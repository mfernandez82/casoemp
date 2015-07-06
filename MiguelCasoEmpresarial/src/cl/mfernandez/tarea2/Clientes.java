package cl.mfernandez.tarea2;


import Tablas.Cliente;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Clientes extends Activity implements OnClickListener {

	private Cliente manager;
	private Cursor cursor;
	private ListView lista;
	SimpleCursorAdapter adapter;
	private Button botonAgregarPersona;

	// Constantes privadas.
	private int CODIGO_RESULT_EDITAR_PERSONA = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_clientes);

		manager = new Cliente(this);
	manager.insertar_cliente("MIGUEL FFF ", "LA DASDAS" , "222", 1, 1);
		manager.insertar_cliente("ROSA PARRA ", "LA DASDAS" , "222", 1, 1);
		manager.insertar_cliente("LUIS LOPEZ ", "LA DASDAS" , "222", 1, 1);
		manager.insertar_cliente("JUAN RIVERA ", "LA DASDAS" , "222", 1, 1); 
	

		lista = (ListView) findViewById(R.id.lv_usuarios);
		botonAgregarPersona = (Button) findViewById(R.id.btnAgregarCliente);

		registerForContextMenu(lista);

		cursor = manager.cargarCursorClientes();
		String[] from = new String[] { manager.CLI_NOMBRES,
				manager.CLI_DIRECCION, manager.CLI_FONO };

		int[] to = new int[] { R.id.txtnombre, R.id.txtdireccion, R.id.txtfono };

		adapter = new SimpleCursorAdapter(this, R.layout.filas, cursor, from,
				to);
		lista.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		botonAgregarPersona.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Se crea una nueva persona.
				editarPersona(0);
			}
		});
		
		

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		android.view.MenuInflater inflater = getMenuInflater();
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		inflater.inflate(R.menu.opciones_personas, menu);
	}

	/**
	 * Metodo publico que se sobreescribe. En este metodo colocamos las acciones
	 * de las opciones del menu contextual para el ListView de personas.
	 */
	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.menu_contextual_editar_persona:
			editarPersona((int) info.id);
			return true;
		case R.id.menu_contextual_eliminar_persona:
			eliminarPersona((int) info.id);
			// recuperarTodasPersonas();

			return true;
		default:
			return super.onContextItemSelected((android.view.MenuItem) item);
		}
	}

	/**
	 * Metodo publico que edita una persona.
	 * 
	 * @param p_id
	 */
	public void editarPersona(int p_id){
		// Si el p_id es 0, entonces se crea una nueva persona.
		if(p_id == 0){			
			Toast.makeText(
					getApplicationContext(),
					"Se creara un nuevo cliente!!"+ p_id,
					Toast.LENGTH_SHORT).show();
			// Se dirige a la actividad EditarPersonaActivity.
	        Intent actividad_editarPersona = new Intent(Clientes.this, CrearEditarCliente.class);
	        actividad_editarPersona.putExtra("cod_cliente", "0");			   	        
	        startActivity(actividad_editarPersona);	         
		}else{
			// Recupera un cliente especifico.
		
			
			Toast.makeText(
					getApplicationContext(),
					"Editar el cliente seleccionado!!"+ p_id,
					Toast.LENGTH_SHORT).show();
			
			
			
		        Intent actividad_editarPersona = new Intent(this, CrearEditarCliente.class);
		            
		        String codigo = String.valueOf(p_id);
		        // Se le coloca parametros para enviar a la actividad editar clieter.		        		      
		        actividad_editarPersona.putExtra("cod_cliente", codigo);		   
		        startActivity(actividad_editarPersona);
			
		}
	}

	/**
	 * Metodo privado que elimina una persona.
	 * 
	 * @param id_persona
	 */
	private void eliminarPersona(int id_persona) {
		// Objetos.
		AlertDialog.Builder mensaje_dialogo = new AlertDialog.Builder(this);

		// Variables.
		final int v_id_persona = id_persona;

		mensaje_dialogo.setTitle("Importante");
		mensaje_dialogo.setMessage("Estas seguro de eliminar a este Cliente?");
		mensaje_dialogo.setCancelable(false);
		mensaje_dialogo.setPositiveButton("Confirmar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogo1, int id) {
						/*
						 * try{ baseDatos.eliminaPersona(v_id_persona);
						 * 
						 * recuperarTodasPersonas(); }catch(Exception e){
						 */
						String ff = String.valueOf(v_id_persona);
						manager.eliminar_cliente_por_id(ff);
						Toast.makeText(
								getApplicationContext(),
								"Se ha eliminado al cliente satisfactoriamente!!!",
								Toast.LENGTH_SHORT).show();
						// clientes.remove(v_id_persona);
						cursor = manager.cargarCursorClientes();
						lista.setAdapter(adapter);
						adapter.notifyDataSetChanged();

						Intent actividad_MENU = new Intent(Clientes.this,
								MiMenu.class);
						startActivity(actividad_MENU);
						/*
						 * e.printStackTrace(); }finally{ baseDatos.cerrar(); }
						 */
					}
				});
		mensaje_dialogo.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogo1, int id) {
						// recuperarTodasPersonas();
					}
				});
		mensaje_dialogo.show();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.main_clientes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clientes, menu);
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
		Intent actividad_editarPersona = new Intent(Clientes.this,
				CrearEditarCliente.class);
	     actividad_editarPersona.putExtra("cod_cliente", "f");
		   
         
	        startActivity(actividad_editarPersona);
	   

	}
}
