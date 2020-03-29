cd backend/facilitymanagement
bash ./gradlew build -x test
cd ../..

docker-compose -f docker-compose-debug.yml build facilitymanagement
docker-compose -f docker-compose-debug.yml up facilitymanagement
