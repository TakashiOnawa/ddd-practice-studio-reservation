cd backend/identityaccess
./gradlew build
cd ../..

cd backend/reservation
./gradlew build
cd ../..

cd bff/managementsite
./gradlew build
cd ../..

docker-compose up --build
