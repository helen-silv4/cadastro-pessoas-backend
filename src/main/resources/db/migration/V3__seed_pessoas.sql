-- flyway:transaction=false

INSERT INTO pessoa (nome, documento, email, data_nascimento, cep, logradouro, bairro, cidade, uf, numero, complemento, login, criado_em) VALUES
     ('Maria Silva Santos', '12345678901', 'maria.silva.santos1@exemplo.com', '1998-03-14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'mariasi', GETDATE()),
     ('Maria Simoes Andrade', '12345678902', 'maria.simoes.andrade@exemplo.com', '1999-07-22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'mariasa', GETDATE()),
     ('Maria Silva Soares', '12345678903', 'maria.silva.soares@exemplo.com', '2000-01-10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'mariass', GETDATE()),
     ('Maria Siqueira Lima', '12345678904', 'maria.siqueira.lima@exemplo.com', '1997-11-05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'mariasl', GETDATE()),
     ('Joao Pedro Alves', '12345678905', 'joao.pedro.alves@exemplo.com', '1998-05-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'joaoped', GETDATE()),
     ('Joao Pedrosa Lima', '12345678906', 'joao.pedrosa.lima@exemplo.com', '2001-09-18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'joaopel', GETDATE()),
     ('Joao Pedro Antunes', '12345678907', 'joao.pedro.antunes@exemplo.com', '1996-12-01', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'joaoant', GETDATE()),
     ('Joao Pedreira Costa', '12345678908', 'joao.pedreira.costa@exemplo.com', '1999-04-27', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'joaocos', GETDATE()),
     ('Ana Clara Souza', '12345678909', 'ana.clara.souza@exemplo.com', '2000-08-13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anaclar', GETDATE()),
     ('Ana Claudia Rocha', '12345678910', 'ana.claudia.rocha@exemplo.com', '1998-02-16', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anaclau', GETDATE()),
     ('Ana Clara Lima', '12345678911', 'ana.clara.lima@exemplo.com', '1997-06-30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anaclal', GETDATE()),
     ('Ana Clarice Melo', '12345678912', 'ana.clarice.melo@exemplo.com', '2002-10-02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anaclam', GETDATE()),
     ('Carlos Eduardo Lima', '12345678913', 'carlos.eduardo.lima@exemplo.com', '1995-03-11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'carlose', GETDATE()),
     ('Carlos Emanuel Rosa', '12345678914', 'carlos.emanuel.rosa@exemplo.com', '1996-07-20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'carlosr', GETDATE()),
     ('Carlos Ernesto Dias', '12345678915', 'carlos.ernesto.dias@exemplo.com', '1994-12-29', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'carlosd', GETDATE()),
     ('Carlos Esteves Prado', '12345678916', 'carlos.esteves.prado@exemplo.com', '2001-01-06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'carlosp', GETDATE()),
     ('Paula Fernanda Reis', '12345678917', 'paula.fernanda.reis@exemplo.com', '1998-09-09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'paulafe', GETDATE()),
     ('Paula Ferreira Nunes', '12345678918', 'paula.ferreira.nunes@exemplo.com', '1999-11-23', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'paulafn', GETDATE()),
     ('Paula Fernanda Lima', '12345678919', 'paula.fernanda.lima@exemplo.com', '1997-04-04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'paulafl', GETDATE()),
     ('Lucas Henrique Prado', '12345678920', 'lucas.henrique.prado@exemplo.com', '2000-05-17', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'lucashe', GETDATE());
