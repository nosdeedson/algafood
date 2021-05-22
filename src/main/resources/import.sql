/*inseri cozinha*/
insert into cozinha (id, nome) values(1, 'tailandesa');
insert into cozinha (id, nome) values(2, 'mexicana');

/*inseri fomas de pagamento*/
insert into forma_pagamento(id, descricao) values(1, 'dinheiro');
insert into forma_pagamento(id, descricao) values(2, 'cartao de debito');
insert into forma_pagamento(id, descricao) values(3, 'cartao de credito');

/*inseri restaurante*/
insert into restaurante(id, aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id) values(1, 1, 1, '2021-01-01 00:00:00', '2018-02-23T08:30', 'Tailandes', 10.5, 1);

insert into restaurante(id, aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id) values(2, 1, 1, '2018-02-23T08:30', '2018-02-23T08:30', 'Tailandes Gourmet', 15.5, 1);

insert into restaurante(id, aberto, ativo, data_atualizacao, data_cadastro, nome, taxa_frete, cozinha_id) values(3, 1, 1, '2018-02-23T08:30', '2018-02-23T08:30', 'Mexicana', 15.5, 2);

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
insert into permissao (nome, descricao) values('editar', 'Permite editar objetos');

/* inseri estado*/
insert into estado (id, nome) values(1, 'Minas Gerais');

/*inseri cidade*/
insert into cidade (nome, estado_id) values('Itajuba', 1);



