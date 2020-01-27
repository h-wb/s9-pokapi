Feature: Export CSV

  En tant qu'utilisateur, je veux exporter le contenu de mon API au format csv

  Scenario: Export de données au format CSV
    Given L'utilisateur veut exporter au format CSV des données contenant le Pokémon Bulbizarre
    When  Une requête d'export en CSV est lancée
    Then  Les données exportées en CSV doivent contenir le Pokémon Bulbizarre