package fabio.prof.testews;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.model.PresentesModel;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_NOVO = 1112;
    private ListView listView;
    @Override protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); listView=findViewById(R.id.listView); loadListView(); }
    private void loadListView() {
        Toast.makeText(this,"Carregando lista...",Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() { @Override public void run() { final List<Presente> presentes=PresentesModel.get(); runOnUiThread(new Runnable() { @Override public void run() { populateListView(presentes); } }); } }).start();
    }
    private void populateListView(List<Presente> presentes) {
        if(presentes==null){ Toast.makeText(this,"Erro ao obter lista de presentes",Toast.LENGTH_LONG).show(); return; }
        List<String> values=new ArrayList<>(); for(Presente p:presentes) values.add(p.getTitulo()+" ("+p.getConvidado()+")");
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values));
    }
    @Override public boolean onCreateOptionsMenu(Menu menu){ menu.add(0,MENU_NOVO,0,"Novo Presente"); return super.onCreateOptionsMenu(menu); }
    @Override public boolean onOptionsItemSelected(MenuItem item){ if(item.getItemId()==MENU_NOVO) startActivity(new Intent(this,CadastroActivity.class)); return super.onOptionsItemSelected(item); }
}
