#Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
select hd from pc group by hd having count(model)>=2 