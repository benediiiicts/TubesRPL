function popUp(){
    let addButton = document.getElementsByClassName('add-button');
    let popUpContainer = document.getElementById('popUp-container');
    let closeButton = document.getElementsByClassName('close-button-popUp');

    addButton[0].addEventListener('click', function () {
        popUpContainer.style.display = 'flex';
    });
    closeButton[0].addEventListener('click', function () {
        popUpContainer.style.display = 'none';
    });
    window.addEventListener('click', function (event) {
        if (event.target === popUpContainer) {
            popUpContainer.style.display = 'none';
        }
    });
}
popUp();
