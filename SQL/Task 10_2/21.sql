/*Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker,
максимальная цена*/

select maker, max(price) from pc inner join product on pc.model= product.model  group by maker 