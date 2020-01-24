#!/usr/bin/env sh

cd docs/.vuepress/dist

git init
git add -A
git commit -m 'Deploy'

git push -f git@github.com:h-wb/Pokapi.git develop:gh-pages

cd -