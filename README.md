# Teste Mutantes Mercado Livre

# Requisitos
* Desenvolver um programa com um método ou função com a seguinte assinatura boolean isMutant(String[] dna)
* As letras da String só podem ser: (A, T, C, G) , que representa cada base nitrogenada do DNA.
* Você saberá se um humano é um mutante, se encontrar mais de uma sequência de quatro letras iguais nas direções horizontais, verticais ou nas diagonais.

# Desafios
  * Nível 1
    * Desenvolva um método ou função que esteja de acordo com a assinatura proposta isMutant(String[] dna) , que seja capaz de identificar corretamente mutantes.
  
  * Nível 2
    * Crie uma API REST e hospede em algum ambiente de computação em nuvem gratuita (Google App Engine, Amazon AWS, etc).
 
  * Nível 3
    * Crie um banco de dados, que armazena os DNAs verificados pela API. Esse banco deve garantir a unicidade, ou seja, apenas 1 registro por DNA.

# API
* URL local: http://localhost:5000
* URL cloud: http://mutantdetector-ml.us-east-2.elasticbeanstalk.com

# /mutant - POST
Para verificar se um DNA é mutante, deve realizar um post para o endpoint http://mutantdetector-ml.us-east-2.elasticbeanstalk.com/mutant


Exemplo: POST -> /mutant/{"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] }


Em caso positivo, a resposta será terá código 200. Em caso negativo ou inválido, 403.

# /stats - GET
Para acessar as estatísticas dos DNAs registrados, deve-se realizar uma requisição get para o endpoint http://mutantdetector-ml.us-east-2.elasticbeanstalk.com/stats

# Alterações locais
Para executar a aplicação localmente é necessário que o ambiente de desenvolvimento esteja configurado corretamente com JDK e Maven.

A informações de usuário e senha do arquivo application.properties foram substituídas por placeholders para poderem ser disponibilizadas no github. Para executar a aplicação é necessário alterar esse arquivo.

# TODO
  * Incluir cobertura de testes no controller /stats
  * Aumentar cobertura de testes no controller /mutant
  * Adicionar serviços para visualização dos dados
  * Adicionar tratamentos de erro com mensagens descritivas para as chamadas
