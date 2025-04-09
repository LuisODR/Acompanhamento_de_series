# 🎬 Series Tracker App

Acompanhamento de séries é um aplicativo desenvolvido com o objetivo de auxiliar usuários no acompanhamento das séries que assistem. Ele oferece funcionalidades para cadastrar séries, marcar como favoritas, editar informações, visualizar o dia de exibição de novos episódios, além de navegar por uma interface intuitiva e responsiva. Todo o funcionamento do app ocorre de forma **offline**, utilizando persistência local de dados por meio do banco de dados **Room**.

A ideia surgiu da necessidade de muitas pessoas em se organizarem melhor com suas séries, principalmente em tempos em que diversas plataformas de streaming estão disponíveis, cada uma com seus próprios lançamentos semanais. O app proporciona ao usuário uma forma simples de controle, sem depender de login, internet ou plataformas específicas.

O projeto foi construído utilizando a linguagem **Kotlin**, como aprendido na aula, com foco na interface declarativa do **Jetpack Compose**. A arquitetura utilizada segue o padrão **MVVM (Model–View–ViewModel)**, que permite separar a lógica de negócio da interface, facilitando a manutenção e escalabilidade do código.

O usuário inicia o uso do app sem necessidade de autenticação. Na tela principal, ele pode visualizar a lista de todas as séries cadastradas. Cada item da lista exibe o nome da série, uma imagem representativa, o dia da semana em que a série lança novos episódios, e três botões de ação: favoritar, editar e excluir.

Ao clicar em **adicionar nova série**, o usuário é levado a uma tela com campos obrigatórios para nome e dia da semana, e um campo opcional para selecionar uma imagem. Se nenhuma imagem for escolhida, o app exibe uma mensagem informando que não há imagem associada, utilizando uma imagem padrão de “placeholder”.

O app também possui duas abas complementares acessíveis pela **barra de navegação inferior (Bottom Navigation)**:  
- A aba de **favoritos**, que exibe apenas as séries marcadas com estrela, organizadas por dia de exibição  
- A aba de **calendário**, que mostra todos os dias da semana com as respectivas séries que lançam episódios nesses dias.  
Caso não haja nenhuma série em um determinado dia, o aplicativo informa ao usuário que não há episódios lançados naquele dia.

Toda a persistência de dados é feita localmente utilizando **Room**, um banco de dados próprio do Android. A comunicação entre o banco de dados e a interface é feita através de um **ViewModel**, que expõe os dados como **LiveData**. Para que a interface seja reativa e atualize automaticamente ao detectar mudanças, foi utilizado o método `observeAsState()` do Jetpack Compose, que observa o LiveData e transforma os dados em estado observável da interface.  
*Tentei realizar essa funcionalidade utilizando Flow, como feito em aula, mas não obtive sucesso com minha implementação.*

## 🧱 Estrutura do Projeto

A estrutura do projeto está organizada em camadas e pastas, separando responsabilidades:

- `model`: define os dados (como a classe `SerieEntity`);
- `local`: implementa o banco e o DAO (`SerieDao`, `SerieDatabase`);
- `viewmodel`: contém a lógica de acesso aos dados e regras de exibição;
- `ui/components`: componentes reutilizáveis de interface (como a `TopBar` e a `BottomNavigationBar`);
- `ui/screens`: telas principais da aplicação (`Home`, `Favoritos`, `Calendário`, `Detalhes`, `Adicionar/Editar`).

## 🎨 Interface

A interface foi desenhada com inspiração nas **cores da Netflix**, usando tons escuros, contrastes fortes e ícones marcantes, garantindo uma estética familiar ao usuário.

---

## ✅ Funcionalidades implementadas

- Cadastro de novas séries
- Edição de séries existentes
- Exclusão de séries
- Marcação e visualização de favoritas
- Calendário por dias da semana
- Busca por nome com lupa
- Detalhes da série
- Interface inspirada na Netflix
- Totalmente offline com Room
- Navegação entre telas com Bottom Navigation Bar

---

## 👨‍💻 Autor

**Luis Otavio Duarte Rodrigues**

Desenvolvido como trabalho final da aula de Desenvolvimento Mobile II no curso de Análise e Desenvolvimento de Sistemas na Universidade de Passo Fundo (UPF).
