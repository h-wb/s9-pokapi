Feature: Export

  En tant qu'utilisateur, je veux exporter le contenu de mon API au format csv

  Scenario:
    Given L'utilisateur qui veut exporter les données
    When  Une requête d'export est lancée
    Then  Le fichier csv est créé