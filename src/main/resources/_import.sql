/*inseri cozinha*/
insert into cozinha (nome) values('tailandesa') ON CONFLICT DO NOTHING;
insert into cozinha (nome) values('mexicana') ON CONFLICT DO NOTHING;


/*inseri fomas de pagamento*/
insert into forma_pagamento(descricao) values('dinheiro') ON CONFLICT DO NOTHING;
insert into forma_pagamento(descricao) values('cartao de debito') ON CONFLICT DO NOTHING;
insert into forma_pagamento(descricao) values('cartao de credito') ON CONFLICT DO NOTHING;

/*inseri restaurante*/
insert into restaurante(aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id) 
values(true, true, (now() at time zone 'utc'), (now() at time zone 'utc'), 'Tailandes', 10.5, 1) ON CONFLICT DO NOTHING;

insert into restaurante(aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id)
values(true, true, (now() at time zone 'utc'), (now() at time zone 'utc'), 'Tailandes Gourmet', 15.5, 1) ON CONFLICT DO NOTHING;

insert into restaurante(aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id)
values(true, true, (now() at time zone 'utc'), (now() at time zone 'utc'), 'Mexicana', 15.5, 2) ON CONFLICT DO NOTHING;

insert into produto(ativo, descricao, nome, preco, restaurante_id)
values(true, 'arroz integral', 'arroz', 26.8, 1) ON CONFLICT DO NOTHING;
insert into produto(ativo, descricao, nome, preco, restaurante_id) 
values(true, 'feijão preto', 'feijão', 6.8, 1) ON CONFLICT DO NOTHING;

/*inseri forma de pagamento restaurante*/
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 1) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 2) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 3) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 1) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 2) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 3)ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 1) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 2) ON CONFLICT DO NOTHING;
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 3) ON CONFLICT DO NOTHING;

/* inseri estado*/
insert into estado (nome) values('Minas Gerais') ON CONFLICT DO NOTHING;

/*inseri cidade*/
insert into cidade (nome, estado_id) values('Itajuba', 1) ON CONFLICT DO NOTHING;

/* inseri usuario */
insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'lu@gmail.com', 'Lucineia Souza', '1234') ON CONFLICT DO NOTHING;;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'jose@gmail.com', 'Jose Souza', '1234') ON CONFLICT DO NOTHING;;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'Edson@gmail.com', 'Edson Souza', '1234') ON CONFLICT DO NOTHING;;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'joao@gmail.com', 'joao Souza', '1234') ON CONFLICT DO NOTHING;

/* inseri grupos */
insert into grupo (nome) values ('gerente'), ('vendedor'), ('secretária'), ('cadastrador');

/* inseri permissao*/
insert into permissao (nome, descricao) values('editar', 'Permite editar objetos') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_COZINHA', 'Permite editar cozinha') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('CONSULTAR_COZINHA', 'Permite editar cozinha') ON CONFLICT DO NOTHING;


/*inseri grupo_permissao */
insert into grupo_permissao(grupo_id, permissao_id) values(1, 1) ON CONFLICT DO NOTHING;
insert into grupo_permissao(grupo_id, permissao_id) values(1, 2) ON CONFLICT DO NOTHING;
insert into grupo_permissao(grupo_id, permissao_id) values(1, 3) ON CONFLICT DO NOTHING;
insert into grupo_permissao(grupo_id, permissao_id) values(2, 3) ON CONFLICT DO NOTHING;


/* inseri usuario a grupos */
insert into usuario_grupo(usuario_id, grupo_id) values(1, 1) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(1, 2) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(1, 3) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(2, 2) ON CONFLICT DO NOTHING;

/* inseri relação entre restaurante e usuario*/
insert into restaurante_usuario(restaurante_id, usuario_id)
values(1, 1) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(2, 1) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(2, 2) ON CONFLICT DO NOTHING;


