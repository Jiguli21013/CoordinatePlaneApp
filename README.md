# CoordinatePlaneApp
Android-приложение, демонстрирующее  построения графиков и таблиц. Приложение запрашивает у сервера координаты точек, отображает их в виде таблицы и визуализирует на графике с возможностью масштабирования и сохранения изображения.

Функциональность:

	1.	Запрос данных с сервера:
		•	Пользователь вводит количество точек и отправляет запрос к API.
		•	Ответ сервера обрабатывается (успешный или с ошибкой).
	2.	Отображение данных:
		•	Таблица с координатами точек.
		•	График с точками, соединенными линиями (ломаными или сглаженными).
	3.	Работа с графиком:
		•	Поддержка изменения масштаба.
		•	Адаптация отображения для портретной и ландшафтной ориентаций.
	4.	Сохранение графика:
		•	Возможность сохранить изображение графика в файл (в галерею устройства).
Технические особенности:

	1.	Язык разработки: Kotlin.
		•	Архитектура: MVVM (Model-View-ViewModel).
	2.	UI:
		•	Классическая система View/Layouts.
		•	RecyclerView для таблицы.
		•	Кастомный View для графика.
	3.	Работа с API:
		•	Retrofit для взаимодействия с сервером.
		•	Обработка ошибок сервера и исключений.
	4.	Файловая система:
		•	MediaStore API для сохранения изображений.
	5.	Дополнительные возможности:
		•	Поддержка разных ориентаций экрана.
