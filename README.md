# Easy-food
EasyFood é um aplicativo que facilita a preparação de alimentos e fornece informações completas sobre a refeição selecionada, incluindo instruções de como fazer essa refeição fornecidas por um vídeo


# Bibliotecas e tecnologias usadas
* Componente de navegação: uma atividade contém vários fragmentos em vez de criar várias atividades.
* Retrofit: fazendo conexão HTTP com a API restante e convertendo o arquivo json refeição em objeto Kotlin/Java.
*Room: Salve as refeições no banco de dados local.
*MVVM & LiveData: Saperate logic code from views e save the state in case the screen configuration change.
*Coroutines: faça algum código em segundo plano.
*vinculação de exibição: em vez de inflar visualizações manualmente, a vinculação de exibição cuidará disso.
*Deslizar: capture imagens e carregue-as no imageView.
