#Найдите принтеры, имеющие самую высокую цену. Вывести: model, price

select model,price from printer where price = (select max(price) from printer)