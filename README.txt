silogismos-bingo (v7)

Mudanças desta versão (v4)
- A página não exibe nenhum identificador que revele a resposta
- Argumento em destaque central (área de avaliação)
- Textos da interface com acentuação

Requisitos
- JDK 21
- Maven 3.9+

Rodar
mvn spring-boot:run

Acessar
http://localhost:8080

API
- GET  /api/next    sorteia a próxima carta e retorna newBingos quando alguma cartela fechou
- GET  /api/answer  revela o gabarito da carta atual
- GET  /api/status  retorna quantidade de jogadas e lista de cartelas com bingo
- POST /api/reset   inicia um novo ciclo (embaralha baralho e zera bingos)

- Ajustes de interface solicitados: regra simplificada, texto do argumento maior, rodapé com créditos.
