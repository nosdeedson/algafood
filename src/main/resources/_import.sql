/*inseri cozinha*/
insert into cozinha (nome) values('tailandesa') ON CONFLICT DO NOTHING;
insert into cozinha (nome) values('mexicana') ON CONFLICT DO NOTHING;
insert into cozinha (nome) values('italiana') ON CONFLICT DO NOTHING;
insert into cozinha (nome) values('mineira') ON CONFLICT DO NOTHING;

/*inseri fomas de pagamento*/
insert into forma_pagamento(descricao,data_atualizacao) values('dinheiro', (now() at time zone 'utc')) ON CONFLICT DO NOTHING;
insert into forma_pagamento(descricao, data_atualizacao) values('cartao de debito', (now() at time zone 'utc')) ON CONFLICT DO NOTHING;
insert into forma_pagamento(descricao, data_atualizacao) values('cartao de credito', (now() at time zone 'utc')) ON CONFLICT DO NOTHING;

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
values((now() at time zone 'utc'), 'josemariazumira+joseGER@gmail.com', 'Jose Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+lucianeiaVND@gmail.com', 'Lucineia Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;


insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+EdsonAUX@gmail.com', 'Edson Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;


insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+joaoCAD@gmail.com', 'joao Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;


insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+isaLOJA@gmail.com', 'isabela Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+terezaCLI@gmail.com', 'tereza Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;

insert into usuario (data_cadastro, email, nome, senha) 
values((now() at time zone 'utc'), 'josemariazumira+silvanaCLI@gmail.com', 'silvana Souza', '$2a$10$V.lGMurz93DI/c.3ijf/2Oilfe.BRqMtaMWBHppEYn22cEM0eaQT6') ON CONFLICT DO NOTHING;



/* inseri grupos */
insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

/* inseri permissao*/
insert into permissao (nome, descricao) values('EDITAR_COZINHAS', 'Permite editar cozinha') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_FORMAS_PAGAMENTO', 'Permite editar formas de pagamento') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_CIDADES', 'Permite editar objetos') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_ESTADOS', 'Permite editar estados') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuarios') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite editar usuatios') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('EDITAR_RESTAURANTES', 'Permite editar restaurantes') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('CONSULTAR_PEDIDOS', 'Permite consultar pedidos') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos') ON CONFLICT DO NOTHING;
insert into permissao (nome, descricao) values('GERAR_RELATORIOS', 'Permite gerar relatórios') ON CONFLICT DO NOTHING;
/*insert into permissao (nome, descricao) values('EDITAR_GRUPOS', 'Permite editar grupos') ON CONFLICT DO NOTHING;*/


/*inseri grupo_permissao */

/*adiciona todas as permissões no grupo gerente*/
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao ON CONFLICT DO NOTHING;

/*adiciona todas as permissões no grupo vendedor*/
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

/*adiciona permissao de editar restauranet para o vendedor*/
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like '%_RESTAURANTES';

/* adiciona permissao a secretararia */
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

/* ADICIONA permissao ao cadastrador */
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like '%_RESTAURANTES';


/* inseri usuario a grupos */
insert into usuario_grupo(usuario_id, grupo_id) values(1, 1) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(1, 2) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(2, 2) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(3, 3) ON CONFLICT DO NOTHING;

insert into usuario_grupo(usuario_id, grupo_id) values(4, 4) ON CONFLICT DO NOTHING;

/* inseri relação entre restaurante e usuario
insert into restaurante_usuario(restaurante_id, usuario_id)
values(1, 1) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(2, 1) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(2, 2) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(1, 5) ON CONFLICT DO NOTHING;

insert into restaurante_usuario(restaurante_id, usuario_id)
values(3, 5) ON CONFLICT DO NOTHING;*/

insert into restaurante_usuario (restaurante_id, usuario_id) values (1, 5), (3, 5);


/** inseri pedido */
insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, sub_total, taxa_frete, valor_total)
values ('a34f4d68-cc56-449b-9636-7870f4585d2a', 1, 5, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil',
'CRIADO', (now() at time zone 'utc'), 298.90, 10, 308.90) ON CONFLICT DO NOTHING;

insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	status, data_criacao, sub_total, taxa_frete, valor_total)
values ('1789c9aa-0320-437f-8012-79184519b784', 1, 6, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', (now() at time zone 'utc'), 79, 0, 79);

insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	status, data_criacao, sub_total, taxa_frete, valor_total)
values ('5ae7facd-27d0-43b2-bf4d-a70a3515321f', 2, 7, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', (now() at time zone 'utc'), 79, 0, 79);

insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	status, data_criacao, sub_total, taxa_frete, valor_total)
values ('9f10c133-fef4-4aee-aed2-8ffc4e3a44b8', 2, 7, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', (now() at time zone 'utc'), 79, 0, 79);


insert into pedido (codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
	endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
	status, data_criacao, sub_total, taxa_frete, valor_total)
values ('beabbb87-0c28-4bb7-aea0-3d4d7796c2b9', 2, 6, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
'CRIADO', (now() at time zone 'utc'), 79, 0, 79);




/* inseri item pedido */
insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 78.9, 78.9, 'teste');

insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 2, 1, 79, 79, 'Ao ponto');

insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 2, 1, 79, 79, 'Ao ponto');

insert into item_pedido (pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 2, 1, 79, 79, 'Ao ponto');

