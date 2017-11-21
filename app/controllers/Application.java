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

public class Application extends Controller {

    public Result index() {
        return ok(index.render("SEBiblioteca"));
    }

    /*0-9 v <volume>
    * digito 10 ou + é livro
    * */
    public Result autores(){
        JsonNode json = request().body().asJson();

        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String genero= json.findPath("genero").asText();
            int paginas = json.findPath("npaginas").asInt();

            List<Livro> livros = Ebean.find(Livro.class)
                    .where()
                    .like("desc_area_conhecimento",genero)
                    .findList();
            List<String> autores = new ArrayList<String>();
            System.out.println(json.toString());
            for (Livro L: livros) {

                String pg = L.pagina;

                if (pg!=null) {
                   // System.out.println(pg);
                    if ((pg.charAt(pg.length() - 1) == '.') && (pg.charAt(pg.length() - 2) == 'p')) {
                        if ((pg.length() <= 6)) {
                            if (paginas < 200) {
                                autores.add(L.autor);
                            } else if ((pg.charAt(0) == '2') || (pg.charAt(0) == '3')) {
                                autores.add(L.autor);
                            } else if (pg.charAt(0) == '4') {
                                autores.add(L.autor);
                            }
                        } else if (paginas > 999) {
                            autores.add(L.autor);
                        }

                    }
                }
//                autores.add(pg);
            }
        }
        return ok(Json.toJson(autores()));

    }
}
