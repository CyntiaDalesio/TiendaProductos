/*redirect login button*/
let login = document.getElementById("signIn");
login.addEventListener("click", ()=>{
    location.href ="/login";
})

/*reditect to home page*/
let home =  document.getElementById("home");
if (home) {
    home.addEventListener("click", ()=>{
        location.href = "/";
    })
}
