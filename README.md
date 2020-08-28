# AKADEMIA SPRING WEEK 9


_Wczytaj do aplikacji 1000 obiektów pobranych z https://www.mockaroo.com/ (z formatu CSV)_  
_Stwórz metodę, która będzie zapisywała wszystkie elementy do lokalnej bazy danych._  
_Stwórz aspekt, który będzie nasłuchiwać metodę i w momencie startu włączy licznik startu, a po zakończeniu operacji odczytanie zostanie czas wykonania operacji._  
_Zrób do samo dla operacji wczytywania danych z bazy danych (bez wyświetlania ich, bo to może spowodować przekłamanie wyniku). Odnotuj wynik odczytu._  
_Zrealizuj to dla relacyjnej bazy danych i nierelacyjnej bazy danych. Porównaj wyniki, napisz wnioski, podziel się nimi na grupie – zobaczymy komu co udało się osiągnąć ?_

## Rezultaty:
[
    {
        "id": 1,
        "saveSql": 2167.6666666666665,
        "loadSql": 2.2,
        "saveMongo": 344.46666666666664,
        "loadMongo": 13.133333333333333,
        "numberOfTests": 15
    },
    {
        "id": 2,
        "saveSql": 2284.9846153846156,
        "loadSql": 2.1076923076923078,
        "saveMongo": 364.4153846153846,
        "loadMongo": 12.569230769230769,
        "numberOfTests": 50
    },
    {
        "id": 3,
        "saveSql": 2277.5,
        "loadSql": 2.1,
        "saveMongo": 363.0571428571429,
        "loadMongo": 12.571428571428571,
        "numberOfTests": 5
    }
]
