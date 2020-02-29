cd backend/reservation
./gradlew build -x test
cd ../..

docker-compose -f docker-compose-debug.yml build reservation
docker-compose -f docker-compose-debug.yml up reservation
