-- noinspection SqlNoDataSourceInspectionForFile

-- Kada se stavi ime import.sql radi !!!!
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, bodovi) VALUES ('ADMINFAN', 'Novi Sad', 'adminfan@adminfan', 'Ivan', '0635569989', 'adminfan', 'Perkovic', 'adminfan',0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, bodovi) VALUES ('ADMINFAN', 'Zvornik', 'radestojkic@gmail.com', 'Radisa', '064941267', 'rasko', 'Stojkic', 'raskica',0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, bodovi) VALUES ('ADMINSISTEMA', 'Novi Sad', 'adminsys@adminsys', 'Ilija', '063429989', 'adminsys', 'Grahovac', 'adminsys',0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, bodovi) VALUES ('ADMINUSTANOVE','Novi Sad', 'adminshow@adminshow', 'Marko', '0635565239', 'adminsys', 'Lazic', 'adminshow',0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, accepted,bodovi) VALUES ('REGPOSETILAC','Beograd', 'dejan@dejan', 'Dejan', '0615565239', 'dejan', 'Stojkic', 'dejan', 1,0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, accepted, bodovi) VALUES ('REGPOSETILAC','Novi Sad', 'milan@milan', 'Milan', '0645565239', 'milan', 'Stankovic', 'milan', 1,0);
INSERT INTO korisnik (type, grad, email, ime, number, password, prezime, username, accepted, bodovi) VALUES ('REGPOSETILAC','Novi Sad', 'mironikos94@gmail.com', 'Dejan', '0645565239', 'sifra12', 'Stojko', 'Stojko', 1,0);

INSERT INTO ustanova (id, name, type, addres, description, rating) VALUES (1, 'Arena Cineplex', 'BIOSKOP', 'Novi Sad Centar' , 'Opis neki bezvezeke',4 );
INSERT INTO ustanova (id, name, type,addres, description, rating) VALUES (2, 'Teatar 212', 'POZORISTE','Zvornicankaa', 'patrolaa nazivOpisne predstavewe',3 );

INSERT INTO novi_rekvizit (id, datum_kreiranja, image, naslov,opis, cena, admin_fan_id, registrovani_korisnik_id,ustanova_id,verzija) VALUES (1, '2018-01-29 00:55:56','http://www.coldsteel.com/media/catalog/product/cache/1/thumbnail/960x/17f82f742ffe127f42dca9de82fb58b1/8/8/88cs.jpg' , 'Mac', 'Mac sa logom Mocnih rendzera', 500, 1,NULL ,1,0);
INSERT INTO novi_rekvizit (id, datum_kreiranja, image, naslov,opis, cena, admin_fan_id, registrovani_korisnik_id,ustanova_id,verzija) VALUES (2, '2018-01-24 03:55:56','https://cdn.shopify.com/s/files/1/0221/1146/products/Hogwarts_black_gold_thumb.png?v=1511187218', 'Majica', 'Majica sa posterom Hari Potera', 150, 1,NULL ,1,0);

INSERT INTO korisceni_rekvizit (id, datum_kreiranja,image, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id,admin_fan_id, verzija) VALUES (1, '2018-01-29 02:32:40', 'https://games.rs/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/k/a/kacket-maonsteel.jpg','Kacket', 'Licni kacket od Stivena Spilberga prilikom snimanja Supermen filma jednog od najvecih ostvarenja', '2018-01-29 02:32:45',NULL , 'PRIHVACEN', 5,1,0);
INSERT INTO korisceni_rekvizit (id, datum_kreiranja,image, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id,admin_fan_id,verzija) VALUES (2, '2018-01-27 02:32:40', 'http://www.costumes.rs/wp-content/uploads/store/products/images/135701999083594.jpg','Sesir', 'Sesir glavnog junaka iz filma Pirati sa Kariba', '2019-02-15 02:32:45',NULL , 'PRIHVACEN', 5,1,0 );
INSERT INTO korisceni_rekvizit (id, datum_kreiranja, image, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id,admin_fan_id,verzija) VALUES (3, '2018-01-27 01:32:40','http://www.mozzartsport.com/upload/OldMozzartImages/img/news/f/5/5/14766131048363.jpg' ,'Dres', 'Dres licno potpisan od Ramba Petkovica', '2019-02-15 02:32:45',NULL , 'PRIHVACEN', 6,1,0 );
INSERT INTO korisceni_rekvizit (id, datum_kreiranja, image, naslov,opis, active_until,accepted_bid, status, registrovani_korisnik_id,admin_fan_id,verzija) VALUES (4, '2018-01-27 01:32:40','https://goo.gl/TJ9gBe' ,'Dres', 'Premijera filma fudbalskog maga Maradonee ', '2019-02-15 02:32:45',NULL , 'NACEKANJU', 6,NULL );

INSERT INTO bid (accepted, date_created, price, korisceni_rekvizit_id, registrovani_korisnik_id) VALUES (0, '2018-01-29 03:25:24', 250, 1, 5);
INSERT INTO bid (accepted, date_created, price, korisceni_rekvizit_id, registrovani_korisnik_id) VALUES (0, '2018-02-01 17:25:24', 260, 1, 6);
INSERT INTO bid (accepted, price, date_created,korisceni_rekvizit_id, registrovani_korisnik_id) VALUES (0, 456, '2018-03-30 22:09:05', 2, 6);


INSERT INTO sala(id,naziv,ustanova) VALUES (1,'SALA 1', 1);

INSERT INTO projekcija(id, average_score,description,director, genre,image_url,name, trajanje, ustanova) VALUES(1,4, 'Jako lijepa prica', NULL ,NULL ,NULL ,'Carobnjak iz oza',125,1);

INSERT  INTO datumi_projekcije( id, datum, termin, cijena, projekcija) VALUES (1, '2018-01-29', '03:25:24', 350,1);
INSERT  INTO datumi_projekcije( id, datum, termin, cijena,projekcija) VALUES (2, '2018-01-29','05:50:24', 400,1);

INSERT INTO sale_datumiprojekcije(datumprojekcije_id, sale_id) VALUES (1,1);
INSERT INTO sale_datumiprojekcije(datumprojekcije_id, sale_id) VALUES (2,1);

INSERT INTO segment (id,tip_segmenta,sala,broj_redova, broj_sjedista) VALUES (1,'UPSTAGE',1,1,1);
INSERT INTO sjediste(id,pozicija, red, segment) VALUES (1,1,1,1);