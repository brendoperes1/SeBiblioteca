package controllers;
import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
//import com.sun.xml.internal.ws.api.model.ExceptionType;
import models.Livro;
import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("SEBiblioteca"));
    }
    public Result result() { return ok(result.render()); }

    /*0-9 v <volume>
    * digito 10 ou + é livro
    * */
    public Result Autores(){
        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String genero= json.findPath("genero").asText();
            int paginas = json.findPath("npaginas").asInt();

//            System.out.println(json.findPath(genero));
//            System.out.println(json.toString());
//            System.out.println(genero);
//            System.out.println(paginas);
            List<Livro> livros = Ebean.find(Livro.class)
                    //.setDistinct(true)
                    .where()
                    .like("desc_area_conhecimento",genero)
                    .findList();


            //List<Livro> autores = new ArrayList<Livro>();
            List<String> autores = new ArrayList<String>();
            List<String> Saida=new ArrayList<String>();
            for (Livro L: livros) {
                String pg = L.pagina;
                if ((pg != null)&&(L.autor!=null)) {
                    // System.out.println(pg);
                    if((paginas==0)){
                        autores.add(L.autor);
                    }else{
                        if ((pg.charAt(pg.length() - 1) == '.') && (pg.charAt(pg.length() - 2) == 'p')) {
                            if ((pg.length() <= 5)) {
                                if (paginas < 100) {
                                    autores.add(L.autor);
                                }
                            } else if ((pg.length() == 6)) {
                                if ((paginas >= 100) && (paginas < 200)) {
                                    if ((pg.charAt(0) == '1'))
                                        autores.add(L.autor);
                                } else if ((paginas >= 200) && (paginas < 400))
                                    if ((pg.charAt(0) == '2') || (pg.charAt(0) == '3'))
                                        autores.add(L.autor);

                            } else if ((pg.charAt(0) != ' ') && (paginas >= 400)) {
                                autores.add(L.autor);
                            }
                            if ((pg.charAt(0) == ' ') && (paginas < 500)) {
                                if ((pg.length() <= 6)) {
                                    if (paginas < 100) {
                                        autores.add(L.autor);
                                    }
                                } else if ((pg.length() == 7)) {
                                    if ((paginas >= 100) && (paginas < 200)) {
                                        if ((pg.charAt(0) == '1'))
                                            autores.add(L.autor);
                                    }
                                    if ((paginas >= 200) && (paginas < 400))
                                        if ((pg.charAt(0) == '2') || (pg.charAt(0) == '3'))
                                            autores.add(L.autor);
                                } else if ((pg.length() >= 7) && paginas >= 400) {
                                    autores.add(L.autor);
                                }
                            }
                        }
                    }

                    String aux = new String();
                    for (int i = 0; i < autores.size(); i++) {
                        if (!aux.equals(autores.get(i))) {
                            aux = autores.get(i);

                        } else if (aux.equals(autores.get(i))) {
                            autores.remove(i);
                        }
                        for (int j = i + 1; j < autores.size(); j++) {
                            if (aux.equals(autores.get(j))) {
                                autores.remove(j);
                            }
                        }
                    }
                }

            }
            if(autores.size()>20){
                Random random = new Random();
                int nAleatorio[] = new int[20];

                for(int i=0;i<20;i++){
                    nAleatorio[i] = random.nextInt(autores.size());
                    for(int j=i;j>=0;j--){
                        if(nAleatorio[i]==nAleatorio[j]){
                            nAleatorio[i] = random.nextInt(autores.size());
                        }
                    }
                    Saida.add(autores.get(nAleatorio[i]));
                }
                return ok(Json.toJson(Saida));
            }
            else{
                return ok(Json.toJson(autores));
            }

        }

    }
    public Result Livros(){
        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String autor = json.findPath("autor").asText();
            int paginas = json.findPath("npaginas").asInt();
            List<Livro> livros = Ebean.find(Livro.class)
                    //.setDistinct(true)
                    .where()
                    .like("autor","%"+autor+"%")
                    .findList();

            List<Livro> selecionados = new ArrayList<Livro>();
            for (Livro L: livros) {
                String pg = L.pagina;
                if (pg!=null) {
                    // System.out.println(pg);
                    if((paginas==0)&&(L.autor!=null)){
                        selecionados.add(L);
                    }else{
                        if ((pg.charAt(pg.length() - 1) == '.') && (pg.charAt(pg.length() - 2) == 'p')) {
                            if ((pg.length() <= 5)) {
                                if (paginas < 100) {
                                    selecionados.add(L);
                                }
                            } else if ((pg.length() == 6)) {
                                if ((paginas >= 100) && (paginas < 200)) {
                                    if ((pg.charAt(0) == '1'))
                                        selecionados.add(L);

                                } else if ((paginas >= 200) && (paginas < 400))
                                    if ((pg.charAt(0) == '2') || (pg.charAt(0) == '3'))
                                        selecionados.add(L);
                                    else if (paginas >= 400) {
                                        selecionados.add(L);
                                    }

                            } else if ((pg.charAt(0) != ' ') && (paginas >= 400) && (pg.length() >= 6)) {
                                selecionados.add(L);
                            }
                            if ((pg.charAt(0) == ' ') && (paginas < 500)) {
                                if ((pg.length() <= 6)) {
                                    if (paginas < 100) {
                                        selecionados.add(L);
                                    }
                                } else if ((pg.length() == 7)) {
                                    if ((paginas >= 100) && (paginas < 200)) {
                                        if ((pg.charAt(0) == '1'))
                                            selecionados.add(L);
                                    }
                                    if ((paginas >= 200) && (paginas < 400))
                                        if ((pg.charAt(0) == '2') || (pg.charAt(0) == '3'))
                                            selecionados.add(L);
                                        else if (paginas >= 400) {
                                            selecionados.add(L);
                                        }
                                } else if ((pg.length() >= 7) && paginas >= 400) {
                                    selecionados.add(L);
                                }
                            }
                        }
                    }
                }
            }
            return ok(Json.toJson(selecionados));
        }
    }
}
