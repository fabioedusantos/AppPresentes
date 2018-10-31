package fabio.prof.testews;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.model.PresentesModel;

public class MainActivity extends AppCompatActivity {

    private final int MENU_ATUALIZAR = 1111;
    private final int MENU_NOVO = 1112;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadListView();
    }

    private void loadListView() {
        Toast.makeText(MainActivity.this, "Carregando lista...",
                Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Presente> presentes = PresentesModel.get();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateListView(presentes);
                    }
                });
            }
        }).start();
    }

    private void populateListView(final List<Presente> presentes) {
        if (presentes == null) {
            Toast.makeText(MainActivity.this, "Erro ao obter lista de presentes",
                    Toast.LENGTH_LONG).show();
            return;
        }
        List<String> values = new ArrayList<>();

        for (Presente p : presentes) {
            values.add(p.getTitulo() + " (" + p.getConvidado() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Presente p = presentes.get(position);
                Intent i = new Intent(MainActivity.this, CadastroActivity.class);
                i.putExtra("id", p.getId());
                i.putExtra("titulo", p.getTitulo());
                i.putExtra("valor", p.getValor());
                i.putExtra("mensagem", p.getMensagem());
                i.putExtra("convidado", p.getConvidado());
                i.putExtra("data", p.getData());
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                deletar(presentes.get(position));
                return true;
            }
        });
    }

    private void deletar(final Presente presente) {
        new AlertDialog.Builder(this)
                .setMessage("Deseja remover " + presente.getTitulo() + " (" + presente.getConvidado() + ")" + "?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final String ret = PresentesModel.delete(presente.getId());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (ret == null) {
                                            Toast.makeText(MainActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                                            loadListView();
                                        } else {
                                            Toast.makeText(MainActivity.this, ret, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }).start();


                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_ATUALIZAR, 0, "Atualizar Lista");
        menu.add(0, MENU_NOVO, 1, "Novo Presente");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ATUALIZAR:
                loadListView();
                break;
            case MENU_NOVO:
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
