/*Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с
самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker*/

select distinct maker from product where type='принтер' and maker in 
(select distinct maker from product  inner join  pc on product.model = pc.model join (
select max(speed) as speed, ram from pc
where ram = (select min(ram) from pc)
group by ram) as a on a.speed = pc.speed and a.ram = pc.ram)