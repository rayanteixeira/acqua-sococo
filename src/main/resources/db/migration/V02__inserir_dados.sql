insert into permissao (id, nome) values 
	(1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO usuario (id,codigo,data_cadastro,enabled,nome,"password",sobrenome,username) VALUES
	 (4,'004','2017-08-19 00:00:00',true,'ACQUA','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Default','acqua'),
	 (5,'10327','2017-10-23 04:00:00',true,'Fernando ','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Martins Ribeiro','fernandom'),
	 (6,'1617','2017-10-22 05:00:00',true,'Alana','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Fernandes','alana.costa'),
	 (7,'1665','2017-11-21 05:00:00',true,'Ana','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Carolina Rodrigues','ana.carolina'),
	 (8,'1328','2017-11-21 05:00:00',true,'Paulo','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Cassiano Macedo ','pauloc'),
	 (9,'1095','2017-11-21 05:00:00',true,'Roque','$2a$10$6seaJ.I/0iVkGaA5rl1FmuQCRRKx/UuFLEOsxVPMeKv2q3gXapufm','Mesquita Neto','roquem');
	
insert into usuario_permissoes (usuario_id, permissao_id) values 
	 (4,1),
	 (4,2),
	 (5,1),
	 (5,2),
	 (6,2),
	 (7,1),
	 (7,2),
	 (8,2),
	 (9,2);
	 