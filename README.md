Projeto Entregando Mercadorias
=============

**Requisitos:**
* Java 8
* MySql
* Maven

**Passo a passo:**

* Criar banco de dados:
```
create database walmart;
create database test_walmart;
```

* Atualizar os dados do datasource (url, username, password) no arquivo ./teste-walmart/src/main/resources/application.properties
```
spring.datasource.url: jdbc:mysql://localhost/walmart
spring.datasource.username: root
spring.datasource.password: 123456
```

* Compilar o projeto

Entre na pasta ./teste-walmart 
Execute:
```
mvn package
```

* Execute o projeto:
```
java -jar target\teste-walmart-0.0.1-SNAPSHOT.jar
```

Nesse momento o serviço está funcionando!

Serviços:
```
GET http://localhost:8080/services/menorValorDeEntrega
POST http://localhost:8080/services/novoMapa
```

**Exemplo de parâmetro do serviço "menorValorDeEntrega"**
```
?origem=A&destino=F&autonomia=10&valorLitroCombustivel=2.5
```

**Exemplo de request para o serviço "novoMapa"**
```
{
   "nome":"MAPA DE SP",
   "rotas":[
      {
         "origem":"A",
         "destino":"B",
         "distancia":5
      },
      {
         "origem":"A",
         "destino":"C",
         "distancia":10
      },
      {
         "origem":"B",
         "destino":"D",
         "distancia":3
      },
      {
         "origem":"B",
         "destino":"G",
         "distancia":10
      },
      {
         "origem":"C",
         "destino":"D",
         "distancia":10
      },
      {
         "origem":"C",
         "destino":"E",
         "distancia":10
      },
      {
         "origem":"D",
         "destino":"F",
         "distancia":10
      },
      {
         "origem":"D",
         "destino":"E",
         "distancia":5
      },
      {
         "origem":"E",
         "destino":"F",
         "distancia":4
      },
      {
         "origem":"E",
         "destino":"H",
         "distancia":1
      },
      {
         "origem":"G",
         "destino":"F",
         "distancia":10
      },
      {
         "origem":"H",
         "destino":"F",
         "distancia":1
      }
   ]
}
```


