DROP TABLE IF EXISTS Catatan;
DROP TABLE IF EXISTS Nilai_Sidang;
DROP TABLE IF EXISTS Sidang;
DROP TABLE IF EXISTS TA;
DROP TABLE IF EXISTS Bobot;
DROP TABLE IF EXISTS Role_Dosen;
DROP TABLE IF EXISTS Semester;
DROP TYPE IF EXISTS PERIODE;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Dosen;
DROP TABLE IF EXISTS Mahasiswa;

CREATE TYPE PERIODE AS ENUM ('ganjil', 'genap');

CREATE TABLE Mahasiswa(
	npm VARCHAR(15) UNIQUE NOT NULL PRIMARY KEY,
	nama VARCHAR (60) NOT NULL,
	email VARCHAR (30) UNIQUE NOT NULL,
	password VARCHAR (255) NOT NULL
);

CREATE TABLE Dosen(
	nik VARCHAR(15) UNIQUE NOT NULL PRIMARY KEY,
	nama VARCHAR (60) NOT NULL,
	email VARCHAR (30) UNIQUE NOT NULL,
	password VARCHAR (255) NOT NULL
);

CREATE TABLE Roles(
	role_id SERIAL PRIMARY KEY,
	role_name VARCHAR(15) NOT NULL UNIQUE
);

INSERT INTO Roles (role_name) VALUES
	('Koordinator'),
	('Penguji'),
	('Pembimbing');

CREATE TABLE Semester(
	semester_id SERIAL PRIMARY KEY,
	tahun_ajaran VARCHAR(9) NOT NULL,
	periode PERIODE NOT NULL
);

CREATE TABLE Role_Dosen(
	semester_id INT NOT NULL,
	role_id INT NOT NULL,
	nik VARCHAR(15) NOT NULL,
	PRIMARY KEY (semester_id, role_id, nik),
	FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
	FOREIGN KEY (role_id) REFERENCES Roles(role_id),
	FOREIGN KEY (nik) REFERENCES Dosen(nik)
);

CREATE OR REPLACE FUNCTION auto_increment_komponen_id()
RETURNS TRIGGER AS $$
DECLARE
    max_komponen_id INTEGER;
BEGIN
    -- Find the max komponen_id for the given semester_id and role_id
    SELECT COALESCE(MAX(komponen_id), 0) + 1 INTO max_komponen_id
    FROM 
		Bobot
    WHERE 
		semester_id = NEW.semester_id AND
		role_id = NEW.role_id;

    -- Assign the next komponen_id for this semester_id
    NEW.komponen_id := max_komponen_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE Bobot(
	bobot_id SERIAL PRIMARY KEY,
	semester_id INT NOT NULL,
	role_id INT NOT NULL,                                                                                                                                                                                                                                                                                                                                        
	komponen_id INTEGER NOT NULL,
	komponen VARCHAR(120) NOT NULL,
	persentase NUMERIC(5, 2),
	FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
	FOREIGN KEY (role_id) REFERENCES Roles(role_id)
);

CREATE TRIGGER bobot_koor_trigger
BEFORE INSERT ON Bobot
FOR EACH ROW
EXECUTE FUNCTION auto_increment_komponen_id();

CREATE TABLE TA(
	ta_id SERIAL PRIMARY KEY,
	semester_id INT NOT NULL,
	npm VARCHAR(15) NOT NULL,
	nik_pembimbing1 VARCHAR(15) NOT NULL,
	nik_pembimbing2 VARCHAR(15), --nullable
	topic VARCHAR(252) NOT NULL,
	FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
	FOREIGN KEY (nik_pembimbing1) REFERENCES Dosen(nik),
	FOREIGN KEY (nik_pembimbing2) REFERENCES Dosen(nik)
);

CREATE TABLE Sidang(
	sidang_id SERIAL PRIMARY KEY,
	semester_id INT NOT NULL,
	ta_id INT NOT NULL,
	nik_penguji1 VARCHAR(15) NOT NULL,
	nik_penguji2 VARCHAR(15) NOT NULL,
	tanggal DATE NOT NULL,
	waktu TIME NOT NULL,
	tempat VARCHAR(100) NOT NULL,
	FOREIGN KEY (semester_id) REFERENCES Semester(semester_id),
	FOREIGN KEY (ta_id) REFERENCES TA(ta_id),
	FOREIGN KEY (nik_penguji1) REFERENCES Dosen(nik),
	FOREIGN KEY (nik_penguji2) REFERENCES Dosen(nik)
);

CREATE OR REPLACE FUNCTION calculate_nilai_final()
RETURNS TRIGGER AS $$
BEGIN
    -- Update nilai_final after insert or update
    UPDATE 
		Nilai_Sidang
    SET 
		nilai_final = NEW.nilai * 
			(SELECT persentase 
			FROM Bobot 
			WHERE 
				bobot_id = NEW.bobot_id) / 100
    WHERE 
		sidang_id = NEW.sidang_id AND 
		bobot_id = NEW.bobot_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE Nilai_Sidang(
	sidang_id INT NOT NULL,
	bobot_id INT NOT NULL,
	nik VARCHAR(15) NOT NULL,
	nilai INT NOT NULL,
	nilai_final INT,
	FOREIGN KEY (sidang_id) REFERENCES Sidang(sidang_id),
	FOREIGN KEY (bobot_id) REFERENCES Bobot(bobot_id),
	FOREIGN KEY (nik) REFERENCES Dosen(nik)
);

CREATE TRIGGER trigger_calculate_nilai_final
AFTER INSERT ON Nilai_Sidang
FOR EACH ROW
EXECUTE FUNCTION calculate_nilai_final();


CREATE TABLE Catatan(
	sidang_id INT NOT NULL,
	nik VARCHAR(15) NOT NULL,
	catatan TEXT,
	PRIMARY KEY (sidang_id, nik),
	FOREIGN KEY (sidang_id) REFERENCES Sidang(sidang_id),
	FOREIGN KEY (nik) REFERENCES Dosen(nik)
);




