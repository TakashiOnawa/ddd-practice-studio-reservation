cd bff/managementsite
bash ./gradlew build -x test
cd ../..

docker-compose -f docker-compose-debug.yml build managementsite
docker-compose -f docker-compose-debug.yml up managementsite
