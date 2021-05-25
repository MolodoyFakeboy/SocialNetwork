/*Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести:
Maker, число моделей*/

select maker, count(model) from product where type = 'ПК' group by maker 
having count(model) >= 3   