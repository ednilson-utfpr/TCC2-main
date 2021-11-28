function endAlert() {
    alert("Verifique o endereço de entrega!!!");
        }

function myFunction(x) {
        x.style.background = "yellow";
     }

     swal({
       title: "Are you sure?",
       text: "You will not be able to recover this imaginary file!",
       type: "warning",
       showCancelButton: true,
       confirmButtonColor: "#DD6B55",
       confirmButtonText: "Yes, delete it!",
       closeOnConfirm: false
     },
     function alertaEnd(){
       swal("Atenção!", "Verifique o seu endereço de entrega.", "success");
     });



