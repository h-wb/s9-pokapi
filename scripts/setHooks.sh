#!/usr/bin/env sh

sed -i -E 's=gitmoji?=./node_modules/gitmoji-cli/lib/cli.js=g' .git/hooks/prepare-commit-msg
