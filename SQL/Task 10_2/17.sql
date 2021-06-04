#Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК. 

select type,laptop.model,speed from laptop inner join product on laptop.model= product.model  where speed < (select MIN(speed) from pc) 