-- temporary mockup for testing -- 
INSERT INTO Dosen (nik, nama, email, password) 
VALUES
    ('D001', 'Dr. Alice', 'alice@example.com', 'hashed_password1'),
    ('D002', 'Dr. Bob', 'bob@example.com', 'hashed_password2'),
    ('D003', 'Dr. Carol', 'carol@example.com', 'hashed_password3'),
    ('D004', 'Dr. David', 'david@example.com', 'hashed_password4'),
    ('D005', 'Dr. Eve', 'eve@example.com', 'hashed_password5'),
    ('D006', 'Dr. Frank', 'frank@example.com', 'hashed_password6');
INSERT INTO Semester (tahun_ajaran, periode) 
VALUES
    ('2023/2024', 'ganjil'),
    ('2023/2024', 'genap'),
    ('2022/2023', 'ganjil'),
    ('2022/2023', 'genap'),
    ('2021/2022', 'ganjil'),
    ('2021/2022', 'genap');
INSERT INTO Bobot (semester_id, role_id, komponen, persentase)
VALUES
    (1, 1, 'Assignment', 30.00),
    (1, 1, 'Midterm', 30.00),
    (2, 1, 'Final Exam', 40.00),
	(1, 1, 'Final Exam', 40.00);
INSERT INTO Mahasiswa (npm, nama, email, password)
VALUES 
	('19010001', 'Ahmad Fauzi', 'ahmad.fauzi@example.com', 'hashed_password1'),
	('19010002', 'Siti Nurhaliza', 'siti.nurhaliza@example.com', 'hashed_password2'),
	('19010003', 'Budi Santoso', 'budi.santoso@example.com', 'hashed_password3'),
	('19010004', 'Rina Hartati', 'rina.hartati@example.com', 'hashed_password4'),
	('19010005', 'Dian Permata', 'dian.permata@example.com', 'hashed_password5');
INSERT INTO TA (semester_id, npm, nik_pembimbing1, nik_pembimbing2, topic)
VALUES
	(1, '19010001', 'D001', 'D002', 'Fenwick Tree'),
	(1, '19010002', 'D003', NULL, 'Genetic Algorithm'),
	(1, '19010003', 'D003', NULL, 'Back-tracking and Forward-checking');
INSERT INTO Sidang (semester_id, ta_id, nik_penguji1, nik_penguji2, tanggal, waktu, tempat)
VALUES
	(1, 1, 'D004', 'D005', '2024-1-15', '10:00:00', 'Ruang 101'),
	(1, 2, 'D005', 'D006', '2024-1-16', '8:00:00', 'Ruang 103'),
	(1, 3, 'D004', 'D006', '2024-1-16', '10:00:00', 'Ruang 103');
-- Sidang ID 1 (for student '19010001')
INSERT INTO Nilai_Sidang (sidang_id, bobot_id, nilai)
VALUES
    (1, 1, 85),  -- Assignment: 85
    (1, 2, 90),  -- Midterm: 90
    (1, 3, 80);  -- Final Exam: 80

-- Sidang ID 2 (for student '19010002')
INSERT INTO Nilai_Sidang (sidang_id, bobot_id, nilai)
VALUES
    (2, 1, 88),  -- Assignment: 88
    (2, 2, 92),  -- Midterm: 92
    (2, 3, 85);  -- Final Exam: 85

-- Sidang ID 3 (for student '19010003')
INSERT INTO Nilai_Sidang (sidang_id, bobot_id, nilai)
VALUES
    (3, 1, 78),  -- Assignment: 78
    (3, 2, 84),  -- Midterm: 84
    (3, 3, 75);  -- Final Exam: 75