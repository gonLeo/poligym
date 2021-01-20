-- CRIANDO SEEDERS DE USUARIOS
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(1, 'Joao da Silva', 'joaosilva@teste.com', '123456', '1234561', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(2, 'Eva Brandão Souto', 'evasouto@teste.com', '123456', '1234562', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(3, 'Salvador Trigueiro Reis', 'salvadorreis@teste.com', '123456', '1234563', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(4, 'Keyson Simões Caldeira', 'keysoncaldeira@teste.com', '123456', '1234564', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(5, 'Eric Quintela Salvador', 'ericsalvador@teste.com', '123456', '1234565', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(6, 'Carla Pestana Aveiro', 'carlaaveiro@teste.com', '123456', '1234566', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(7, 'Sara Fitas Canário', 'saracanario@teste.com', '123456', '1234567', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(8, 'Fabiana Furquim Vilante', 'fabianavilante@teste.com', '123456', '1234568', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(9, 'Valéria Valcanaia Marroquim', 'valeriamarroquim@teste.com', '123456', '1234569', '01/19/2021', '02/19/2021', '01/19/2021');
INSERT INTO Users(id, name, email, password, registration_code, registration_date, medical_certificate_validate, created_at) values(10, 'Evelin Vilanova Pádua', 'evelinpadua@teste.com', '123456', '12345610', '01/19/2021', '02/19/2021', '01/19/2021');

-- CRIANDO SEEDERS DE GRUPO MUSCULAR
INSERT INTO muscular_group(id, description, created_at) values(1, 'Peito', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(2, 'Ombro', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(3, 'Bíceps', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(4, 'Tríceps', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(5, 'Antebraço', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(6, 'Costas', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(7, 'Trapezio', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(8, 'Abdomên', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(9, 'Perna', '01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(10, 'Panturrilha','01/19/2021');
INSERT INTO muscular_group(id, description, created_at) values(11, 'Cardiovascular', '01/19/2021');

-- CRIANDO SEEDERS DE EXERCICIO
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(1, 'Banco de Supino', 'Supino Reto', 1, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(2, 'Halter', 'Desenvolvimento Arnold', 2, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(3, 'Barra', 'Rosca na Barra', 3, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(4, 'CrossOver com Puxador de tríceps', 'Tríceps Polia Alta', 4, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(5, 'Banco de Supino', 'Supino Reto', 5, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(6, 'Halter', 'Rosca Direta Invertida com Halteres', 6, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(7, 'Halter', 'Encolhimento com Halteres', 7, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(8, 'Colchonete', 'Abdominal', 8, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(9, 'LegPress', 'LegPress', 9, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(10, null, 'Elevação do calcanhar com duas pernas', 10, '01/19/2021');
INSERT INTO Exercises(id, equipment, description, muscular_group_id, created_at) values(11, 'Esteira', 'Corrida', 11, '01/19/2021');