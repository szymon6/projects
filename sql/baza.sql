CREATE TABLE Pasazer (
id  INT  IDENTITY(1,1) PRIMARY KEY,
imie VARCHAR(15) NOT NULL,
nazwisko VARCHAR(20) NOT NULL,
data_urodzenia date NOT NULL,
kraj varchar(30) NOT NULL,
miasto varchar(30) NOT NULL,
ulica varchar(30) NOT NULL,
numer varchar(30) NOT NULL,
);


CREATE TABLE Bagaz (
id  INT  IDENTITY(1,1) PRIMARY KEY,
waga decimal NOT NULL,
szerokosc decimal NOT NULL CHECK(szerokosc>0),
wysokosc decimal NOT NULL CHECK(wysokosc>0),
dlugosc decimal NOT NULL CHECK(dlugosc>0),
id_pasazer INT NOT NULL REFERENCES Pasazer(id) ON UPDATE CASCADE
);




CREATE TABLE Recepcjonista (
id  INT  IDENTITY(1,1) PRIMARY KEY,
imie VARCHAR(15) NOT NULL,
nazwisko VARCHAR(20) NOT NULL,
miasto varchar(30) NOT NULL,
ulica varchar(30) NOT NULL,
numer varchar(30) NOT NULL,
pensja money not null 
);

CREATE TABLE Pilot (
id  INT  IDENTITY(1,1) PRIMARY KEY,
imie VARCHAR(15) NOT NULL,
nazwisko VARCHAR(20) NOT NULL,
stopien Varchar(20) Not null,
data_urodzenia date not null  CHECK(data_urodzenia<getdate()),
data_zatrudnienia date not null  DEFAULT GETDATE(),
pensja money not null

);



CREATE TABLE Stewardessa (
id  INT  IDENTITY(1,1) PRIMARY KEY,
imie VARCHAR(15) NOT NULL,
nazwisko VARCHAR(20) NOT NULL,
data_zatrudnienia date DEFAULT GETDATE(),
pensja money not null
);




CREATE TABLE Linie_lotnicze (
id  INT  IDENTITY(1,1) PRIMARY KEY,
nazwa VARCHAR(50) NOT NULL UNIQUE,
opis VARCHAR(200),
data_powstania date not null CHECK(data_powstania<getdate()),
miasto varchar(30) NOT NULL,
ulica varchar(30) NOT NULL,
numer varchar(30) NOT NULL,
);






CREATE TABLE Producent_Samolotu (
id  INT  IDENTITY(1,1) PRIMARY KEY,
nazwa VARCHAR(50) NOT NULL UNIQUE,
opis VARCHAR(200) ,
data_powstania date not null CHECK(data_powstania<getdate()),
);



CREATE TABLE Model_samolotu (
id  INT  IDENTITY(1,1) PRIMARY KEY,
typ VARCHAR(50) NOT NULL,
model VARCHAR(50) NOT NULL,
dlugosc decimal,
szerokosc decimal,
max_dystans decimal,
id_Producent_Samolotu INT NOT NULL REFERENCES Producent_Samolotu(id) ON UPDATE CASCADE
)





CREATE TABLE Samolot (
id  INT  IDENTITY(1,1) PRIMARY KEY,
nazwa VARCHAR(50) NOT NULL,
data_powstania date not null CHECK(data_powstania<getdate()),
id_Model_samolotu INT NOT NULL REFERENCES  Model_samolotu(id) ON UPDATE CASCADE,
id_Linie_lotnicze INT NOT NULL REFERENCES  Linie_lotnicze(id) ON UPDATE CASCADE
)





CREATE TABLE Zaloga (
id  INT  IDENTITY(1,1) PRIMARY KEY,
nazwa VARCHAR(50) NOT NULL
);


CREATE TABLE Stewardessa_zaloga (
id_Stewardessa INT NOT NULL REFERENCES Stewardessa(id) ON UPDATE CASCADE,
id_Zaloga INT NOT NULL REFERENCES Zaloga(id) ON UPDATE CASCADE
);




CREATE TABLE Pilot_zaloga (
id_Pilot INT NOT NULL REFERENCES Stewardessa(id) ON UPDATE CASCADE,
id_Zaloga INT NOT NULL REFERENCES Zaloga(id) ON UPDATE CASCADE
);



CREATE TABLE Lotnisko (
id  INT  IDENTITY(1,1) PRIMARY KEY,
kraj VARCHAR(50) NOT NULL,
miasto VARCHAR(50) NOT NULL,
nazwa VARCHAR(100) NOT NULL,
);



CREATE TABLE Wylot(
id  INT  IDENTITY(1,1) PRIMARY KEY,
data date NOT NULL,
czas time NOT NULL,
id_Lotnisko INT NOT NULL REFERENCES Lotnisko(id) ON UPDATE CASCADE
);



CREATE TABLE Przylot(
id  INT  IDENTITY(1,1) PRIMARY KEY,
data date NOT NULL,
czas time NOT NULL,
id_Lotnisko INT NOT NULL REFERENCES Lotnisko(id) ON UPDATE CASCADE
);

CREATE TABLE Lot(
id  INT  IDENTITY(1,1) PRIMARY KEY,
id_Zaloga INT NOT NULL REFERENCES Zaloga(id),
id_Wylot INT NOT NULL REFERENCES Wylot(id),
id_Przylot INT NOT NULL REFERENCES Przylot(id),
id_Samolot INT NOT NULL REFERENCES Samolot(id)
);




CREATE TABLE Miejsce(
id  INT  IDENTITY(1,1) PRIMARY KEY,
numer int not null,
klasa varchar(30) not null,
);

CREATE TABLE Bilet(
id  INT  IDENTITY(1,1) PRIMARY KEY,
id_Miejsce INT NOT NULL REFERENCES Miejsce(id) ON UPDATE CASCADE,
id_Lot INT NOT NULL REFERENCES Lot(id) ON UPDATE CASCADE
);



CREATE TABLE ZakupBiletu(
id  INT  IDENTITY(1,1) PRIMARY KEY,
cena money not null, 
id_Bilet INT NOT NULL REFERENCES Bilet(id) ON UPDATE CASCADE,
id_Recepcjonista INT NOT NULL REFERENCES Recepcjonista(id) ON UPDATE CASCADE,
id_Pasazer INT NOT NULL REFERENCES Pasazer(id) ON UPDATE CASCADE
);







