# AutoPeças Chabas

Um sistema de apoio gerenciamento de estoque e de clientes para uma Loja de AutoPeças.

## Pré-Requisitos

* Java 23
* Apache Maven 3.6.3
* PostgresSQL

## Instalação de Dependências

```shell
git https://github.com/felipehidequel/AutopecasChabas.git
cd AutopecasChabas/autopecaschabas
mvn clean
mvn -X install
```

## Criação e configuração do banco de dados

Em 'autopecas.sql' disponibilizamos um script para a criação do banco de dados d
a aplicação. Após a criação do banco com o script, em resources/db.properties en
tre com as informações do bando de dados.

```propeties
db.url=jdbc:postgresql://localhost:5432/autopecas
db.username=postgres
db.password=<sua senha aqui>
```

## Execução

Após a instalação das dependências e criação do banco, basta executar a classe 
*App.java*