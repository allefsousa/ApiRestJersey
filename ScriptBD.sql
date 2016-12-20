create database notes_db;
use notes_db;
create table tb_nota(
id_note int not null primary key auto_increment,
titulo varchar(200),
descricao varchar(200)
);