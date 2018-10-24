package fabio.prof.testews.model;
import android.util.Log;
import com.google.gson.Gson;
import java.util.List;
import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.domain.ResponseListaPresentes;
import fabio.prof.testews.domain.ResponsePresente;
import fabio.prof.testews.util.Http;
import fabio.prof.testews.util.HttpParam;

public class PresentesModel {
    private static final String URL_BASE = "http://fabiosantos.tk/aula_ws_presentes/Presentes";
    private static String result;
    public static List<Presente> get() {
        try {
            result = Http.get(URL_BASE, null);
            ResponseListaPresentes response = new Gson().fromJson(result, ResponseListaPresentes.class);
            if(response != null && response.getError() == null) return response.getResponse();
        } catch (Exception e) { Log.e("teste", e.getMessage()); }
        return null;
    }
    public static Presente get(long id) {
        try {
            result = Http.get(URL_BASE, new HttpParam().add("id", String.valueOf(id)).getParam());
            ResponsePresente response = new Gson().fromJson(result, ResponsePresente.class);
            if(response != null && response.getError() == null) return response.getResponse();
        } catch (Exception e) { Log.e("teste", e.getMessage()); }
        return null;
    }
}
