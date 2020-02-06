cd backend/identityaccess
./gradlew build -x test
cd ../..

cd backend/reservation
./gradlew build -x test
cd ../..

cd bff/managementsite
./gradlew build -x test
cd ../..

docker-compose up --build
