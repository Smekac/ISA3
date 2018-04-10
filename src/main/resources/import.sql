-- Kada se stavi ime import.sql radi !!!!
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('ADMINFAN', 'Novi Sad', 'adminfan@adminfan', 'Ivan', '0635569989', 'adminfan', 'Perkovic', 'adminfan');
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('ADMINFAN', 'Zvornik', 'radestojkic@gmail.com', 'Radisa', '064941267', 'rasko', 'Stojkic', 'raskica');
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('ADMINSISTEMA', 'Novi Sad', 'adminsys@adminsys', 'Vladimir', '063429989', 'adminsys', 'Krnic', 'adminsys');
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('ADMINUSTANOVE','Novi Sad', 'adminshow@adminshow', 'Marko', '0635565239', 'adminsys', 'Lazic', 'adminshow');
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('REGPOSETILAC','Beograd', 'dejan@dejan', 'Dejan', '0615565239', 'dejan', 'Stojkic', 'dejan');
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username) VALUES ('REGPOSETILAC','Novi Sad', 'milan@milan', 'Milan', '0645565239', 'milan', 'Stankovic', 'milan');

INSERT INTO ustanova (id, name, type) VALUES (1, 'Arena Cineplex', 'BIOSKOP');
INSERT INTO ustanova (id, name, type) VALUES (2, 'Teatar 212', 'POZORISTE');

INSERT INTO novi_rekvizit (id, datum_kreiranja, naslov,opis, cena, admin_fan_id, registrovani_korisnik_id,ustanova_id) VALUES (1, '2018-01-29 00:55:56', 'Mac', 'Mac sa logom Mocnih rendzera', 500, 1, 5,1);
INSERT INTO novi_rekvizit (id, datum_kreiranja, naslov,opis, cena, admin_fan_id, registrovani_korisnik_id,ustanova_id) VALUES (2, '2018-01-24 03:55:56', 'Majica', 'Majica sa posterom Hari Potera', 150, 1, 5,1);

INSERT INTO korisceni_rekvizit (id, datum_kreiranja, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id) VALUES (1, '2018-01-29 02:32:40', 'Kacket', 'Licni kacket od Stivena Spilberga prilikom snimanja Avatar filma jednog od najvecih ostvarenja', '2018-01-29 02:32:45',0, 'NACEKANJU', 5);
INSERT INTO korisceni_rekvizit (id, datum_kreiranja, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id) VALUES (2, '2018-01-27 02:32:40', 'Sesir', 'Sesir glavnog junaka iz filma Pirati sa Kariba', '2018-02-15 02:32:45',0, 'NACEKANJU', 4);

INSERT INTO bid (accepted, date_created, cena, korisceni_rekvizit_id, registrovani_korisnik_id) VALUES (0, '2018-01-29 03:25:24', 250, 1, 5);
INSERT INTO bid (accepted, date_created, cena, korisceni_rekvizit_id, registrovani_korisnik_id) VALUES (1, '2018-02-01 17:25:24', 260, 1, 4);