INSERT INTO tb_usuario (email, senha) VALUES ('teste@hotmail.com', '$2a$10$LIJAO7oLrhfQllqFwe7qn.vjNuS3t5A9A2NIykpCo0tTQO6k1KvKa');
INSERT INTO tb_usuario (email, senha) VALUES ('master@hotmail.com', '$2a$10$LIJAO7oLrhfQllqFwe7qn.vjNuS3t5A9A2NIykpCo0tTQO6k1KvKa');
INSERT INTO tb_usuario (email, senha) VALUES ('usjt@gmail.com','$2a$10$LIJAO7oLrhfQllqFwe7qn.vjNuS3t5A9A2NIykpCo0tTQO6k1KvKa');

INSERT INTO master.tb_entrega (vlr_entrega, vlr_gorjeta, hr_duracao_corrida, dt_entrega, usuario_id) VALUES (10.50,0, '00:29', sysdate(), 1);
INSERT INTO master.tb_entrega (vlr_entrega, vlr_gorjeta, hr_duracao_corrida, dt_entrega, usuario_id) VALUES (3.50,0, '00:10', sysdate(), 1);
INSERT INTO master.tb_entrega (vlr_entrega, vlr_gorjeta, hr_duracao_corrida, dt_entrega, usuario_id) VALUES (2.50, 10.0, '00:05', sysdate(), 1);
INSERT INTO master.tb_entrega (vlr_entrega, vlr_gorjeta, hr_duracao_corrida, dt_entrega, usuario_id) VALUES (10.50, 10.0, '00:50', sysdate(), 1);
INSERT INTO master.tb_entrega (vlr_entrega, vlr_gorjeta, hr_duracao_corrida, dt_entrega, usuario_id) VALUES (19.0, 0, '00:20', sysdate(), 2);



INSERT INTO master.tb_plataforma (id, cnpj_plataforma, nm_plataforma) VALUES ('1', '08.902.11X/0001-XX', 'IFOOD');
INSERT INTO master.tb_plataforma (id, cnpj_plataforma, nm_plataforma) VALUES ('2', '26.900.16X/0001-XX', 'RAPPI');
INSERT INTO master.tb_plataforma (id, cnpj_plataforma, nm_plataforma) VALUES ('3', '17.895.64X/0001-XX', 'Uber Eats');









