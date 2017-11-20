function SelecionaAutores() {
    console.log("foi")
    var listaDeAutores = [];
    var cont = 0;
    for(var i = 0; i < autores.size(); i++){
        if(autores[i] == null) {
            listaDeAutores[cont] = autores[i];
            cont++;
            console.log(listaDeAutores);
        }
    }

}