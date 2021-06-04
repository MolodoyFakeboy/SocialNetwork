/*Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем B
(латинская буква).*/

Select laptop.model, laptop.price from laptop inner join product on laptop.model = product.model 
where product.maker= 'B'
union 
Select pc.model , pc.price from pc inner join product on pc.model = product.model  
where product.maker= 'B' 
union 
Select printer.model , printer.price from printer inner join product on printer.model = product.model  
where product.maker= 'B' 
 