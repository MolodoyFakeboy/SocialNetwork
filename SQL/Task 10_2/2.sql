#Найдите производителей принтеров. Вывести: maker
select  maker  FROM Product WHERE type = 'принтер' GROUP BY maker