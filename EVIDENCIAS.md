# 📋 Evidências de testes

Este documento reúne as evidências de funcionamento da aplicação, cobrindo frontend, backend e banco de dados.

<br>

## 🎨 Frontend

### Formulário vazio

Tela inicial com todos os campos disponíveis para preenchimento.

![Formulário vazio](assets/evidencia01.png)

<br>

### Validação de campos obrigatórios

Ao tentar submeter o formulário sem preencher nenhum campo, todas as mensagens de erro são exibidas.

![Campos obrigatórios](assets/evidencia02.png)

<br>

### Validação de campos inválidos + preenchimento automático de endereço via CEP

Campos com valores inválidos exibem mensagens específicas por campo. Ao informar um CEP válido, o endereço é preenchido automaticamente via ViaCEP.

![Campos inválidos e CEP automático](assets/evidencia04.png)

<br>

### Formulário preenchido corretamente

Formulário com todos os dados válidos antes de submeter.

![Formulário preenchido](assets/evidencia03.png)

<br>

### Cadastro realizado com sucesso

Após o cadastro, o sistema exibe os dados cadastrados e o login gerado automaticamente.

![Cadastro com sucesso](assets/evidencia05.png)

<br>

## ⚙️ Backend (Swagger UI)

### Campos vazios: 400 Bad Request

Chamada direta à API com campos vazios retorna `400` com mensagens específicas por campo.

![400 campos vazios](assets/evidencia12.png)

<br>

### CPF duplicado: 422 Unprocessable Entity

Tentativa de cadastro com CPF já existente retorna `422` com a mensagem `"CPF já cadastrado"`.

![422 CPF duplicado](assets/evidencia09.png)

<br>

### E-mail duplicado: 422 Unprocessable Entity

Tentativa de cadastro com e-mail já existente retorna `422` com a mensagem `"E-mail já cadastrado"`.

![422 E-mail duplicado](assets/evidencia10.png)

<br>

### CEP não encontrado: 422 Unprocessable Entity

CEP inválido ou inexistente retorna `422` com a mensagem `"CEP não encontrado"`.

![422 CEP inválido](assets/evidencia11.png)

<br>

### Cadastro com sucesso: 201 Created

Cadastro válido retorna `201` com todos os dados persistidos e o login gerado.

![201 Sucesso](assets/evidencia13.png)

<br>

## 🧪 Testes unitários

### Backend: 14 testes passando

Execução do `./mvnw test` com `Tests run: 14, Failures: 0, Errors: 0, Skipped: 0` e `BUILD SUCCESS`.

![Testes backend](assets/evidencia06.png)

<br>

## 🗄️ Banco de dados

### Registros persistidos no SQL Server

25 registros no banco: 20 do seed (dados legados) + 5 cadastrados pelo frontend.

![Banco de dados](assets/evidencia08.png)