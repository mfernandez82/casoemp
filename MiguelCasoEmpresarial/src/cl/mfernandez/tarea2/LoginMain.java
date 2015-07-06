package cl.mfernandez.tarea2;





import Tablas.Login;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMain extends Activity {
	Login loginDataBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);
		

		// creo una instancion de la bd
		loginDataBaseAdapter = new Login(this);
		
		loginDataBaseAdapter.insertar_login("123", "123");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_main, menu);
		return true;
	}
	
	// Methos to handleClick Event of Sign In Button
		public void ingresar(View V) {
			final Dialog dialog = new Dialog(LoginMain.this);
			dialog.setContentView(R.layout.login);
			dialog.setTitle("Login");

			// get the Refferences of views
			final EditText editTextUserName = (EditText) dialog
					.findViewById(R.id.editTextUserNameToLogin);
			final EditText editTextPassword = (EditText) dialog
					.findViewById(R.id.editTextPasswordToLogin);

			Button btnSignIn = (Button) dialog.findViewById(R.id.btn_ingresar);

			// Set On ClickListener
			btnSignIn.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					// llamo a los texto del xml
					String userName = editTextUserName.getText().toString();
					String password = editTextPassword.getText().toString();

					// obteno la pass de usuario
					String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

					// comparo la pass ingresada con la de la bd
					if (password.equals(storedPassword)) {
						Toast.makeText(LoginMain.this, "nombre vendedor "+userName, Toast.LENGTH_LONG);
						
						Toast.makeText(LoginMain.this, "Usuario correcto",
								Toast.LENGTH_LONG).show();

						// ir_al_menu();

					
						// Login.this.startActivity(intent);
						dialog.dismiss();

						Intent intent = new Intent(LoginMain.this, MiMenu.class);
						 intent.putExtra("usuario_vendedor", userName);
						 
					 //intent.putExtra("codigo_vendedor", cod_vendedor);
						LoginMain.this.startActivity(intent);
					} else {
						Toast.makeText(LoginMain.this,
								"Usuario o contraseña no existe ",
								Toast.LENGTH_LONG).show();
					}
				}
			});

			dialog.show();
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
