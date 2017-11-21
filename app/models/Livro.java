package models;
import javax.persistence.Entity;

@Entity
public class Livro {
    public int cod_acervo;
    public String autor;
    public String titulo;
    public String titulo_n;
    public String sub_titulo;
    public String titulo_revista;
    public String pagina;
    public String ref_artigo;
    public String edicao;
    public String nota_monog;
    public String escala;
    public String publicacao;
    public String classificacao;
    public String ano;
    public String tit_ant_post;
    public String indice;
    public int exe;
    public int exe_adicional;
    public int area_aux;
    public String area_aux_desc;
    public String cod_area_conhecimento;
    public String desc_area_conhecimento;
    public int cod_biblioteca;
    public String desc_biblioteca;
    public int cod_tipo_obra;
    public String desc_tipo_obra;
    public String resumo;
    public String area_aux_formatado;
    public String titulo_geral;

}
