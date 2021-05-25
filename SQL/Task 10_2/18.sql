#Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price

select price, maker from printer inner join product on printer.model = product.model
WHERE price = (select min(price)from printer where color = 'y' ) 