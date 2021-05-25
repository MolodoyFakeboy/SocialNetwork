/* Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не
менее 10 Гбайт.*/

Select maker, speed  from Product INNER JOIN Laptop on Product.model = Laptop.model  where hd >= 10  
