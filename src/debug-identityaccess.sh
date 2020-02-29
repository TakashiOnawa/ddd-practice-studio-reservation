cd backend/identityaccess
./gradlew build -x test
cd ../..

docker-compose -f docker-compose-debug.yml build identityaccess
docker-compose -f docker-compose-debug.yml up identityaccess
