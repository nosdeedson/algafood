create table cozinha(
	id bigserial not null,
    nome varchar(60) not null,
    primary key(id)
);

create table estado(
	id bigserial not null,
    nome varchar(60) not null,
    primary key (id)
);

create table cidade(
	id bigserial not null,
    nome varchar(60) not null,
    estado_id bigint not null,
    primary key(id)
);

alter table cidade add constraint fk_estado_id
foreign key(estado_id) references estado(id);

create table forma_pagamento(
	id bigserial not null,
    descricao varchar(100) not null,
    primary key(id)
);

create table grupo(
	id bigserial not null,
    nome varchar(100) not null,
    primary key(id)
);

create table permissao(
	id bigserial not null,
    descricao varchar(255) not null,
	nome varchar(100) not null,
	primary key(id)
);

create table grupo_permissao(
	grupo_id bigserial not null,
    permissao_id bigint not null
);

alter table grupo_permissao 
add constraint fk_permissao_id 
foreign key (permissao_id) references permissao(id);

alter table grupo_permissao
add constraint fk_grupo_id
foreign key (grupo_id) references grupo(id);

CREATE TABLE restaurante (
  id bigserial not null,
  aberto boolean DEFAULT NULL,
  ativo boolean not NULL,
  data_atualizacao timestamp NOT NULL,
  data_cadastro timestamp NOT NULL,
  endereco_bairro varchar(255) DEFAULT NULL,
  endereco_cep varchar(255) DEFAULT NULL,
  endereco_complemento varchar(255) DEFAULT NULL,
  endereco_numero varchar(255) DEFAULT NULL,
  endereco_rua varchar(255) DEFAULT NULL,
  nome varchar(255) not NULL,
  taxa_frete decimal DEFAULT NULL,
  cozinha_id bigint NOT NULL,
  endereco_cidade_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

alter table restaurante
add constraint fk_cozinha_id
foreign key (cozinha_id) references cozinha(id);

alter table restaurante
add constraint fk_endereco_cidade_id foreign key(endereco_cidade_id) references cidade(id);

create table produto(
	id bigserial not null,
    ativo boolean not null,
    descricao varchar(150) default null,
    nome varchar(80) not null,
    preco decimal(10,2) not null,
    restaurante_id bigint not null,
    primary key(id)
);

alter table produto 
add constraint fk_restaurante_id
foreign key (restaurante_id) references restaurante (id);

create table restaurante_forma_pagamento(
	restaurante_id bigint not null,
    forma_pagamento_id bigint not null
);

alter table restaurante_forma_pagamento
add constraint fk_restaurante_id
foreign key(restaurante_id) references restaurante(id);

alter table restaurante_forma_pagamento
add constraint fk_forma_pagamento_restaurante_id
foreign key (forma_pagamento_id) references forma_pagamento(id);

create table usuario(
	id bigserial not null,
    data_cadastro timestamp(6) not null,
    email varchar(150) not null,
    nome varchar(100) not null,
    senha varchar(30) not null,
    primary key(id)
);

create table usuario_grupo(
	usuario_id bigint not null,
    grupo_id bigint not null
);

alter table usuario_grupo
add constraint fk_grupo_usuario_id
foreign key (grupo_id) references grupo(id);

alter table usuario_grupo
add constraint fk_usuario_grupo_id
foreign key (usuario_id) references usuario(id);
