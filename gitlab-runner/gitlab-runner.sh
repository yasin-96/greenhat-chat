#!/bin/sh

registration_token=LmuyK5zd1QFfcPfuKWms

gitlab-runner register \
  --non-interactive \
  --registration-token ${registration_token} \
  --locked=false \
  --description docker-runner \
  --url http://gitlab:80 \
  --executor docker \
  --docker-image alpine:latest \
  --docker-volumes "/var/run/docker.sock:/var/run/docker.sock" \
  --docker-network-mode gitlab
