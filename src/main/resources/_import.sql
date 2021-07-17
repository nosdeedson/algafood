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
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 3) ON CONFLICT DO NOTHING;
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


/** inseri pedido */
insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, sub_total, taxa_frete, valor_total)
values ('a34f4d68-cc56-449b-9636-7870f4585d2a', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', (now() at time zone 'utc'), 298.90, 10, 308.90) ON CONFLICT DO NOTHING;

insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	status, data_criacao, sub_total, taxa_frete, valor_total)
values ('1789c9aa-0320-437f-8012-79184519b784', 1, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', (now() at time zone 'utc'), 79, 0, 79);

/* inseri item pedido */
insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 78.9, 78.9, 'teste');

insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 2, 1, 79, 79, 'Ao ponto');


