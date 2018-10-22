package fabio.prof.testews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.model.PresentesModel;

public class CadastroActivity extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtValor;
    private EditText txtMensagem;
    private Spinner spnConvidado;
    private DatePicker dpData;
    private Presente p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtValor = findViewById(R.id.txtValor);
        txtMensagem = findViewById(R.id.txtMensagem);
        spnConvidado = findViewById(R.id.spnConvidado);
        dpData = findViewById(R.id.dpData);

        initComponents();

        p = new Presente();
        p.setId(0);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            p.setId(b.getLong("id"));
            txtTitulo.setText(b.getString("titulo"));
            txtValor.setText(String.valueOf(b.getDouble("valor")));
            txtMensagem.setText(b.getString("mensagem"));
            spnConvidado.setSelection(b.getString("convidado").equals("noivo") ? 0 : 1);
            dpData.updateDate(getAno(b.getString("data")), getMes(b.getString("data")), getDia(b.getString("data")));
        }
    }

    private void initComponents(){
        String list[] = new String[] {
                "noivo",
                "noiva"
        };
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnConvidado.setAdapter(dataAdapter);
    }

    public void salvar(View v){
        enable(false);
        p.setTitulo(txtTitulo.getText().toString());
        p.setValor(Double.valueOf(txtValor.getText().toString()));
        p.setMensagem(txtMensagem.getText().toString());
        p.setConvidado(spnConvidado.getSelectedItem().toString());
        p.setData(getData());

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String ret = p.getId() == 0 ? PresentesModel.add(p) : PresentesModel.update(p);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(ret == null) {
                            Toast.makeText(CadastroActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            Toast.makeText(CadastroActivity.this, ret, Toast.LENGTH_LONG).show();
                        }
                        enable(true);
                    }
                });
            }
        }).start();
    }

    private void enable(boolean enable) {
        txtTitulo.setEnabled(enable);
        txtValor.setEnabled(enable);
        txtMensagem.setEnabled(enable);
        spnConvidado.setEnabled(enable);
        dpData.setEnabled(enable);
    }

    private String getData() {
        int dia = dpData.getDayOfMonth();
        int mes = dpData.getMonth() + 1;
        int ano = dpData.getYear();

        return String.valueOf(ano) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia);
    }

    private int getAno(String date) {
        String arr[] = date.split("-");
        return Integer.valueOf(arr[0]);
    }
    private int getMes(String date) {
        String arr[] = date.split("-");
        return Integer.valueOf(arr[1]) - 1;
    }
    private int getDia(String date) {
        String arr[] = date.split("-");
        return Integer.valueOf(arr[2]);
    }

}
