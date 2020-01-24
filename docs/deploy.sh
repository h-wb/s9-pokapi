#!/usr/bin/env sh

set -e

npm run docs:build

cd docs/.vuepress/dist

git init
git add -A
git commit -m 'Deploy'

git push -f git@github.com:h-wb/Pokapi.git master:gh-pages

cd -
