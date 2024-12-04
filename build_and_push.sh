docker build -t $1:1.0 $3
docker tag $1:1.0 gcr.io/$2/$1:1.0
docker push gcr.io/$2/$IMAGE_NAME:1.0

