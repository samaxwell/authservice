#!/bin/sh
echo "*******************************************************"
echo "Oauth service - Waiting for the eureka server to start"
echo "*******************************************************"
while ! `nc -z eurekaserver $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"


#echo "*********************************************************"
#echo "Oauth service - Waiting for the database server to start"
#echo "*********************************************************"
#while ! `nc -z database $DATABASE_PORT`; do sleep 3; done
#echo "******** Database Server has started "

echo "**************************************************************"
echo "Oauth service - Waiting for the configuration server to start"
echo "**************************************************************"
while ! `nc -z configserver $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"


echo "***********************"
echo "Starting Oauth Service"
echo "***********************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.profiles.active=$PROFILE                                   \
     -jar /usr/local/app/app.jar