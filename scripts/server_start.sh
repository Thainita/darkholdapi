#!/usr/bin/env bash
cd /home/ec2-user/server
sudo java -jar -Dserver.port=80 *.jar 2> /dev/null < /dev/null &