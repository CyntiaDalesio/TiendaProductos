
let input = document.querySelector(".input-cantity");

input.addEventListener("change", function () {
    if (input.value % 1 == 0) {
        console.log(input.value)
        let valueProduct = document.getElementById("valueProduct").innerHTML;

        let total = valueProduct * parseInt(input.value);
        console.log(total)
        let totalPurchase = document.getElementById("totalPurchase").innerHTML = total;
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El valor ingresado no es valido',

        })
    }

});

let form = document.querySelector('form').addEventListener("submit", () => {
    Swal.fire({   
        position: 'center',
        icon: 'success',
        title: 'Compra realizada con exito',
        showConfirmButton: false,
    })
});





