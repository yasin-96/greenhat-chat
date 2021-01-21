#!/bin/sh

usermod -aG docker gitlab-runner

docker exec -it gitlab-runner gitlab-runner register \
  --non-interactive \
  --registration-token LmuyK5zd1QFfcPfuKWms \
  --description shell-runner \
  --url http://10.48.19.45 \
  --executor shell
