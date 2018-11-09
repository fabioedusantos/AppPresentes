package fabio.prof.testews.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import fabio.prof.testews.domain.Response;
import fabio.prof.testews.domain.ResponseListaPresentes;
import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.domain.ResponseManipulation;
import fabio.prof.testews.domain.ResponsePresente;
import fabio.prof.testews.util.Http;
import fabio.prof.testews.util.HttpParam;

public class PresentesModel {
    private static final String URL_BASE = "http://fabiosantos.tk/aula_ws_presentes/Presentes";
    private static String result;

    public static List<Presente> get() {
        ResponseListaPresentes reponse;
        List<Presente> ret = null;
        try {
            result = Http.get(URL_BASE, null);
            reponse = new Gson().fromJson(result, ResponseListaPresentes.class);
            if(reponse != null && reponse.getError() == null) {
                ret = reponse.getResponse();
            }
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        } finally {
            reponse = null;
        }
        return ret;
    }

    public static Presente get(long id) {
        ResponsePresente response;
        Presente ret = null;
        try {
            result = Http.get(URL_BASE, new HttpParam()
                    .add("id", String.valueOf(id)).getParam());
            response = new Gson().fromJson(result, ResponsePresente.class);
            if(response != null && response.getError() == null) {
                ret = response.getResponse();
            }
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        } finally {
            response = null;
        }
        return ret;
    }

    public static String add(Presente presente) {
        ResponseManipulation response;
        String ret = null;
        try {
            result = Http.put(URL_BASE, new HttpParam()
                    .add("titulo", presente.getTitulo())
                    .add("valor", String.valueOf(presente.getValor()))
                    .add("mensagem", presente.getMensagem())
                    .add("convidado", presente.getConvidado())
                    .add("data", presente.getData())
                    .getParam());
            response = new Gson().fromJson(result, ResponseManipulation.class);
            if(response != null && !response.isResponse()) {
                ret = response.getError();
        }
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        } finally {
            response = null;
        }
        return ret;
    }

    public static String update(Presente presente) {
        ResponseManipulation response;
        String ret = null;
        try {
            result = Http.post(URL_BASE, new HttpParam()
                    .add("id", String.valueOf(presente.getId()))
                    .add("titulo", presente.getTitulo())
                    .add("valor", String.valueOf(presente.getValor()))
                    .add("mensagem", presente.getMensagem())
                    .add("convidado", presente.getConvidado())
                    .add("data", presente.getData())
                    .getParam());
            response = new Gson().fromJson(result, ResponseManipulation.class);
            if(response != null && !response.isResponse()) {
                ret = response.getError();
            }
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        } finally {
            response = null;
        }
        return ret;
    }

    public static String delete(long id) {
        ResponseManipulation response;
        String ret = null;
        try {
            result = Http.delete(URL_BASE, new HttpParam()
                    .add("id", String.valueOf(id))
                    .getParam());
            response = new Gson().fromJson(result, ResponseManipulation.class);
            if(response != null && !response.isResponse()) {
                ret = response.getError();
            }
        } catch (Exception e) {
            Log.e("teste", e.getMessage());
        } finally {
            response = null;
        }
        return ret;
    }
}