<h1>🏥Postinho - Sistema de Gerenciamento de Consultas Médicas </h1>

<p>Postinho é um sistema terminal desenvolvido para gerenciar pacientes e médicos em um pequeno hospital. Ele permite 
cadastrar profissionais de saúde e pacientes, além de agendar e registrar consultas médicas. O projeto foi desenvolvido com o objetivo 
de aprimorar o uso de SQL por meio de classes DAO e gerenciar dependências utilizando Maven.</p>

<h2>✨ Funcionalidades</h2>
<ul>
    <li>📋 <b>Cadastro de pacientes e médicos</b> – Gerencie informações essenciais dos profissionais e pacientes.</li>
    <li>📅 <b>Agendamento de consultas</b> – Marque e organize consultas médicas com facilidade.</li>
    <li>✅ <b>Registro de atendimentos médicos</b> – Registre os atendimentos realizados para acompanhamento.</li>
    <li>🗄️ <b>Persistência dos dados</b> – Armazene informações de forma segura utilizando <i>SQLite</i>.</li>
</ul>

<h2>⚙️ Tecnologias Utilizadas</h2>
<ul>
    <li>💻 <b>Linguagem:</b> Java 17</li>
    <li>🗃️ <b>Banco de Dados:</b> SQLite 3.45.3</li>
    <li>🚀 <b>Gerenciador de Dependências:</b> Maven 3.9.6</li>
    <li>🛠️ <b>Plataforma de Desenvolvimento:</b> Visual Studio Code</li>
</ul>

<h2>📦 Dependências Principais</h2>
<ul>
    <li>🔗 <b>SQLite JDBC:</b> <code>org.xerial:sqlite-jdbc:3.49.0.0</code> – Integração com o banco de dados.</li>
    <li>🔍 <b>Stella:</b> <code>br.com.caelum.stella-core:2.1.2</code> – Validação de CPF, CNPJ e outros dados brasileiros.</li>
    <li>🧪 <b>JUnit 5:</b> <code>org.junit.jupiter:junit-jupiter-api:5.9.2</code> – Framework para testes unitários.</li>
</ul>

<h2>🚀 Como Executar</h2>
<ol>
    <li>📥 <b>Clone o repositório</b>:
        <pre><code>git clone https://github.com/AntWills/Postinho.git</code></pre>
    </li>

  <li> 📂 <b>Acesse a pasta do projeto</b>:
        <pre><code>cd Postinho</code></pre>
    </li>

  <li>🔧 <b>Compile o projeto com Maven</b>:
        <pre><code>mvn compile</code></pre>
    </li>

  <li>⚡ <b>Execute o projeto</b>:
        <pre><code>mvn exec:java -Dexec.mainClass="com.project.Main"</code></pre>
    </li>
</ol>

<h2>📝 Observações</h2>
<ul>
    <li>Certifique-se de ter o Java 17 e o Maven instalados corretamente.</li>
    <li>Se necessário, configure o banco SQLite antes de executar o programa.</li>
    <li>Se o programa não executar, abra o vs code e tente executar por lá.</li>
</ul>
