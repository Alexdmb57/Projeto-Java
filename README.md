# Projeto-Java
Projeto para avaliação em estágio.

## Desafio Backend
  Orientações e algumas dicas para você mandar bem no nosso desafio:
  Antes de manipular os dados do cliente é necessário realizar a autenticação do usuário.
  Um usuário poderá se autenticar no sistema com duas contas diferentes:
  1-	Usuário admin
  Senha: 123qwe!@#
  Esse usuário possui permissão total no sistema.
  2-	Usuário padrão 
  Senha: 123qwe123
  Esse usuário só tem permissão de visualização dos dados.

## Um registro de um cliente deverá ter os seguintes campos com suas respectivas regras:
  NOME:
  •	Mínimo de 3 caracteres;
  •	Máximo de 100 caracteres;
  •	Campo obrigatório;
  •	Permite apenas letras, espaços e números.
  CPF:
  •	Sempre deve ser mostrado com máscara
  •	Deve ser persistido na base sem máscara;
  •	É um campo obrigatório.
  Endereço:
  •	Obrigatório preenchimento de CEP, logradouro, bairro, cidade e UF;
  •	Opcional complemento;
  •	Outros dados não devem ser preenchidos;
  •	Deve estar integrado com um serviço de consulta de CEP.
  Sugestão: https://viacep.com.br/
  •	O usuário pode alterar os dados que vieram do serviço de consulta de CEP;
  •	O CEP deve ser mostrado com máscara;
  •	O CEP deve ser persistido sem máscara;
  Telefone:
  •	Podem ser cadastrados múltiplos telefones;
  •	Pelo menos um telefone deve ser cadastrado;
  •	No cadastro de telefone, deve ser informado o tipo de telefone (residencial, comercial e celular) e o número;
  •	A máscara de telefone deve ser de acordo com o tipo de telefone (celular possui um digito a mais);
  •	O telefone deve ser mostrado com máscara;
  •	O telefone deve ser persistido sem máscara;
  E-mail:
  •	Podem ser cadastrados múltiplos e-mails;
  •	Pelo menos um e-mail deve ser cadastrado;
  •	Deve ser um e-mail válido.

## O Sistema deverá ser divido em dois projetos, um projeto para front (opcional) e outro para serviço.
	Serviço

## O projeto de serviços deverá ser desenvolvido com as seguintes tecnologias:

  •	Java 8 
  •	Spring / Sprinboot
  •	Hibernate
  •	Maven
  •	Você pode adicionar tecnologias a mais se achar necessário.

## Frontend
  O projeto de front, de preferência, deverá utilizar o React, mas outro framework/biblioteca Javascript pode ser utilizado.
  [PLUS]: 
  NÃO É OBRIGATÓRIO DESENVOLVER O FRONTEND, MAS É UMA VANTAGEM.

