const valueBobotBerubah = document.getElementById('value-bobot-berubah');
const valueText = document.getElementById('value-text');
const updateButton = document.querySelector('.update-button');
const deleteButton = document.querySelector('.delete-button');
// const addNewButton = document.querySelector('add-button');

// update button
updateButton.addEventListener('click', () => {
    const newValue = valueText.value.trim(); // Ambil nilai input dengan filter spasi
    if (newValue && !isNaN(newValue)) {
        valueBobotBerubah.textContent = newValue;// Update nilai bobot
        valueText.value = '';
    } else {
        alert('Masukkan nilai angka yang valid!');
    }
});

//delete button
deleteButton.addEventListener('click', () => {
    valueText.value = ''; // inputnya jadi kosong
    // valueBobotBerubah.textContent = '0';
});

const addNewButton = document.getElementById('add-new');
const popup = document.getElementById('popup');
const popupOverlay = document.getElementById('popup-overlay');
const submitNewButton = document.getElementById('submit-new');
const componentContainer = document.getElementById('component-container');
const newTitleInput = document.getElementById('new-title');
const newPercentageInput = document.getElementById('new-percentage');

// Fungsi untuk menampilkan pop-up
function showPopup() {
    popup.style.display = 'block';
    popupOverlay.style.display = 'block';
}

// Fungsi untuk menyembunyikan pop-up
function hidePopup() {
    popup.style.display = 'none';
    popupOverlay.style.display = 'none';
    newTitleInput.value = ''; // Reset input
    newPercentageInput.value = '';
}

// Tambahkan event listener untuk tombol "Add new"
addNewButton.addEventListener('click', showPopup);

// Tambahkan event listener untuk klik di luar pop-up
popupOverlay.addEventListener('click', hidePopup);

// Tambahkan komponen baru setelah submit
submitNewButton.addEventListener('click', () => {
    const title = newTitleInput.value.trim();
    const percentage = newPercentageInput.value.trim();

    // Validasi input
    if (title === '' || percentage === '' || isNaN(percentage) || percentage <= 0 || percentage > 100) {
        alert('Silakan masukkan data yang valid.');
        return;
    }

    // Buat elemen baru untuk komponen
    const newComponent = document.createElement('div');
    newComponent.classList.add('component');
    newComponent.innerHTML = `
        <div class="comp-title">${title}</div>
        <div class="comp-bobot">${percentage}%</div>
        <button class="comp-delete">delete</button>
    `;

    // Tambahkan event listener untuk tombol delete
    newComponent.querySelector('.comp-delete').addEventListener('click', () => {
        newComponent.remove();
    });

    // Tambahkan komponen ke container
    componentContainer.appendChild(newComponent);

    // Sembunyikan pop-up
    hidePopup();
});
// function deleteComponent(componentElement) {
//     if (confirm('Apakah Anda yakin ingin menghapus komponen ini?')) {
//         componentElement.remove();
//     }
// }
// newComponent.querySelector('.comp-delete').addEventListener('click', () => {
//     deleteComponent(newComponent);
// });


