#!/usr/bin/env sh

set -e

npm run docs:build

rm -rf docs/.vuepress/gh-pages
git clone --branch=gh-pages https://h-wb:$1@github.com/h-wb/Pokapi.git docs/.vuepress/gh-pages
cd docs/.vuepress/gh-pages
yes | cp -rf ../dist/* .

git add -A
git commit -m 'Deploy'
git push -fq origin gh-pages

cd -
