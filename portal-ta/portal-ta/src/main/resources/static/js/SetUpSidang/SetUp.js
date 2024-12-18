console.log('Script loaded successfully!');

// Select the "Add Staff" button
const addStaffButton = document.getElementById('addstaff');

// Test if the button works
addStaffButton.addEventListener('click', () => {
    console.log('Add Staff button clicked!');
});

function setUpPopupLogic() {
    const popupContainer = document.querySelector('.addstaffcontainer');
    const addStaffButton = document.getElementById('addstaff');
    const submitButton = popupContainer.querySelector('button[type="submit"]');
    const idDosenField = document.getElementById('iddosentextfield');
    const kategoriDropdown = document.getElementById('kategoridosen');
    const staffContainerBody = document.querySelector('.staffcontainer-body');

    // Show popup when "Add Staff" button is clicked
    addStaffButton.addEventListener('click', () => {
        popupContainer.style.display = 'block'; // Show popup
    });

    // Submit popup and add new staff component
    submitButton.addEventListener('click', (event) => {
        event.preventDefault();

        // Get input values
        const idDosenValue = idDosenField.value.trim();
        const kategoriValue = kategoriDropdown.value;

        // Validate inputs
        if (!idDosenValue || kategoriValue === 'Default') {
            alert('Please fill in all fields.');
            return;
        }

        // Create a new staff component
        const newDosenBox = document.createElement('div');
        newDosenBox.classList.add('dosenbox');
        newDosenBox.innerHTML = `
            <p>${idDosenValue}</p>
            <p>${kategoriValue}</p>
        `;

        // Append to the staff container body
        staffContainerBody.appendChild(newDosenBox);

        // Clear inputs and hide popup
        idDosenField.value = '';
        kategoriDropdown.value = 'Default';
        popupContainer.style.display = 'none';
    });
}
setUpPopupLogic();