package fabio.prof.testews.javateste;

import java.util.List;

import fabio.prof.testews.domain.Presente;
import fabio.prof.testews.model.PresentesModel;

public class Teste {

    public static void main(String[] args) throws Exception {
        List<Presente> lista = PresentesModel.get();
        if(lista != null) {
            for (Presente p : lista) {
                System.out.println(p.getId());
                System.out.println(p.getTitulo());
                System.out.println(p.getValor());
                System.out.println(p.getMensagem());
                System.out.println(p.getData());
                System.out.println(p.getDataCadastro());
                System.out.println("");
            }
        }

        Presente p = PresentesModel.get(87654334);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getValor());
        System.out.println(p.getMensagem());
        System.out.println(p.getData());
        System.out.println(p.getDataCadastro());


        System.out.println("");
        p = PresentesModel.get(87654377);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getValor());
        System.out.println(p.getMensagem());
        System.out.println(p.getData());
        System.out.println(p.getDataCadastro());

        p = new Presente();
        p.setId(87654377);
        p.setTitulo("Teste Aula Update2");
        p.setValor(99999999.99);
        p.setMensagem("Teste Aula Update2");
        p.setConvidado("noivo");
        p.setData("2018-11-01");

        String ret = PresentesModel.add(p);
        System.out.println(ret == null);
        System.out.println(ret);

        ret = PresentesModel.update(p);
        System.out.println(ret == null);
        System.out.println(ret);

        System.out.println("");
        p = PresentesModel.get(87654377);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getValor());
        System.out.println(p.getMensagem());
        System.out.println(p.getData());
        System.out.println(p.getDataCadastro());

        p = PresentesModel.get(87654377);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getValor());
        System.out.println(p.getMensagem());
        System.out.println(p.getData());
        System.out.println(p.getDataCadastro());

        System.out.println("");
        ret = PresentesModel.delete(87654376);
        System.out.println(ret == null);

        System.out.println("");
        p = PresentesModel.get(87654377);
        System.out.println(p.getId());
        System.out.println(p.getTitulo());
        System.out.println(p.getValor());
        System.out.println(p.getMensagem());
        System.out.println(p.getData());
        System.out.println(p.getDataCadastro());

    }
}
