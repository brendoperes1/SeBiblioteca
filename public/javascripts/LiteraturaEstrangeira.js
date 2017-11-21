function SelecionaAutores() {
    console.log("foi")
    var listaDeAutores = [];
    var cont = 0;
    //console.log(autor.size)

    for(var i = 0; i < 9; i++){
        console.log(autor[i])
        if(autor[i] != null) {
            listaDeAutores[cont] = autor[i];
            cont++;
        }
    }
    for(var i=0;i<9;i++ ){
        console.log(listaDeAutores[i]);
    }

}
function PesquisaAutor() {
    
}