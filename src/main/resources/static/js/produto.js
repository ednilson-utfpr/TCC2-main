function filtrar(filtro) {
    // $.ajax({
    //     type: 'GET',
    //     url: '/produto?filter=' + filtro,
    //     success: function() {
    //         console.log('sucesso');
    //         // Swal.fire({
    //         //     title: 'Salvo!',
    //         //     text: 'Registro salvo com sucesso!',
    //         //     type: 'success'
    //         // }).then((result) => {
    //         //         window.location = urlDestino;
    //         //     }
    //         // );//FIM swal()
    //     },
    //     error: function() {
    //         console.log('Errou!', 'Falha!', 'error');
    //     }
    // });
    // return false;
    window.location = '/produto?nome=' + filtro;
}