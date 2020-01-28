Feature: Export XLSX

  En tant qu'utilisateur, je veux exporter le contenu de mon API au format XLSX

  Scenario: Export de données au format XLSX
    Given L'utilisateur veut exporter au format XLSX des données contenant le Pokémon Bulbizarre
    When  Une requête d'export en XLSX est lancée
    Then  Les données exportées en XLSX doivent contenir le Pokémon Bulbizarre