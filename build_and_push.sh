docker build -t $1:latest $3
docker tag $1:latest gcr.io/$2/$1:1.0
docker tag $1:latest gcr.io/$2/$1:1.0
docker push gcr.io/$2/$IMAGE_NAME:1.0

