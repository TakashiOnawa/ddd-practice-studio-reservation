docker rm -f managementsite
docker rm -f identityaccess
docker rm -f reservation
docker rmi -f src_managementsite
docker rmi -f src_identityaccess
docker rmi -f src_reservation
