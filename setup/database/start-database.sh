set -x

cd `dirname $0`
docker-compose -f docker-compose.yml pull
docker-compose -f docker-compose.yml up -d --force-recreate