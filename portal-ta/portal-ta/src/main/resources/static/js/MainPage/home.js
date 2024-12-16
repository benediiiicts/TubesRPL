// function handleSelectChange() {
//     var selectElement = document.getElementById("sort");
//     var selectedValue = selectElement.value;

//     if (selectedValue) {
//         fetch('/submit-semester', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify({ semesterId: selectedValue })
//         })
//         .then(response => response.json())
//         .then(data => {
//             console.log("Selected Semester:", data);
//             // You can update the UI or perform other actions here
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
//     } else {
//         console.error("No semester selected.");
//     }
// }