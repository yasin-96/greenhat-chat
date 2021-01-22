#!/bin/sh

usermod -aG docker gitlab-runner

registration_token=LmuyK5zd1QFfcPfuKWms

docker exec -it gitlab-runner gitlab-runner register \
  --non-interactive \
  --registration-token ${registration_token} \
  --locked=false \
  --description docker-runner \
  --url http://10.48.19.45:80 \
  --executor docker \
  --docker-image alpine:latest \
  --docker-volumes "/var/run/docker.sock:/var/run/docker.sock"
#  --docker-network-mode gitlab
