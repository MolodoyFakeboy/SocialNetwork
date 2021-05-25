#Найдите среднюю скорость ПК, выпущенных производителем A. 

Select avg(speed) from pc inner join product on pc.model= product.model where maker = 'Asus'  
group by maker 