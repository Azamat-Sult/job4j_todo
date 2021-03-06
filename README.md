[![Build Status](https://app.travis-ci.com/Azamat-Sult/job4j_todo.svg?branch=main)](https://app.travis-ci.com/Azamat-Sult/job4j_todo)
# job4j_todo

В данном проекте реализован планировщик задач TODO:
Можно добавлять новые задачи с описанием в список.
Добавлять и просматривать задачи имеет право только авторизованный
пользователь.
По мере выполнения задач, можно их помечать как выполненые,
после чего задачи пропадают из списка.
Реализован фильтр отображающий только активные задачи или все задачи,
как активные, так и завершенные.

В данном проекте применены технологии: Hibernate, Bootstrap, Servlet,
шаблон MVC, PostgreSQL, H2, JavaScript, AJAX, JSON

Проект разворачивается на сервере Apache Tomcat.
Все данные сохраняются в PostgreSQL или H2 через Hibernate

Скриншоты:
1. Форма добавления новой задачи в список. Список задач не показан, 
т.к. нет авторизации. Добавить задачу тоже не получится без авторизации
   ![ScreenShot](images/Screenshot_1.png)
2. Форма авторизации с валидацией
   ![ScreenShot](images/Screenshot_2.png)
3. Форма регистрации с валидацией
   ![ScreenShot](images/Screenshot_3.png)
4. Авторизованному пользователю показаны активные задачи,
выполненные задачи не отображаются
   ![ScreenShot](images/Screenshot_4.png)
5. Но их можно увидеть, поставив галочку "Показать все задачи"
   ![ScreenShot](images/Screenshot_5.png)
