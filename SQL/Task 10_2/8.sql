#Найдите производителя, выпускающего ПК, но не ПК-блокноты

select maker from product where type='ПК' and 
maker not in (select maker from product where type ='ПК-блокнот') 