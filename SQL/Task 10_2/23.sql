/*Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и
ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker*/

select distinct maker from pc inner join product on pc.model = product.model  
where pc.speed >= 75 
and maker in ( select  maker  from laptop inner join product on laptop.model = product.model where laptop.speed >= 750)
