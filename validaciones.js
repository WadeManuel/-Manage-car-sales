const nombre = document.querySelector('.nombre');
const apellido = document.querySelector('.apellido');
const ano = document.querySelector('.ano');
const btnGuardar = document.getElementById('btn-guardar');
const resultado = document.querySelector(".resultado");


btnGuardar.addEventListener("click",(e)=>{
    let error = validarCampos();
    if(error[0]){
        e.preventDefault();
        resultado.innerHTML = error[1];
        resultado.classList.add("red");

    }else{
        resultado.innerHTML = "Datos creados correctamentes ";
        resultado.classList.add("green");
    }

})

const validarCampos=()=>{
    let error = [];
    if(nombre.value.length < 3 ){
        error[0]=true;
        error[1]="El nombre no puede contener menos de 5 caracteres";
    }else if(nombre.value.length > 30 ){
        error[0]=true;
        error[1]="El nombre no puede contener mas de 40 caracteres .";
    }else if(apellido.value.length < 4){
        error[0]=true;
        error[1]="El apellido no puede contener menos de 4 caracteres  ";
    }else if(ano.value >= 1 && ano.value <= 45){
        error[0]=true;
        error[1]="El año es inválido ";
    }
   
    return error;
}











