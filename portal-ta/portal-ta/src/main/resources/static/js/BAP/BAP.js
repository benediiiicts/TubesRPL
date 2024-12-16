// JavaScript untuk mengelola fungsi-fungsi pada halaman BAP

        // Fungsi untuk menampilkan popup upload
        function showPopup() {
            const popup = document.getElementById('uploadPopup');
            popup.style.display = 'block';
        }

        // Fungsi untuk menyembunyikan popup upload
        function hidePopup() {
            const popup = document.getElementById('uploadPopup');
            popup.style.display = 'none';
        }

        // Fungsi untuk menangani konfirmasi upload
        function confirmUpload() {
            const fileInput = document.getElementById('fileInput');

            if (fileInput.files.length === 0) {
                alert('Silakan pilih file terlebih dahulu!');
                return;
            }

            const file = fileInput.files[0];
            alert(`File ${file.name} berhasil diupload!`);

            // Simulasi upload file
            // Di sini Anda dapat menambahkan logika untuk mengirim file ke server dengan fetch atau XMLHttpRequest

            // Perbarui status setelah upload
            const statusDiv = document.querySelector('.status');
            statusDiv.textContent = 'Anda Telah Menyetujui!';

            // Sembunyikan popup
            hidePopup();
        }

        // Tambahkan event listener untuk tombol unduh jika diperlukan
        const downloadBtn = document.querySelector('.download-btn');
        if (downloadBtn) {
            downloadBtn.addEventListener('click', () => {
                alert('Fitur unduh belum diimplementasikan.');
                // Tambahkan logika unduh file jika sudah tersedia
            });
        }

        // Pastikan script ini berjalan setelah DOM siap
        document.addEventListener('DOMContentLoaded', () => {
            // Logika tambahan setelah halaman selesai dimuat
        });