-- flyway:transaction=false
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'cadastro_pessoas')
    CREATE DATABASE cadastro_pessoas;