package fabio.prof.testews;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.model.PresentesModel;
public class CadastroActivity extends AppCompatActivity {
    private EditText txtTitulo,txtValor,txtMensagem; private Presente p;
    @Override protected void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); setContentView(R.layout.activity_cadastro); txtTitulo=findViewById(R.id.txtTitulo); txtValor=findViewById(R.id.txtValor); txtMensagem=findViewById(R.id.txtMensagem); p=new Presente(); p.setId(0); Bundle b=getIntent().getExtras(); if(b!=null){ p.setId(b.getLong("id")); txtTitulo.setText(b.getString("titulo")); txtValor.setText(String.valueOf(b.getDouble("valor"))); txtMensagem.setText(b.getString("mensagem")); }}
    public void salvar(View v){ p.setTitulo(txtTitulo.getText().toString()); p.setValor(Double.valueOf(txtValor.getText().toString())); p.setMensagem(txtMensagem.getText().toString()); p.setConvidado("noivo"); p.setData("2018-11-01"); new Thread(new Runnable(){ public void run(){ final String ret=p.getId()==0?PresentesModel.add(p):PresentesModel.update(p); runOnUiThread(new Runnable(){ public void run(){ if(ret==null){Toast.makeText(CadastroActivity.this,"Sucesso!",Toast.LENGTH_SHORT).show();finish();}else Toast.makeText(CadastroActivity.this,ret,Toast.LENGTH_LONG).show(); }}); }}).start(); }
}
