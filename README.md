# Objetivo
Teste backend developer

## Iniciando

- `git clone https://github.com/sergio-aliceral/teste-backend-developer.git`
- `cd teste-backend-developer`

## Pré-requisitos
- `mvn --version`<br>

Você deverá ver a indicação da versão do Maven instalada e a versão do JDK. Observe que o JDK é obrigatório, assim como a definição das variáveis de ambiente **JAVA_HOME** e **M2_HOME**.

## Limpar, compilar e empacotar
- `mvn clean install`<br>

Gera arquivo _teste-backend-developer-0.0.1-SNAPSHOT.jar_ no diretório _target_.

## Executando a aplicação
- `java -jar target/teste-backend-developer-0.0.1-SNAPSHOT.jar`<br>

Executa o aplicativo por meio do arquivo jar criado pelo comando `mvn clean install`, conforme comentado anteriormente.

### Documentação com Swagger

- http://localhost:8080/swagger-ui.html
 
