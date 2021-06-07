# Busca-de-curriculo-virtual

<p> A equipe de recrutamento e seleção ao divulgar uma vaga, como consequência, recebe os arquivos de currículos de diversas fontes, a mais comum são os e-mails.</p>
<p>Com o recebimento dos currículos (em maioria no formato pdf) é necessário abrir arquivo por arquivo para começar a fase que no setor é chamado de triagem. Nessa fase o profissional tem que ler cada currículo com o objetivo de procurar os candidatos que tenham os perfis mais próximo da vaga.  </p>
<p>A fase de triagem pode se tornar bem repetitiva e manual, porém essa etapa é muito importante, pois o RH não pode deixar passar nenhuma informação para que possíveis aprovados não deixem de ser vistos.</p>
<p>Com isso, se torna necessário a criação de uma forma de automatizar esse processo de busca, já que em muitas empresas a fase de triagem é a que mais consome tempo do profissional de RH, e que dependendo da vaga, nada pode garantir que em uma certa quantidade de currículos haja o candidato com experiência por exemplo em linguagem de programação C#.</p>
<p>A ideia será apresentar como solução do problema um sistema no qual consiga analisar as informações dos currículos que originalmente estarão em formato pdf (e armazenados como anexo no e-mail da empresa) e transferir essas informações para um banco de dados. Dessa forma será possível pesquisar mais rapidamente os candidatos com os perfis desejados, com os dados armazenados será capaz de criar uma plataforma de visualização dessas informações.</p>
<p>A solução dessa proposta será importante para agilizar o processo de seleção, para que o tempo do selecionador seja utilizado para ações mais estratégicas e menos operacionais e burocráticas.</p>

## Arquitetura ##

  <br>
  <p align="center">
  <img src=https://github.com/AldaniloSilva/Busca-de-curriculo-virtual/blob/master/arquitetura.png width="700" title="hover text">
  </p>
  <br>
  
  <ol>
  <li><b>Fonte de Arquivo:</b> Quando o usuário se autenticar no software, o sistema irá processar os arquivos que estarão na pasta que o usuário escolheu (caso tenha feito). Então para cada arquivo dentro da pasta, o texto que estiver dentro do currículo será capturado através de uma biblioteca do Java chamada PDFBox. Quando os dados estiverem na memória o arquivo original será enviado para uma pasta de destino do próprio software.</li>
  <br>
  <li><b>Coleta e limpeza dos dados de interesse:</b> Para cada currículo que estiver na pasta fonte será feito um processo de captura de dados. Nesse processo as seguintes informações do currículo serão coletas:
  <ul>
  <li>Nome do candidato;</li>
  <li>Idade;</li>
  <li>Cidade;</li>
  <li>Objetivo do candidato;</li>
  <li>Escolaridade;</li>
  <li>E-mail.</li>
  </ul>
  <br>
  A coleta das informações de cada arquivo será feita através da linguagem Java, e no próprio código de programação usando regex.
  </li>
  <br>
  <li><b>Armazenar as informações no banco de dados: </b>depois que os dados foram analisados, esses serão endereçados na classe de negócio “Candidato”. Com isso as informações poderão ser enviadas para o banco de dados. </li>
  <br>
  <li><b>Consulta no banco de dados: </b>Com os dados no banco é possível o usuário fazer a pesquisa na aplicação, para isso, como solução tem se uma aplicação desktop que posteriormente pode evoluir para web. O usuário escolhe a categoria de dados que ele deseja e digita sua consulta.</li>
  <br>
  <li><b>Visualização dos dados: </b>Com os dados no banco é possível ter algumas visualizações, por exemplo verificar qual a região que tem se mais candidaturas, verificar a média de faixa etária dos candidatos para determinada vaga, etc. Para isso será usado o software Power BI.</li>
  </ol>
  
## Tela Principal ##

<br>
  <p align="center">
  <img src=https://github.com/AldaniloSilva/Busca-de-curriculo-virtual/blob/master/tela_principal.png width="700" title="hover text">
  </p>
  <br>

