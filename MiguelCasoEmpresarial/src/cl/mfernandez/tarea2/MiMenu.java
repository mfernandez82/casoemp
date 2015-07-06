package cl.mfernandez.tarea2;




import Tablas.Productos;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MiMenu extends  TabActivity{

	private Button btncliente, btnProductos, btnEfectuarVentas; //Evento por código 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.mi_menu);
		setContentView(R.layout.mi_tabs);
		
		TabHost tabHost = getTabHost(); // Creamos el tabhost de la actividad
        
        TabHost.TabSpec spec; // Creamos un recurso para las propiedades de la pestana
        Intent intent; // Intent que se utilizara para abrir cada pestana
        Resources res = getResources(); //Obtenemos los recursos
        
        //Se crea el intent para abrir la actividad que en realidad es una clase
        intent = new Intent().setClass(this, Clientes.class);
        //Se configura la pestana con sus propiedades
        spec = tabHost.newTabSpec("Pestaña1").setContent(intent).setIndicator("    Clientes     ");        
        //Se carga la pestana en el contenedor TabHost 
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#0099CC")); 
        
        //Se hace exactamente lo mismo con las demas pestanas
        
        intent = new Intent().setClass(this, ListarProductos.class);
        spec = tabHost.newTabSpec("Pestaña2").setContent(intent).setIndicator("  Listar Productos  ");  
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#9933CC"));
        
        intent = new Intent().setClass(this, EfectuarVenta.class);
        spec = tabHost.newTabSpec("Pestaña3").setContent(intent).setIndicator("  Ventas  ");  
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#669900"));
        
        intent = new Intent().setClass(this, Resumen.class);
        spec = tabHost.newTabSpec("Pestaña4").setContent(intent).setIndicator("  Resumen Vta.  ");  
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#FF8800"));
        
        intent = new Intent().setClass(this, Mapa.class);
        spec = tabHost.newTabSpec("Pestaña5").setContent(intent).setIndicator("  Mapa  ");  
        tabHost.addTab(spec);
        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#FF7700"));
        /*
		
		 String nombre_vendedor  = getIntent().getStringExtra("usuario_vendedor");
		 
		 Toast.makeText(MiMenu.this, "nombre vendedor "+nombre_vendedor, Toast.LENGTH_LONG).show();
		
		 TextView msn_bienvenido = (TextView) findViewById(R.id.bienvenido);
		 
		 msn_bienvenido.setText("hola que tal "+nombre_vendedor);
		btncliente =(Button) findViewById(R.id.btn_cliente); //Evento por código
		btnProductos = (Button) findViewById(R.id.btn_producto);
		btnEfectuarVentas  =(Button) findViewById(R.id.btn_venta);
		
		btncliente.setOnClickListener(onClickListener);
		btnProductos.setOnClickListener(onClickListener);
		btnEfectuarVentas.setOnClickListener(onClickListener);
		
		*/
		
	}
	
	private OnClickListener onClickListener = new OnClickListener() {
	     @Override
	     public void onClick(View v) {
	         switch(v.getId()){
	             case R.id.btn_cliente:
	            	 Intent intent = new Intent(MiMenu.this, Clientes.class);	                
	                		MiMenu.this.startActivity(intent);    
	             break;
	             case R.id.btn_venta:
	            	 Intent intent2 = new Intent(MiMenu.this, EfectuarVenta.class);	                
             		MiMenu.this.startActivity(intent2);    
	             break;
	             case R.id.btn_producto:
	            	 Intent intent3 = new Intent(MiMenu.this, ListarProductos.class);	                
             		MiMenu.this.startActivity(intent3);
	             break;
	             
	             case R.id.btn_resumen:
	            	 Intent intent4 = new Intent(MiMenu.this, Resumen.class);	                
             		MiMenu.this.startActivity(intent4);
	             break;
	             
	         }

	   }
	};
	
	
	public void productos(View v){
		Intent intent = new Intent(MiMenu.this, Productos.class);
       	//	intent.putExtra("codigo_vendedor", cod_vendedor);
       		MiMenu.this.startActivity(intent); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	
		MenuInflater inflando_mi_menu =  getMenuInflater();
		inflando_mi_menu.inflate(R.menu.mi_menu_v2, menu);
		
		 return true;
		 
		/*
		getMenuInflater().inflate(R.menu.mi_menu, menu);
		return true; */
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
/*
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}*/
}
