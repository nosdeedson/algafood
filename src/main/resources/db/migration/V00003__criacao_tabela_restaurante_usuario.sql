create table restaurante_usuario(
	restaurante_id bigserial not null,
    usuario_id bigint not null,
	primary key (restaurante_id, usuario_id)
);

alter table restaurante_usuario
add constraint fk_restaurante_id
foreign key (restaurante_id) references restaurante(id);


alter table restaurante_usuario add constraint fk_usuario_id
foreign key (usuario_id) references usuario(id);
