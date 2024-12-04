docker build -t europe-west1-docker.pkg.dev/$2/ebank-docker-repo/$1:1.0 $3
docker push europe-west1-docker.pkg.dev/$2/ebank-docker-repo/$1:1.0
