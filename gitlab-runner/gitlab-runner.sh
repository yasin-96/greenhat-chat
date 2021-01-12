#!/bin/sh

registration_token=LmuyK5zd1QFfcPfuKWms

gitlab-runner register \
  --non-interactive \
  --url "http://10.48.19.45" \
  --registration-token ${registration_token} \
  --executor "docker" \
  --docker-image alpine:latest \
  --description "docker-runner" \
  --tag-list "docker" \
  --run-untagged="true" \
  --locked="false" \
  --access-level="not_protected"
