cd backend/identityaccess
bash ./gradlew build -x test
cd ../..

cd backend/facilitymanagement
bash ./gradlew build -x test
cd ../..

cd backend/reservation
bash ./gradlew build -x test
cd ../..

cd bff/managementsite
bash ./gradlew build -x test
cd ../..

docker-compose up --build
