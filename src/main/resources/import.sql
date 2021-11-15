insert into categoria (nome) values ('BOLOS');
insert into categoria (nome) values ('DOCINHOS');
insert into categoria (nome) values ('TORTAS');
insert into categoria (nome) values ('AGRIDOCES');

insert into massa (descricao, valor) values ('quadrada', 12.50);
insert into massa (descricao, valor) values ('oval', 10.50);
insert into massa (descricao, valor) values ('redonda', 8.0);

insert into recheio (nome, valor) values ('Chocolate branco', 12.50);
insert into recheio (nome, valor) values ('Chocolate preto', 12.50);
insert into recheio (nome, valor) values ('Chocolate branco zero açucar', 25.50);
insert into recheio (nome, valor) values ('Chocolate preto zero açucar', 12.50);
insert into recheio (nome, valor) values ('Prestigio preto', 20.50);

insert into cobertura (nome, valor) values ('Confetes com Chocolate branco', 12.50);
insert into cobertura (nome, valor) values ('Chocolate preto com BIS de chocolate', 12.50);

insert into formato (nome ) values ('Redonda');
insert into formato (nome ) values ('Quadrada');
insert into formato (nome ) values ('Oval');
insert into formato (nome ) values ('Retangular');


insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Bolo de Aniversário','Camisetinha azul claro', 56.0, 15, '/images/home/bolo1.jpg', 2);
insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Bolo Chocolate','Boa pra jogar baseball com os amigos', 49.95, 5, '/images/home/bolo2.jpg', 1);
insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Bolo 100% Cacal','Perfeita para usar no escuro.', 99.99, 4, '/images/home/bolo3.jpg', 1);
insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Bolo Kids Rosa','Kit pra ir nas festas', 199.0, 12, '/images/home/bolo4.jpg', 1);
insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Bolo Velvet','Perfeita para sono leve', 81.12, 20, '/images/home/bolovelvet.jpg', 3);
insert into produto (nome, descricao, valor, estoque, imagem, categoria_id) values ('Doce','Perfeita para sono leve', 81.12, 20, '/images/home/doce1.jpg', 2);


INSERT INTO permissao (nome) values('ROLE_ADMIN');
INSERT INTO permissao (nome) values('ROLE_USER');
INSERT INTO permissao (nome) values('ROLE_CONFEITEIRO');

INSERT INTO usuario(email, username, password) VALUES ('efs@msn.com', 'admin','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
INSERT INTO usuario(email, username, password) VALUES ('efs26@gmail.com', 'teste','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');
INSERT INTO usuario(email, username, password) VALUES ('efs@hotmail.com', 'confeiteira','$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');


INSERT INTO employee_tbl(name, designation, salary,doj) VALUES ('ednilson', 'admin','12.0','doj');


INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 2);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (2, 2);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (3, 3);

INSERT INTO cliente(bairro, cep, cidade, cpf, nome, endereco, estado, telefone, usuario_id) VALUES('ednilson', '12346-500', 'Parána', '121.121.121-01', 'EFS', 'Rua X', 'Guarapuava', '(46) 99131-6334', 2);

INSERT INTO situacao(descricao) VALUES('Pedido Efetuado');
INSERT INTO situacao(descricao) VALUES('Aguardando pagamento');
INSERT INTO situacao(descricao) VALUES('Pagamento Aprovado');
INSERT INTO situacao(descricao) VALUES('Pagamento não aprovado');
INSERT INTO situacao(descricao) VALUES('Pedido em separação');
INSERT INTO situacao(descricao) VALUES('Pedido em Produção');
INSERT INTO situacao(descricao) VALUES('Pedido Enviado');

INSERT INTO pedido(data_alteracao, data_pedido, tipo_frete, tipo_pagamento, valor_frete, valor_total, cliente_id, situacao_id) VALUES('05/05/2021', '05/05/2021', 1, 1, 0, 5087.63, 1, 1);
INSERT INTO pedido(data_alteracao, data_pedido, tipo_frete, tipo_pagamento, valor_frete, valor_total, cliente_id, situacao_id) VALUES('05/05/2021', '05/05/2021', 1, 1, 0, 5087.63, 1, 1);
INSERT INTO pedido(data_alteracao, data_pedido, tipo_frete, tipo_pagamento, valor_frete, valor_total, cliente_id, situacao_id) VALUES('05/05/2021', '05/05/2021', 1, 1, 0, 5087.63, 1, 1);
INSERT INTO pedido(data_alteracao, data_pedido, tipo_frete, tipo_pagamento, valor_frete, valor_total, cliente_id, situacao_id) VALUES('05/05/2021', '05/05/2021', 1, 1, 0, 5087.63, 1, 1);

INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(1, 56.0, 1, 1);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(2, 49.95, 1, 2);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(3, 99.99, 1, 3);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(2, 199.0, 1, 4);

INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(1, 56.0, 2, 4);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(1, 49.95, 2, 5);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(1, 99.99, 2, 3);
INSERT INTO pedido_item(quantidade, valor, pedido_id, produto_id) VALUES(3, 199.0, 2, 6);
