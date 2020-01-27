# GitFlow

**develop** est la branche principale.

Lors d'un nouveau développement, il est nécessaire d'ouvrir une branche **feature**.
Chaque nouvelle feature a sa propre branche.

Les branches terminées sont mergées dans **develop** quand elles sont prêtes.
Une **pull request** est alors ouverte, permettant une revue de code avant validation.
Une fois la **pull request** validée, la branche est mergée dans **develop** puis supprimée.

Lors d'une livraison, une branche **release** est créée depuis la branche **develop**, les corrections y seront faites.
Les versions finales sont mergées dans **master** et taguées.

Les corrections critiques sont effectuées dans la branche **hotfix**.
Elles sont mergées dans **develop** et sont mergées et taguées dans **master**.

![GitFlow](../resources/git_flow.jpg)
