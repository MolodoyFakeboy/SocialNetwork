#Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker

select maker from pc inner join product on pc.model = product.model where speed >= 450 group by maker
 