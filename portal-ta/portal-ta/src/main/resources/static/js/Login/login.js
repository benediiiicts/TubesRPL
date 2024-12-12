function checkLogin(){

    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;

    const tempPassword = "Admin123";
    const koorUser = "koor";
    const ujiUser = "uji";
    const bimbingUser = "bimbing";
    const siswaUser = "siswa";

    if (username === koorUser){
        if (password === tempPassword){
            window.location.href = "../MainPage/Koordinator/home.html";
        } else{
            alert("Password Anda Salah.");
        }
    }
    else if(username === ujiUser){
        if (password === tempPassword){
            window.location.href = "../MainPage/Penguji/home.html";
        } else{
            alert("Password Anda Salah.");
        }
    }
    else if(username === bimbingUser){
        if (password === tempPassword){
            window.location.href = "../MainPage/Pembimbing/home.html";
        } else{
            alert("Password Anda Salah.");
        }
    }
    else if(username === siswaUser){
        if (password === tempPassword){
            window.location.href = "../MainPage/Mahasiswa/home.html";
        } else{
            alert("Password Anda Salah.");
        }
    }
    else{
        alert("Username Anda Salah.");
    }
}

document.querySelector("form").addEventListener("submit", event => {
    event.preventDefault();
    
    checkLogin();
});