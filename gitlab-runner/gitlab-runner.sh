#!/bin/sh

docker exec -it gitlab gitlab-runner register \
  --non-interactive \
  --registration-token LmuyK5zd1QFfcPfuKWms \
  --description docker-runner \
  --url http://10.48.19.45 \
  --executor docker \
  --docker-image alpine:latest \
