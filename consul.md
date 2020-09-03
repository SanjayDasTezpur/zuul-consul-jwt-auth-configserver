# Consul production setup guide line
 - [Consul Deployment guideline by Digital Ocean](https://www.digitalocean.com/community/tutorials/how-to-configure-consul-in-a-production-environment-on-ubuntu-14-04)
 - [Consul Deployment Guyide line By Hashicorp](https://learn.hashicorp.com/consul/datacenter-deploy/deployment-guide)
 - [download consul](https://www.consul.io/downloads)

## To run agent locally, which should join consul server 
consul agent -data-dir=/consul/data -config-dir=/consul/config -advertise=<IP_OF_MACHINE> -retry-join=<CONSUL_SERVER:PORT> -client=0.0.0.0 -node=<HOST_NAME> -bind=<IP_OF_MACHINE> -datacenter=<DATA_CENTER> -raft-protocol=3

## To run clsuter server, which can join another consul server 
consul agent -data-dir=/consul/data -config-dir=/consul/config -server -ui -advertise=<IP_OF_MACHINE> -client=0.0.0.0 -node=<HOST_NAME> -bind=<IP_OF_MACHINE> -datacenter=<DATA_CENTER> -raft-protocol=3 -retry-join=<ANOTHER_CONSUL_SERVER:PORT>
IN DOCKER :-  sudo docker run -d --net=host -e 'CONSUL_LOCAL_CONFIG={"skip_leave_on_interrupt": true}' consul agent -server -ui -bind=0.0.0.0 -advertise=34.93.119.163
 -retry-join=34.93.119.163 -bootstrap-expect=1


## Recommended  Setup
Production should have a cluster of more than one server [internally by Gossip protocol one will be leader and another willl be follower], Each machine/vm (where  microservice is running) should have an consul agent connected to one server from cluster.
