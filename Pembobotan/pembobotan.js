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