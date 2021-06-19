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
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 1);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 2);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(1, 3);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 1);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 2);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(2, 3);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 1);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 2);
insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values(3, 3);

/* inseri permissao*/
insert into permissao (nome, descricao) values('editar', 'Permite editar objetos') ON CONFLICT DO NOTHING;

/* inseri estado*/
insert into estado (nome) values('Minas Gerais') ON CONFLICT DO NOTHING;

/*inseri cidade*/
insert into cidade (nome, estado_id) values('Itajuba', 1) ON CONFLICT DO NOTHING;
