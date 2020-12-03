#!/bin/bash

function die {
    declare MSG="$@"
    echo -e "$0: Error: $MSG">&2
    exit 1
}

echo BUILD BEFORE DEPLOY!

env="$1"
[ -z "$env" ] && die "Environment $env - cannot be empty. Valid options are dev|hml|prod"

bucket="rovit-apps-deploy"

echo "==============================================="
echo "1 . Starting package build"
echo "==============================================="
./gradlew clean build -x test -Dquarkus.package.type=native --info

echo "==============================================="
echo "2. Starting stack #'books-api-$env"
echo "==============================================="

sam package --template-file ./infra/deploy.yaml --profile personal --s3-bucket $bucket \
    --output-template-file ./infra/deploy-output.yaml --region us-east-1 --force-upload || die 'Packing template'

sam deploy --template-file ./infra/deploy-output.yaml --profile personal --region us-east-1 --s3-bucket $bucket \
    --stack-name books-api-$env --capabilities CAPABILITY_NAMED_IAM --parameter-overrides Stage=$env || die 'Packing deploy'