# Easy-food
EasyFood é um aplicativo que facilita a preparação de alimentos e fornece informações completas sobre a refeição selecionada, incluindo instruções de como fazer essa refeição fornecidas por um vídeo


# Bibliotecas e tecnologias usadas
* Componente de navegação: uma activity contém vários fragments em vez de criar várias activites.
* Retrofit: fazendo conexão HTTP com a Rest-API e convertendo o arquivo json refeição em objeto Kotlin/Java.
* Room: Salve as refeições no banco de dados local.
* MVVM & LiveData: Saperate logic code from views e save the state in case the screen configuration change.
* Coroutines: faça algum código em segundo plano.
* View Banding: em vez de inflar visualizações manualmente, a ViewBinding cuidará disso.
* Glide: capture imagens e carregue-as no imageView.
