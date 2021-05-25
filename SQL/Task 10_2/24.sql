/*Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в
базе данных продукции.*/

SELECT model FROM( 
select distinct model, price from laptop where laptop.price = (select MAX(price) from laptop)  
union 
select distinct model, price from pc where pc.price = (select MAX(price) from pc)  
union  
select distinct model, price from printer where printer.price = (select MAX(price) from printer)  
) as x 
where x.price=(select MAX(price) from ( 
select distinct price from laptop where laptop.price = (select MAX(price) from laptop)  
union  
select distinct price from pc where pc.price = (select MAX(price) from pc)  
union  
select distinct price from printer where printer.price = (select MAX(price) from printer)  
) as 2x )  