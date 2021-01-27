#!/bin/sh

registration_token=LmuyK5zd1QFfcPfuKWms

gitlab-runner register \
  --non-interactive \
  --registration-token ${registration_token} \
  --locked=false \
  --description docker-stable \
  --url http://10.48.19.45:80 \
  --executor docker \
  --docker-image docker:stable \
  --docker-volumes "/var/run/docker.sock:/var/run/docker.sock" \
  --docker-network-mode gitlab
