IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'cadastro_pessoas')
BEGIN
    CREATE DATABASE cadastro_pessoas;
END
GO

USE cadastro_pessoas;
GO

CREATE TABLE pessoa (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    documento VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    cep VARCHAR(8),
    logradouro VARCHAR(200),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    login VARCHAR(7) NOT NULL UNIQUE,
    criado_em DATETIME2 NOT NULL
);