function checkLogin(){

    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;

    const tempPassword = "Admin123";
    if (password === tempPassword){
        window.location.href = "../MainPage/Koordinator/home.html";
    } else{
        alert("Password Anda Salah.");
    }
}

document.querySelector("form").addEventListener("submit", event => {
    event.preventDefault();
    
    checkLogin();
});