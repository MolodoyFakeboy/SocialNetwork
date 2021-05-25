/*Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов.
Вывести: maker, средний размер экрана.*/

select maker, avg(screen) from laptop inner join product on laptop.model =  product.model group by maker