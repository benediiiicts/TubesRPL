-- Query General TA
SELECT 
	ta_id, 
	semester_id, 
	Mahasiswa.nama AS "Mahasiswa", 
	Dosen1.nama AS "Dosen Pembimbing Utama", 
	Dosen2.nama "Dosen Pembimbing Pendamping", 
	topic
FROM 
	TA 
	LEFT JOIN Mahasiswa
	ON TA.npm = Mahasiswa.npm
	LEFT JOIN Dosen as Dosen1
	ON TA.nik_pembimbing1 = Dosen1.nik
	LEFT JOIN Dosen as Dosen2
	ON TA.nik_pembimbing2 = Dosen2.nik;
-- ubah disini: tambah WHERE (kondisi)

-- Query General Sidang
SELECT
	Page_TA.topic AS "Topic TA",
	tanggal AS "Tanggal",
	waktu AS "Waktu",
	tempat AS "Tempat",
	Page_TA."Mahasiswa",
	Page_TA."Dosen Pembimbing Utama",
	Page_TA."Dosen Pembimbing Pendamping",
	Dosen1.nama AS "Dosen Ketua Penguji",
	Dosen2.nama AS "Dosen Anggota Penguji"
FROM
	Sidang
	LEFT JOIN (
		SELECT 
			ta_id, 
			semester_id, 
			Mahasiswa.nama AS "Mahasiswa", 
			Dosen1.nama AS "Dosen Pembimbing Utama", 
			Dosen2.nama "Dosen Pembimbing Pendamping", 
			topic
		FROM 
			TA 
			LEFT JOIN Mahasiswa
			ON TA.npm = Mahasiswa.npm
			LEFT JOIN Dosen as Dosen1
			ON TA.nik_pembimbing1 = Dosen1.nik
			LEFT JOIN Dosen as Dosen2
			ON TA.nik_pembimbing2 = Dosen2.nik
	)AS Page_TA
	ON Sidang.ta_id = Page_TA.ta_id
	LEFT JOIN Dosen as Dosen1
	ON Sidang.nik_penguji1 = Dosen1.nik
	LEFT JOIN Dosen as Dosen2
	ON Sidang.nik_penguji2 = Dosen2.nik
WHERE
	-- ubah disini
	Sidang.semester_id = Page_TA.semester_id;
	-- tambah di sini kalo ingin mengambil Page_TA spesifik
	-- Sidang.semester_id = *semester yang diinginkan -> filter per semester
	-- Page_TA.npm = *npm mahasiswa yang diinginkan -> filter per mahasiswa
	-- Page_TA.nik_pembimbing1/pembimbing2 -> filter per pendamping


-- fungsi show nilai per sidang
SELECT 
	ta_topic.topic,
	ta_topic.npm,
	Roles.role_name,
	Bobot.komponen,
	Nilai_Sidang.nilai,
	Bobot.persentase,
	Nilai_Sidang.nilai_final
	
FROM
	Nilai_Sidang
	INNER JOIN Sidang
	ON Nilai_sidang.sidang_id = Sidang.sidang_id
	INNER JOIN Bobot
	ON Nilai_sidang.bobot_id = Bobot.bobot_id
	INNER JOIN(
		SELECT
			ta_id,
			npm,
			topic
		FROM
			TA
	) AS ta_topic
	ON Sidang.ta_id = ta_topic.ta_id
	INNER JOIN Roles
	ON Bobot.role_id = Roles.role_id;
