create table cozinha(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    primary key(id)
) engine=InnoDB default charset=UTF8MB4;

create table estado(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table cidade(
	id bigint not null auto_increment,
    nome varchar(60) not null,
    estado_id bigint not null,
    primary key(id),
    key fk_estado_id (estado_id),
    constraint fk_estado_id foreign key (estado_id)
    references estado (id)
) engine=InnoDB default charset=UTF8MB4 ;

create table forma_pagamento(
	id bigint not null auto_increment,
    descricao varchar(100) not null,
    primary key(id)
)engine=InnoDB charset=utf8mb4;

create table grupo(
	id bigint not null auto_increment,
    nome varchar(100) not null,
    primary key(id)
)engine=InnoDB charset=utf8mb4;

create table permissao(
	id bigint not null auto_increment,
    descricao varchar(255) not null,
	nome varchar(100) not null,
	primary key(id)
)engine=InnoDb default charset=utf8mb4;

create table grupo_permissao(
	grupo_id bigint not null,
    permissao_id bigint not null,
    key fk_permissao_id (permissao_id),
    key fk_grupo_id (grupo_id),
    constraint fk_permissao_id 
    foreign key (permissao_id) 
    references permissao(id),
    constraint fk_grupo_id
    foreign key(grupo_id)
    references grupo(id)
)engine=InnoDB default charset=utf8mb4;

CREATE TABLE restaurante (
  id bigint NOT NULL AUTO_INCREMENT,
  aberto bit(1) DEFAULT NULL,
  ativo bit(1) not NULL,
  data_atualizacao datetime NOT NULL,
  data_cadastro datetime NOT NULL,
  endereco_bairro varchar(255) DEFAULT NULL,
  endereco_cep varchar(255) DEFAULT NULL,
  endereco_complemento varchar(255) DEFAULT NULL,
  endereco_numero varchar(255) DEFAULT NULL,
  endereco_rua varchar(255) DEFAULT NULL,
  nome varchar(255) not NULL,
  taxa_frete decimal(10,2) DEFAULT NULL,
  cozinha_id bigint NOT NULL,
  endereco_cidade_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_cozinha_id (cozinha_id),
  KEY fk_endereco_cidade_id (endereco_cidade_id),
  CONSTRAINT fk_cozinha_id FOREIGN KEY (cozinha_id) REFERENCES cozinha (id),
  CONSTRAINT fk_endereco_cidade_id FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


create table produto(
	id bigint not null auto_increment,
    ativo bit(1) not null,
    descricao varchar(150) default null,
    nome varchar(80) not null,
    preco decimal(10,2) not null,
    restaurante_id bigint not null,
    primary key(id),
    key fk_restaurante_id (restaurante_id),
    constraint fk_restaurante_id 
    foreign key (restaurante_id)
    references restaurante(id)
);


create table restaurante_forma_pagamento(
	restaurante_id bigint not null,
    forma_pagamento_id bigint not null,
    key fk_restaurante_id (restaurante_id),
    key fk_forma_pagamento_id (forma_pagamento_id),
    constraint fk_restaurante_forma_pagamento_id 
    foreign key(restaurante_id)
    references restaurante (id),
    constraint fk_forma_pagamento_restaurante_id
    foreign key (forma_pagamento_id)
    references forma_pagamento(id)
)engine=InnoDB default charset=utf8mb4;

create table usuario(
	id bigint not null auto_increment,
    data_cadastro datetime(6) not null,
    email varchar(150) not null,
    nome varchar(100) not null,
    senha varchar(30) not null,
    primary key(id)
)engine=InnoDB default charset=utf8mb4;

create table usuario_grupo(
	usuario_id bigint not null,
    grupo_id bigint not null,
    key fk_grupo_usuario (grupo_id),
    key fk_usuario_grupo (usuario_id),
    constraint fk_grupo_usuario
    foreign key (grupo_id)
    references grupo(id),
    constraint fk_usuario_grupo
    foreign key (usuario_id)
    references usuario(id)
)engine=InnoDB default charset=utf8mb4;


















