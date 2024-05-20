#language: pt
@TEXOit
Funcionalidade: Desafio de automação WEB
  Como um candidato
  Eu quero acessar as informações do album de fotos
  Para que seja possível validar as informações

@automatizado
  Cenário: Devo validar os dados do Objeto de Id 6
  Dado que eu esteja no site do desafio
  E acesso o menu Guide
  Quando clico no link '/albums/1/photos'
  Então deve validar os dados do objeto com id '6'