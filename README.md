# Lost and Found

O Lost and Found é uma ideia que foi criada durante a disciplina de Programação para Dispositivos Móveis do curso de Sistemas de Informação da Universidade Federal de Mato Grosso do Sul. A ideia do Lost and Found é de ser um serviço de utilidade pública dentro da UFMS, onde pessoas que perderam ou encontraram objetos possam se comunicar.

O Lost and Found é composto por dois sistemas:

* [Aplicativo Android](https://github.com/michelmotta/monitoring_system/tree/master/subsistema_android)
* [Aplicação Servidora](https://github.com/michelmotta/monitoring_system/tree/master/subsistema_arduino)


### Aplicativo Android

O Aplicativo Android é um aplicativo cliente da aplicação servidora. A comunicação entre o aplicativo e o servidor é realizada através de mensagens HTTP e Json.

* Android 6.0 (Marshmallow)
* Padrão MVP (Model-View-Presenter)
* Sessão de usuário
* Biblioteca Android Asynchronous Http Client
* Biblioteca Butter Knife
* Biblioteca de Notificações One Signal


### Aplicação Servidora

A aplicação servidora é um sistema desenvolvido em Cakephp 3 e um banco de dados MySql. Essa aplicação possui uma estrutura rest e uma api que possibilita que outros sistemas possam obter dados e salvar dados. 

* CakePHP 3.6
* Padrão MVC (Model-View-Controller)
* Arquitetura REST
* Web Service
* Comunicação Json


### Funcionalidades

* Gerenciamento de usuários
* Gerenciamento de objetos
* Gerenciamento de comentários 
* Notificações de comentários (Serviço externo)


## Imagens do Aplicativo Lost and Found

![Imagem do Aplicativo](https://github.com/michelmotta/lost-and-found/blob/master/app1.jpg)
![Imagem do Aplicativo](https://github.com/michelmotta/lost-and-found/blob/master/app2.jpg)
![Imagem do Aplicativo](https://github.com/michelmotta/lost-and-found/blob/master/app3.jpg)