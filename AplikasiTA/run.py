from app import create_app

# Membuat aplikasi Flask menggunakan fungsi create_app
app = create_app()

if __name__ == "__main__":
    # Menjalankan aplikasi di server lokal
    app.run(debug=True)
