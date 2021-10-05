[![Build Status](https://app.travis-ci.com/Azamat-Sult/job4j_todo.svg?branch=main)](https://app.travis-ci.com/Azamat-Sult/job4j_todo)
# job4j_todo

В данном проекте реализован планировщик задач TODO:

Можно добавлять новые задачи с описанием в список.

По мере выполнения задач, можно их помечать как выполненые, после чего задачи пропадают из списка.

Реализован фильтр отображающий только активные задачи или все задачи, как активные, так и завершенные.

В данном проекте применены технологии: Hibernate, Bootstrap, Servlet,
шаблон MVC, PostgreSQL, H2, JavaScript, AJAX, JSON

Проект разворачивается на сервере Apache Tomcat.
Все данные сохраняются в PostgreSQL или H2 через Hibernate

Скриншоты:
1. Форма добавления новой задачи в список с валидацией
   ![ScreenShot](images/Screenshot1.png)
2. Список текущих задач
   ![ScreenShot](images/Screenshot2.png)
3. Выполненные задачи удалились из списка
   ![ScreenShot](images/Screenshot3.png)
4. Но их можно увидеть, поставив галочку "Показать все задачи"
   ![ScreenShot](images/Screenshot4.png)
